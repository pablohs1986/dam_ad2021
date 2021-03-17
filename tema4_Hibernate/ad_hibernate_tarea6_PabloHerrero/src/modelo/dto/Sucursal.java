/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

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
@Entity // Es una entidad.
public class Sucursal {
    
    @Id // Id con valor autogenerado sobre el atributo id.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numero;
    private String telefono;
    private String eMail;
    private String fax;
    
    // Una sucursal puede tener varios clientes.
    // Hace referencia a los clientes que tiene la sucursal.
    // Cascade.ALL propaga todas las operaciones a las entidades hijas.
    // MappedBy representa qué atributo representa a Sucursal en la otra entidad
    // siendo necesario por ser bidireccional y la otra entidad tener constancia de la relación.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sucursal")
    private List<Cliente> clientes = new ArrayList<Cliente>();
    
    public Sucursal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
        cliente.setSucursal(this);
    }
}
