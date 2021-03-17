/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.io.File;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import jaxb.biblioteca.Biblioteca;
import jaxb.biblioteca.ObjectFactory;
import jaxb.biblioteca.SocioType;
import modelo.MetodosBiblioteca;
import modelo.excepciones.SocioNoExisteException;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetodosBiblioteca metodos = new MetodosBiblioteca();
        File archivoXMLentrada = new File("BIBLIOTECA.xml");
        File archivoXMLsalida = new File("bibliotecaSALIDA.xml");

        try {
            // Unmarshalizo
            JAXBElement elemento = metodos.unmarshalizar(archivoXMLentrada);
            // Creo fábrica
            ObjectFactory fabrica = new ObjectFactory();
            // Creo pedidos
            Biblioteca nodoBiblioteca = fabrica.createBiblioteca();
            // Asigno valores del elemento al nodo
            nodoBiblioteca = (Biblioteca) elemento.getValue();
            
            
            // Apartado A
            GregorianCalendar fecha = new GregorianCalendar(1986, 10, 24);
            XMLGregorianCalendar calendar = new XMLGregorianCalendarImpl(fecha);
            metodos.aniadirSocio(nodoBiblioteca, "111", "PAblo", "Herrero", "Sánchez", new BigInteger("666111222"), "0000", "AAAA", calendar);
            
            // Apartado B
            SocioType socioBuscado = metodos.buscarSocio(nodoBiblioteca, "111", new BigInteger("666111222"));
            System.out.println("Socio buscado: " + socioBuscado.getCodigoSocio() + " " + socioBuscado.getTelefono());
//            SocioType socioBuscado2 = metodos.buscarSocio(nodoBiblioteca, "23", new BigInteger("666111222"));
//            SocioType socioBuscado3 = metodos.buscarSocio(nodoBiblioteca, "23", new BigInteger("666666666"));

            // Apartado C
            SocioType socioABorrarPrestamos = new SocioType();
            socioABorrarPrestamos.setCodigoSocio("222");
            boolean prestamosBorrados = metodos.borrarPrestamosSocio(nodoBiblioteca, socioABorrarPrestamos);
            System.out.println("¿Prestamos de " + socioABorrarPrestamos.getCodigoSocio() + " borrados?: " + prestamosBorrados);
            
            // Apartado D
            boolean prestamosRenovados = metodos.renovarPrestamosSocio(nodoBiblioteca, "333");
            System.out.println("¿Prestamos renovados?: " + prestamosRenovados);

            // Marshalizo
            metodos.marshalizar(elemento, archivoXMLsalida);
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocioNoExisteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
