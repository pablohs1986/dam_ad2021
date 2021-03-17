/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.Clientes.Clientes;
import jaxb.Clientes.ObjectFactory;
import modelo.Metodos;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Metodos metodos = new Metodos();
            File archivoXMLentrada = new File("clientes.xml");
            File archivoXMLsalida = new File("clientesSALIDA.xml");
            
            // Unmarshalizo
            JAXBElement elemento = metodos.unmarshalizar(archivoXMLentrada);
            // Creo fábrica
            ObjectFactory fabrica = new ObjectFactory();
            // Creo pedidos
            Clientes nodoClientes = fabrica.createClientes();
            // Asigno valores del elemento al nodo
            nodoClientes = (Clientes) elemento.getValue();
            
            // Apartado 1
            
            JsonObject direccion1 = metodos.crearDireccion("asdads", 1, 1, "a", 33006, "Oviedo");
            JsonObject direccion2 = metodos.crearDireccion("qweq", 2, 2, "q", 24404, "Ponferrada");
            
            JsonArray arrayDirecciones = Json.createArrayBuilder().add(direccion1).add(direccion2).build();

            List<JsonObject> listaDirecciones = new ArrayList<>(); 
            listaDirecciones.add(direccion1);
            listaDirecciones.add(direccion2);
            
            JsonObject cliente = metodos.crearCliente("pepe", "asd", "asd", listaDirecciones, 627107436);
            
            metodos.escribirJsonEnDisco(cliente, "pruebaCliente.json");
            
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
