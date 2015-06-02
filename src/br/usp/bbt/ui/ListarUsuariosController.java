/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.bbt.ui;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.collections.*;
import br.usp.bbt.*;
import javafx.scene.control.ListView;
import java.util.*;

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
    public static final ObservableList<Usuario> showUsuarios = FXCollections.observableArrayList();
    private final Biblioteca bib = MenuPrincipalController.pegaBiblioteca();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUsuarios.clear();
		bib.pegaUsuarios(showUsuarios);
    }    

    @FXML
    private void Sair(ActionEvent event) throws IOException {
        sair.getScene().getWindow().hide();
    }
    
}
