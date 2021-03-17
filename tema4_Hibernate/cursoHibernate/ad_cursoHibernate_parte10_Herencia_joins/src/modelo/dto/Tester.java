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
public class Tester extends Tecnologo {
    
    private String herramientaDeTesteo;

    public Tester() {
    }

    public Tester(String herramientaDeTesteo, int aniosDeEstudios, String nombre, int edad) {
        super(aniosDeEstudios, nombre, edad);
        this.herramientaDeTesteo = herramientaDeTesteo;
    }

    public String getHerramientaDeTesteo() {
        return herramientaDeTesteo;
    }

    public void setHerramientaDeTesteo(String herramientaDeTesteo) {
        this.herramientaDeTesteo = herramientaDeTesteo;
    }
    
    
}
