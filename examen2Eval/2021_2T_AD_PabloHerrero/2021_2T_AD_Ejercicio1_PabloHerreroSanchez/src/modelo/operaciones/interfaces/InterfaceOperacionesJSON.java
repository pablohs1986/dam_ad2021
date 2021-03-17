/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.operaciones.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.json.JsonArray;
import javax.json.JsonObject;
import modelo.dto.Cita;
import modelo.dto.Colegiado;
import modelo.dto.Paciente;

/**
 *
 * @author Pablo Herrero
 */
public interface InterfaceOperacionesJSON {
    
    public ArrayList<Paciente> getListaPacientes(JsonObject clinica);
    
    public JsonObject getClinica() throws FileNotFoundException;
    
    public Paciente jsonAPaciente(JsonObject pacienteJson);
    
    public ArrayList<Cita> getCitas(JsonArray pacientes);
    
    public ArrayList<Colegiado> getColegiados(JsonArray pacientes);
    
    public JsonObject leerJsonArchivo() throws FileNotFoundException;
    
    public void escribirJson(JsonArray arrayLibros) throws IOException;
}
