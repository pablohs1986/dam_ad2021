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

/**
 *
 * @author Pablo Herrero
 */
@Entity // Es una entidad.
public class Cuenta implements Serializable {
    
    @Id // Id con valor autogenerado sobre el atributo id.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numeroCC;
    private double saldo;
    private String divisa;
    
    // Muchas cuentas son de un cliente.
    // Hace referencia al cliente dueño de la cuenta
    @ManyToOne
    private Cliente cliente;
    
    // Una cuente tiene muchos movimientos. 
    // Hace referencia a los movimientos que tiene la cuenta.
    // Cascade.ALL propaga todas las operaciones
    // a la entidad hija y carga dicha entidad al instante (fetch EAGER)
    // Al ser unidireccional no necesita mappedBy (la otra entidad no tiene
    // referencia de la relación.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Movimiento> movimientos = new ArrayList<Movimiento>();

    public Cuenta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroCC() {
        return numeroCC;
    }

    public void setNumeroCC(String numeroCC) {
        this.numeroCC = numeroCC;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
    
    public void addMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }
}
