package br.usp.bbt;

public class Aluno extends Usuario
{
    private int livros_emprestados;

    public Aluno(String username, String nome, long pena, int livros)
    {
        super(username, nome, pena);
        this.livros_emprestados = livros;
    }

    public Emprestimo emprestaLivro(Livro l, long data_atual)
    {
        if(!estaPenalizado(data_atual) && livros_emprestados < 4)
        {
            livros_emprestados++;
            return new Emprestimo(pegaUsername(), 0,
                       data_atual, data_atual + 15 * 86400000);
        }
        
        throw new EmprestimoException("Impedido de pegar livro");
    }

    public int pegaNumEmprestados() {return this.livros_emprestados;}
}

