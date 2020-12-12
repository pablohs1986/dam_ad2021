/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.clientes.Clientes;
import jaxb.clientes.Clientes.Cliente;
import jaxb.clientes.TipoDireccion;

/**
 *
 * @author Pablo
 */
public class MetodosClientes implements InterfaceClientes{

    @Override
    public JAXBElement unmarshalizar(File archivoXML) throws JAXBException{
        
        JAXBContext contexto = JAXBContext.newInstance("jaxb.clientes");
        Unmarshaller unmarshaller = contexto.createUnmarshaller();
        JAXBElement elemento = unmarshaller.unmarshal(new StreamSource("clientes.xml"), Clientes.class);
        return elemento;
    }
    
    @Override
    public void marshalizar(JAXBElement jaxbElement, File archivoSalida) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("jaxb.clientes");
        Marshaller marshalizador = contexto.createMarshaller();
        marshalizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshalizador.marshal(jaxbElement, archivoSalida);
    }

    @Override
    public int totalClientes(Clientes nodoClientes) throws ExcepcionesClientes {
        
        
        List<Cliente> listaCliente = nodoClientes.getCliente();
        return listaCliente.size();
    }

    @Override
    public int totalClientesProvincia(Clientes nodoClientes, int cp) {
        List<Clientes.Cliente> listaClientes = nodoClientes.getCliente();
        int codigoProvinciaABuscar = devolverDosPrimerosDigitos(cp);
        int totalClientesProvincia = 0;
        
        for (Cliente cliente : listaClientes) {
            List<TipoDireccion> listaDirecciones = cliente.getDireccion();
            
            for (TipoDireccion direccion : listaDirecciones) {
                int codigoProvinciaDireccion = devolverDosPrimerosDigitos(direccion.getCp());
                if(codigoProvinciaABuscar == codigoProvinciaDireccion) {
                    totalClientesProvincia++;
                }
            }
        }
        return totalClientesProvincia;
    }
    
    public int devolverDosPrimerosDigitos(int cp){
        int codigoProvincia = cp/1000;
        return codigoProvincia;
    }

    @Override
    public void borrarCliente(Clientes nodoClientes, String apellido1, String apellido2) {
        List<Clientes.Cliente> listaClientes = nodoClientes.getCliente();
        
        for (Cliente cliente : listaClientes) {
            List<String> listaApellidos = cliente.getApellido();
            
            if(listaApellidos.get(0).equalsIgnoreCase(apellido1) && listaApellidos.get(1).equalsIgnoreCase(apellido2)) {
                listaClientes.remove(cliente);
            }
        }
    }

    @Override
    public void aniadirCliente(Clientes nodoClientes, String apellido1, String apellido2, String lenguaje, TipoDireccion direccion, String telefono) {
        List<Clientes.Cliente> listaClientes = nodoClientes.getCliente();
        
        Clientes.Cliente clienteAAniadir = new Cliente();
        Clientes.Cliente.Nombre nombreAAniadir = new Cliente.Nombre();
        
        List<String> listaApellidosAniadir = clienteAAniadir.getApellido();
        listaApellidosAniadir.add(apellido1);
        listaApellidosAniadir.add(apellido2);
        
        List<TipoDireccion> listaDireccionesAniadir = clienteAAniadir.getDireccion();
        listaDireccionesAniadir.add(direccion);
        
        nombreAAniadir.setLenguaje(lenguaje);
        clienteAAniadir.setNombre(nombreAAniadir);
        clienteAAniadir.setTelefono(telefono);
        
        listaClientes.add(clienteAAniadir);
    }

    @Override
    public void aniadirDireccionACliente(Clientes nodoClientes, String apellido1, String apellido2, Cliente.Nombre nombre, TipoDireccion direccion) {
        List<Clientes.Cliente> listaClientes = nodoClientes.getCliente();
        
        for (Cliente cliente : listaClientes) {
            List<String> listaApellidosCliente = cliente.getApellido();
            if(listaApellidosCliente.get(0).equalsIgnoreCase(apellido1) 
                    && listaApellidosCliente.get(1).equalsIgnoreCase(apellido2)
                    && cliente.getNombre().getLenguaje().equalsIgnoreCase(nombre.getLenguaje())) {
                List<TipoDireccion> listaDireccionesCliente = cliente.getDireccion();
                listaDireccionesCliente.add(direccion);
            }
        }
    }
    
}
