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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class MenuPrincipalController implements Initializable  {
    @FXML
    private AnchorPane window;
    @FXML
    private Button sair;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cadastrarUsuario(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.hide();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Cadastro de Usuarios");
        Parent root = FXMLLoader.load(getClass().getResource("CadastrarUsuario.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cadastrarLivro(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.hide();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Cadastro de Livros");
        Parent root = FXMLLoader.load(getClass().getResource("CadastrarLivro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void listarUsuarios(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.hide();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Lista de Usuarios");
        Parent root = FXMLLoader.load(getClass().getResource("ListarUsuarios.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void listarLivros(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.hide();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Lista de Livros");
        Parent root = FXMLLoader.load(getClass().getResource("ListarLivro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void emprestimos(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.hide();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Empréstimo de Livros");
        Parent root = FXMLLoader.load(getClass().getResource("EmprestarLivro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void devolucoes(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.hide();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Devolução de Livros");
        Parent root = FXMLLoader.load(getClass().getResource("ReceberLivro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sair(ActionEvent event) {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        menu.close();
    }


}
