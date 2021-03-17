/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Linea implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numeroTelefono;
    private double datosDisponibles;
    private int minutosConsumidos;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Llamada> llamadas = new ArrayList<Llamada>();
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE}) 
    private Terminal terminal;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "linea")
    private List<Tarifa> tarifas = new ArrayList<Tarifa>();
    @ManyToOne
    private Usuario usuario;

    public Linea() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public double getDatosDisponibles() {
        return datosDisponibles;
    }

    public void setDatosDisponibles(double datosDisponibles) {
        this.datosDisponibles = datosDisponibles;
    }

    public int getMinutosConsumidos() {
        return minutosConsumidos;
    }

    public void setMinutosConsumidos(int minutosConsumidos) {
        this.minutosConsumidos = minutosConsumidos;
    }

    public List<Llamada> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(List<Llamada> llamadas) {
        this.llamadas = llamadas;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
        terminal.setLinea(this);
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void addLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }
    
    public void addTarifa(Tarifa tarifa) {
        tarifas.add(tarifa);
        tarifa.setLinea(this);
    }
    
}
