/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.operaciones.interfaces;

import java.util.ArrayList;
import modelo.dto.Cita;
import modelo.dto.Colegiado;
import modelo.dto.Paciente;

/**
 *
 * @author Pablo Herrero
 */
public interface InterfaceOperacionesMYSQL {
    
    public void insertarPaciente(ArrayList<Paciente> pacientes);
    
    public void insertarCitas(ArrayList<Cita> citas);
    
    public void insertarColegiados(ArrayList<Colegiado> colegiados);
    
}
