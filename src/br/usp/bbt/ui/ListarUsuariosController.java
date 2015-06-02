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
import javafx.stage.Stage;
import br.usp.bbt.*;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class ListarUsuariosController implements Initializable {
    @FXML
    private Button sair;
    @FXML
    private ListView<?> listUsuarios;
    
    List usuarios;
    public static final ObservableList<String> showUsuarios = FXCollections.observableArrayList();
    private final Biblioteca bib = MenuPrincipalController.pegaBiblioteca();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
		bib.pegaUsuarios(usuarios);
		showUsuarios.addAll(usuarios);		
		listUsuarios.setItems(showUsuarios);
        // TODO
    }    

    @FXML
    private void Sair(ActionEvent event) throws IOException {
        sair.getScene().getWindow().hide();
    }
    
}
