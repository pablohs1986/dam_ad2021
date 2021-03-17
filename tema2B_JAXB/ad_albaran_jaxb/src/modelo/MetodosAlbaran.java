/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import excepcionesAlbaran.ErrorFecha;
import java.io.File;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;
import jaxb.Albaran.Articulos;
import jaxb.Albaran.Articulos.Articulo;
import jaxb.Albaran.Direccion;
import jaxb.Albaran.PedidoType;
import excepcionesAlbaran.*;

/**
 * Clase con la implementación de los métodos de InterfaceAlbaran
 *
 * @author Pablo Herrero
 */
public class MetodosAlbaran implements InterfaceAlbaran {

    /**
     * Método para unmarshalizar (leer).
     *
     * @param ficheroXML
     * @return JAXBElement
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("jaxb.Albaran");
        Unmarshaller unmarshalizador = contexto.createUnmarshaller();
        JAXBElement elemento = unmarshalizador.unmarshal(new StreamSource(ficheroXML), PedidoType.class);
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
        JAXBContext contexto = JAXBContext.newInstance("jaxb.Albaran");
        Marshaller marshalizador = contexto.createMarshaller();
        marshalizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshalizador.marshal(jaxbElement, archivoSalida);
    }

    /**
     * Método que añade un nuevo artículo.
     *
     * @param pedido
     * @param codigo
     * @param nombreProducto
     * @param cantidad
     * @param precio
     * @param comentario
     * @param dia
     * @param mes
     * @param anio
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public void aniadirArticulo(PedidoType pedido, String codigo, String nombreProducto,
            int cantidad, int precio, String comentario, int dia, int mes, int anio) throws Exception {
        Articulos.Articulo articuloNuevo = new Articulos.Articulo();
        articuloNuevo.setCodigo(codigo);
        articuloNuevo.setNombreProducto(nombreProducto);
        articuloNuevo.setCantidad(cantidad);
        articuloNuevo.setPrecio(new BigDecimal(precio));
        articuloNuevo.setComentario(codigo);
        try {
            if (isFechaValida(dia, mes, anio)) {
                XMLGregorianCalendar fechaEnvio = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                fechaEnvio.setDay(dia);
                fechaEnvio.setMonth(mes);
                fechaEnvio.setYear(anio);
                articuloNuevo.setFechaEnvio(fechaEnvio);
            }
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(MetodosAlbaran.class.getName()).log(Level.SEVERE, null, ex);
        }

        Articulos nodoArticulo = pedido.getArticulos();
        List<Articulos.Articulo> listadoArticulos = nodoArticulo.getArticulo();
        listadoArticulos.add(articuloNuevo);
    }

    /**
     * Método para modificar la dirección del pedido.
     *
     * @param pedido
     * @param calle
     * @param ciudad
     * @param provincia
     * @param pais
     * @param codigoPostal
     */
    @Override
    public void modificarDireccion(PedidoType pedido, String calle,
            String ciudad, String provincia, String pais, int codigoPostal) {

        Direccion direccion = pedido.getFacturarA();
        direccion.setCalle(calle);
        direccion.setCiudad(ciudad);
        direccion.setProvincia(provincia);
        direccion.setPais(pais);
        direccion.setCodigoPostal(new BigDecimal(codigoPostal));
    }

    /**
     * Método que calcula el importe total del pedido.
     *
     * @param pedido
     * @return
     */
    @Override
    public double calcularImportePedido(PedidoType pedido) {
        double importePedido = 0;
        Articulos nodoArticulos = pedido.getArticulos();

        List<Articulo> listaArticulos = nodoArticulos.getArticulo();

        for (Articulo articulo : listaArticulos) {
            importePedido += Double.parseDouble(articulo.getPrecio().toString());
        }

        return importePedido;
    }

    /**
     * Método que borra un artículo.
     *
     * @param pedido
     * @param nombreProducto
     * @throws java.lang.Exception
     */
    @Override
    public void borrarArticulo(PedidoType pedido, String nombreProducto) throws Exception {
        Articulos nodoArticulos = pedido.getArticulos();
        List<Articulo> listaArticulos = nodoArticulos.getArticulo();
        Iterator<Articulo> it = listaArticulos.iterator();
        boolean flagArticuloEncontrado = false;

        while (it.hasNext()) {
            Articulos.Articulo articulo = it.next();
            String nombreArticulo = articulo.getNombreProducto();
            if (articulo.getNombreProducto().equalsIgnoreCase(nombreProducto)) {
                System.out.println("Eliminado el producto \"" + articulo.getNombreProducto() + "\"");
                it.remove();
                flagArticuloEncontrado = true;
            }
        }

        if (!flagArticuloEncontrado) {
            throw new ArticuloNoExiste("El producto indicado no existe. Operación cancelada");
        }
    }

    /**
     * Método que valida una fecha.
     *
     * @param dia
     * @param mes
     * @param anio
     * @return
     * @throws java.lang.Exception
     */
    private boolean isFechaValida(int dia, int mes, int anio) throws Exception {
        boolean flag = false;
        if ((dia >= 1 && dia <= 31) && (mes >= 1 && mes <= 12) && (anio >= 1989 && anio <= 2030)) {
            flag = true;
        } else {
            throw new ErrorFecha("La fecha no es válida. Operación cancelada");
        }
        return flag;
    }
}
