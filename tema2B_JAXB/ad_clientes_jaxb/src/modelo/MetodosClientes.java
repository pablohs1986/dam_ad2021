/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import jaxb.clientes.Clientes.Cliente.Nombre;
import jaxb.clientes.TipoDireccion;

/**
 *
 * @author Pablo Herrero
 */
public class MetodosClientes implements InterfaceClientes {

    /**
     * Método para unmarshalizar (leer).
     *
     * @param ficheroXML
     * @return JAXBElement
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("jaxb.clientes");
        Unmarshaller unmarshalizador = contexto.createUnmarshaller();
        JAXBElement elemento = unmarshalizador.unmarshal(new StreamSource("clientes.xml"), Clientes.class);
        return elemento;
    }

    /**
     * Método para marshalizar (escribir).
     *
     * @param jaxbElement
     * @param archivoSalida
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public void marshalizar(JAXBElement jaxbElement, File archivoSalida) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("jaxb.clientes");
        Marshaller marshalizador = contexto.createMarshaller();
        marshalizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshalizador.marshal(jaxbElement, archivoSalida);
    }

    @Override
    public int totalClientes(Clientes clientes) {
        List<Clientes.Cliente> listaClientes = clientes.getCliente();
        return listaClientes.size();
    }

    @Override
    public int totalClientesProvincia(Clientes clientes, int cp) {
        List<Clientes.Cliente> listaClientes = clientes.getCliente();
        int totalClientesPorProvincia = 0;

        for (Clientes.Cliente cliente : listaClientes) {
            List<TipoDireccion> nodoDireccion = cliente.getDireccion();
            for (TipoDireccion direccion : nodoDireccion) {
                int codigoProvinciaDireccion = calcularCodigoProvincia(direccion.getCp());
                int codigoProvinciaABuscar = calcularCodigoProvincia(cp);
                if(codigoProvinciaABuscar == codigoProvinciaDireccion) {
                    totalClientesPorProvincia++;
                }
            }
        }
        return totalClientesPorProvincia;
    }

    private int calcularCodigoProvincia(int cp) {
        return cp/1000;
    }

    @Override
    public boolean borrarClientePorApellido(Clientes clientes, String apellido1, String apellido2) {
        boolean borrado = false;
        List<Cliente> nodoClientes = clientes.getCliente();
        Iterator<Cliente> it = nodoClientes.iterator();
        Clientes.Cliente clienteABuscar = new Clientes.Cliente();
        
        while (it.hasNext()) {
            Cliente cliente = it.next();
            String apellidoABorrar = apellido1+apellido2;
            String apellidosCliente = null;
            List<String> listaApellidos = cliente.getApellido();
            for (String apellido : listaApellidos) {
                apellidosCliente += apellido;
            }
            
            if(apellidoABorrar.equals(apellidosCliente)) {
                it.remove();
                return borrado = true;
            }
        }
        return borrado;
    }
  
//    Versión sin iterator
//    @Override
//    public void borrarCliente(Clientes nodoClientes, String apellido1, String apellido2) {
//        List<Clientes.Cliente> listaClientes = nodoClientes.getCliente();
//
//        for (Cliente cliente : listaClientes) {
//            List<String> listaApellidos = cliente.getApellido();
//
//            if (listaApellidos.get(0).equalsIgnoreCase(apellido1) && listaApellidos.get(1).equalsIgnoreCase(apellido2)) {
//                listaClientes.remove(cliente);
//            }
//        }
//    }

    @Override
    public boolean aniadirCliente(Clientes clientes, Nombre nombre, String apellido1, String apellido2, TipoDireccion direccion, String telefono) {
        boolean aniadido = false;
        List<Cliente> listadoClientes = clientes.getCliente();
        
        Clientes.Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(nombre);
        nuevoCliente.getApellido().add(apellido1);
        nuevoCliente.getApellido().add(apellido2);
        nuevoCliente.getDireccion().add(direccion);
        nuevoCliente.setTelefono(telefono);
        
        listadoClientes.add(nuevoCliente);
        
        return listadoClientes.contains(nuevoCliente) ? !aniadido : aniadido;
    }

    @Override
    public boolean aniadirDireccionACliente(Clientes clientes, String nombre, String apellido1, String apellido2, TipoDireccion direccion) {
        List<Cliente> listadoClientes = clientes.getCliente();
        boolean aniadir = false;
        
        for (Cliente cliente : listadoClientes) {
            String nombreCliente = cliente.getNombre().getLenguaje();
            List<String> apellidosCliente = cliente.getApellido();
            if(nombreCliente.equalsIgnoreCase(nombre) 
                    && apellidosCliente.get(0).equalsIgnoreCase(apellido1) 
                    && apellidosCliente.get(1).equalsIgnoreCase(apellido2)) {
                cliente.getDireccion().add(direccion);
                aniadir = true;
            }
        }
        
        return aniadir;
    }
    
//    -> Otra versión
//    @Override
//    public void aniadirDireccionACliente(Clientes nodoClientes, String apellido1, String apellido2, Cliente.Nombre nombre, TipoDireccion direccion) {
//        List<Clientes.Cliente> listaClientes = nodoClientes.getCliente();
//
//        for (Cliente cliente : listaClientes) {
//            List<String> listaApellidosCliente = cliente.getApellido();
//            if (listaApellidosCliente.get(0).equalsIgnoreCase(apellido1)
//                    && listaApellidosCliente.get(1).equalsIgnoreCase(apellido2)
//                    && cliente.getNombre().getLenguaje().equalsIgnoreCase(nombre.getLenguaje())) {
//                List<TipoDireccion> listaDireccionesCliente = cliente.getDireccion();
//                listaDireccionesCliente.add(direccion);
//            }
//        }
//    }

    @Override
    public int borrarDireccionesSinCodigoPostal(Clientes clientes) {
        List<Cliente> listadoClientes = clientes.getCliente();
        int totalDireccionesBorradas = 0;
        
        for (Cliente cliente : listadoClientes) {
            List<TipoDireccion> listadoDireccionesCliente = cliente.getDireccion();
            Iterator<TipoDireccion> it = listadoDireccionesCliente.iterator();
            
            while(it.hasNext()){
                TipoDireccion direccion = it.next();
                if (direccion.getCp() == 0) {
                    it.remove();
                    totalDireccionesBorradas++;
                }
            }
        }
        return totalDireccionesBorradas;
    }

    @Override
    public File pasarClientesAHtml(Clientes clientes) {
        List<Cliente> listadoClientes = clientes.getCliente();
        File clientesHtml = new File("clientes.html");
        
        FileWriter fw;
        try {
            fw = new FileWriter(clientesHtml);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Cliente cliente : listadoClientes) {
                List<String> apellidosCliente = cliente.getApellido();
                for (String apellido : apellidosCliente) {
                    bw.write(apellido + " ");
                    bw.newLine();
                }
                List<TipoDireccion> direccionesCLiente = cliente.getDireccion();
                for (TipoDireccion direccion : direccionesCLiente) {
                    bw.write(direccion.getCalle());
                    bw.write(direccion.getCiudad());
                    bw.write(direccion.getCp());
                    bw.write(direccion.getEscalera());
                    bw.write(direccion.getNumero());
                    bw.write(direccion.getPiso());
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(MetodosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return clientesHtml;
    }
}
