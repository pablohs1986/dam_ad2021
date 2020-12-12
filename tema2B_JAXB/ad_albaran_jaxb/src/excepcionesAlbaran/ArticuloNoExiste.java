/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionesAlbaran;

/**
 * Clase con excepción que se retorna si el artículo no existe.
 *
 * @author Pablo Herrero
 */
public class ArticuloNoExiste extends Exception {

    public ArticuloNoExiste(String mensaje) {
        super(mensaje);
    }

}
