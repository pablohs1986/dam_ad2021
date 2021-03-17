/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Poliza implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date fechaVencimiento;
    private double precioActual;
    private double precioRenovacion;
    private int descuento;

    @OneToOne
    private Vivienda vivienda;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poliza")
    private List<Siniestro> siniestros = new ArrayList<Siniestro>();
    
    public Poliza() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(List<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }
    
    public void addSiniestro(Siniestro siniestro) {
        siniestros.add(siniestro);
        siniestro.setPoliza(this);
    }
}
