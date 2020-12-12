/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.HashMap;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.clientes.Clientes;
import jaxb.clientes.Clientes.Cliente.Nombre;
import jaxb.clientes.TipoDireccion;

/**
 *
 * @author Pablo Herrero
 */
public interface InterfaceClientes {
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException;

    public void marshalizar(JAXBElement jaxbElement, File archivoSalida) throws JAXBException;
    
    public int totalClientes(Clientes clientes);
    
    public int totalClientesProvincia(Clientes clientes, int cp);
    
    public boolean borrarClientePorApellido(Clientes clientes, String apellido1, String apellido2);
    
    public boolean aniadirCliente(Clientes clientes, Nombre nombre, String apellido1, String apellido2, TipoDireccion direccion, String telefono);
    
    public boolean aniadirDireccionACliente(Clientes clientes, String nombre, String apellido1, String apellido2, TipoDireccion direccion);
    
    public int borrarDireccionesSinCodigoPostal(Clientes clientes);
    
    public File pasarClientesAHtml(Clientes clientes);
}
