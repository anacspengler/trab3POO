package br.usp.bbt;

import java.util.Stack;

class Livro implements Registro, Comparable<Livro>
{
    private String titulo;
    private String genero;
    private int id;

    Livro()
    {
        this.titulo = null;
        this.genero = null;
        this.id = -2;
    }

    public Livro(int id, String titulo, String genero)
    {
        this.titulo = titulo;
        this.genero = genero;
        this.id = id;
    }

    public boolean equals(Livro l)
    {
        return id == l.pegaId();
    }

    public int compareTo(Livro l)
    {
        return Integer.compare(id, l.pegaId());
    }

    public Stack<String> pegaDados()
    {
        Stack<String> dados = new Stack<String>();
        dados.push(Long.toString(id));
        dados.push(titulo);
        dados.push(genero);
        return dados;
    }

    public void carregaDados(Stack<String> dados)
    {
        genero = dados.pop();
        titulo = dados.pop();
        id = Integer.parseInt(dados.pop());
    }

    public String pegaTitulo() {return titulo;}
    public String pegaGenero() {return genero;}
    public int pegaId() {return id;}
}
