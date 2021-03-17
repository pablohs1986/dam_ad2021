/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.Date;
import java.util.List;
import modelo.dto.Linea;
import modelo.dto.Llamada;
import modelo.dto.Tarifa;
import modelo.dto.Usuario;

/**
 *
 * @author Pablo Herrero
 */
public class UsuarioDAO extends AbstractDAO {
    
    public List<Llamada> listadoUltimasLlamadas(Linea linea) {
        return linea.getLlamadas();
    }
    
    public boolean insertarLlamada(Linea linea, int duracionSegundos) {
        boolean isLlamadaInsertada;
        
        if(comprobarMinutosDisponibles(linea, duracionSegundos)) {
            iniciarOperacion();
            Llamada nuevaLlamada = new Llamada();
            nuevaLlamada.setFechaInicio(new Date());
            nuevaLlamada.setTelefonoOrigen(linea.getNumero());
            nuevaLlamada.setTelefonoDestino("989898989");
            nuevaLlamada.setDuracionEnSegundos(duracionSegundos);
            linea.setMinutosConsumidos(linea.getMinutosConsumidos() + (nuevaLlamada.getDuracionEnSegundos() / 60));
            linea.addLlamada(nuevaLlamada);
            actualizarEntidad(linea);
            terminarOperacion();
            isLlamadaInsertada = true;
        } else {
            isLlamadaInsertada = false;
        }
        return isLlamadaInsertada;
    }
    
    private boolean comprobarMinutosDisponibles(Linea linea, int minutosDesados) {
        boolean isMinutosDisponibles = false;
        List<Tarifa> tarifas = linea.getTarifas();
        
        for (Tarifa tarifa : tarifas) {
            if(tarifa.getMinutosMaxLlamadas()>=(minutosDesados/60)) {
                isMinutosDisponibles = true;
                break;
            } else {
                isMinutosDisponibles = false;
            }
        }
        return isMinutosDisponibles;
    }
    
    public Linea contratarLinea(Usuario usuario, Tarifa tarifa) {
        iniciarOperacion();
        Linea nuevaLinea = new Linea();
        nuevaLinea.setNumero(generarNumero());
        nuevaLinea.setDatosDisponibles(tarifa.getGigasMaxDatos());
        nuevaLinea.addTarifa(tarifa);
        usuario.addLinea(nuevaLinea);
        actualizarEntidad(usuario);
        terminarOperacion();
        return nuevaLinea;
    }
    
    private String generarNumero() {
        List<Linea> lineas = getListaEntidades(Linea.class);
        String ultimoNumero = lineas.get(lineas.size() - 1).getNumero();
        long ultimoNumeroLong = Long.parseLong(ultimoNumero);
        long nuevoNumeroLong = ultimoNumeroLong + 1;
        String nuevoNumero = String.valueOf(nuevoNumeroLong);
        return nuevoNumero;
    }
}
