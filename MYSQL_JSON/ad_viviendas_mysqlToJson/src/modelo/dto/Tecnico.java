/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.util.ArrayList;

/**
 *
 * @author Pablo Herrero
 */
public class Tecnico {
    
    private int id;
    private String nombre;
    private String telefono;
    ArrayList<Siniestro> siniestros;

    public Tecnico() {
    }

    public Tecnico(int id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(ArrayList<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }
    
    
}
