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
@Entity // Porque la clase es una entidad
public class TarjetaBeneficiario implements Serializable {
    
    @Id // Indica que el atributo id es la id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenera el valor del id para la entidad
    private long id;
    private String numeroTarjeta;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String relacionTitular;
    
    // Una o varias tarjetas pertenecen a un titular.
    // Hace referencia al titular de las tarjetas.
    @ManyToOne
    private Titular titular;
    
    // Una tarjeta puede tener varias citas
    // Hace referencia a la citas que posee la tarjeta
    // Casacade ALL para que propague todas las acciones a la entidad relacionada.
    // Fech EAGER para que la carga sea inmediata.
    // mappedBy porque al ser bidireccional, hay que indicar dónde se "almacena"
    // esta entidad en la otra entidad.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tarjeta")
    private List<Cita> citas = new ArrayList<Cita>();

    public TarjetaBeneficiario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
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

    public String getRelacionTitular() {
        return relacionTitular;
    }

    public void setRelacionTitular(String relacionTitular) {
        this.relacionTitular = relacionTitular;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
    
    public void addCita(Cita cita) {
        citas.add(cita);
        cita.setTarjeta(this);
    }
    
    
    
}
