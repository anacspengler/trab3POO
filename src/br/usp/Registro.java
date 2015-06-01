package br.usp.bbt;

import org.apache.commons.csv.*;
import java.util.*;
import java.io.*;

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

    /**
     * Escreve uma sequencia de registros num arquivo.
     */
    static void
    escreveRegistros(File arquivo, Iterable<? extends Registro> regs)
    throws FileNotFoundException, IOException
    {
        // Abre o arquivo e cria um "printer" para manipular a saída
        CSVPrinter saida = new CSVPrinter(new FileWriter(arquivo),
                                          CSVFormat.RFC4180);
        // Escreve cada registro no arquivo
        for(Registro r : regs)
            saida.printRecord(r.pegaDados());

        saida.close();
    }

/*
    private void carregaRegistros(File arquivo, Set<Registro> regs,
            Class<? extends Registro> tipo)
    {
        // Cria um parser para ler o arquivo .csv
        CSVParser parser = CSVFormat.RFC4180.parse(new FileReader(arquivo));

        // Esvazia os registros atuais
        regs.clear();

        // Percorre cada registro no arquivo
        for(CSVRecord r : parser)
        {
            Registro novo = tipo.newInstance();
            novo.carregaDados(empilhaCSVRecord(r));
            regs.add(novo);
        }
    }
*/
}
