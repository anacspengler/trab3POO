package br.usp.bbt;

import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class Biblioteca
{
    private Set<Usuario> usuarios;
    private Set<Livro> livros;
    private Set<Emprestimo> emprestimos;
    private long dataAtual;
    private String dir_dados;
    private int prox_id;

    /**
     * Cria uma biblioteca usando a data atual como referência.
     *
     * \param dir Diretório usado para carregar e salvar os dados.
     */
    public Biblioteca(String dir)
    {
        this(dir, System.currentTimeMillis());
    }

    /**
     * Cria uma biblioteca usando a data especificada.
     *
     * \param dir Diretório usado para carregar e salvar os dados.
     * \param data Data da biblioteca simulada.
     */
    public Biblioteca(String dir, long data)
    {
        this.dir_dados = dir;
        this.livros = new HashSet<Livro>();
        this.usuarios = new TreeSet<Usuario>();
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
            usuarios.add(novo);
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
            livros.add(novo);
        }

        // Gera prox_id olhando qual o maior id ate agora
        prox_id = livros.stream()
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
        escreveRegistros(new File(dir_dados, "/usuarios.csv"), usuarios);
        escreveRegistros(new File(dir_dados, "/livros.csv"), livros);
        escreveRegistros(new File(dir_dados, "emprestimos.csv"), emprestimos);
    }

    /**
     * Adiciona um novo usuário na biblioteca.
     *
     * Se um usuário com o mesmo username já estiver cadastrado nada acontece.
     */
    public void cadastraUsuario(String tipo, String username, String nome)
    {
        usuarios.add(new Usuario(username, nome, -1, 0, tipo));
    }

    /**
     * Adiciona uma (ou mais) cópias do livro no acrevo.
     */
    public void cadastraLivro(String titulo, String autor,
                              String genero, int copias)
    {
        for(int i = 0; i < copias; ++i)
            livros.add(new Livro(prox_id++, titulo, genero));
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
    public registraEmprestimo(String username, int id)
    {
        //TODO 
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
     * @param id ID do livro sendo devolvido
     */
    public void registraDevolucao(int id)
    {

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

    // "Getters e setters"
    public Set<Usuario> pegaUsuarios() {return usuarios;}
    public Set<Livro> pegaLivros() {return livros;}
    public Set<Emprestimo> pegaEmprestimos() {return emprestimos;}
    public void defineData(long data) {this.dataAtual = data;}


    /**
     * Escreve uma sequencia de registros no arquivo dado.
     */
    private void escreveRegistros(File arquivo, Set<? extends Registro> regs)
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
        b.cadastraLivro("Guia do mochileiro...", "alguem q eu desconheco", "SciFi", 1);
        b.cadastraUsuario("ALUNO", "bardes", "Paulo Bardes");
        b.salvaDados();
    }
}
