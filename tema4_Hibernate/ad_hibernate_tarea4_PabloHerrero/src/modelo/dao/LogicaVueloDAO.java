/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.Date;
import modelo.dao.interfaces.interfaceLogicaVueloDAO;
import modelo.dto.Aeropuerto;
import modelo.dto.Asiento;
import modelo.dto.Vuelo;

/**
 *
 * @author Pablo Herrero
 */
public class LogicaVueloDAO extends AbstractDAO implements interfaceLogicaVueloDAO {
    
    /**
     * Método que crea un nuevo Vuelo 737 con 31 filas y 6 letras, siendo las 
     * 5 primeras filas de clase Business y el resto Turista.
     *
     * @param origen
     * @param destino
     * @param fechaVuelo
     * @return Vuelo creado.
     */
    @Override
    public Vuelo crearVuelo737(Aeropuerto origen, Aeropuerto destino, Date fechaVuelo) {
        iniciarOperacion();
        Vuelo vuelo737 = new Vuelo();
        int numeroAsiento = 0;
        int numeroFila = 0;
        
        vuelo737.setOrigen(origen);
        origen.setVueloSalida(vuelo737);
        vuelo737.setDestino(destino);
        destino.setVueloLlegada(vuelo737);
        vuelo737.setFechaVuelo(fechaVuelo);
        
        for (int i = 1; i <= 31; i++) { // para cada fila...
            numeroFila = i;
            for (int j = 1; j <= 6; j++) { // para cada asiento de la fila...
                Asiento asiento = new Asiento();
                asiento.setFila(numeroFila);
                numeroAsiento = j;
                asignarLetraAsiento737(asiento, numeroAsiento);
                asiento.setTipo(generarTipoAsiento737(asiento));
                asiento.setIsOcupado(false);
                vuelo737.addAsiento(asiento);
            }
        }
        almacenarEntidad(vuelo737);
        terminarOperacion();
        
        String notificacion = vuelo737.getAsientos().size() == 186 ? 
                ">>>>>> Vuelo con código " + vuelo737.getCodigo() + ", origen " 
                + origen.getCiudad() + ", destino " + destino.getCiudad() + " creado." 
                : ">>>>>> Error en la creación del vuelo " + vuelo737.getCodigo() + ", inténtalo de nuevo.";
        System.out.println(notificacion);
        
        return vuelo737;
    }
    
    /**
     * Método que asigna la letra a un asiento según su número de asiento, 
     * recibiendo ambos datos como parámetros.
     *
     * @param asiento
     * @param numeroAsiento
     */
    private void asignarLetraAsiento737(Asiento asiento, int numeroAsiento) {
        switch (numeroAsiento) {
            case 1:
                asiento.setLetra("A");
                break;
            case 2:
                asiento.setLetra("B");
                break;
            case 3:
                asiento.setLetra("C");
                break;
            case 4:
                asiento.setLetra("D");
                break;
            case 5:
                asiento.setLetra("E");
                break;
            case 6:
                asiento.setLetra("F");
                break;
        }
    }
    
    /**
     * Método que genera la clase apropiada para el asiento, según su número
     * de fila.
     *
     * @param asiento
     */
    private String generarTipoAsiento737(Asiento asiento) {
        return asiento.getFila() <= 5 ? "Business" : "Turista";
    }
}
