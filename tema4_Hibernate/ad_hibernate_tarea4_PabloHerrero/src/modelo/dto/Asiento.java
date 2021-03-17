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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Asiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
    private int fila;
    private String letra;
    private String tipo;
    @OneToOne
    private Pasajero pasajero; 
    @ManyToOne
    private Vuelo vuelo;
    boolean isOcupado;

    public Asiento() {
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
        this.isOcupado = true;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public boolean isIsOcupado() {
        return isOcupado;
    }

    public void setIsOcupado(boolean isOcupado) {
        this.isOcupado = isOcupado;
    }

    
    
}
