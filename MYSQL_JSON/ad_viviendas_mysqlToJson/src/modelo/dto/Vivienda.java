/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

/**
 *
 * @author Pablo Herrero
 */
public class Vivienda {
    
    private int id;
    private String calle;
    private String cp;
    private double metrosCuadrados;
    private Poliza poliza;

    public Vivienda() {
    }

    public Vivienda(int id, String calle, String cp, double metrosCuadrados) {
        this.id = id;
        this.calle = calle;
        this.cp = cp;
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
    
    

  
}
