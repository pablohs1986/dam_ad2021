/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Aeropuerto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
    private String ciudad;
    @OneToOne
    private Vuelo vueloLlegada;
    @OneToOne
    private Vuelo vueloSalida;

    public Aeropuerto() {
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Vuelo getVueloLlegada() {
        return vueloLlegada;
    }

    public void setVueloLlegada(Vuelo vueloLlegada) {
        this.vueloLlegada = vueloLlegada;
    }

    public Vuelo getVueloSalida() {
        return vueloSalida;
    }

    public void setVueloSalida(Vuelo vueloSalida) {
        this.vueloSalida = vueloSalida;
    }

    
    
    
}
