/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Pasajero implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
    private String dni;
    private String nombre;
    private Date fechaNacimiento;
    private boolean isNinio;
    @ManyToOne
    private Billete billete;
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE}) // el asiento se crea en cascada
    private Asiento asiento;

    public Pasajero() {
    }

    public long getId() {
        return codigo;
    }

    public void setId(long id) {
        this.codigo = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Billete getBillete() {
        return billete;
    }

    public void setBillete(Billete billete) {
        this.billete = billete;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public boolean isIsNinio() {
        return isNinio;
    }

    public void setIsNinio(boolean isNinio) {
        this.isNinio = isNinio;
    }
    
    
    
}
