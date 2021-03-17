/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

/**
 *
 * @author Pablo Herrero
 */
public class Colegiado {
    
    private int id;
    private int numeroColegiado;
    private String especialidad;
    private String nombre;
    private String apellido;

    public Colegiado() {
    }

    public Colegiado(int numeroColegiado, String especialidad, String nombre, String apellido) {
        this.numeroColegiado = numeroColegiado;
        this.especialidad = especialidad;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(int numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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
    
    
}
