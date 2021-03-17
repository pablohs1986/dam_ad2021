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
@Entity // Indica que la clase es una entidad
public class Linea implements Serializable {
    
    @Id // Indica que el atributo id es el id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor del id 
    private long id;
    private String numero;
    private double datosDisponibles;
    private int minutosConsumidos;

    // Una o varias líneas pueden pertenecer a un usuario.
    // Hace referencia al usuario al que pertenece la línea.
    @ManyToOne
    private Usuario usuario;
    
    // Una línea puede tener una o varias tarifas asociadas.
    // Hace referencia a las tarifas que tiene la línea.
    // Fetch para que la carga de la entidad hija sea instantánea.
    // MappedBy porque la otra entidad necesita tener referencia de la relación
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "linea")
    private List<Tarifa> tarifas = new ArrayList<Tarifa>();
    
    // Una línea puede tener un terminal.
    // Hace referencia al terminal que tiene el terminal.
    // Cascade ALL porque me interesa que si desaparece la línea, desaparezca el terminal.
    // Al ser bidireccional, la otra entidad tiene referencia.
    @OneToOne(cascade = CascadeType.ALL)
    private Terminal terminal;
    
    // Una línea puede tener varias llamadas.
    // Hace referencia a las llamadas que tiene la línea (las que hace).
    // Cascade ALL para que todas las operaciones se propagen de la entidad madre a la hija.
    // Fetch eager para que la entidad hija se cargue al instante.
    // Al ser unidireccional, la otra entidad no tiene referencia de la relación.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Llamada> llamadas = new ArrayList<Llamada>();
    
    public Linea() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
    
    public void addTarifa(Tarifa tarifa) {
        tarifas.add(tarifa);
        tarifa.setLinea(this);
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
        terminal.setLinea(this);
    }

    public List<Llamada> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(List<Llamada> llamadas) {
        this.llamadas = llamadas;
    }
    
    public void addLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }
    
}
