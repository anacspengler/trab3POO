package br.usp.bbt;
import java.util.*;

/**
 * Classe representando um emprestimo.
 */
class Emprestimo
{
    private int id;          // ID do livro emprestado
    private String username; // username do usuário que pegou o livro
    private long data_emp;   // Data de emprestimo
    private long data_dev;   // Prazo maximo de devolução
    private long data_rec;   // Data em que o livro foi de fato recebido

    Emprestimo(String username, int id, long data_emp, long data_dev)
    {
        this.username = username;
        this.id = id;
        this.data_emp = data_emp;
        this.data_dev = data_dev;
        this.data_rec = -1;
    }

    // Registra a data em que o livro for de fato recebido de volta
    public void devolve(long data) {this.data_rec = data;}

    // Checa se ja foi devolvido
    public boolean devolvido() {return data_rec >= 0;}

    // Getters
    public long pegaDataEmprestado() {return data_emp;}
    public long pegaDataDevolucao() {return data_dev;}
    public long pegaDataRecebido() {return data_rec;}
    public String pegaUsuario() {return username;}
    public int pegaIdLivro() {return id;}
}
