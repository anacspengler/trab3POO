package br.usp.bbt;

import java.util.Stack;

class Livro implements Registro, Comparable<Livro>
{
    private String titulo;
    private String genero;
    private String autor;
    private int id;

    Livro()
    {
        this.titulo = null;
        this.genero = null;
        this.autor = null;
        this.id = -2;
    }

    public Livro(int id, String titulo, String autor, String genero)
    {
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.id = id;
    }

    public String toString()
    {
        return "[" + id +"] \"" + titulo +
            "\", " + autor + " - " + genero; 
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
        dados.push(autor);
        dados.push(titulo);
        dados.push(genero);
        return dados;
    }

    public void carregaDados(Stack<String> dados)
    {
        genero = dados.pop();
        titulo = dados.pop();
        autor = dados.pop();
        id = Integer.parseInt(dados.pop());
    }

    public String pegaTitulo() {return titulo;}
    public String pegaGenero() {return genero;}
    public String pegaAutor() {return autor;}
    public int pegaId() {return id;}
}
