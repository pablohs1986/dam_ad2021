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
@Entity // Indica que la clase es una entidad
public class Tarifa implements Serializable {
    
    @Id // Indica que el atributo id es el id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor del id 
    private long id;
    private String nombreTarifa;
    private double gigasMaxDatos;
    private int minutosMaxLlamadas;
    
    // Una o varias tarifas pueden pertenecer a una línea.
    // Hace referencia a la línea a la que pertenece la tarifa.
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

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }
    
    
}
