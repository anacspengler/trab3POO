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
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import br.usp.bbt.*;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class ListarLivroController implements Initializable {
 
	@FXML
    private ListView<?> listLivros;
    @FXML
    private Button sair;
    
    List livros;
    public static final ObservableList<String> showLivros = FXCollections.observableArrayList();
    private final Biblioteca bib = MenuPrincipalController.pegaBiblioteca();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
		bib.pegaLivros(livros);
		showLivros.addAll(livros);		
		listLivros.setItems(showLivros);
        // TODO
        
    }    

    @FXML
    private void sair(ActionEvent event) throws IOException {
        sair.getScene().getWindow().hide();
    }
    
}
