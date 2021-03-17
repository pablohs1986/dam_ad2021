/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Llamada implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String telefonoOrigen;
    private String telefonoDestino;
    private int duracionSegundos;
    private Date fechaInicio;

    public Llamada() {
    }

//    public Llamada(String telefonoOrigen, String telefonoDestino, int duracionSegundos, Date fechaInicio) {
//        this.telefonoOrigen = telefonoOrigen;
//        this.telefonoDestino = telefonoDestino;
//        this.duracionSegundos = duracionSegundos;
//        this.fechaInicio = fechaInicio;
//    }

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

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    
    
    
}
