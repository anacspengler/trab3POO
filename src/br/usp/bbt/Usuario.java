package br.usp.bbt;

import java.util.Stack;
/**
 * Representa um usuário da biblioteca.
 *
 * Essa classe é responsável por toda a lógica específica a um usuário
 * em particular, por exemplo, punições e limites de emprestimos são
 * calculados aqui, já que eles variam de acordo com o tipo e situação
 * do usuário.
 */
public class Usuario implements Registro, Comparable<Usuario>
{
    private String username;
    private String nome;
    private long penalizado_ate;
    protected long livros_emprestados;
    String tipo;

    protected Usuario()
    {
        this.username = null;
        this.nome = null;
        this.penalizado_ate = -1;
        this.livros_emprestados = -1;
    }

    protected Usuario(String username, String nome,
                      long pena, long livros, String tipo)
    {
        this.username = username;
        this.nome = nome;
        this.penalizado_ate = pena;
        this.livros_emprestados = livros;
        this.tipo = tipo;
    }

    public boolean equals(Usuario u)
    {
        return username.equals(u.pegaUsername());
    }

    @Override
    public int compareTo(Usuario u)
    {
        return username.compareTo(u.pegaUsername());
    }

    @Override
    public int hashCode()
    {
        return username.hashCode();
    }

    /**
     * Aplica uma penalização nesse usuário.
     *
     * Note que para a penalização ser consistente entre mudanças de datas
     * é necessário saber a data atual para calcular a penalidade correta.
     */
    private void penaliza(long dias, long data_atual)
    {
        if(dias < 1) // Não permite penas negativas ou nulas
            return;
        else if(estaPenalizado(data_atual)) // Se ja estiver penalizado
            penalizado_ate += dias; // Apenas acumula na pena atual
        else // Se não, uma pena é calculada à partir da data atual 
            penalizado_ate = data_atual + dias;
    }

    /**
     * Determina se o usuário está penalizado no momento.
     *
     * Note que a pena só começa a ser contada à partir do dia seguinte á
     * devoulução. Ex: Se o livro é devolvido com um dia de atraso às 11h
     * do dia 5 o usuário será penalizado até o fim do dia 6, só podendo
     * realizar um novo empréstimo à partir das 0h do dia 7.
     */
    public boolean estaPenalizado(long data_atual)
    {
        return penalizado_ate >= data_atual;
    }

    /**
     * Realiza devolução de um emprestimo.
     *
     * O número de dias de atraso é retornado à carater informativo, já
     * que o próprio usuário já aplica a pena.
     *
     * @return Valores negativos indicam dias de adiantamento, positivos
     * indicam dias e atraso, e 0 significa que devolveu no limite do prazo.
     */
    public long devolveLivro(Emprestimo e, long data_atual)
    {
        if(e.devolvido())
            throw new RuntimeException("Dupla devolução!");

        // Calcula os dias de atraso fazendo um "delta"
        long dias_de_atraso = e.pegaDataDevolucao() - data_atual;

        // Se der um numero positivos de dias, será penalizado.
        penaliza(dias_de_atraso, data_atual);

        // Registra a devolução
        e.devolve(data_atual);

        return dias_de_atraso;
    }

    public Emprestimo emprestaLivro(Livro l, long data_atual)
        throws EmprestimoException
    {
        // Verifica se está penalizado
        if(estaPenalizado(data_atual))
        {
            throw new EmprestimoException("Está penalizado por " +
                            (pegaPena() - data_atual) + " dia(s).");
        }

        long data_dev;
        if(pegaTipo().equals("COMUNIDADE"))
        {
            if(pegaQtdLivros() >= 2)
                throw new EmprestimoException("Você já está no seu " +
                        "limite máximo de emprestimos.");
            if(l.pegaGenero().equals("TEXTO"))
                throw new EmprestimoException("Você não pode pegar " +
                        "livros do tipo TEXTO!");
            data_dev = data_atual + 15;
        }
        else if(pegaTipo().equals("ALUNO"))
        {
            if(pegaQtdLivros() >= 4)
                throw new EmprestimoException("Você já está no seu " +
                        "limite máximo de emprestimos.");
            data_dev = data_atual + 15;
        }
        else if(pegaTipo().equals("PROFESSOR"))
        {
            if(pegaQtdLivros() >= 6)
                throw new EmprestimoException("Você já está no seu " +
                        "limite máximo de emprestimos.");
            data_dev = data_atual + 15;
        }
        else
        {
            throw new RuntimeException("Tipo de usuário inválido!");
        }

        return new Emprestimo(pegaUsername(), l.pegaId(), data_atual,
                              data_dev);
    }

    /**
     * Gera uma pilha com os dados desse usuário.
     *
     * @see #carregaDados(), Registro
     */
    public Stack<String> pegaDados()
    {
        Stack<String> dados = new Stack<String>();
        dados.push(this.tipo);
        dados.push(this.username);
        dados.push(this.nome);
        dados.push(Long.toString(this.penalizado_ate));
        dados.push(Long.toString(this.livros_emprestados));
        return dados;
    }

    /**
     * Carrega os dados do usuário à partir de uma pilha.
     *
     * @see #pegaDados(), Registro
     */
    public void carregaDados(Stack<String> dados)
    {
        this.livros_emprestados = Long.parseLong(dados.pop());
        this.penalizado_ate = Long.parseLong(dados.pop());
        this.nome = dados.pop();
        this.username = dados.pop();
        this.tipo = dados.pop();
    }

    public String pegaNome() {return this.nome;}
    public String pegaTipo() {return this.tipo;}
    public String pegaUsername() {return this.username;}
    public long pegaPena() {return this.penalizado_ate;}
    public long pegaQtdLivros() {return this.livros_emprestados;}
}
