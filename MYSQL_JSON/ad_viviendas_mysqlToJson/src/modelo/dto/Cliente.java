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
public class Cliente {
    
    private int id;
    private String nombre;
    private String apellido;
    private boolean vip;
    private ArrayList<Vivienda> viviendas;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String apellido, boolean vip) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.vip = vip;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public ArrayList<Vivienda> getViviendas() {
        return viviendas;
    }

    public void setViviendas(ArrayList<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }
    
    public void addVivienda(Vivienda vivienda) {
        viviendas.add(vivienda);
    }
    
    
}
