/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.diccionario.DiccionarioEspanol;
import jaxb.diccionario.PalabraType;

/**
 * Interface con los métodos a implementar
 *
 * @author Pablo Herrero
 */
public interface InterfaceDiccionario {

    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException;

    public void marshalizar(JAXBElement jaxbElement) throws JAXBException;

    public int contarDefiniciones(DiccionarioEspanol diccionario, PalabraType palabra);

    public File borrarTraduccionesYAlmacenarEnArchivo(DiccionarioEspanol diccionario, String idiomaTraduccionABorrar, File archivoTraduccionesBorradas) throws IOException;

    public Map<String, Integer> generarMapaNumeroTraduccionesPorIdioma(DiccionarioEspanol diccionario);
    
    public Map<String, List<String>> generarMapaDeSinonimosYDefinicionesDePalabra(DiccionarioEspanol diccionario, String palabraABuscar);
}
