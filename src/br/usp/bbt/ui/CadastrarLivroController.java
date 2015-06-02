/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.bbt.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import br.usp.bbt.*;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class CadastrarLivroController implements Initializable {
   @FXML
    private Button sair;

    @FXML
    private TextField genero;

    @FXML
    private TextField nCopias;

    @FXML
    private TextField titulo;

    @FXML
    private TextField autor;

    private final Biblioteca bib = MenuPrincipalController.pegaBiblioteca();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void salvar(ActionEvent event) throws IOException {
		
		bib.cadastraLivro(titulo.getText(), autor.getText(), genero.getText(), Integer.parseInt(nCopias.getText()));
               
        Scene scn = sair.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.close();
    }

    @FXML
    private void sair(ActionEvent event) throws IOException {
        Scene scn = sair.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.close();
    }
}
