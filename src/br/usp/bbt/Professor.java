package br.usp.bbt;

public class Professor extends Usuario
{
    private int livros_emprestados;

    public Professor(String username, String nome, long pena, int livros)
    {
        super(username, nome, pena);
        this.livros_emprestados = livros;
    }

    public Emprestimo emprestaLivro(Livro l, long data_atual)
    {
        if(!estaPenalizado(data_atual) && livros_emprestados < 6)
        {
            livros_emprestados++;
            return new Emprestimo(pegaUsername(), 0,
                       data_atual, data_atual + 60 * 86400000);
        }
        
        return null;
    }

    public int pegaNumEmprestados() {return this.livros_emprestados;}
}
