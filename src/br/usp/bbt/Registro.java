package br.usp.bbt;

import java.util.*;

public interface Registro
{
    Stack<String> pegaDados();
    void carregaDados(Stack<String> dados);
}
