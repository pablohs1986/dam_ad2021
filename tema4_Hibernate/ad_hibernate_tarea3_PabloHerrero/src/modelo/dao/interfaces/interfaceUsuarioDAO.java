/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.interfaces;

import java.util.List;
import modelo.dto.Linea;
import modelo.dto.Llamada;
import modelo.dto.Usuario;

/**
 *
 * @author Pablo Herrero
 */
public interface interfaceUsuarioDAO {

    public void contratarLinea(Usuario usuario, Linea linea);

    public void insertarLlamada(Llamada llamada, Linea linea);

    public List<Llamada> listadoUltimasLlamadas(Usuario usuario);

    public List<Linea> listadoLineasUsuario(Usuario usuario);
    
}
