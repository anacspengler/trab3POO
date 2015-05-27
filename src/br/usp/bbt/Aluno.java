package br.usp.bbt;

public class Aluno extends Usuario
{
    public Aluno(String username, String nome)
    {
        super(username, nome);
    }

    public boolean emprestaLivro(Livro l)
    {
        return false;
    }
}

