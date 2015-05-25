import java.util.*;

public class Biblioteca
{
    //lista vazia de usuarios
    List<Usuario> usuarios = new ArrayList<Usuario>();

    //lista vazia de livros
    List<Livro> livros = new ArrayList<Livro>();

    void cadastrarUsuario(String nome, String identificador)
    {
        Usuario usuario = null;

        switch(identificador)
        {
            case "aluno":
                usuario = new Aluno(nome,identificador);

            case "professor":
                usuario = new Professor(nome,identificador);

            case "comunidade":
                usuario = new Comunidade(nome,identificador);
        }

        usuarios.add(usuario);
    }

    void cadastrarLivro(String nome, String tipo)
    {
        Livro livro = null;

        switch(tipo)
        {
            case "texto":
                livro = new Texto(nome,tipo);

            case "geral":
                livro = new Geral(nome,tipo);
        }

        livros.add(livro);
    }

    void listarUsuarios()
    {
        usuarios.stream()
            .forEach(u -> System.out.println(u.getNome()));
    }

    void listarLivros()
    {
        livros.stream()
            .forEach(l -> System.out.println(l.getNome()));
    }

}