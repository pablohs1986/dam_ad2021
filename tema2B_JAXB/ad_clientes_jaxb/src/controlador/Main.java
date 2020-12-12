/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.clientes.Clientes;
import jaxb.clientes.Clientes.Cliente.Nombre;
import jaxb.clientes.ObjectFactory;
import jaxb.clientes.TipoDireccion;
import modelo.MetodosClientes;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetodosClientes metodos = new MetodosClientes();
        File archivoXMLentrada = new File("clientes.xml");
        File archivoXMLsalida = new File("clientesSalida.xml");
        
        // Unmarshalizo
        JAXBElement elemento;
        try {
            elemento = metodos.unmarshalizar(archivoXMLentrada);
            // Creo fábrica
            ObjectFactory fabrica = new ObjectFactory();
            // Creo pedidos
            Clientes nodoClientes = fabrica.createClientes();
            // Asigno valores del elemento al nodo
            nodoClientes = (Clientes) elemento.getValue();
            
            // Apartado 1
            int totalClientes = metodos.totalClientes(nodoClientes);
            System.out.println("Total clientes: " + totalClientes);
            
            // Apartado 2
            int cp = 33006;
            int totalClienteProvincia = metodos.totalClientesProvincia(nodoClientes, cp);
            System.out.println("Total clientes en el código postal " + cp + ": " + totalClienteProvincia);
            
            // Apartado 3 // REVISAR
            String apellido1 = "Herrero";
            String apellido2 = "Sánchez";
            metodos.borrarClientePorApellido(nodoClientes, apellido1, apellido2);
            
            // Apartado 4
            Nombre nombre = new Nombre();
            nombre.setLenguaje("Sandra");
            TipoDireccion direccion = new TipoDireccion();
            String telefono = "666666666";
            boolean aniadido = metodos.aniadirCliente(nodoClientes, nombre, "García", "Pérez", direccion, telefono);
            System.out.println("Cliente añadido: " + aniadido);
            
            // Apartado 5
//            TipoDireccion direccionAAniadir = new TipoDireccion();
//            boolean direccionAniadida = metodos.aniadirDireccionACliente(nodoClientes, "Pablo", "Herrero", "Sánchez", direccionAAniadir);
//            System.out.println("Dirección añadida: " + direccionAniadida);

            // Apartado 6 FALTA
            // Apartado 7
            int totalDireccionesBorradas = metodos.borrarDireccionesSinCodigoPostal(nodoClientes);
            System.out.println("Total direcciones sin cp borradas: " + totalDireccionesBorradas);
            
            // Apartado 8
            File clientesHtml = metodos.pasarClientesAHtml(nodoClientes);
            
            // Marshalizo
            
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
