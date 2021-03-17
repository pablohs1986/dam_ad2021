/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.interfaces;

import java.util.List;
import modelo.dto.Tecnologo;

/**
 *
 * @author Pablo Herrero
 */
public interface interfaceTecnologoDAO {

    public List<Tecnologo> retornarTecnologosSegunAniosEstudio(int aniosEstudio);

}
