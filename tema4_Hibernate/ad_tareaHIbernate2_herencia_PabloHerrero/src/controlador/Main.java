/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.util.List;
import modelo.dao.AbstractDAO;
import modelo.dao.TecnologoDAO;
import modelo.dto.Normal;
import modelo.dto.Programador;
import modelo.dto.Tecnologo;
import modelo.dto.Tester;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Creo Personas
        Normal normal = new Normal("Empleado", "normal", 21);
        Tecnologo tecnologo1 = new Tecnologo(1, "tecnologo1", 24);
        Tecnologo tecnologo2 = new Tecnologo(5, "tecnologo2", 35);
        Tecnologo tecnologo3 = new Tecnologo(6, "tecnologo3", 28);
        Programador programador1 = new Programador("java", 4, 4, "primer programador", 25);
        Programador programador2 = new Programador("java", 6, 2, "segundo programador", 25);
        Tester tester = new Tester("JUnit", 3, "tester", 3);
        
        // Persisto entidades
        AbstractDAO.almacenarEntidad(normal);
        AbstractDAO.almacenarEntidad(tecnologo1);
        AbstractDAO.almacenarEntidad(tecnologo2);
        AbstractDAO.almacenarEntidad(tecnologo3);
        AbstractDAO.almacenarEntidad(programador1);
        AbstractDAO.almacenarEntidad(programador2);
        AbstractDAO.almacenarEntidad(tester);
        
        // Consultas
        TecnologoDAO tecnologoDAO = new TecnologoDAO();
        List<Tecnologo> tecnologosConMasO5AniosEstudio = tecnologoDAO.retornarTecnologosSegunAniosEstudio(5);
        System.out.println("Tecnólogos con más de 5 años de estudio: " + tecnologosConMasO5AniosEstudio.size());
        for (Tecnologo tecnologo : tecnologosConMasO5AniosEstudio) {
            System.out.println(tecnologo.getNombre() + " Años de estudio: " + tecnologo.getAniosDeEstudios());
        }
    }
    
}
