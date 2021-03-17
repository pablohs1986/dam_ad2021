/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.interfaces;

import java.util.Date;
import modelo.dto.Poliza;
import modelo.dto.Siniestro;
import modelo.dto.Tecnico;

/**
 *
 * @author Pablo Herrero
 */
public interface interfaceClienteDAO {
    
    public Poliza contratarPoliza(long idVivienda, Date fechaVencimiento,
            double precioActual, double precioRenovacion, int descuento);
    
    public Siniestro notificarSiniestro(long idPoliza);
    
    public void asignarSiniestroATecnico(Siniestro siniestro, Tecnico tecnico);
    
    public void aplicarDescuentoClientesVip(int descuento);
    
}
