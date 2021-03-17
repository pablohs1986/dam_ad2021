/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dao.interfaces.InterfaceCitaDAO;
import modelo.dto.Agenda;
import modelo.dto.Cita;
import modelo.dto.Especialista;
import modelo.dto.TarjetaBeneficiario;

/**
 *
 * @author Pablo Herrero
 */
public class CitaDAO extends AbstractDAO implements InterfaceCitaDAO {
    
    /**
     * Método que genera una nueva cita para el día, tramo y tarjeta de beneficiario
     * que se pasa como parámetro. Para ello, comprueba en el listado de tramos
     * y días de la agenda la disponibilidad de los mismos, en caso de ser así
     * asigna la cita a la agenda y a la tarjeta del beneficiario. Retorna un
     * boolean confirmando si se ha creado la tarjeta o no.
     *
     * @param dia
     * @param tramo
     * @param agenda
     * @param tarjeta
     * @return boolean que confirma o no la operación.
     */
    @Override
    public boolean nuevaCita(int dia, int tramo, Agenda agenda, TarjetaBeneficiario tarjeta) {
        iniciarOperacion();
        boolean isCitaAsignada = false;
        Cita nuevaCita = new Cita();
        nuevaCita.setDia(dia);
        nuevaCita.setTramo(tramo);
        
        
        List<Cita> citas = agenda.getCitas();
        for (Cita cita : citas) {
            if(cita.getDia() == nuevaCita.getDia()) {
                isCitaAsignada = false;
            } else {
                if(cita.getTramo() == nuevaCita.getTramo()) {
                    
                } else {
                    agenda.addCitas(cita);
                    tarjeta.addCita(cita);
                }
            }
        }
        actualizarEntidad(tarjeta);
        actualizarEntidad(agenda);
        terminarOperacion();
        return isCitaAsignada;
    }
    
    /**
     * Método que busca un hueco para el especialista que recibe en el 
     * día de su agenda que recibe. Para ello comprueba el número de citas
     * que tiene el día indicado, si es menor que el total de tramos de la 
     * agenda, asigna el siguiente tramo disponible.
     *
     * @param especialista
     * @param dia
     * @return 
     */
    @Override
    public int buscarHueco(Especialista especialista, int dia) {
        Agenda agendaEspecialista = especialista.getAgenda();
        List<Cita> citasEspecialista = agendaEspecialista.getCitas();
        int contadorCitasDiarias = 0;
        boolean isHuecoDisponible = false;
        int ultimoTramoDisponible = 0;
        
        for (Cita cita : citasEspecialista) {
            if(cita.getDia() == dia) {
                contadorCitasDiarias++;
            }
        }
        
        if (contadorCitasDiarias < agendaEspecialista.getTotalTramos()) {
            for (Cita cita : citasEspecialista) {
                if (cita.getDia() == dia) {
                    ultimoTramoDisponible = cita.getTramo();
                    isHuecoDisponible = true;
                }
            }
        } else {
            isHuecoDisponible = false;
        }
        
        if (isHuecoDisponible) {
            ultimoTramoDisponible = ultimoTramoDisponible + 1;
        }
        
        return ultimoTramoDisponible;
        
    }
    
    /**
     * Método que busca el primer hueco disponible para el día indicado y
     * lo asigna a la agenda y tarjeta que se pasan como parámetros.
     *
     * @param dia
     * @param agenda
     * @param tarjeta
     * @return boolean confirmando la operación.
     */
    @Override
    public boolean nuevaCitaRapida(int dia, Agenda agenda, TarjetaBeneficiario tarjeta) {
        iniciarOperacion();
        boolean isCitaAsignada = false;
        int ultimoTramoAsignado;
        int contadorTramos = 0;
        
        Cita nuevaCita = new Cita();
        nuevaCita.setDia(dia);

        List<Cita> citas = agenda.getCitas();
        
        if (citas.size() < agenda.getTotalTramos()) {
            for (Cita cita : citas) {
                contadorTramos++;
                ultimoTramoAsignado = cita.getTramo();
                if(contadorTramos < agenda.getTotalTramos())
                nuevaCita.setTramo(ultimoTramoAsignado+1);
                
                tarjeta.addCita(nuevaCita);
                agenda.addCitas(nuevaCita);
                actualizarEntidad(tarjeta);
                actualizarEntidad(agenda);
                terminarOperacion();
            }
            isCitaAsignada = true;
        } else {
            isCitaAsignada = false;
        }
    
        return isCitaAsignada;
    }
}
