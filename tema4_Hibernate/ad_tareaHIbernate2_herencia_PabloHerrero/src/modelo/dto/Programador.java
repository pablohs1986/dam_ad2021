/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dto;

import javax.persistence.Entity;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Programador extends Tecnologo {
    
    private String lenguajeFavorito;
    private int aniosExperiencia;

    public Programador() {
    }

    public Programador(String lenguajeFavorito, int aniosExperiencia, int aniosDeEstudios, String nombre, int edad) {
        super(aniosDeEstudios, nombre, edad);
        this.lenguajeFavorito = lenguajeFavorito;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getLenguajeFavorito() {
        return lenguajeFavorito;
    }

    public void setLenguajeFavorito(String lenguajeFavorito) {
        this.lenguajeFavorito = lenguajeFavorito;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
    
    
}
