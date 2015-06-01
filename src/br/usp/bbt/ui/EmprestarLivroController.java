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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class EmprestarLivroController implements Initializable {
    @FXML
    private TextField nomeDeUsuario;
    @FXML
    private TextField id;
    @FXML
    private DatePicker dataRetorno;
    @FXML
    private Button sair;
    @FXML
    private ListView<?> list;
    
    public static final ObservableList emprestimo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list.setItems(emprestimo);
    }    

    @FXML
    private void emprestar(ActionEvent event) throws IOException {
        System.out.println(nomeDeUsuario.getCharacters());
        System.out.println(dataRetorno.getValue());
        System.out.println(id.getCharacters());
        
        Scene scn = sair.getScene();
        Stage menu = (Stage) scn.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        Scene scene = new Scene(root);
        menu.setScene(scene);
        menu.show();
    }

    @FXML
    private void sair(ActionEvent event) throws IOException {
        Scene scn = sair.getScene();
        Stage menu = (Stage) scn.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        Scene scene = new Scene(root);
        menu.setScene(scene);
        menu.show();
    }

    @FXML
    private void adicionar(ActionEvent event) {
      
        emprestimo.add(dataRetorno.getValue());
        emprestimo.add(nomeDeUsuario.getText());
        emprestimo.add(id.getText());  
    }
    
}
