/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.Clientes.Clientes;

/**
 *
 * @author Pablo Herrero
 */
public class Metodos implements Interface {
    
    /**
     * Método para unmarshalizar (leer).
     *
     * @param ficheroXML
     * @return JAXBElement
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("jaxb.Clientes");
        Unmarshaller unmarshalizador = contexto.createUnmarshaller();
        JAXBElement elemento = unmarshalizador.unmarshal(new StreamSource(ficheroXML), Clientes.class);
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
    public JsonObject crearDireccion(String calle, int numero, int piso, String escalera, int cp, String ciudad) {
        
        JsonObject direccion = Json.createObjectBuilder()
                .add("calle", calle)
                .add("numero", numero)
                .add("piso", piso)
                .add("escalera", escalera)
                .add("cp", cp)
                .add("ciudad", ciudad)
                .build();
        
        return direccion;
    }

    @Override
    public JsonObject crearCliente(String nombre, String apellido1, String apellido2, 
            List<JsonObject> direcciones, int telefono) {
        
        JsonObject cliente = Json.createObjectBuilder()
                .add("apellido", Json.createArrayBuilder()
                        .add(apellido1)
                        .add(apellido2))
                .add("direccion", pasarListaAJsonArray(direcciones))
                .add("telefono", telefono)
                .add("nombre", nombre)
                .build();
        
        return cliente;
    }
    
    public JsonArray pasarListaAJsonArray(List<JsonObject> listaDirecciones) {
        
        JsonArrayBuilder factoria = Json.createArrayBuilder();
        JsonArray arrayDirecciones;

        for (JsonObject direccion : listaDirecciones) {
            factoria.add(direccion);
        }
        
        arrayDirecciones = factoria.build();
        
        return arrayDirecciones;
    }
    
    public void escribirJsonEnDisco(JsonObject objeto, String nombreArchivoSalida) throws IOException {
        FileWriter ficheroSalida = new FileWriter(nombreArchivoSalida);
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
//        jsonWriter.writeArray(arrayJson);
        jsonWriter.writeObject(objeto);
        ficheroSalida.flush();
        ficheroSalida.close();
        
    }

}
