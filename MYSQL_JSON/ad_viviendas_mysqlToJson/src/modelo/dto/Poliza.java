/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Pablo Herrero
 */
public class Poliza {
    
    private int id;
    private Date fechaVencimiento;
    private double precioActual;
    private double precioRenovacion;
    private int descuento;
    private Vivienda vivienda;
    private ArrayList<Siniestro> siniestros;

    public Poliza() {
    }

    public Poliza(int id, Date fechaVencimiento, double precioActual, double precioRenovacion, int descuento) {
        this.id = id;
        this.fechaVencimiento = fechaVencimiento;
        this.precioActual = precioActual;
        this.precioRenovacion = precioRenovacion;
        this.descuento = descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

   

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public double getPrecioRenovacion() {
        return precioRenovacion;
    }

    public void setPrecioRenovacion(double precioRenovacion) {
        this.precioRenovacion = precioRenovacion;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public ArrayList<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(ArrayList<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }
    
    
    
}
