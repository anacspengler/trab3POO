/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.bbt.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import br.usp.bbt.*;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class AvisoController implements Initializable {
    @FXML
    private Button sim;
    @FXML
    private Button nao;

    private final Biblioteca bib = MenuPrincipalController.pegaBiblioteca();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fechar(ActionEvent event) {
        try {
            bib.salvaDados();
        } catch (Exception e) {
            System.err.println("Não conseguiu salvar ao sair!");
            System.err.println(e);
        }
        Platform.exit();
    }

    @FXML
    private void naofechar(ActionEvent event) {
        Stage stage;
        stage = (Stage) nao.getScene().getWindow();
        stage.close();
    }
    
}
