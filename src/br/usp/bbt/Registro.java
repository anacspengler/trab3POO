package br.usp.bbt;

import java.util.*;

/**
 * Representa um registro que pode ser lido/escrito num arquivo.
 *
 * **Aviso:** Cuidado com a ordem dos dados na pilha. Para carregar o
 * Registro de volta na memória os dados serão desempilhados em ordem
 * reversa à que foram adicionados.
 */
public interface Registro
{
    /**
     * Gera uma pilha com os dados de um registro.
     *
     * Essa pilha deve conter todos os dados necessários para
     * criar e recuperar um registro válido.
     *
     * @see #carregaDados()
     */
    Stack<String> pegaDados();

    /**
     * Carrega os dados de um registro.
     *
     * Essa função sobrescreve os dados atuais do registro com os dados
     * encontrados na pilha. A pilha deve ser organizada da mesma forma
     * que a pilha gerada pela função #pegaDados().
     *
     * @see #pegaDados()
     */
    void carregaDados(Stack<String> dados);
}
