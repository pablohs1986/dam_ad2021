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
import jaxb.fitness.Fitness;
import jaxb.fitness.ObjectFactory;
import modelo.MetodosFitness;
import modelo.excepciones.UsuarioNoExisteException;

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
            MetodosFitness metodos = new MetodosFitness();
            File archivoXMLentrada = new File("fitness2.xml");
            File archivoXMLsalida = new File("fitnessSALIDA.xml");
            
            // Unmarshalizo
            JAXBElement elemento = metodos.unmarshalizar(archivoXMLentrada);
            // Creo fábrica
            ObjectFactory fabrica = new ObjectFactory();
            // Creo pedidos
            Fitness nodoFitness = fabrica.createFitness();
            // Asigno valores del elemento al nodo
            nodoFitness =  (Fitness) elemento.getValue();
            
            // Apartado 1
//            for (int i = 0; i < 4; i++) {
//                try {
//                    boolean entrenamientoAsignado = metodos.asignarEntrenamientoDeDificultadAleatoria(nodoFitness, 1);
//                } catch (UsuarioNoExisteException ex) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
            
            // Apartado 2

            // Marshalizo
            metodos.marshalizar(elemento, archivoXMLsalida);
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
