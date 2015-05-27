package br.usp.bbt;
import java.util.*;

/**
 * Classe representando um emprestimo.
 */
class Emprestimo
{
    private int id;          // ID do livro emprestado
    private String username; // username do usuário que pegou o livro
    private Date data_emp;   // Data de emprestimo
    private Date data_dev;   // Prazo maximo de devolução
    private Date data_rec;   // Data em que o livro foi de fato recebido

    Emprestimo(String username, int id, Date data_emp, Date data_dev)
    {
        this.username = username;
        this.id = id;
        this.data_emp = data_emp;
        this.data_dev = data_dev;
        this.data_rec = null;
    }

    public void devolve(Date d)
    {
        if(devolvido()) return;

        this.data_rec = d;
    }

    public boolean devolvido() {return data_rec == null;}
    public Date pegaDataEmprestado() {return data_emp;}
    public Date pegaDataDevolucao() {return data_dev;}
    public Date pegaDataRecebido() {return data_rec;}
    public String pegaUsuario() {return username;}
    public int pegaIdLivro() {return id;}
}
