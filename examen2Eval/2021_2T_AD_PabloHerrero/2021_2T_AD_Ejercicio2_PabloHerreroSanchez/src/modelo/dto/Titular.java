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

/**
 *
 * @author Pablo Herrero
 */
@Entity // Porque la clase es una entidad
public class Titular implements Serializable {
    
    @Id // Indica que el atributo id es la id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenera el valor del id para la entidad
    private long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    
    // Un titular puede tener varias tarjetas
    // Hace referencia a la tarjetas que posee el titular
    // Casacade ALL para que propague todas las acciones a la entidad relacionada.
    // Fech EAGER para que la carga sea inmediata.
    // mappedBy porque al ser bidireccional, hay que indicar dónde se "almacena"
    // esta entidad en la otra entidad.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "titular")
    private List<TarjetaBeneficiario> tarjetas = new ArrayList<TarjetaBeneficiario>();

    public Titular() {
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<TarjetaBeneficiario> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaBeneficiario> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
    public void addTarjeta(TarjetaBeneficiario tarjeta) {
        tarjetas.add(tarjeta);
        tarjeta.setTitular(this);
    }
}
