/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author pablo
 */
@Entity
public class Barco implements Serializable {
    
    @Id
    private String matricula;
    private String nombre;
    private double eslora;
    private double manga;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Amarre amarre;

    public Barco() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEslora() {
        return eslora;
    }

    public void setEslora(double eslora) {
        this.eslora = eslora;
    }

    public double getManga() {
        return manga;
    }

    public void setManga(double manga) {
        this.manga = manga;
    }

    public Amarre getAmarre() {
        return amarre;
    }

    public void amarrar(Amarre amarre) {
        this.amarre = amarre;
        amarre.setBarcoAmarrado(this);
        amarre.setOcupado(true);
    }

    
}
