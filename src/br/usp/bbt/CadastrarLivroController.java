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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class CadastrarLivroController implements Initializable {
    @FXML
    private TextField titulo;
    @FXML
    private TextField genero;
    @FXML
    private ComboBox<?> tipoLivro;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void escolheTipoLivro(ActionEvent event) {
        System.out.println(tipoLivro.getValue());
    }

    @FXML
    private void salvar(ActionEvent event) {
        System.out.println(titulo.getCharacters());
        System.out.println(genero.getCharacters());
        System.out.println(id.getCharacters());
        System.out.println(tipoLivro.getValue());
    }
}
