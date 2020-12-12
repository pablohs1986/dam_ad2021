/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.Albaran.Articulos;
import jaxb.Albaran.Direccion;
import jaxb.Albaran.PedidoType;

/**
 * Interface que declara los métodos a implementar por la clase
 * MetodosDiccionario.
 *
 * @author Pablo Herrero
 */
public interface InterfaceAlbaran {

    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException;

    public void marshalizar(JAXBElement jaxbElement, File archivoSalida) throws JAXBException;

    public void aniadirArticulo(PedidoType pedido, String codigo, String nombreProducto,
            int cantidad, int precio, String comentario, int dia, int mes, int anio) throws Exception;

    public void modificarDireccion(PedidoType pedido, String calle,
            String ciudad, String provincia, String pais, int codigoPostal);

    public double calcularImportePedido(PedidoType pedido);

    public void borrarArticulo(PedidoType pedido, String nombreProducto) throws Exception;
}
