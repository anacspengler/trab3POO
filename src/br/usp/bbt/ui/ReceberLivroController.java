/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.bbt.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import br.usp.bbt.*;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class ReceberLivroController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField nomeDeUsuario;
    @FXML
    private Button sair;
    @FXML
    private ListView<?> list;
    
    private final Biblioteca bib = MenuPrincipalController.pegaBiblioteca();
    
    public static final ObservableList devolucao = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list.setItems(devolucao);
    }    

    @FXML
    private void receber(ActionEvent event) throws IOException {
                
        Scene scn = sair.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.close();
    }

    @FXML
    private void sair(ActionEvent event) throws IOException {
        sair.getScene().getWindow().hide();
    }

    @FXML
    private void adicionar(ActionEvent event) {
        devolucao.add(nomeDeUsuario.getText());
        devolucao.add(id.getText());  
    }
    
}
