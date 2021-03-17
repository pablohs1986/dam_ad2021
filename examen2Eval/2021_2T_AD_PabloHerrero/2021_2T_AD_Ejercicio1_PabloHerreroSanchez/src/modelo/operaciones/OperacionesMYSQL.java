/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.operaciones;

import java.util.ArrayList;
import modelo.dto.Cita;
import modelo.dto.Colegiado;
import modelo.dto.Paciente;
import modelo.operaciones.interfaces.InterfaceOperacionesMYSQL;

/**
 *
 * @author Pablo Herrero
 */
public class OperacionesMYSQL implements InterfaceOperacionesMYSQL {

    @Override
    public void insertarPaciente(ArrayList<Paciente> pacientes) {
        for (int i = 0; i < pacientes.size(); i++) {
            
        }
    }

    @Override
    public void insertarCitas(ArrayList<Cita> citas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarColegiados(ArrayList<Colegiado> colegiados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
