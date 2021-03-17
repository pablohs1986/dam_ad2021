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
import javax.persistence.OneToMany;

/**
 *
 * @author Pablo Herrero
 */
@Entity // Indica que la clase es una entidad
public class Usuario implements Serializable {
    
    @Id // Indica que el atributo id es el id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor del id 
    private long id;
    private String dni;
    private String nombre;
    private String apellido;

    // Un usuario puede tener varias líneas.
    // Hace referencia a las líneas que posee el usuario.
    // Cascade ALL porque me interesa que si se elimina un usuario, se eliminen las líneas.
    // Fetch eager para que la carga sea instantánea.
    // El mappedBy también es necesario por ser una relación bidireccional.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    private List<Linea> lineas = new ArrayList<Linea>();
    
    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }
    
    public void addLinea(Linea linea) {
        lineas.add(linea);
        linea.setUsuario(this);
    }
}
