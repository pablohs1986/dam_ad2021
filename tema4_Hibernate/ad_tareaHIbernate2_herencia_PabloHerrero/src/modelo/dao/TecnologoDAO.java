/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dao;

import java.util.List;
import modelo.dao.interfaces.interfaceTecnologoDAO;
import modelo.dto.Tecnologo;

/**
 *
 * @author Pablo Herrero
 */
public class TecnologoDAO extends AbstractDAO implements interfaceTecnologoDAO {

    /**
     * Método que retorna un lista de tecnólogos con años de estudio mayor
     * o igual al valor que se pasa como parámetro.
     *
     * @param aniosEstudio Cantidad de años de estudio
     * @return Lista con los tecnologos que cumplen el requisito
     */
    @Override
    public List<Tecnologo> retornarTecnologosSegunAniosEstudio(int aniosEstudio) {
        List<Tecnologo> listaTecnologos;
        
        iniciarOperacion();
        listaTecnologos = getSession()
                .createQuery(" FROM Tecnologo WHERE aniosDeEstudios >= :parametro")
                .setParameter("parametro", aniosEstudio)
                .list();
        return listaTecnologos;
    }
    
}
