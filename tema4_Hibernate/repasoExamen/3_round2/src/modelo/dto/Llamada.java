/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Pablo Herrero
 */
@Entity // Indica que la clase es una entidad
public class Llamada {
    
    @Id // Indica que el atributo id es el id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor del id 
    private long id;
    private String telefonoOrigen;
    private String telefonoDestino;
    private int duracionEnSegundos;
    private Date fechaInicio;

    public Llamada() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelefonoOrigen() {
        return telefonoOrigen;
    }

    public void setTelefonoOrigen(String telefonoOrigen) {
        this.telefonoOrigen = telefonoOrigen;
    }

    public String getTelefonoDestino() {
        return telefonoDestino;
    }

    public void setTelefonoDestino(String telefonoDestino) {
        this.telefonoDestino = telefonoDestino;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getDuracionEnSegundos() {
        return duracionEnSegundos;
    }

    public void setDuracionEnSegundos(int duracionEnSegundos) {
        this.duracionEnSegundos = duracionEnSegundos;
    }
    
}
