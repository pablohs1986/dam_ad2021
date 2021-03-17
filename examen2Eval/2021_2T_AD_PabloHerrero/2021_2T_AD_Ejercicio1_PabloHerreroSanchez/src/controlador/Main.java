/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import modelo.dto.Paciente;
import modelo.operaciones.OperacionesJSON;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            OperacionesJSON operaciones = new OperacionesJSON();
            JsonObject clinica = operaciones.getClinica();
            ArrayList<Paciente> listaPacientes = operaciones.getListaPacientes(clinica);
            for (Paciente paciente : listaPacientes) {
                System.out.println(paciente);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
