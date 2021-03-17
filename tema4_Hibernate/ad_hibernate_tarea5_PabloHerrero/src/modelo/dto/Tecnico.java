/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Tecnico implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String telefono;
    @OneToMany(mappedBy = "tecnico")
    private List<Siniestro> siniestros = new ArrayList<Siniestro>();

    public Tecnico() {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(List<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }
    
    public void addSiniestro(Siniestro siniestro) {
        siniestros.add(siniestro);
        siniestro.setTecnico(this);
    }
}
