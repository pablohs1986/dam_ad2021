/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import excepciones.nombreDuplicado;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.agenda.Agenda;
import jaxb.agenda.ObjectFactory;
import modelo.metodosAgenda;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        metodosAgenda metodos = new metodosAgenda();
        File archivoXMLentrada = new File("agenda.xml");
        File archivoXMLsalida = new File("agendaSalida.xml");
        
        try {
            JAXBElement elemento = metodos.unmarshalizar(archivoXMLentrada);
            // Creo fábrica
            ObjectFactory fabrica = new ObjectFactory();
            // Creo pedidos
            Agenda nodoAgenda = fabrica.createAgenda();
            // Asigno valores del elemento al nodo
            nodoAgenda = (Agenda) elemento.getValue();
            
            // Apartado 1
            metodos.aniadirAlarma(nodoAgenda, "ALARMA AÑADIDA", "1", "1", "1");
            
            // Apartado 2
            metodos.aniadirTelefonoAContacto(nodoAgenda, "PEPE", "666666666");
            
            // Apartado 3 
            System.out.println(metodos.resumenCorreos(nodoAgenda));
            
            // Apartado 4
            List<String> listaTelefonosContacto = metodos.buscarTelefono(nodoAgenda, "pepe");
            for (String telefono : listaTelefonosContacto) {
                System.out.println(telefono);
            }
            
            try {
                // Apartado 5
                metodos.borrarContacto(nodoAgenda, "Pepe");
            } catch (nombreDuplicado ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Apartado 6
            HashMap<String, Integer> mapaTotales = metodos.generarMapaTotalElementos(nodoAgenda);
            System.out.println(mapaTotales);
            
            // Apartado 7
            int totalFechasSuperiores = metodos.totalAlarmasPendientesPosterioresAFecha(nodoAgenda, 24, 10, 1986);

            // Marshalizo
            metodos.marshalizar(elemento, archivoXMLsalida);
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
