package br.usp.bbt;

public abstract class Usuario
{
    private String username;
    private String nome;
    private long penalizado_ate;

    protected Usuario(String username, String nome, long pena)
    {
        this.username = username;
        this.nome = nome;
        this.penalizado_ate = pena;
    }

    private void penaliza(long dias, long data_atual)
    {
        if(dias < 1)
            return;
        else if(penalizado_ate >= data_atual)
            penalizado_ate += dias * 86400000;
        else
            penalizado_ate = data_atual + dias * 86400000;
    }

    public boolean estaPenalizado(long data_atual)
    {
        return penalizado_ate >= data_atual;
    }

    public long devolveLivro(Emprestimo e, long data_atual)
    {
        if(e.devolvido())
            throw new RuntimeException("Dupla devolução!");

        // Calcula os dias de atraso fazendo um "delta"
        long dias_de_atraso = (e.pegaDataDevolucao()/86400000) -
                              (data_atual/86400000);

        //Se der um numero positivos de dias, será penalizado.
        penaliza(dias_de_atraso, data_atual);

        // Registra a devolução
        e.devolve(data_atual);

        return dias_de_atraso;
    }

    public abstract Emprestimo emprestaLivro(Livro l, long data_atual);

    public String pegaNome() {return this.nome;}
    public String pegaUsername() {return this.username;}
    public long pegaPena() {return this.penalizado_ate;}
    public abstract int pegaNumEmprestados();
}
