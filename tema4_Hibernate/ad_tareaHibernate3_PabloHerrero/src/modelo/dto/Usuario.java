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
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Usuario implements Serializable {
    
    @Id
    private String dni;
    private String nombre;
    private String apellido;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = ("usuario"))
    private List<SIMLinea> lineas = new ArrayList<SIMLinea>();

    public Usuario() {
    }

//    public Usuario(String dni, String nombre, String apellido) {
//        this.dni = dni;
//        this.nombre = nombre;
//        this.apellido = apellido;
//    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<SIMLinea> getLineas() {
        return lineas;
    }

    public void setLineas(List<SIMLinea> lineas) {
        this.lineas = lineas;
    }
    
    public void addLinea(SIMLinea linea) {
        lineas.add(linea);
    }
}
