package com.example.iniciosesion.controller;

import com.example.iniciosesion.model.CifrarPasswd;
import com.example.iniciosesion.model.Propiedades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class logInController implements Initializable {

    @FXML
    private TextField tf_usuario;
    @FXML
    private PasswordField pf_contra;
    @FXML
    private Button bt_inicio;
    @FXML
    private Button bt_registrar;


    private static HashMap<String, String> listaUsuarios = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaUsuarios = Propiedades.cargarConfig();
    }

    public void iniciarSesion(ActionEvent actionEvent){

        if (tf_usuario.getText().isBlank()){
            generarAlerta(Alert.AlertType.ERROR,"Error","El usuario no puede estar vacío");

        }else if (tf_usuario.getText().isBlank()){
            generarAlerta(Alert.AlertType.ERROR,"Error","La comtraseña no puede estar vacía");

        }else {

            if (!listaUsuarios.containsKey(tf_usuario.getText())){
                generarAlerta(Alert.AlertType.WARNING,"Ojo","El usuario \'"+tf_usuario.getText()+"\' no existe");

            }else if (comprobaInicio(tf_usuario.getText(), pf_contra.getText())){
                generarAlerta(Alert.AlertType.CONFIRMATION,"Inicio exitoso","");
                limpiar();
            }else {
                generarAlerta(Alert.AlertType.WARNING,"Te has equivocado :c","La comtraseña es incorrecta");
            }
        }
    }

    public void registrarse(ActionEvent actionEvent){


        if (tf_usuario.getText().isBlank()){
            generarAlerta(Alert.AlertType.ERROR,"Error","El usuario no puede estar vacío");
        }else if (tf_usuario.getText().isBlank()){
            generarAlerta(Alert.AlertType.ERROR,"Error","La comtraseña no puede estar vacía");
        }else{
            if (registrarUsuario(tf_usuario.getText(), pf_contra.getText())){
                generarAlerta(Alert.AlertType.CONFIRMATION,"Registro exitoso","");
                limpiar();
            }else{
                generarAlerta(Alert.AlertType.WARNING,"Ojo","El usuario "+tf_usuario.getText()+" ya está registrado");
            }
        }

    }

    public void limpiar(){
        tf_usuario.setText("");
        pf_contra.setText("");
    }


    public static boolean registrarUsuario(String user, String passwd){

        if (!listaUsuarios.containsKey(user)){
            listaUsuarios.put(user, CifrarPasswd.generarHash(passwd));
            Propiedades.guardarConfig(listaUsuarios);
            return true;
        }

        return false;
    }


    public static boolean comprobaInicio(String user, String passwd){

        if (listaUsuarios.containsKey(user)){
            return CifrarPasswd.comprobarPasswd(listaUsuarios.get(user), passwd);
        }else {
            return false;
        }
    }

    public void generarAlerta(Alert.AlertType tipoAlerta, String titulo, String texto){
        Alert a = new Alert(tipoAlerta);

        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(texto);
        a.showAndWait();
    }




}
