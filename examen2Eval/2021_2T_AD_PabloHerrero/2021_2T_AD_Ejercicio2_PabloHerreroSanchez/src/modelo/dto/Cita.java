/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity // Porque la clase es una entidad
public class Cita implements Serializable {
    
    @Id // Indica que el atributo id es la id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenera el valor del id para la entidad
    private long id;
    private int dia;
    private int tramo;
    
    // Una o varias citas pertenecen a una tarjeta.
    // Hace referencia a la tarjeta a la que pertenecen dichas citas.
    @ManyToOne
    private TarjetaBeneficiario tarjeta;
    
    // Una o varias citas pertenecen a una agenda.
    // Hace referencia a la agenda a la que pertenecen dichas citas.
    @ManyToOne
    private Agenda agenda;

    public Cita() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getTramo() {
        return tramo;
    }

    public void setTramo(int tramo) {
        this.tramo = tramo;
    }

    public TarjetaBeneficiario getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaBeneficiario tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    
    
}
