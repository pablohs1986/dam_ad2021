/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_proyectoclientes;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.clientes.Clientes;
import jaxb.clientes.ObjectFactory;
import jaxb.clientes.TipoDireccion;
import modelo.ExcepcionesClientes;
import modelo.MetodosClientes;

/**
 *
 * @author Pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        File archivo = new File("clientes.xml");
        File arhivoSalida = new File("clientesSalida.xml");
        MetodosClientes metodos = new MetodosClientes();
        
       
        try {
            JAXBElement elemento = metodos.unmarshalizar(archivo);
            
            ObjectFactory factoria = new ObjectFactory();
            
            Clientes nodoClientes = factoria.createClientes();
            
            nodoClientes = (Clientes) elemento.getValue();
            
            // Apartado 1
            System.out.println(metodos.totalClientes(nodoClientes));
            
            // Apartado 2
            System.out.println(metodos.totalClientesProvincia(nodoClientes, 33006));
            
            // Apartado 3
            metodos.borrarCliente(nodoClientes, "Chapuli", "García");
            
            // Apartado 4
            TipoDireccion direccion = new TipoDireccion();
            direccion.setCalle("Calle 1");
            direccion.setCiudad("Oviedo");
            direccion.setCp(33006);
            direccion.setEscalera("1");
            direccion.setNumero("26");
            direccion.setPiso(2);
            
            metodos.aniadirCliente(nodoClientes, "García", "García", "Español", direccion, "666111222");
            
            // Apartado 5
            TipoDireccion direccion2 = new TipoDireccion();
            direccion2.setCalle("Calle 2");
            direccion2.setCiudad("Gijon");
            direccion2.setCp(33006);
            direccion2.setEscalera("1");
            direccion2.setNumero("26");
            direccion2.setPiso(2);
            
            Clientes.Cliente.Nombre nombreCliente = new Clientes.Cliente.Nombre();
            nombreCliente.setLenguaje("Español");
            
            metodos.aniadirDireccionACliente(nodoClientes, "García", "García", nombreCliente, direccion2);
            // Marshalizador
            metodos.marshalizar(elemento, arhivoSalida);
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcepcionesClientes ex) {
            System.out.println("No se ha encontrado ningún cliente");
        }
        
    }
    
}
