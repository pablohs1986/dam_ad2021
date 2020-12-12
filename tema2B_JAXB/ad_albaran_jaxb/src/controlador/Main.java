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
import jaxb.Albaran.ObjectFactory;
import jaxb.Albaran.PedidoType;
import modelo.MetodosAlbaran;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetodosAlbaran metodos = new MetodosAlbaran();
        File archivoXMLentrada = new File("albaran.xml");
        File archivoXMLsalida = new File("albaranSalida.xml");
        
        try {
            // Unmarshalizo
            JAXBElement elemento = metodos.unmarshalizar(archivoXMLentrada);
            // Creo fábrica
            ObjectFactory fabrica = new ObjectFactory();
            // Creo pedidos
            PedidoType nodoPedidos = fabrica.createPedidoType();
            // Asigno valores del elemento al nodo
            nodoPedidos = (PedidoType) elemento.getValue();
            
            try {
                // Apartado A
                metodos.aniadirArticulo(nodoPedidos, "1111", "ARTICULO_PRUEBA", 3, (int) 2.8, "blablablba", 10, 2, 2020);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Apartado B
            metodos.modificarDireccion(nodoPedidos, "Avda Segadas 26", "Oviedo", "Asturias", "España", 33006);
            
            // Apartado C
            double importePedido = metodos.calcularImportePedido(nodoPedidos);
            System.out.println("Importe total del pedido: " + importePedido);
            
            try {
                // Apartado D
                metodos.borrarArticulo(nodoPedidos, "Alfombra");
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Marshalizo
            metodos.marshalizar(elemento, archivoXMLsalida);
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
