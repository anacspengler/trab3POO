package br.usp.bbt;

public abstract class Usuario
{
    private String username;
    private String nome;

    protected Usuario(String username, String nome)
    {
        this.username = username;
        this.nome = nome;
    }

    public abstract boolean emprestaLivro(Livro l);

    public String getNome() {return this.nome;}
    public String getUsername() {return this.username;}
}
