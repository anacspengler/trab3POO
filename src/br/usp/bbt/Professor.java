package br.usp.bbt;

public class Professor extends Usuario
{
    public Professor(String username, String nome)
    {
        super(username, nome);
    }

    public boolean emprestaLivro(Livro l)
    {
        return false;
    }
}
