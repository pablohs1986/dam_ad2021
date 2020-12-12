/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_albaped;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import jaxb.albaran.Articulos;
import jaxb.albaran.PedidoType;

/**
 * Ejemplos de operaciones con albaranes
 *
 * @author Jose Luis Arias
 */
public class OperacionesAlbaran {

    /**
     * Ejemplo añadir artículo
     * @param pedidoType
     * @param articulo
     *
     */
    protected static void aniadirArticulo(PedidoType pedidoType, Articulos.Articulo articulo) {
        Articulos articulos = pedidoType.getArticulos();
        List<Articulos.Articulo> listaArticulos = articulos.getArticulo();
        try {
            listaArticulos.add(articulo);
        } catch (UnsupportedOperationException e) {
            System.err.println("UnsupportedOperationException");
        } catch (NullPointerException e) {
            System.err.println("NullPointerException");
        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException");
        }
    }

    /** Ejemplo añadir artículo2
     * Añade un articulo a un pedido
     * @param pedidoType
     * @param cantidad
     * @param codigo
     * @param nombre
     * @param precio
     * @param anio
     * @param mes
     * @param dia
     */
    protected static void aniadirArticulo(PedidoType pedidoType, int cantidad,
            String codigo, String nombre, int precio, int anio, int mes, int dia) {

        Articulos.Articulo articuloNuevo = new Articulos.Articulo();
        articuloNuevo.setCantidad(cantidad);
        articuloNuevo.setCodigo(codigo);
        articuloNuevo.setNombreProducto(nombre);
        articuloNuevo.setPrecio(new BigDecimal(precio));
        try {
            XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            fecha.setYear(anio);
            fecha.setMonth(mes);
            fecha.setDay(dia);
            articuloNuevo.setFechaEnvio(fecha);
        } catch (DatatypeConfigurationException ex) {
            System.err.println("DatatypeConfigurationException");
        }
        Articulos articulos = pedidoType.getArticulos();
        List<Articulos.Articulo> listaArticulos = articulos.getArticulo();
        try {
            listaArticulos.add(articuloNuevo);
        } catch (UnsupportedOperationException e) {
            System.err.println("UnsupportedOperationException");
        } catch (NullPointerException e) {
            System.err.println("NullPointerException");
        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException");
        }
    }
}
