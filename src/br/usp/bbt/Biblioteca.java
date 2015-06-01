package br.usp.bbt;

import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca
{
    private Map<String, Usuario> usuarios;
    private Map<Integer, Livro> livros;
    private Set<Emprestimo> emprestimos;
    private long data_atual;
    private String dir_dados;
    private int prox_id;

    /**
     * Cria uma biblioteca usando a data atual como referência.
     *
     * @param dir Diretório usado para carregar e salvar os dados.
     */
    public Biblioteca(String dir)
    {
        this(dir, -1);
    }

    /**
     * Cria uma biblioteca usando a data especificada.
     *
     * @param dir Diretório usado para carregar e salvar os dados.
     * @param data Data da biblioteca simulada.
     */
    public Biblioteca(String dir, long data)
    {
        this.dir_dados = dir;
        this.livros = new TreeMap<Integer, Livro>();
        this.usuarios = new LinkedHashMap<String, Usuario>();
        this.emprestimos = new TreeSet<Emprestimo>();

        // Tenta carregar dados já existentes
        try {carregaDados();}
        catch (FileNotFoundException e) {}
        catch (Exception e) {e.printStackTrace();}

    }

    /**
     * Carrega os dados dos arquivos csv.
     *
     * **AVISO:** Essa função sobrescreve quaisquer dados atualmente
     * carregados na memória.
     */
    public void carregaDados() throws FileNotFoundException, IOException
    {
        // Apaga todas as entradas na memoria
        usuarios.clear();
        livros.clear();
        emprestimos.clear();

        // Cria o parser para os usuários
        CSVParser parser = CSVFormat.RFC4180.parse(new FileReader(
                           new File(dir_dados, "usuarios.csv")));

        // Passa por todos os registros e vai criando os usuários
        for(CSVRecord r : parser)
        {
            Usuario novo = new Usuario();
            novo.carregaDados(empilhaCSVRecord(r));
            usuarios.put(novo.pegaUsername(), novo);
        }

        // Cria um parser para os empréstimos
        parser = CSVFormat.RFC4180.parse(new FileReader(new File(
                                         dir_dados, "emprestimos.csv")));

        // Passa por todos os registros e vai criando os empréstimos
        for(CSVRecord r : parser)
        {
            Emprestimo novo = new Emprestimo();
            novo.carregaDados(empilhaCSVRecord(r));
            emprestimos.add(novo);
        }

        // Cria um parser para os livros
        parser = CSVFormat.RFC4180.parse(new FileReader(new File(
                                         dir_dados, "livros.csv")));
        
        // Passa por todos os registros e vai criando os livros
        for(CSVRecord r : parser)
        {
            Livro novo = new Livro();
            novo.carregaDados(empilhaCSVRecord(r));
            livros.put(novo.pegaId(), novo);
        }

        // Gera prox_id olhando qual o maior id ate agora
        prox_id = livros.values().stream()
            .mapToInt(Livro::pegaId)
            .max()
            .orElse(0) + 1;
    }

    /**
     * Salva os dados atuais nos arquivos csv.
     *
     * **AVISO:** Essa função sobrescreve os arquivos de dados.
     */
    public void salvaDados() throws FileNotFoundException, IOException
    {
        escreveRegistros(new File(dir_dados, "usuarios.csv"),
                usuarios.values());
        escreveRegistros(new File(dir_dados, "livros.csv"),
                livros.values());
        escreveRegistros(new File(dir_dados, "emprestimos.csv"),
                emprestimos);
    }

    /**
     * Adiciona um novo usuário na biblioteca.
     *
     * Se um usuário com o mesmo username já estiver cadastrado
     * nada acontece.
     */
    public void cadastraUsuario(String tipo, String username, String nome)
    {
        // Se já existe a pena e qtd. de livros não pode mudar...
        Usuario u = usuarios.get(username);
        if(u != null)
            usuarios.put(username, new Usuario(username, nome,
                        u.pegaPena(), u.pegaQtdLivros(), tipo));
        else
            usuarios.put(username, new Usuario(username, nome,
                         -1, 0, tipo));
    }

    /**
     * Adiciona uma (ou mais) cópias do livro no acrevo.
     */
    public void cadastraLivro(String titulo, String autor,
                              String genero, int copias)
    {
        for(int i = 0; i < copias; ++i, ++prox_id)
            livros.put(prox_id, new Livro(prox_id, titulo, autor, genero));
    }

    /**
     * Tenta registrar um emprestimo em nome do usuário dado.
     *
     * O trabalho de verificar se o empréstimo é válido é dividido entre
     * a biblioteca e o usuário: enquanto a biblioteca verifica a 
     * disponibilidade do livro, o usuário se responsabiliza por verificar
     * limites de emprestimos, tipos de livros e penalidades.
     *
     * Se por algum motivo não for possível fazer o empréstimo, uma excessão
     * é lançada descrevendo o motivo.
     */
    public void registraEmprestimo(String username, int id) 
        throws EmprestimoException
    {
        // Verifica se o livro esta disponivel
        if(!estaDisponivel(id))
            throw new RuntimeException("Livro indisponivel");

        // Verifica se o usuario pode pegar o livro
        Usuario u = usuarios.get(username);
        Livro l = livros.get(id);

        if(u == null || l == null)
            throw new RuntimeException("Dados inconsistentes detectados");

        Emprestimo e = u.emprestaLivro(l, data_atual);
    
    }

    /**
     * Registra a devolução de um livro na data atual.
     *
     * Tenta fazer a devolução de um livro. Se o livro não existir ou
     * não estiver emprestado nada acontece.
     *
     * **Nota:** Em caso de atraso o proprio usuário calcula e aplica a
     * penalidade após a devolução.
     *
     * @param id ID do livro sendo devolvido.
     *
     * @return true se achou e devolveu com sucesso.
     */
    public boolean registraDevolucao(int id)
    {
        // Procura um emprestimo em aberto com o id especificado
        List<Emprestimo> achado = emprestimos.stream()
            .filter(e -> e.pegaIdLivro() == id)
            .limit(2)
            .collect(Collectors.toList());

        // Testa possíveis inconsistências nos dados caso o mesmo livro
        // esteja emprestado por mais de uma pessoa ao mesmo temp
        if(achado.size() > 1)
            throw new RuntimeException("Dados inconsistentes detectados!");

        // Se o livro não existe ou ja foi devolvido
        if(achado.isEmpty())
            return false; // Não faz nada

        // Procura o usuário responsável pela devoulção
        Usuario u = usuarios.get(achado.get(0).pegaUsername());
        if(u == null)
            throw new RuntimeException("Dados inconsistentes detectados!");

        // Registra a devolução
        u.devolveLivro(achado.get(0), pegaData());

        return true;
    }

    /**
     * Verifica se um livro está disponível para ser emprestado.
     *
     * **Aviso:** Além de não estar emprestado no momento, para que um livro
     * esteja disponível também não pode haver nenhum registro de empréstimo
     * do mesmo livro numa data futura. **Ou seja**, para que o livro esteja
     * disponível todas as datas de devolução deve ser anteriores à data
     * atual e não pode haver um empréstimo em aberto.
     */
    public boolean estaDisponivel(int id)
    {
        return false;
    }

    /**
     * Define a data usada pela biblioteca.
     *
     * Se a data for menor que zero, o relogio do sistema é usado.
     * Caso contrário a data é congelada no dia especificado.
     */
    public void defineData(long data) {this.data_atual = data;}

    /**
     * Retorna a data atual da biblioteca.
     */
    public long pegaData()
    {
        return data_atual < 0 ? System.currentTimeMillis()/86400000 :
                                data_atual;
    }

    /**
     * Escreve uma sequencia de registros no arquivo dado.
     */
    private void
    escreveRegistros(File arquivo, Iterable<? extends Registro> regs)
    throws FileNotFoundException, IOException
    {
        // Abre o arquivo e cria um "printer" para manipular a saída
        CSVPrinter saida = new CSVPrinter(new FileWriter(arquivo),
                                          CSVFormat.RFC4180);
        // Escreve cada registro no arquivo
        for(Registro r : regs)
            saida.printRecord(r.pegaDados());

        saida.close();
    }

/*
    private void carregaRegistros(File arquivo, Set<Registro> regs,
            Class<? extends Registro> tipo)
    {
        // Cria um parser para ler o arquivo .csv
        CSVParser parser = CSVFormat.RFC4180.parse(new FileReader(arquivo));

        // Esvazia os registros atuais
        regs.clear();

        // Percorre cada registro no arquivo
        for(CSVRecord r : parser)
        {
            Registro novo = tipo.newInstance();
            novo.carregaDados(empilhaCSVRecord(r));
            regs.add(novo);
        }
    }
*/
    private Stack<String> empilhaCSVRecord(CSVRecord r)
    {
        Stack<String> empilhado = new Stack<String>();
        for(String dado : r)
            empilhado.add(dado);
        return empilhado;
    }

    public static void main(String args[]) throws Exception
    {
        Biblioteca b = new Biblioteca(args[0]);
        
        b.cadastraLivro("Guia do mochileiro", "Asimov, Isaac", "SciFi", 3);
        b.cadastraLivro("Design Patterns: " + 
                        "Elements of Reusable Object-Oriented Software",
                        "GoF", "TEXTO", 1);
        b.cadastraLivro("Moby-Dick", "Melville, Herman ", "TEXTO", 1);

        b.cadastraUsuario("ALUNO", "bardes", "Paulo Bardes");
        b.cadastraUsuario("PROFESSOR", "adenilso", "Adenilso Simão");

        b.salvaDados();
    }
}
