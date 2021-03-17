/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dto.ContactosDAO;
import java.util.List;
import modelo.Contacto;

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ContactosDAO contactosDAO = new ContactosDAO();
        Contacto contactoRecuperado = null;
        long idAEliminar = 0;
        
        // 3 instancias de contacto
        Contacto contacto1 = new Contacto("Contacto 1", "contacto1@contacto.com", "12345678");
        Contacto contacto2 = new Contacto("Contacto 2", "contacto2@contacto.com", "87654321");
        Contacto contacto3 = new Contacto("Contacto 3", "contacto3@contacto.com", "45612378");
        
        // Inserto las 3 instancias
        idAEliminar = contactosDAO.guardarContacto(contacto1);
        contactosDAO.guardarContacto(contacto2);
        contactosDAO.guardarContacto(contacto3);
        
        // Modifico el contacto 2
        contacto2.setNombre("Pepín García");
        contactosDAO.actualizarContacto(contacto2);
        
        // Selecciono el contacto 1
        contactoRecuperado = contactosDAO.obtenerContacto(idAEliminar);
        System.out.println("Recuperando a contacto " + contactoRecuperado.getNombre());
        
        // Elimino el contacto recuperado
        contactosDAO.eliminarContacto(contactoRecuperado);
        
        // Obtengo la lista de contactos
        List<Contacto> obtenerListaContactos = contactosDAO.obtenerListaContactos();
        System.out.println("En la base de datos hay estos contactos: " + obtenerListaContactos.size());
        
        for (Contacto contacto : obtenerListaContactos) {
            System.out.println(contacto.getNombre());
        }
    }
    
}
