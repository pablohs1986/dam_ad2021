/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Herrero
 */
@Entity
public class Terminal implements Serializable {
    
    @Id
    private String imeiTerminal;
    private String modelo;
    private String marca;
    
    @OneToOne
    private SIMLinea linea;

    public Terminal() {
    }

//    public Terminal(String imeiTerminal, String modelo, String marca) {
//        this.imeiTerminal = imeiTerminal;
//        this.modelo = modelo;
//        this.marca = marca;
//    }

    public String getImeiTerminal() {
        return imeiTerminal;
    }

    public void setImeiTerminal(String imeiTerminal) {
        this.imeiTerminal = imeiTerminal;
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

    public SIMLinea getLinea() {
        return linea;
    }

    public void setLinea(SIMLinea linea) {
        this.linea = linea;
    }

   

    
    
}
