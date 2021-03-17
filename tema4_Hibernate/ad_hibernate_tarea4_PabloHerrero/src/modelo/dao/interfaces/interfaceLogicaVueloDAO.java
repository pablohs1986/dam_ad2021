/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.interfaces;

import java.util.Date;
import modelo.dto.Aeropuerto;
import modelo.dto.Vuelo;

/**
 *
 * @author Pablo Herrero
 */
public interface interfaceLogicaVueloDAO {

    public Vuelo crearVuelo737(Aeropuerto origen, Aeropuerto destino, Date fechaVuelo);

}
