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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Billete implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
    private Date fechaEmite;
    private double precioPasajero;
    private int descuentoNinios;
    private double precioFinal;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "billete")
    private List<Pasajero> pasajeros = new ArrayList<Pasajero>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Vuelo vuelo;
        
    public Billete() {
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaEmite() {
        return fechaEmite;
    }

    public void setFechaEmite(Date fechaEmite) {
        this.fechaEmite = fechaEmite;
    }

    public double getPrecioPasajero() {
        return precioPasajero;
    }

    public void setPrecioPasajero(double precioPasajero) {
        this.precioPasajero = precioPasajero;
    }

    public int getDescuentoNinios() {
        return descuentoNinios;
    }

    public void setDescuentoNinios(int descuentoNinios) {
        this.descuentoNinios = descuentoNinios;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(List<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
    public void addPasajero(Pasajero pasajero) {
        pasajeros.add(pasajero);
        pasajero.setBillete(this);
    }
    
    
}
