/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.bbt.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void escolheTipoUsuario(ActionEvent event) {
        System.out.println(tipoUsuario.getValue());
    }

    @FXML
    private void salvar(ActionEvent event) {
        System.out.println(nome.getCharacters());
        System.out.println(nomeDeUsuario.getCharacters());
        System.out.println(tipoUsuario.getValue());
    }
    
}
