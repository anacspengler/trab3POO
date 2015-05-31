package br.usp.bbt;

import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class Biblioteca
{
    Set<Usuario> usuarios;
    Set<Livro> livros;
    Set<Emprestimo> emprestimos;
    long dataAtual;
    String dir_dados;

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
     * Se um usuário com o mesmo username já estiver cadastrado os dados
     * são atualizados.
     */
    public void cadastraUsuario(String tipo, String username, String nome)
    {
        //TODO Avisar duplicatas, checar o tipo
        usuarios.add(new Usuario(username, nome, -1, 0, tipo));
    }

    /**
     * Adiciona uma (ou mais) cópias do livro no acrevo.
     */
    public void cadastraLivro(String titulo,String autor, String genero, int copias)
    {
        // TODO Gerar IDs unicos para os livros
        // TODO Adicionar varias cópias
        if(copias < 1) return;

        int id = (int) System.currentTimeMillis()/1000;
        livros.add(new Livro(id, titulo, autor, genero));
    }

    public void registraEmprestimo(String username, int id)
    {
        //TODO 
    }

    public void registraDevolucao()
    {
    }

    public boolean estaDisponivel(Livro l)
    {
        return false;
    }

    // "Getters e setters"
    public Set<Usuario> pegaUsuarios() {return usuarios;}
    public Set<Livro> pegaLivros() {return livros;}
    public Set<Emprestimo> pegaEmprestimos() {return emprestimos;}
    public void defineData(long data) {this.dataAtual = data;}


    private void escreveRegistros(File arquivo, Set<? extends Registro> regs)
    throws FileNotFoundException, IOException
    {
        CSVPrinter saida = new CSVPrinter(new FileWriter(arquivo),
                                          CSVFormat.RFC4180);
        for(Registro r : regs)
            saida.printRecord(r.pegaDados());

        System.out.println(arquivo);
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
