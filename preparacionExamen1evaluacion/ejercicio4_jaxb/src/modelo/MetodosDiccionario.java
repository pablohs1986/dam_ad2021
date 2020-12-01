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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.diccionario.DiccionarioEspanol;
import jaxb.diccionario.PalabraType;
import jaxb.diccionario.SinonimoType;
import jaxb.diccionario.TraduccionType;

/**
 * Clase con diversos métodos para hacer distintas operaciones.
 *
 * @author Pablo Herrero
 */
public class MetodosDiccionario implements InterfaceDiccionario {
    /**
     * Método para unmarshalizar (leer)
     *
     * @param ficheroXML Archivo XML.
     * @throws JAXBException 
     */
    @Override
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException {
        // Creo instancia de JAXBContext que referencia al paquete clases mapeadas.
        JAXBContext contexto = JAXBContext.newInstance("jaxb.diccionario");
        // Creo unmarshalizador
        Unmarshaller unmarshalizador = contexto.createUnmarshaller();
        // Creo elemento que referencia al archivo XML y a la clase madre
        JAXBElement elemento = unmarshalizador.unmarshal(new StreamSource(new File("diccionario.xml")), DiccionarioEspanol.class);
        return elemento;
    }

    /**
     * Método para unmarshalizar (escribir)
     *
     * @param jaxbElement
     * @throws JAXBException
     */
    @Override
    public void marshalizar(JAXBElement jaxbElement) throws JAXBException {
        // Creo contexto
        JAXBContext contexto = JAXBContext.newInstance("jaxb.diccionario");
        // Creo marshalizador
        Marshaller marshalizador = contexto.createMarshaller();
        // Asigno propiedades al marshalizador
        marshalizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshalizador.marshal(jaxbElement, System.out);
    }

    /**
     * Método que cuenta las definiciones para la palabra que recibe.
     *
     * @param diccionario
     * @param palabra Palabra a buscar
     * @return int Número de definiciones
     */
    @Override
    public int contarDefiniciones(DiccionarioEspanol diccionario, PalabraType palabra) {
        int contadorDefiniciones = 0;

        for (PalabraType palabraType : diccionario.getPalabra()) {
            if (palabraType.equals(palabra)) {
                contadorDefiniciones = palabraType.getDefinicion().size();
            }
        }
        return contadorDefiniciones;
    }

    /**
     * Método que borra las traducciones en el idioma buscado y las almacena en un archivo de texto.
     *
     * @param diccionario
     * @param idiomaTraduccionABorrar Idioma en el que buscar las traducciones.
     * @return File Archivo en el que se guardarán las traducciones eliminadas.
     */
    @Override
    public File borrarTraduccionesYAlmacenarEnArchivo(DiccionarioEspanol diccionario, String idiomaTraduccionABorrar, File archivoTraduccionesBorradas) throws IOException {
        FileWriter fw = new FileWriter(archivoTraduccionesBorradas, true);
        BufferedWriter bw = new BufferedWriter(fw);

        for (PalabraType palabra : diccionario.getPalabra()) {
            Iterator<TraduccionType> it = palabra.getTraducciones().getTraduccion().iterator();

            while (it.hasNext()) {
                TraduccionType traduccion = it.next();
                if (traduccion.getIdiomaTraduccion().equals(idiomaTraduccionABorrar)) {
                    bw.write(traduccion.getFonetica());
                    bw.newLine();
                    bw.flush();
                    it.remove();
                }
            }
        }
        bw.close();
        return archivoTraduccionesBorradas;
    }

    /**
     * Método que genera un mapa con el número de traducciones por idioma.
     *
     * @param diccionario
     * @param diccionario 
     * @return Map<String, Integer> Mapa con el número de traducciones por idioma.
     */
    @Override
    public Map<String, Integer> generarMapaNumeroTraduccionesPorIdioma(DiccionarioEspanol diccionario) {
        Map<String, Integer> mapaTraducciones = new HashMap<>();
        for (PalabraType palabra : diccionario.getPalabra()) {
            for (TraduccionType traduccion : palabra.getTraducciones().getTraduccion()) {
                mapaTraducciones.merge(traduccion.getIdiomaTraduccion(), 1, Integer::sum); // Añade el key y el valor si el key no existe. Si existe, suma el valor.
            }
        }
        return mapaTraducciones;
    }

    /**
     * Método que genera un mapa con una lista de sinónimos y definiciones
     * para una palabra dada.
     *
     * @param diccionario
     * @param palabraABuscar
     * @return Map<String, List<String>> Mapa con una lista de sinónimos y
     * definiciones para una palabra dada.
     */
    @Override
    public Map<String, List<String>> generarMapaDeSinonimosYDefinicionesDePalabra(DiccionarioEspanol diccionario, String palabraABuscar) {
        Map<String, List<String>> mapaSinonimosDefinicionesDePalabra = new HashMap<>();
        List<String> listaSinonimosDefiniciones = new ArrayList<>();

        for (PalabraType palabra : diccionario.getPalabra()) {
            if(palabra.getGrafia() == palabraABuscar){
                for (SinonimoType sinonimo : palabra.getSinonimos().getSinonimo()) {
                    listaSinonimosDefiniciones.add(sinonimo.getGrafia());
                }
                for (String definicion : palabra.getDefinicion()) {
                    listaSinonimosDefiniciones.add(definicion);
                }
            }
        }
        mapaSinonimosDefinicionesDePalabra.put(palabraABuscar, listaSinonimosDefiniciones);
        return mapaSinonimosDefinicionesDePalabra;
    }
}
