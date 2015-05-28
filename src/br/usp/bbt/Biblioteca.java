package br.usp.bbt;

import java.util.*;
import java.nio.file.Path;

public class Biblioteca
{
    Set<Usuario> usuarios;
    Set<Livro> livros;
    Set<Emprestimo> emprestimos;
    long dataAtual;
    CSVDataBase dados;

    /**
     * Cria uma biblioteca usando a data atual como referência.
     *
     * \param dir Diretório usado para carregar e salvar os dados.
     */
    public Biblioteca(Path dir)
    {
        this(dir, System.currentTimeMillis());
    }

    /**
     * Cria uma biblioteca usando a data especificada.
     *
     * \param dir Diretório usado para carregar e salvar os dados.
     * \param data Data da biblioteca simulada.
     */
    public Biblioteca(Path dir, long data)
    {
        dados = new CSVDataBase(dir);
        carregaDados();
    }

    /**
     * Carrega os dados dos arquivos csv.
     *
     * **AVISO:** Essa função sobrescreve quaisquer dados atualmente
     * carregados na memória.
     */
    public void carregaDados()
    {
    }

    /**
     * Salva os dados atuais nos arquivos csv.
     *
     * **AVISO:** Essa função sobrescreve os arquivos de dados.
     */
    public void salvaDados()
    {
    }

    /**
     * Adiciona um novo usuário na biblioteca.
     *
     * Se um usuário com o mesmo username já estiver cadastrado os dados
     * são atualizados.
     */
    public void cadastraUsuario(Usuario u)
    {
    }

    /**
     * Adiciona uma (ou mais) cópias do livro no acrevo.
     */
    public void cadastraLivro(Livro l, int copias)
    {
        if(copias < 1) return;
    }

    public void registraEmprestimo(Usuario u, int id)
    {
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
}
