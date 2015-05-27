package br.usp.bbt;

class Livro
{
    private String titulo;
    private String genero;
    private int id;

    public Livro(int id, String titulo, String genero)
    {
        this.titulo = titulo;
        this.genero = genero;
        this.id = id;
    }

    public String pegaTitulo() {return titulo;}
    public String pegaGenero() {return genero;}
}
