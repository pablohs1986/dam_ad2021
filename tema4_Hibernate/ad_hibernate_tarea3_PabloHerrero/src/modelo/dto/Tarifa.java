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
@Entity
public class Tarifa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private double gigasMaxDatos;
    private int minutosMaxLlamadas;
    @ManyToOne
    private Linea linea;

    public Tarifa() {
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

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }
    
    
    
}
