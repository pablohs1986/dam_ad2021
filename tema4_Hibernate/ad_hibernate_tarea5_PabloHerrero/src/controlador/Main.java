/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;
import modelo.UtilidadFechas;
import modelo.dao.AbstractDAO;
import modelo.dao.ClienteDAO;
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
        // Creo viviendas
        Vivienda vivienda1 = new Vivienda();
        vivienda1.setCalle("Calle1");
        vivienda1.setCp("33006");
        vivienda1.setMetrosCuadrados(80.00);
        
        // Creo clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Cliente1");
        cliente1.setApellido("Apellido1");
        cliente1.setVip(true);
        
        // Asigno viviendas a clientes
        cliente1.addVivienda(vivienda1);
        
        // Creo pólizas
        Poliza poliza1 = new Poliza();
        poliza1.setFechaVencimiento(UtilidadFechas.generarFecha(2021, 10, 10));
        poliza1.setPrecioActual(200.00);
        poliza1.setPrecioRenovacion(205.00);
        poliza1.setDescuento(0);
        
        // Asigno pólizas a viviendas y viceversa
        vivienda1.setPoliza(poliza1);
        poliza1.setVivienda(vivienda1);
        
        // Creo siniestros
        Siniestro siniestro1 = new Siniestro();
        siniestro1.setFechaCreacion(new Date());
        siniestro1.setDescripcion("Descripción del siniestro1");
        siniestro1.setFechaVisitaTecnico(UtilidadFechas.generarFecha(2021, 03, 10));
        siniestro1.setResuelto(false);
        
        // Asigno siniestros a pólizas
        poliza1.addSiniestro(siniestro1);
        
        // Creo técnicos
        Tecnico tecnico1 = new Tecnico();
        tecnico1.setNombre("Técnico1");
        tecnico1.setTelefono("111111111");
        AbstractDAO.almacenarEntidad(tecnico1);
        
        Tecnico tecnico2 = new Tecnico();
        tecnico2.setNombre("Técnico2");
        tecnico2.setTelefono("222222222");
        AbstractDAO.almacenarEntidad(tecnico2);
        
        // Asigno siniestros a técnicos
        tecnico1.addSiniestro(siniestro1);
        
        // Persisto
        AbstractDAO.almacenarEntidad(cliente1);
        
        ////// OPERACIONES //////
        ClienteDAO clienteDAO = new ClienteDAO();
        // Contratar una póliza
        // Creo una vivienda nueva, la asigno al cliente existente y actualizo
        Vivienda vivienda2 = new Vivienda();
        vivienda2.setCalle("Calle2");
        vivienda2.setCp("33007");
        vivienda2.setMetrosCuadrados(100.00);
        
        cliente1.addVivienda(vivienda2);
        AbstractDAO.actualizarEntidad(cliente1);
        
        // Contrato una nueva póliza para la vivienda con id X
        Poliza nuevaPoliza = clienteDAO.contratarPoliza(2, UtilidadFechas.generarFecha(2022, 03, 03), 300.00, 0, 0);
        
        // Notifico un nuevo siniestro para la póliza con id 2
        Siniestro nuevoSiniestro = clienteDAO.notificarSiniestro(2);
        
        // Asocio el siniestro al técnico 2
        clienteDAO.asignarSiniestroATecnico(nuevoSiniestro, tecnico2);
        
        // Aplico descuento a los clientes vip
        clienteDAO.aplicarDescuentoClientesVip(50);
        
    }
    
}
