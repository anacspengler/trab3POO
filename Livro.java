abstract class Livro
{
    private String nome;
    private String genero;

    Livro(String nome, String genero)
    {
        this.nome = nome;
        this.genero = genero;
    }

    String getNome()
    {
        return this.nome;
    }
}