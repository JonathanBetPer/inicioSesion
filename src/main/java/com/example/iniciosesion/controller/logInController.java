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

/**
 * @author JonathanBetPer
 * @version v1
 * @since 22/10/2023
 *
 * Controlador del interfaz logInView
 */
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

    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * Método para cargar la información de los usuarios y contraseñas guardarda en el arhivo
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaUsuarios = Propiedades.cargarConfig();
    }

    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * método Action Listener del botón 'Iniciar Sesión'
     *
     */
    public void iniciarSesion(ActionEvent actionEvent){

        if (tf_usuario.getText().isBlank()){
            generarAlerta(Alert.AlertType.ERROR,"Error","El usuario no puede estar vacío");

        }else if (tf_usuario.getText().isBlank()){
            generarAlerta(Alert.AlertType.ERROR,"Error","La comtraseña no puede estar vacía");

        }else {

            if (!listaUsuarios.containsKey(tf_usuario.getText())){
                generarAlerta(Alert.AlertType.WARNING,"Ojo","El usuario \'"+tf_usuario.getText()+"\' no existe");

            }else if (comprobaInicio(tf_usuario.getText(), pf_contra.getText())){
                generarAlerta(Alert.AlertType.INFORMATION,"Inicio exitoso","BRAVO, HAS INICIADO SESIÓN\n╰(*°▽°*)╯");
                limpiar();
            }else {
                generarAlerta(Alert.AlertType.WARNING,"Te has equivocado :c","La comtraseña es incorrecta");
            }
        }
    }


    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * método  Action Listener del botón 'Registrarse'
     *
     */
    public void registrarse(ActionEvent actionEvent){


        if (tf_usuario.getText().isBlank()){
            generarAlerta(Alert.AlertType.ERROR,"Error","El usuario no puede estar vacío");
        }else if (tf_usuario.getText().isBlank()){
            generarAlerta(Alert.AlertType.ERROR,"Error","La comtraseña no puede estar vacía");
        }else{
            if (registrarUsuario(tf_usuario.getText(), pf_contra.getText())){
                generarAlerta(Alert.AlertType.INFORMATION,"Registro exitoso","Te has registrao' máquina\nBienvenido! ༼ つ ◕_◕ ༽つ");
                limpiar();
            }else{
                generarAlerta(Alert.AlertType.WARNING,"Ojo","El usuario "+tf_usuario.getText()+" ya está registrado");
            }
        }
    }

    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * Método que limpia los textField de la interfaz
     *
     */
    public void limpiar(){
        tf_usuario.setText("");
        pf_contra.setText("");
    }


    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * Método para registrar un usuario
     * Comprueba si el usuario ya esta registrado y retorna falso si ya existe
     */
    public static boolean registrarUsuario(String user, String passwd){

        if (!listaUsuarios.containsKey(user)){
            listaUsuarios.put(user, CifrarPasswd.generarHash(passwd));
            Propiedades.guardarConfig(listaUsuarios);
            return true;
        }

        return false;
    }


    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * Método comprobar si se puede iniciar sesión
     */
    public static boolean comprobaInicio(String user, String passwd){

        if (listaUsuarios.containsKey(user)){
            return CifrarPasswd.comprobarPasswd(listaUsuarios.get(user), passwd);
        }else {
            return false;
        }
    }

    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * método para generar alertas en pantalla
     */

    public void generarAlerta(Alert.AlertType tipoAlerta, String titulo, String texto){
        Alert a = new Alert(tipoAlerta);

        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(texto);
        a.showAndWait();
    }




}
