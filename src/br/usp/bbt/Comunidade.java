package br.usp.bbt;

public class Comunidade extends Usuario
{
    private int livros_emprestados;

    public Comunidade(String username, String nome, long pena, int livros)
    {
        super(username, nome, pena);
        this.livros_emprestados = livros;
    }

    public Emprestimo emprestaLivro(Livro l, long data_atual)
    {
        // Impede de emprestar seja por penalização, ou por que eh um
        // livro texto, ou por causa do limite de emprestimos
        if(estaPenalizado(data_atual) || l.pegaGenero().equals("TEXTO")
                                             || livros_emprestados >= 2)
            return null;

        livros_emprestados++;
        return new Emprestimo(pegaUsername(), 0,
                data_atual, data_atual + 15 * 86400000);
    }

    public int pegaNumEmprestados() {return this.livros_emprestados;}
}
