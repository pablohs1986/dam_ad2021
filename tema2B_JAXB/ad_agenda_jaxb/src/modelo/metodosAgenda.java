/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import excepciones.nombreDuplicado;
import java.io.File;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.agenda.Agenda;
import jaxb.agenda.Agenda.Contactos;
import jaxb.agenda.AlarmaType;
import jaxb.agenda.ContactoType;
import jaxb.agenda.CorreoType;
import jaxb.agenda.DiaType;
import jaxb.agenda.HoraType;
import jaxb.agenda.ObjectFactory;

/**
 *
 * @author Pablo Herrero
 */
public class metodosAgenda implements interfaceAgenda {

    /**
     * Método para unmarshalizar (leer).
     *
     * @param ficheroXML
     * @return JAXBElement
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("jaxb.agenda");
        Unmarshaller unmarshalizador = contexto.createUnmarshaller();
        JAXBElement elemento = unmarshalizador.unmarshal(new StreamSource(ficheroXML), Agenda.class);
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
        JAXBContext contexto = JAXBContext.newInstance("jaxb.agenda");
        Marshaller marshalizador = contexto.createMarshaller();
        marshalizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshalizador.marshal(jaxbElement, archivoSalida);
    }

    @Override
    public void aniadirAlarma(Agenda agenda, String asunto, String dia, String hora, String prioridad) {
        // Genero nodo y lista
        Agenda.Alarmas nodoAlarmas = agenda.getAlarmas();
        List<AlarmaType> listadoAlarmas = nodoAlarmas.getAlarma();

        // Creo la alarma
        AlarmaType alarma = new AlarmaType();
        DiaType nuevoDia = new DiaType();
        HoraType nuevaHora = new HoraType();

        // Creo diaHora
        AlarmaType.DiaHora diaHoraDeAlarma = new AlarmaType.DiaHora();

        // Asigno valores al diaHora
        diaHoraDeAlarma.setDia(nuevoDia);
        diaHoraDeAlarma.setHora(nuevaHora);

        // Asigno valores a la nueva alarma
        alarma.setAsunto(asunto);
        alarma.setDiaHora(diaHoraDeAlarma);
        alarma.setPrioridad(prioridad);

        // Añado a la lista
        listadoAlarmas.add(alarma);
    }

    @Override
    public void aniadirTelefonoAContacto(Agenda agenda, String nombre, String telefono) {
        Agenda.Contactos nodoContactos = agenda.getContactos();
        List<ContactoType> listaContactos = nodoContactos.getContacto();

        for (ContactoType contacto : listaContactos) {
            if (contacto.getNombre().getValue().equalsIgnoreCase(nombre)) {
                List<String> listadoTelefonosContacto = contacto.getTelefono();
                listadoTelefonosContacto.add(telefono);
            }
        }
    }

    @Override
    public String resumenCorreos(Agenda agenda) {
        String respuesta = "";
        int totalCorreos = 0;
        Agenda.Correos nodoCorreos = agenda.getCorreos();
        List<CorreoType> listaCorreos = nodoCorreos.getCorreo();

        for (CorreoType correo : listaCorreos) {
            respuesta += "Remitente: " + correo.getRemitente() + " Asunto: " + correo.getAsunto();
        }
        respuesta += "\n Total correos: " + totalCorreos++;
        return respuesta;
    }

    @Override
    public List<String> buscarTelefono(Agenda agenda, String nombreContacto) {
        List<ContactoType> listaContactos = agenda.getContactos().getContacto();
        List<String> telefonosContacto = null;

        for (ContactoType contacto : listaContactos) {
            if (contacto.getNombre().getValue().equalsIgnoreCase(nombreContacto)) {
                telefonosContacto = contacto.getTelefono();
            }
        }
        return telefonosContacto;
    }

    @Override
    public void borrarContacto(Agenda agenda, String nombre) throws nombreDuplicado {
        Contactos nodoContactos = agenda.getContactos();
        List<ContactoType> listaContactos = nodoContactos.getContacto();
        Iterator<ContactoType> it = listaContactos.iterator();
        int coincidenciasNombre = contarCoincidenciasNombre(listaContactos, nombre);
        
        if(coincidenciasNombre == 1) {
            while(it.hasNext()) {
                ContactoType contacto = it.next();
                String nombreContacto = contacto.getNombre().getValue();
                if(nombreContacto.equalsIgnoreCase(nombre)){
                    it.remove();
                }
            }
        } else {
            throw new nombreDuplicado("Nombre duplicado");
        }
        
        
    }
    
    public int contarCoincidenciasNombre(List<ContactoType> listaContactos, String nombre) {
        int coincidenciasNombre = 0;
        
        for (ContactoType contacto : listaContactos) {
            if(contacto.getNombre().getValue().equalsIgnoreCase(nombre)) {
                coincidenciasNombre++;
            }
        }
        
        return coincidenciasNombre;
    }

    @Override
    public HashMap<String, Integer> generarMapaTotalElementos(Agenda agenda) {
        HashMap<String, Integer> mapaTotales = new HashMap<>();
        
        Agenda.Alarmas nodoAlarma = agenda.getAlarmas();
        Agenda.Correos nodoCorreos = agenda.getCorreos();
        Contactos nodoContactos = agenda.getContactos();
        
        int totalAlarmas = 0;
        int totalCorreos = 0;
        int totalContactos = 0;
        
        for (AlarmaType alarma : nodoAlarma.getAlarma()) {
            totalAlarmas++;
        }
        
        for (CorreoType correo : nodoCorreos.getCorreo()) {
            totalCorreos++;
        }
        
        for (ContactoType contacto : nodoContactos.getContacto()) {
            totalContactos++;
        }
        
        mapaTotales.put("Total alarmas: ", totalAlarmas);
        mapaTotales.put("Total correos: ", totalCorreos);
        mapaTotales.put("Total contactos: ", totalContactos);
        
        return mapaTotales;
    }

    @Override
    public int totalAlarmasPendientesPosterioresAFecha(Agenda agenda, int dia, int mes, int anio) {
        int totalAlarmasSuperioresALaFecha = 0;
        // Genero nodo y lista
        Agenda.Alarmas nodoAlarmas = agenda.getAlarmas();
        List<AlarmaType> listadoAlarmas = nodoAlarmas.getAlarma();

        // Creo la alarma
        AlarmaType alarma = new AlarmaType();
        DiaType nuevoDia = new DiaType();
        nuevoDia.setDia(dia);
        nuevoDia.setMes(mes);
        nuevoDia.setAño(anio);
        
        // Creo diaHora
        AlarmaType.DiaHora diaHoraDeAlarma = new AlarmaType.DiaHora();
        diaHoraDeAlarma.setDia(nuevoDia);
        // Pasar a date y comparar
        for (AlarmaType alarm : listadoAlarmas) {
            if(alarm.getDiaHora().getDia().getDia() > dia 
                    && alarm.getDiaHora().getDia().getMes() > mes 
                    && alarm.getDiaHora().getDia().getAño() > anio) {
                totalAlarmasSuperioresALaFecha++;
            }
        }
        return 0;
    }
}
