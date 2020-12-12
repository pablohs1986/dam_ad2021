/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.clientes.Clientes;
import jaxb.clientes.TipoDireccion;

/**
 *
 * @author Pablo
 */
public interface InterfaceClientes {
    
    public JAXBElement unmarshalizar(File archivoXML)throws JAXBException;
    
    public void marshalizar(JAXBElement jaxbElement, File archivoSalida) throws JAXBException;
    
    public int totalClientes(Clientes nodoClientes)throws ExcepcionesClientes;
    
    public int totalClientesProvincia(Clientes nodoClientes, int cp);
    
    public void borrarCliente(Clientes nodoClientes, String apellido1, String apellido2);
    
    public void aniadirCliente(Clientes nodoClientes, String apellido1, String apellido2, String lenguaje, TipoDireccion direccion, String telefono);
    
    public void aniadirDireccionACliente(Clientes nodoClientes, String apellido1, String apellido2, Clientes.Cliente.Nombre nombre, TipoDireccion direccion);
}
