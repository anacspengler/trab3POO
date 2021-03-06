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
import br.usp.bbt.*;
import javafx.application.Platform;

/**
 * FXML Controller class
 *
 * @author sushi
 */
public class MenuPrincipalController implements Initializable  {
    /**
     * Singleton da biblioteca.
     *
     * Usado por todos os outros controladores para acessar os dados e
     * lidar com a biblioteca em gera.
     */
    private static final Biblioteca bib = new Biblioteca(".");


    static Biblioteca pegaBiblioteca() {return bib;}

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
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Cadastro de Usuarios");
        Parent root = FXMLLoader.load(getClass().getResource("/res/ui/CadastrarUsuario.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cadastrarLivro(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Cadastro de Livros");
        Parent root = FXMLLoader.load(getClass().getResource("/res/ui/CadastrarLivro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void listarUsuarios(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Lista de Usuarios");
        Parent root = FXMLLoader.load(getClass().getResource("/res/ui/ListarUsuarios.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void listarLivros(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Lista de Livros");
        Parent root = FXMLLoader.load(getClass().getResource("/res/ui/ListarLivro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void emprestimos(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Empréstimo de Livros");
        Parent root = FXMLLoader.load(getClass().getResource("/res/ui/EmprestarLivro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void devolucoes(ActionEvent event) throws IOException {
        Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Devolução de Livros");
        Parent root = FXMLLoader.load(getClass().getResource("/res/ui/ReceberLivro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void selecionaData(ActionEvent event) throws IOException{
		Scene scn = window.getScene();
        Stage menu = (Stage) scn.getWindow();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Terminar");
        Parent root = FXMLLoader.load(getClass().getResource("/res/ui/SelecionarData.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sair(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Terminar");
        Parent root = FXMLLoader.load(getClass().getResource("/res/ui/Aviso.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }


}
