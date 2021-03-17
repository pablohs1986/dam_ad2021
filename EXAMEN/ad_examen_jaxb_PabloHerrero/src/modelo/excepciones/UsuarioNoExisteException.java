/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.excepciones;

/**
 *
 * @author Pablo Herrero
 */
public class UsuarioNoExisteException extends Exception {

    public UsuarioNoExisteException(String mensaje) {
        super(mensaje);
    }
}
