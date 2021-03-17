/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.util.Date;
import java.util.List;
import modelo.dao.AbstractDAO;
import modelo.dto.Llamada;
import modelo.dto.SIMLinea;
import modelo.dto.Tarifa;
import modelo.dto.Terminal;
import modelo.dto.Usuario;
import org.hibernate.mapping.AbstractAuxiliaryDatabaseObject;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Creo terminales
        Terminal terminal1 = new Terminal();
        terminal1.setImeiTerminal("111");
        terminal1.setModelo("ModeloA");
        terminal1.setMarca("MarcaA");
        
        Terminal terminal2 = new Terminal();
        terminal2.setImeiTerminal("222");
        terminal2.setModelo("ModeloB");
        terminal2.setMarca("MarcaB");
        
        // Creo tarifas
        Tarifa tarifa1 = new Tarifa();
        tarifa1.setNombreTarifa("Tarifa1");
        tarifa1.setGigasMaxDatos(10);
        tarifa1.setMinutosMaxLlamadas(10);
        
        Tarifa tarifa2 = new Tarifa();
        tarifa2.setNombreTarifa("Tarifa2");
        tarifa2.setGigasMaxDatos(20);
        tarifa2.setMinutosMaxLlamadas(20);
        
        Tarifa tarifa3 = new Tarifa();
        tarifa3.setNombreTarifa("Tarifa3");
        tarifa3.setGigasMaxDatos(30);
        tarifa3.setMinutosMaxLlamadas(30);
        
        Tarifa tarifa4 = new Tarifa();
        tarifa4.setNombreTarifa("Tarifa4");
        tarifa4.setGigasMaxDatos(40);
        tarifa4.setMinutosMaxLlamadas(40);
        
        // Creo llamadas
        Llamada llamada1 = new Llamada();
        llamada1.setTelefonoOrigen("111111111");
        llamada1.setTelefonoDestino("222222222");
        llamada1.setDuracionSegundos(10);
        llamada1.setFechaInicio(new Date());
        
        Llamada llamada2 = new Llamada();
        llamada2.setTelefonoOrigen("333333333");
        llamada2.setTelefonoDestino("444444444");
        llamada2.setDuracionSegundos(20);
        llamada2.setFechaInicio(new Date());
        
        // Creo líneas y asocio terminales llamadas y tarifas
        SIMLinea linea1 = new SIMLinea();
        linea1.setNumeroTelefono("111111111");
        linea1.setMinutosConsumidos(10);
        linea1.setDatosDisponibles(100);
        linea1.setTerminal(terminal1);
        linea1.addTarifa(tarifa1);
        linea1.addLlamada(llamada1);
        
        SIMLinea linea2 = new SIMLinea();
        linea2.setNumeroTelefono("111111111");
        linea2.setMinutosConsumidos(10);
        linea2.setDatosDisponibles(100);
        linea2.setTerminal(terminal2);
        linea2.addTarifa(tarifa2);
        linea2.addLlamada(llamada2);

        
        // Creo usuarios
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("UsuarioA");
        usuario1.setApellido("AAAA");
        usuario1.setDni("11111111A");
        
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("UsuarioB");
        usuario2.setApellido("BBBB");
        usuario2.setDni("22222222B");
        
        // Asigno líneas a usuarios
        usuario1.addLinea(linea1);
        usuario2.addLinea(linea2);

        // Persisto
//        AbstractDAO.almacenarEntidad(terminal1);
//        AbstractDAO.almacenarEntidad(terminal2);
//        AbstractDAO.almacenarEntidad(tarifa1);
//        AbstractDAO.almacenarEntidad(tarifa2);
//        AbstractDAO.almacenarEntidad(tarifa3);
//        AbstractDAO.almacenarEntidad(tarifa4);
//        AbstractDAO.almacenarEntidad(llamada1);
//        AbstractDAO.almacenarEntidad(llamada2);
//        AbstractDAO.almacenarEntidad(linea1);
//        AbstractDAO.almacenarEntidad(linea2);
        AbstractDAO.almacenarEntidad(usuario1);
        AbstractDAO.almacenarEntidad(usuario2);
        
    }
    
}
