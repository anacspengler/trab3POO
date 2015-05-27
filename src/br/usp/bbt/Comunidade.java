package br.usp.bbt;

public class Comunidade extends Usuario
{
    public Comunidade(String username, String nome)
    {
        super(username, nome);
    }

    public boolean emprestaLivro(Livro l)
    {
        return false;
    }
}
