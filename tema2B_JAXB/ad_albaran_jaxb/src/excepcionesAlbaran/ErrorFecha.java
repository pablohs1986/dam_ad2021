/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionesAlbaran;

/**
 * Clase con excepción que se retorna si la fecha no es válida.
 *
 * @author Pablo Herrero
 */
public class ErrorFecha extends Exception {

    public ErrorFecha(String mensaje) {
        super(mensaje);
    }
}
