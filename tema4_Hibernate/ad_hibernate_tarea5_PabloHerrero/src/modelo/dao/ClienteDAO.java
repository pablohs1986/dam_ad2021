/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.Date;
import java.util.List;
import modelo.UtilidadFechas;
import modelo.dao.interfaces.interfaceClienteDAO;
import modelo.dto.Cliente;
import modelo.dto.Poliza;
import modelo.dto.Siniestro;
import modelo.dto.Tecnico;
import modelo.dto.Vivienda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo Herrero
 */
public class ClienteDAO extends AbstractDAO implements interfaceClienteDAO {
    
    /**
     * Método que crea una póliza nueva para la vivienda con la id indicada
     * a partir de los datos que recibe como parámetros.
     *
     * @param idVivienda  
     * @param fechaVencimiento  
     * @param precioActual  
     * @param precioRenovacion  
     * @param descuento  
     * @return La póliza creada.
     */
    @Override
    public Poliza contratarPoliza(long idVivienda, Date fechaVencimiento, 
            double precioActual, double precioRenovacion, int descuento) {
        Poliza nuevaPoliza = new Poliza();
        nuevaPoliza.setFechaVencimiento(fechaVencimiento);
        nuevaPoliza.setPrecioActual(precioActual);
        nuevaPoliza.setPrecioRenovacion(precioRenovacion);
        
        Vivienda vivienda = getEntidad(idVivienda, Vivienda.class);
        iniciarOperacion();
        vivienda.setPoliza(nuevaPoliza);
        nuevaPoliza.setVivienda(vivienda);
        actualizarEntidad(vivienda);
        terminarOperacion();
        return nuevaPoliza;
    }
    
    /**
     * Método que crea un nuevo siniestro para la poliza con el id que 
     * se pasa como parámetro.
     *
     * @param idPoliza
     * @return El siniestro creado.
     */
    @Override
    public Siniestro notificarSiniestro(long idPoliza) {
        Poliza poliza = getEntidad(idPoliza, Poliza.class);
        
        iniciarOperacion();
        Siniestro nuevoSiniestro = new Siniestro();
        nuevoSiniestro.setFechaCreacion(new Date());
        nuevoSiniestro.setDescripcion("Vaya avería más gorda!");
        nuevoSiniestro.setFechaVisitaTecnico(UtilidadFechas.generarFecha(2021, 03, 07));
        nuevoSiniestro.setResuelto(false);
        poliza.addSiniestro(nuevoSiniestro);
        actualizarEntidad(poliza);
        terminarOperacion();
        
        return nuevoSiniestro;
    }
    
    /**
     * Método que que asigna un siniestro a un técnico.
     *
     * @param siniestro
     * @param tecnico
     */
    @Override
    public void asignarSiniestroATecnico(Siniestro siniestro, Tecnico tecnico) {
        iniciarOperacion();
        tecnico.addSiniestro(siniestro);
        siniestro.setTecnico(tecnico);
        actualizarEntidad(tecnico);
        actualizarEntidad(siniestro);
        terminarOperacion();
    }
    
    /**
     * Método que asigna y aplica el descuento que se pasa como parámetro
     * a todos los clientes vip de la base de datos, realizando el cálculo
     * del nuevo precio actual.
     *
     * @param descuento
     */
    @Override
    public void aplicarDescuentoClientesVip(int descuento) {
        List<Cliente> clientes = getListaEntidades(Cliente.class);
        
        for (Cliente cliente : clientes) {
            if(cliente.isVip()) {
                List<Vivienda> viviendas = cliente.getViviendas();
                for (Vivienda vivienda : viviendas) {
                    iniciarOperacion();
                    Poliza poliza = vivienda.getPoliza();
                    System.out.println(">>> Precio actual para la póliza " + poliza.getId() 
                            + " perteneciente a la vivienda " + vivienda.getId() 
                            + " del cliente " + cliente.getId() + "-" + cliente.getNombre() + " " 
                            + cliente.getApellido() + ": " + poliza.getPrecioActual());
                    poliza.setDescuento(10);
                    poliza.setPrecioActual(calcularPrecioConDescuento(poliza.getPrecioActual(), descuento));
                    actualizarEntidad(poliza);
                    terminarOperacion();
                    System.out.println(">>> NUEVO precio con un descuento de un " + descuento 
                            + "%: " + poliza.getPrecioActual());
                }
            } else {
                System.out.println("No se han encontrado clientes VIP.");
            }
        }
    }
    
    /**
     * Método que aplica un descuento a un descuento, recibiendo ambos datos 
     * como parámetros y retornando el precio con el descuento aplicado.
     * 
     * @param precioActual
     * @param descuento
     * @return Precio con el descuento aplicado.
     */
    private double calcularPrecioConDescuento(double precioActual, int descuento) {
        return precioActual * ((double) descuento/ 100);
    }
}
