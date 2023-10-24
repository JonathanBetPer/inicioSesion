package com.example.iniciosesion.model;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author JonathanBetPer
 * @version v1
 * @since 22/10/2023
 *
 * Clase Propiedades
 * Se encargar de guardar y cargar de un archivo específico la información de los usuarios y sus contraseñas
 */
public class Propiedades {

    private static File archivo = new File("src/main/resources/files/usuarios.props");

    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * método para cargar el HashMap con toda la informmación de los usuarios
     */
    public static HashMap<String, String>  cargarConfig(){

        HashMap<String, String> lista = new HashMap<>();
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(archivo));

            properties.forEach( (clave, valor) -> {
                lista.put(clave.toString(), valor.toString());
            });


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (lista.isEmpty()){
            System.out.println("No existen usuarios actualmente");
        }

        return lista;
    }

    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * método para guardar el HashMap con toda la informmación de los usuarios
     */
    public static boolean guardarConfig(HashMap<String, String> lista)  {

        Properties properties = new Properties();

        lista.forEach((clave, valor) -> {
            properties.setProperty(clave, valor);
        });

        try {
            properties.store(new FileOutputStream(archivo), "Archivo con usuarios, contraseñas con pimienta");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }




}
