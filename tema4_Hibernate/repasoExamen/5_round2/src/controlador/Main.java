/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;
import modelo.UtilidadFechas;
import modelo.dao.AbstractDAO;
import modelo.dto.Cliente;
import modelo.dto.Poliza;
import modelo.dto.Siniestro;
import modelo.dto.Tecnico;
import modelo.dto.Vivienda;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ////// PERSISTENCIA //////
        // Creo clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Cliente1");
        cliente1.setApellido("Apellido1");
        cliente1.setVip(true);
        
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Cliente2");
        cliente2.setApellido("Apellido2");
        cliente2.setVip(false);
        
        // Creo viviendas
        Vivienda vivienda1 = new Vivienda();
        vivienda1.setCalle("Calle1");
        vivienda1.setCp("33001");
        vivienda1.setMetrosCuadrados(100);
        
        Vivienda vivienda2 = new Vivienda();
        vivienda2.setCalle("Calle2");
        vivienda2.setCp("33002");
        vivienda2.setMetrosCuadrados(140);
        
        // Asigno viviendas a clientes
        cliente1.addVivienda(vivienda2);
        cliente2.addVivienda(vivienda1);
        
        // Creo pólizas
        Poliza poliza1 = new Poliza();
        poliza1.setFechaVencimiento(UtilidadFechas.generarFecha(2021, 8, 20));
        poliza1.setPrecioActual(200);
        poliza1.setPrecioRenovacion(200);
        poliza1.setDescuento(0);
       
        Poliza poliza2 = new Poliza();
        poliza2.setFechaVencimiento(UtilidadFechas.generarFecha(2021, 10, 8));
        poliza2.setPrecioActual(300);
        poliza2.setPrecioRenovacion(300);
        poliza2.setDescuento(0);
        
        // Asigno pólizas a viviendas y viceversa
        vivienda1.setPoliza(poliza1);
        vivienda2.setPoliza(poliza2);
        poliza1.setVivienda(vivienda1);
        poliza2.setVivienda(vivienda2);
        
        // Creo siniestros
        Siniestro siniestro1 = new Siniestro();
        siniestro1.setFechaCreacion(new Date());
        siniestro1.setDescripcion("Descripción siniestro 1");
        siniestro1.setFechaVisitaTecnico(UtilidadFechas.generarFecha(2021, 3, 14));
        siniestro1.setResuelto(false);
        
        Siniestro siniestro2 = new Siniestro();
        siniestro2.setFechaCreacion(new Date());
        siniestro2.setDescripcion("Descripción siniestro 2");
        siniestro2.setFechaVisitaTecnico(UtilidadFechas.generarFecha(2021, 3, 12));
        siniestro2.setResuelto(false);
        
        // Asigno siniestros a pólizas
        poliza1.addSiniestro(siniestro2);
        poliza2.addSiniestro(siniestro1);
        
        // Creo técnicos y los persisto
        Tecnico tecnico1 = new Tecnico();
        tecnico1.setNombre("Tecnico1");
        tecnico1.setTelefono("111111111");

        Tecnico tecnico2 = new Tecnico();
        tecnico2.setNombre("Tecnico2");
        tecnico2.setTelefono("222222222");
        
        AbstractDAO.almacenarEntidad(tecnico1);
        AbstractDAO.almacenarEntidad(tecnico2);
        
        // Asigno siniestros a técnicos
        tecnico1.addSiniestro(siniestro1);
        tecnico2.addSiniestro(siniestro2);
        
        // Persisto
        AbstractDAO.almacenarEntidad(cliente1);
        AbstractDAO.almacenarEntidad(cliente2);
    }
    
}
