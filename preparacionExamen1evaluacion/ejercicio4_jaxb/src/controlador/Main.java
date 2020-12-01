/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.diccionario.DiccionarioEspanol;
import jaxb.diccionario.ObjectFactory;
import jaxb.diccionario.PalabraType;
import modelo.MetodosDiccionario;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetodosDiccionario metodos = new MetodosDiccionario();
        File archivoXML = new File("diccionario.xml");
        try {
            // Unmarshalizo
            JAXBElement elemento = metodos.unmarshalizar(archivoXML);
            // Creo fábrica
            ObjectFactory fabrica = new ObjectFactory();
            // Creo diccionario
            DiccionarioEspanol diccionario = fabrica.createDiccionarioEspanol();
            // Asigno los valores del elemento al diccionario
            diccionario = (DiccionarioEspanol) elemento.getValue();

            // Apartado A
            PalabraType palabra = diccionario.getPalabra().get(0); // Tomo una palabra
            int numeroDefinicionesPalabra = metodos.contarDefiniciones(diccionario, palabra);
            System.out.println("Numero de definiciones para la palabra \"" + palabra.getGrafia() + "\": " + numeroDefinicionesPalabra);
            
            // Apartado B
            File traduccionesBorradas = new File("traducciones.txt");
//            try {
//                metodos.borrarTraduccionesYAlmacenarEnArchivo(diccionario, "String", traduccionesBorradas);
//            } catch (IOException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            // Apartado C
            Map<String, Integer> mapaTraducciones = metodos.generarMapaNumeroTraduccionesPorIdioma(diccionario);
            System.out.println("Mapa de traducciones: \n" + mapaTraducciones);
            
            // Apartado D
            Map<String, List<String>> mapaSinonimosYDefinicionesDePalabra = metodos.generarMapaDeSinonimosYDefinicionesDePalabra(diccionario, "String");
            System.out.println(mapaSinonimosYDefinicionesDePalabra.size());
//            Collection c = mapaSinonimosYDefinicionesDePalabra.values();
           
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
