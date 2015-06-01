package br.usp.bbt;
import java.util.*;

/**
 * Classe representando um emprestimo.
 */
class Emprestimo implements Registro
{
    private int id;          // ID do livro emprestado
    private String username; // username do usuário que pegou o livro
    private long data_emp;   // Data de emprestimo
    private long data_dev;   // Prazo maximo de devolução
    private long data_rec;   // Data em que o livro foi de fato recebido

    Emprestimo()
    {
        this.username = null;
        this.id = -1;
        this.data_emp = -1;
        this.data_dev = -1;
        this.data_rec = -1;
    }

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

    public Stack<String> pegaDados()
    {
        Stack<String> dados = new Stack<String>();
        dados.push(username);
        dados.push(Integer.toString(id));
        dados.push(Long.toString(data_emp));
        dados.push(Long.toString(data_dev));
        dados.push(Long.toString(data_rec));
        return dados;
    }

    public void carregaDados(Stack<String> dados)
    {
        data_rec = Long.parseLong(dados.pop());
        data_dev = Long.parseLong(dados.pop());
        data_emp = Long.parseLong(dados.pop());
        id = Integer.parseInt(dados.pop());
        username = dados.pop();
    }

    // Getters
    public long pegaDataEmprestado() {return data_emp;}
    public long pegaDataDevolucao() {return data_dev;}
    public long pegaDataRecebido() {return data_rec;}
    public String pegaUsername() {return username;}
    public int pegaIdLivro() {return id;}
}
