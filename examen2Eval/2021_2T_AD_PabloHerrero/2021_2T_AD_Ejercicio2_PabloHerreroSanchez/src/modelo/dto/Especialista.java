/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity // Porque la clase es una entidad
public class Especialista {
    
    @Id // Indica que el atributo id es la id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenera el valor del id para la entidad
    private long id;
    private String nColegiado;
    private String nombre;
    private String especialidad;

    // Un especialista tiene una agenda
    // Hace referencia a la agenda que posee el especialista.
    // Casacade ALL para que se propaguen las operaciones a la otra entidad.
    // Fetch EAGER para que la carga de la entidad hija sea instantánea.
    // La otra entidad tendrá referencia por ser una relación bidireccional.
    @OneToOne(cascade = CascadeType.ALL)
    private Agenda agenda;
    
    public Especialista() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getnColegiado() {
        return nColegiado;
    }

    public void setnColegiado(String nColegiado) {
        this.nColegiado = nColegiado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    
    
    
}
