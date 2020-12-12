/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author Pablo Herrero
 */
public class nombreDuplicado extends Exception {
    
    public nombreDuplicado(String mensaje) {
        super(mensaje);
    }
}
