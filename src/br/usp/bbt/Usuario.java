package br.usp.bbt;

import java.util.Stack;

public class Usuario implements Registro, Comparable<Usuario>
{
    private String username;
    private String nome;
    private long penalizado_ate;
    protected long livros_emprestados;
    String tipo;

    protected Usuario()
    {
        this.username = null;
        this.nome = null;
        this.penalizado_ate = -2;
        this.livros_emprestados = -2;
    }

    protected Usuario(String username, String nome,
                      long pena, long livros, String tipo)
    {
        this.username = username;
        this.nome = nome;
        this.penalizado_ate = pena;
        this.livros_emprestados = livros;
        this.tipo = tipo;
    }

    public boolean equals(Usuario u)
    {
        return username.equals(u.pegaUsername());
    }

    public int compareTo(Usuario u)
    {
        return username.compareTo(u.pegaUsername());
    }

    public int hashCode()
    {
        return username.hashCode();
    }

    private void penaliza(long dias, long data_atual)
    {
       if(dias < 1)
            return;
        else if(penalizado_ate >= data_atual)
            penalizado_ate += dias * 86400000;
        else
            penalizado_ate = data_atual + dias * 86400000;
    }

    public boolean estaPenalizado(long data_atual)
    {
        return penalizado_ate >= data_atual;
    }

    public long devolveLivro(Emprestimo e, long data_atual)
    {
        if(e.devolvido())
            throw new RuntimeException("Dupla devolução!");

        // Calcula os dias de atraso fazendo um "delta"
        long dias_de_atraso = (e.pegaDataDevolucao()/86400000) -
                              (data_atual/86400000);

        // Se der um numero positivos de dias, será penalizado.
        penaliza(dias_de_atraso, data_atual);

        // Registra a devolução
        e.devolve(data_atual);

        return dias_de_atraso;
    }

    public Emprestimo emprestaLivro(Livro l, long data_atual)
    {
        //TODO
        return null;
    }

    public Stack<String> pegaDados()
    {
        Stack<String> dados = new Stack<String>();
        dados.push(this.tipo);
        dados.push(this.username);
        dados.push(this.nome);
        dados.push(Long.toString(this.penalizado_ate));
        dados.push(Long.toString(this.livros_emprestados));
        return dados;
    }

    public void carregaDados(Stack<String> dados)
    {
        this.livros_emprestados = Long.parseLong(dados.pop());
        this.penalizado_ate = Long.parseLong(dados.pop());
        this.nome = dados.pop();
        this.username = dados.pop();
        this.tipo = dados.pop();
    }

    public String pegaNome() {return this.nome;}
    public String pegaUsername() {return this.username;}
    public long pegaPena() {return this.penalizado_ate;}
    public long pegaQtdLivros() {return this.livros_emprestados;}
}
