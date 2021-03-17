/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.agenda.Agenda;

/**
 *
 * @author Pablo Herrero
 */
public interface interfaceAgenda {
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException;

    public void marshalizar(JAXBElement jaxbElement, File archivoSalida) throws JAXBException;
    
    public void aniadirAlarma(Agenda agenda, String asunto, String dia, String hora, String prioridad);
    
    public void aniadirTelefonoAContacto(Agenda agenda, String nombre, String telefono);
    
    public String resumenCorreos(Agenda agenda);
    
    public List<String> buscarTelefono(Agenda agenda, String nombreContacto);
    
    public void borrarContacto(Agenda agenda, String nombre) throws Exception;
    
    public HashMap<String, Integer> generarMapaTotalElementos(Agenda agenda);
    
    public int totalAlarmasPendientesPosterioresAFecha(Agenda agenda, int dia, int mes, int anio);
}
