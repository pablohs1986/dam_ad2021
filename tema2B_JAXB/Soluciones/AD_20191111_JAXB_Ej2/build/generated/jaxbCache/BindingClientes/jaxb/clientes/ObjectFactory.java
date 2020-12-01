//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.11.30 a las 11:05:49 AM CET 
//


package jaxb.clientes;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.clientes package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.clientes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Clientes }
     * 
     */
    public Clientes createClientes() {
        return new Clientes();
    }

    /**
     * Create an instance of {@link Clientes.Cliente }
     * 
     */
    public Clientes.Cliente createClientesCliente() {
        return new Clientes.Cliente();
    }

    /**
     * Create an instance of {@link TipoDireccion }
     * 
     */
    public TipoDireccion createTipoDireccion() {
        return new TipoDireccion();
    }

    /**
     * Create an instance of {@link Clientes.Cliente.Nombre }
     * 
     */
    public Clientes.Cliente.Nombre createClientesClienteNombre() {
        return new Clientes.Cliente.Nombre();
    }

}
