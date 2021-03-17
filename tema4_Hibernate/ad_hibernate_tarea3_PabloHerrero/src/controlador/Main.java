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
        
        // PERSISTENCIA
        // Creo llamadas
        Llamada llamada1 = new Llamada();
        llamada1.setTelefonoOrigen("111111111");
        llamada1.setTelefonoDestino("222222222");
        llamada1.setMinutosConsumidos(10);
        llamada1.setFechaInicio(new Date());
        
        Llamada llamada2 = new Llamada();
        llamada2.setTelefonoOrigen("111111111");
        llamada2.setTelefonoDestino("333333333");
        llamada2.setMinutosConsumidos(60);
        llamada2.setFechaInicio(new Date());
        
        Llamada llamada3 = new Llamada();
        llamada3.setTelefonoOrigen("111111111");
        llamada3.setTelefonoDestino("999999999");
        llamada3.setMinutosConsumidos(120);
        llamada3.setFechaInicio(new Date());
        
        // Creo terminales
        Terminal terminal1 = new Terminal();
        terminal1.setImei("1111");
        terminal1.setMarca("MarcaA");
        terminal1.setModelo("ModeloA");
        
        // Crep tarifas
        Tarifa tarifa1 = new Tarifa();
        tarifa1.setNombre("Tarifa1");
        tarifa1.setMinutosMaxLlamadas(100);
        tarifa1.setGigasMaxDatos(1000);
        
        // Creo líneas
        Linea linea1 = new Linea();
        linea1.setNumeroTelefono("111111111");
        linea1.setDatosDisponibles(10000);
        linea1.setMinutosConsumidos(1000);
        
        Linea linea2 = new Linea();
        linea2.setNumeroTelefono("555555555");
        linea2.setDatosDisponibles(3000);
        linea2.setMinutosConsumidos(4000);

        // Asigno llamadas a líneas
        linea1.addLlamada(llamada1);
        linea1.addLlamada(llamada2);
        
        // Asigno terminales a líneas
        linea1.setTerminal(terminal1);
        
        // Asigno tarifas a líneas
        linea1.addTarifa(tarifa1);
        
        // Creo usuarios
        Usuario usuario1 = new Usuario();
        usuario1.setDni("11111111A");
        usuario1.setNombre("NombreA");
        usuario1.setApellido("ApellidoA");

        // Asigno lineas a usuarios
        usuario1.addLinea(linea1);
        
        // Persisto
        AbstractDAO.almacenarEntidad(usuario1);
        
        
        // OPERACIONES
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Listado últimas llamadas de un usuario
        System.out.println(">>> Llamadas para el usuario con ID " + usuario1.getId());
        List<Llamada> llamadasDeLasLineasDelUsuario1 = usuarioDAO.listadoUltimasLlamadas(usuario1);
        
        for (Llamada llamada : llamadasDeLasLineasDelUsuario1) {
            System.out.println("Id: " +  llamada.getId() + " Fecha: " + llamada.getFechaInicio()
                + " Minutos consumidos: " + llamada.getMinutosConsumidos() + " Teléfono destino: " 
                + llamada.getTelefonoDestino());
        }
        
        // Contratar una línea para un usuario
        System.out.println(">>> Usuario con id " + usuario1.getId() + " contratando la línea " 
                + linea2.getNumeroTelefono());
        usuarioDAO.contratarLinea(usuario1, linea2);
        
        System.out.println(">>> Listando las líneas del usuario " + usuario1.getId());
        List<Linea> lineasUsuario1 = usuarioDAO.listadoLineasUsuario(usuario1);
        for (Linea linea : lineasUsuario1) {
            System.out.println(linea.getNumeroTelefono());
        }
        
        // Insertar una llamada en una línea del usuario
        System.out.println(">>> Insertando llamada en la línea " + linea1.getNumeroTelefono()
            + "del usuario con ID" + linea1.getUsuario().getId());
        usuarioDAO.insertarLlamada(llamada3, linea1);
        
        System.out.println(">>> Llamadas para el usuario con ID " + usuario1.getId());
        llamadasDeLasLineasDelUsuario1 = usuarioDAO.listadoUltimasLlamadas(usuario1);
        for (Llamada llamada : llamadasDeLasLineasDelUsuario1) {
            System.out.println("Id: " + llamada.getId() + " Fecha: " + llamada.getFechaInicio()
                    + " Minutos consumidos: " + llamada.getMinutosConsumidos() + " Teléfono destino: "
                    + llamada.getTelefonoDestino());
        }
    }
    
}
