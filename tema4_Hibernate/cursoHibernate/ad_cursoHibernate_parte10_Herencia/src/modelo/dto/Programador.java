/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author pablo
 */
@Entity
@DiscriminatorValue(value = "PG")
public class Programador extends Tecnologo {
 
    private String lenguajeFavorito;
    private int aniosDeExperiencia;

    public Programador() {
    }

    public Programador(String lenguajeFavorito, int aniosDeExperiencia, int aniosDeEstudios, String nombre, int edad) {
        super(aniosDeEstudios, nombre, edad);
        this.lenguajeFavorito = lenguajeFavorito;
        this.aniosDeExperiencia = aniosDeExperiencia;
    }

    public String getLenguajeFavorito() {
        return lenguajeFavorito;
    }

    public void setLenguajeFavorito(String lenguajeFavorito) {
        this.lenguajeFavorito = lenguajeFavorito;
    }

    public int getAniosDeExperiencia() {
        return aniosDeExperiencia;
    }

    public void setAniosDeExperiencia(int aniosDeExperiencia) {
        this.aniosDeExperiencia = aniosDeExperiencia;
    }
    
    
}
