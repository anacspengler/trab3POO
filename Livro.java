abstract class Livro
{
    private String nome;
    private String tipo;

    Livro(String nome, String tipo)
    {
        this.nome = nome;
        this.tipo = tipo;
    }

    String getNome()
    {
        return this.nome;
    }
}