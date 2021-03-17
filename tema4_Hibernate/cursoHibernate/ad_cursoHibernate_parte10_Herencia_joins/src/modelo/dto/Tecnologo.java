/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dto;

import javax.persistence.Entity;

/**
 *
 * @author pablo
 */
@Entity
public class Tecnologo extends Persona {
    
    private int aniosDeEstudios;

    public Tecnologo() {
    }

    public Tecnologo(int aniosDeEstudios, String nombre, int edad) {
        super(nombre, edad);
        this.aniosDeEstudios = aniosDeEstudios;
    }

    public int getAniosDeEstudios() {
        return aniosDeEstudios;
    }

    public void setAniosDeEstudios(int aniosDeEstudios) {
        this.aniosDeEstudios = aniosDeEstudios;
    }
    
    
}
