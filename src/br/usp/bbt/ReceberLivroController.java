/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void receber(ActionEvent event) {
        System.out.println(nomeDeUsuario.getCharacters());
        System.out.println(id.getCharacters());
    }
    
}