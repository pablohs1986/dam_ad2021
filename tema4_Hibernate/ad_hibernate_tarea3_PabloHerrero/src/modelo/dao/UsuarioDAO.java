/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.dao.interfaces.interfaceUsuarioDAO;
import modelo.dto.Linea;
import modelo.dto.Llamada;
import modelo.dto.Usuario;

/**
 *
 * @author Pablo Herrero
 */
public class UsuarioDAO extends AbstractDAO implements interfaceUsuarioDAO {
    
    /**
     * Método que añade una línea que se pasa como parámetro al usuario
     * que se indica
     *
     * @param usuario Usuario sobre el que se añade la línea.
     * @param linea Línea a añadir.
     */
    @Override
    public void contratarLinea(Usuario usuario, Linea linea){
        iniciarOperacion();
        usuario.addLinea(linea);
        terminarOperacion();
        actualizarEntidad(usuario); // para que actualiceDatos
    }
    
    /**
     * Método que añade una llamada que se pasa como parámetro sobre la línea
     * que se indica.
     *
     * @param llamada Llamada a añadir.
     * @param linea Línea sobre la que añadir la llamada.
     */
    @Override
    public void insertarLlamada(Llamada llamada, Linea linea) {
        List<Llamada> llamadas = linea.getLlamadas();
        llamadas.add(llamada);
        actualizarEntidad(linea);
    }
    
    /**
     * Método que retorna un listado con llamadas de todas las líneas
     * del usuario
     *
     * @param usuario Usuario sobre el que se quiere hacer la consulta
     * @return Listado con todas las llamadas del usuario.
     */
    @Override
    public List<Llamada> listadoUltimasLlamadas(Usuario usuario) {
        List<Llamada> llamadasDeLasLineasDelUsuario = new ArrayList<Llamada>();
        List<Linea> lineasUsuario = listadoLineasUsuario(usuario);

        for (Linea linea : lineasUsuario) {
            llamadasDeLasLineasDelUsuario.addAll(linea.getLlamadas());
        }
        return llamadasDeLasLineasDelUsuario;
    }
    
    /**
     * Método que retorna un listado con las líneas del usuario que recibe
     * como parámetro
     *
     * @param usuario Sobre el que se hace la consulta
     * @return Lista con las líneas del usuario
     */
    @Override
    public List<Linea> listadoLineasUsuario(Usuario usuario) {
        List<Linea> lineas;
        
        iniciarOperacion();
        lineas = getSession()
                .createQuery(" FROM Linea WHERE usuario.id = :idUsuario")
                .setParameter("idUsuario", usuario.getId())
                .list();
        terminarOperacion();

        return lineas;
    }
}
