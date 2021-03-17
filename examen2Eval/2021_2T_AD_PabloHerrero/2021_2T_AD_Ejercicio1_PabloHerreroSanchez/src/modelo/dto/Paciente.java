/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pablo Herrero
 */
public class Paciente {
    
    private int id;
    private int numeroSS;
    private String nombre;
    private String apellido;
    private List<Cita> citas = new ArrayList<Cita>();
    private List<Colegiado> colegiados = new ArrayList<Colegiado>();

    public Paciente() {
    }

    public Paciente(int id, int numeroSS, String nombre, String apellido) {
        this.id = id;
        this.numeroSS = numeroSS;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroSS() {
        return numeroSS;
    }

    public void setNumeroSS(int numeroSS) {
        this.numeroSS = numeroSS;
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

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Colegiado> getColegiados() {
        return colegiados;
    }

    public void setColegiados(List<Colegiado> colegiados) {
        this.colegiados = colegiados;
    }
    
    
    
}
