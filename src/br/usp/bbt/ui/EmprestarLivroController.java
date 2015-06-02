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
import br.usp.bbt.*;

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
    private Button sair;
    @FXML
    private ListView<?> list;
    
    private final Biblioteca bib = MenuPrincipalController.pegaBiblioteca();
    
    public static final ObservableList emprestimo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emprestimo.clear();
        bib.pegaLivros(emprestimo);
        list.setItems(emprestimo);
    }    

    @FXML
    private void emprestar(ActionEvent event) throws IOException {
        Scene scn = sair.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.close();
    }

    @FXML
    private void sair(ActionEvent event) throws IOException {
        sair.getScene().getWindow().hide();
    }

    @FXML
    private void adicionar(ActionEvent event){
        try{
            bib.registraEmprestimo(nomeDeUsuario.getText(), Integer.parseInt(id.getText()));
        }
        catch(EmprestimoException e) {
            Scene scn = sair.getScene();
            Stage menu = (Stage) scn.getWindow();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("FALHA");

            try{
                Parent root = FXMLLoader.load(getClass().getResource("/res/ui/EmprestimoException.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            catch(IOException io)
            {
                System.out.println("erro ao abrir EmprestimoException.fxml");
            }
        }

        sair.getScene().getWindow().hide();
    }
    
}
