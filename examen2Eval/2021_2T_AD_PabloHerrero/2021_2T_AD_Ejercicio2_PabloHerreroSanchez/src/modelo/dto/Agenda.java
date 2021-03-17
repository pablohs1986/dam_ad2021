/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity // Porque la clase es una entidad
public class Agenda implements Serializable {
    
    @Id // Indica que el atributo id es la id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenera el valor del id para la entidad
    private long id;
    private String nombre;
    private int horaInicio;
    private int totalTramos;
    
    // Una agenda puede tener varias citas
    // Hace referencia a la citas que posee la agenda
    // Casacade ALL para que propague todas las acciones a la entidad relacionada.
    // Fech EAGER para que la carga sea inmediata.
    // mappedBy porque al ser bidireccional, hay que indicar dónde se "almacena"
    // esta entidad en la otra entidad.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tarjeta")
    private List<Cita> citas = new ArrayList<Cita>();
    
    // Una agenda pertenece a un especialista.
    // Hace referencia al especialista al que pertenece la agenda.
    @OneToOne
    private Especialista especialista;
    
    public Agenda() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getTotalTramos() {
        return totalTramos;
    }

    public void setTotalTramos(int totalTramos) {
        this.totalTramos = totalTramos;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
    
    public void addCitas(Cita cita) {
        citas.add(cita);
        cita.setAgenda(this);
    }

    public Especialista getEspecialista() {
        return especialista;
    }

    public void setEspecialista(Especialista especialista) {
        this.especialista = especialista;
    }
    
    
}
