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
public class Cita {
    
    private int id;
    private int dia;
    private int tramo;
    private int numeroColegiado;

    public Cita() {
    }

    public Cita(int dia, int tramo, int numeroColegiado) {
        this.dia = dia;
        this.tramo = tramo;
        this.numeroColegiado = numeroColegiado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getTramo() {
        return tramo;
    }

    public void setTramo(int tramo) {
        this.tramo = tramo;
    }

    public int getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(int numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }
    
    
}
