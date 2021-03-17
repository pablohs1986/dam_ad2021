/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.sql.Date;

/**
 *
 * @author Pablo Herrero
 */
public class Siniestro {
 
    private int id;
    private Date fechaCreacion;
    private String descripcion;
    private Date fechaVisitaTecnico;
    private boolean resuelto;
    private Poliza poliza;
    private Tecnico tecnico;

    public Siniestro() {
    }

    public Siniestro(int id, Date fechaCreacion, String descripcion, Date fechaVisitaTecnico, boolean resuelto) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.fechaVisitaTecnico = fechaVisitaTecnico;
        this.resuelto = resuelto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaVisitaTecnico() {
        return fechaVisitaTecnico;
    }

    public void setFechaVisitaTecnico(Date fechaVisitaTecnico) {
        this.fechaVisitaTecnico = fechaVisitaTecnico;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
    
    
    
    
}
