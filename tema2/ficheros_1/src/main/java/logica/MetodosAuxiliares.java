/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.util.List;

/**
 * Clase con métodos auxiliares para el proyecto.
 * @version 1.1, 25/10/2020
 * @author Pablo Herrero
 */
public class MetodosAuxiliares {
    /**
     * Muestra el contenido de una lista.
     * @param listado La lista que se va a mostrar.
     */
    public static void mostrarListado(List<File> listado) {
        for (File e : listado) {
            System.out.println(e);
        }
    }
}
