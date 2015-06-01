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
public class CadastrarUsuarioController implements Initializable {
    @FXML
    private ComboBox<?> tipoUsuario;
    @FXML
    private TextField nome;
    @FXML
    private TextField nomeDeUsuario;
    @FXML
    private Button sair;
    
    private final Biblioteca bib = MenuPrincipalController.pegaBiblioteca();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void escolheTipoUsuario(ActionEvent event) {
        
    }

    @FXML
    private void salvar(ActionEvent event) throws IOException {       
        
        bib.cadastraUsuario();
        System.out.println(nome.getCharacters());
        System.out.println(nomeDeUsuario.getCharacters());
        System.out.println(tipoUsuario.getValue());       
        
        Scene scn = sair.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.close();

    }

    @FXML
    private void sair(ActionEvent event) throws IOException {
        sair.getScene().getWindow().hide();
    }
    
}
