/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;
import java.util.List;
import modelo.dao.AbstractDAO;
import modelo.dao.UsuarioDAO;
import modelo.dto.Linea;
import modelo.dto.Llamada;
import modelo.dto.Tarifa;
import modelo.dto.Terminal;
import modelo.dto.Usuario;

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
        // Creo usuarios
        Usuario usuario1 = new Usuario();
        usuario1.setDni("11111111A");
        usuario1.setNombre("Usuario1");
        usuario1.setApellido("Apellido1");
        
        Usuario usuario2 = new Usuario();
        usuario2.setDni("22222222B");
        usuario2.setNombre("Usuario2");
        usuario2.setApellido("Apellido2");
        
        // Creo líneas
        Linea linea1 = new Linea();
        linea1.setNumero("111111111");
        linea1.setDatosDisponibles(10);
        
        Linea linea2 = new Linea();
        linea2.setNumero("222222222");
        linea2.setDatosDisponibles(20);
        
        // Asigno líneas a usuarios
        usuario1.addLinea(linea1);
        usuario2.addLinea(linea2);
        
        // Creo tarifas
        Tarifa tarifa1 = new Tarifa();
        tarifa1.setNombreTarifa("Tarifa1");
        tarifa1.setGigasMaxDatos(10);
        tarifa1.setMinutosMaxLlamadas(100);
        
        Tarifa tarifa2 = new Tarifa();
        tarifa2.setNombreTarifa("Tarifa2");
        tarifa2.setGigasMaxDatos(20);
        tarifa2.setMinutosMaxLlamadas(200);
        
        // Asigno tarfas a lineas
        linea1.addTarifa(tarifa1);
        linea2.addTarifa(tarifa2);
        
        // Creo terminales
        Terminal terminal1 = new Terminal();
        terminal1.setImei("111111");
        terminal1.setModelo("Modelo1");
        terminal1.setMarca("Marca1");
        
        Terminal terminal2 = new Terminal();
        terminal2.setImei("222222");
        terminal2.setModelo("Modelo2");
        terminal2.setMarca("Marca2");

        // Asigno terminales a líneas
        linea1.setTerminal(terminal1);
        linea2.setTerminal(terminal2);
        
        // Creo llamadas
        Llamada llamada1 = new Llamada();
        llamada1.setTelefonoOrigen("999999999");
        llamada1.setTelefonoDestino("888888888");
        llamada1.setDuracionEnSegundos(60);
        llamada1.setFechaInicio(new Date());
        
        // Asigno llamadas a usuarios
        linea1.addLlamada(llamada1);
        
        // Persisto usuarios
        AbstractDAO.almacenarEntidad(usuario1); // El resto de entidades se generan en cascada
        AbstractDAO.almacenarEntidad(usuario2); // El resto de entidades se generan en cascada
        AbstractDAO.almacenarEntidad(tarifa1); // Persisto tarifas, pues no van en cascada
        AbstractDAO.almacenarEntidad(tarifa2); // Persisto tarifas, pues no van en cascada
        
        
        ////// OPERACIONES //////
        UsuarioDAO usuarioDao = new UsuarioDAO();
        // Listo últimas llamadas para una línea
        List<Llamada> llamadasLinea1 = usuarioDao.listadoUltimasLlamadas(linea1);
//        for (Llamada llamada : llamadasLinea1) {
//            System.out.println(llamada.getId() + " Origen: " + llamada.getTelefonoOrigen() + " Destino: " + llamada.getTelefonoDestino());
//        }

        // Contrato una nueva línea para un usuario, indicando la tarifa.
        usuarioDao.contratarLinea(usuario2, tarifa2);
        
        // Inserto varias llamadas
        usuarioDao.insertarLlamada(linea2, 360);
        usuarioDao.insertarLlamada(linea2, 850);
        usuarioDao.insertarLlamada(linea1, 500);
    }
    
}
