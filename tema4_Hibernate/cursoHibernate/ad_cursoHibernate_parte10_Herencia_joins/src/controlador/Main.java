/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import modelo.dao.AbstractDAO;
import modelo.dto.Normal;
import modelo.dto.Programador;
import modelo.dto.Tecnologo;
import modelo.dto.Tester;

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Creo Personas
        Normal normal = new Normal("Empleado", "normal", 21);
        Tecnologo tecnologo = new Tecnologo(4, "tecnologo", 24);
        Programador programador1 = new Programador("java", 4, 4, "primer programador", 25);
        Programador programador2 = new Programador("java", 5, 2, "primer programador", 25);
        Tester tester = new Tester("JUnit", 3, "tester", 3);
        
        // Consultas
        AbstractDAO.almacenarEntidad(normal);
        AbstractDAO.almacenarEntidad(tecnologo);
        AbstractDAO.almacenarEntidad(programador1);
        AbstractDAO.almacenarEntidad(programador2);
        AbstractDAO.almacenarEntidad(tester);
    }
    
}
