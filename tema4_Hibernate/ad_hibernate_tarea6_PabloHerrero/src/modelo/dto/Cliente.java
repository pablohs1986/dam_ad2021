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
public class Cliente implements Serializable {
    
    @Id // Id con valor autogenerado sobre el atributo id.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    
    // Un cliente tiene muchas cuentas. 
    // Hace referencia a las cuentas que tiene el cliente.
    // Cascade.ALL propaga todas las operaciones
    // a la entidad hija y carga dicha entidad al instante (fetch EAGER)
    // MappedBy representa qué atributo representa a Cliente en la otra entidad 
    // (necesario por ser bidireccional, la otra entidad tiene referencia de la relación)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cliente") 
    private List<Cuenta> cuentas = new ArrayList<Cuenta>();
    
    // Muchos clientes pertenecen a una sucursal.
    // Hace referencia a la sucursal al a que pertenece el cliente.
    @ManyToOne
    private Sucursal sucursal;

    public Cliente() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    
    public void addCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        cuenta.setCliente(this);
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
}
