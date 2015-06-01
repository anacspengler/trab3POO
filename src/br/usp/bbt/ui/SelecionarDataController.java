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
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import br.usp.bbt.*;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class SelecionarDataController implements Initializable {
    @FXML
    private DatePicker dataAtual;
    
    private final bib;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Biblioteca bib = MenuPrincipalController.pegaBiblioteca();
    }    

    @FXML
    private void Enviar(ActionEvent event) throws IOException {
        Scene scn = dataAtual.getScene();
        Stage menu = (Stage) scn.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        Scene scene = new Scene(root);
        menu.setScene(scene);
        menu.show();
        
        bib.defineData(dataAtual.getValue().toEpochDay());
        //System.out.println(dataAtual.getValue());
    }
    
}
