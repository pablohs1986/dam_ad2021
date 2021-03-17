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
@Entity // Indica que la clase es una entidad
public class Terminal implements Serializable {
    
    @Id // Indica que el atributo id es el id de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor del id 
    private long id;
    private String imei;
    private String modelo;
    private String marca;
    
    // Una terminal pertenece a una línea.
    // Hace referencia a la líena a la que pertenece el terminal.
    @OneToOne
    private Linea linea;

    public Terminal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }
    
    
    
}
