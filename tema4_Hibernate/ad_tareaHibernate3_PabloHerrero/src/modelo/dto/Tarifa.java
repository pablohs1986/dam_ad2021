/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Tarifa implements Serializable {
    
    @Id
    private String nombreTarifa;
    private double gigasMaxDatos;
    private int minutosMaxLlamadas;
    @ManyToOne
    private SIMLinea linea;

    public Tarifa() {
    }

//    public Tarifa(String nombreTarifa, double gigasMaxDatos, int minutosMaxLlamadas) {
//        this.nombreTarifa = nombreTarifa;
//        this.gigasMaxDatos = gigasMaxDatos;
//        this.minutosMaxLlamadas = minutosMaxLlamadas;
//    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public double getGigasMaxDatos() {
        return gigasMaxDatos;
    }

    public void setGigasMaxDatos(double gigasMaxDatos) {
        this.gigasMaxDatos = gigasMaxDatos;
    }

    public int getMinutosMaxLlamadas() {
        return minutosMaxLlamadas;
    }

    public void setMinutosMaxLlamadas(int minutosMaxLlamadas) {
        this.minutosMaxLlamadas = minutosMaxLlamadas;
    }

    public SIMLinea getLinea() {
        return linea;
    }

    public void setLinea(SIMLinea linea) {
        this.linea = linea;
    }
    
    
    
    
    
}
