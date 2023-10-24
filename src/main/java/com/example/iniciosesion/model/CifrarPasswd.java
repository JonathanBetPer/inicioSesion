package com.example.iniciosesion.model;

import com.password4j.Hash;
import com.password4j.Password;

/**
 * @author JonathanBetPer
 * @version v1
 * @since 22/10/2023
 *
 * Clase CifrarPasswd
 * Utiliza la dependencia password4j (y a su vez la interfaz SLF4J) para cifrar y descifrar contraseñas, con pimienta
 */
public class CifrarPasswd {
    private static String pimienta = "un poco de...PIMIENTA";


    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * Genera el hash de una contraseña con pimienta y retorna su resultad
     */
    public static String generarHash(String passwd){
        Hash hash = Password.hash(passwd).addPepper(pimienta).withArgon2();

        return hash.getResult();
    }


    /**
     * @author JonathanBetPer
     * @version v1
     * @since 22/10/2023
     *
     * Coomprueba que el resultado de un hash con pimienta coincida con una contraseña
     */
    public static boolean comprobarPasswd(String hashResult, String passwd){
        return Password.check(passwd,hashResult).addPepper(pimienta).withArgon2();
    }


}
