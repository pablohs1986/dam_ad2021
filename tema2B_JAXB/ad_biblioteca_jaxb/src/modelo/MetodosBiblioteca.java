/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;
import jaxb.biblioteca.Biblioteca;
import jaxb.biblioteca.Biblioteca.Socios;
import jaxb.biblioteca.PrestamoType;
import jaxb.biblioteca.SocioType;
import modelo.excepciones.SocioNoExisteException;

/**
 *
 * @author Pablo Herrero
 */
public class MetodosBiblioteca implements InterfaceBiblioteca {

    /**
     * Método para unmarshalizar (leer).
     *
     * @param ficheroXML
     * @return JAXBElement
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("jaxb.biblioteca");
        Unmarshaller unmarshalizador = contexto.createUnmarshaller();
        JAXBElement elemento = unmarshalizador.unmarshal(new StreamSource(ficheroXML), Biblioteca.class);
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
        JAXBContext contexto = JAXBContext.newInstance("jaxb.biblioteca");
        Marshaller marshalizador = contexto.createMarshaller();
        marshalizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshalizador.marshal(jaxbElement, archivoSalida);
    }

    @Override
    public void aniadirSocio(Biblioteca biblioteca, String codigoSocio, String nombre,
            String apellido1, String apellido2, BigInteger telefono, String isbn,
            String titulo, XMLGregorianCalendar fechaDevolucion) {
        Biblioteca.Socios nodoSocios = biblioteca.getSocios();
        List<SocioType> listaSocios = nodoSocios.getSocio();
        listaSocios.add(crearSocio(codigoSocio, nombre, apellido1, apellido2, telefono,
                isbn, titulo, fechaDevolucion));

    }

    private SocioType crearSocio(String codigoSocio, String nombre, String apellido1,
            String apellido2, BigInteger telefono, String isbn, String titulo,
            XMLGregorianCalendar fechaDevolucion) {
        SocioType nuevoSocio = new SocioType();
        nuevoSocio.setCodigoSocio(codigoSocio);
        nuevoSocio.setNombreSocio(nombre);
        nuevoSocio.setTelefono(telefono);
        nuevoSocio.getApellidoSocio().add(apellido1);
        nuevoSocio.getApellidoSocio().add(apellido2);
        // Para desatascar, el socio se inicia sin préstamos...
//        List<PrestamoType> prestamos = nuevoSocio.getLibrosPrestados().getPrestamo();
//        prestamos.add(crearPrestamo(isbn, titulo, codigoSocio, 
//                nombre, apellido1, apellido2, fechaDevolucion));
//        
        return nuevoSocio;
    }

//    private PrestamoType crearPrestamo(String isbn, String titulo, String codigoSocio, 
//            String nombreSocio, String apellido1, String apellido2, 
//            XMLGregorianCalendar fechaDevolucion) {
//        PrestamoType nuevoPrestamo = new PrestamoType();
//        nuevoPrestamo.setISBN(isbn);
//        nuevoPrestamo.setTitulo(titulo);
//        nuevoPrestamo.setCodigoSocio(codigoSocio);
//        nuevoPrestamo.setNombreSocio(nombreSocio);
//        nuevoPrestamo.setApellidoSocio(apellido1 + " " + apellido2);
//        nuevoPrestamo.setFechaDevolucion(fechaDevolucion);
//        
//        return nuevoPrestamo;
//    }
    @Override
    public SocioType buscarSocio(Biblioteca biblioteca, String codigoSocio, BigInteger telefono) throws SocioNoExisteException {
        Socios nodoSocios = biblioteca.getSocios();
        List<SocioType> listaSocios = nodoSocios.getSocio();
        SocioType socioBuscado = null;
        boolean isSocioEncontrado = false;

        for (SocioType socio : listaSocios) {
            if (socio.getCodigoSocio().equalsIgnoreCase(codigoSocio)
                    || socio.getTelefono().equals(telefono)) {
                System.out.println("Socio encontrado.");
                socioBuscado = socio;
                isSocioEncontrado = true;
            }
        }

        if (!isSocioEncontrado) {
            throw new SocioNoExisteException("El socio con codigo " + codigoSocio + " y teléfono " + telefono + " no existe.");
        }
        return socioBuscado;
    }

    @Override
    public boolean borrarPrestamosSocio(Biblioteca biblioteca, SocioType socioABorrar) throws SocioNoExisteException {
        List<SocioType> listaSocios = biblioteca.getSocios().getSocio();
        boolean isPrestamosBorrados = false;
        
        for (SocioType socio : listaSocios) {
            if (socioABorrar.getCodigoSocio().equalsIgnoreCase(socio.getCodigoSocio())) {
            //if (socio.getCodigoSocio().equalsIgnoreCase(socioABorrar.getCodigoSocio())) { // MAL! empezar por el parámetro conocido para que no rompa
                List<PrestamoType> listaPrestamos = socio.getLibrosPrestados().getPrestamo();
                Iterator it = listaPrestamos.iterator();

                for (Iterator<PrestamoType> prestamo = listaPrestamos.iterator(); prestamo.hasNext();) {
                    PrestamoType next = prestamo.next();
                    prestamo.remove();
                }
                isPrestamosBorrados = true;
            }
        }

        if (!isPrestamosBorrados) {
            throw new SocioNoExisteException("El socio con codigo " + socioABorrar.getCodigoSocio() + " no existe/sin préstamos.");
        }
        return isPrestamosBorrados;
    }

    @Override
    public boolean renovarPrestamosSocio(Biblioteca biblioteca, String codigoSocio) throws SocioNoExisteException {
        List<SocioType> listaSocios = biblioteca.getSocios().getSocio();
        boolean isPrestamosRenovados = false;
        boolean isClienteEncontrado = false;

        for (SocioType socio : listaSocios) {
            if (socio.getCodigoSocio().equalsIgnoreCase(codigoSocio)) {
                isClienteEncontrado = true;
                List<PrestamoType> listaPrestamosSocio = socio.getLibrosPrestados().getPrestamo();
                if (!listaPrestamosSocio.isEmpty()) {
                    for (PrestamoType prestamo : listaPrestamosSocio) {
                        XMLGregorianCalendar fechaDevolucion = prestamo.getFechaDevolucion();
                        fechaDevolucion.setDay(fechaDevolucion.getDay() + 40);    // Amplío fecha 14 días
                        prestamo.setFechaDevolucion(fechaDevolucion);
                    }
                    isPrestamosRenovados = true;
                } else {
                    isPrestamosRenovados = false;
                }
            } 
        }

        if (!isClienteEncontrado) {
            throw new SocioNoExisteException("El socio con codigo " + codigoSocio + " no existe");
        }
        return isPrestamosRenovados;
    }
}
