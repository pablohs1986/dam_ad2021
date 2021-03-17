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
public class Normal extends Persona {
    
    private String ocupacion;

    public Normal() {
    }

    public Normal(String ocupacion, String nombre, int edad) {
        super(nombre, edad);
        this.ocupacion = ocupacion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    
    
}
