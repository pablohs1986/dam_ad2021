/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity // Es una entidad.
public class Direccion implements Serializable {
 
    @Id // Id con valor autogenerado sobre el atributo id.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String calle;
    private String cp;
    private String ciudad;
    private String provincia;
    
    // Hace referencia a dirección asociada a una sucursal
    // No aplico cascade, no quiero que la sucursal deje de existir
    // si elimino la dirección.
    // La otra entidad no tiene ninguna referencia por ser unidireccional.
    @OneToOne
    private Sucursal sucursal;

    public Direccion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    
}
