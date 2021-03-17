/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.operaciones;

import modelo.operaciones.interfaces.InterfaceOperacionesJSON;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import modelo.dto.Cita;
import modelo.dto.Colegiado;
import modelo.dto.Paciente;

/**
 *
 * @author Pablo Herrero
 */
public class OperacionesJSON implements InterfaceOperacionesJSON {
    
    /**
     * Método que retorna la lista de pacientes a partir del jsonObject de 
     * la clínica que recibe como parámetro.
     *
     * @param clinica
     * @return 
     */
    @Override
    public ArrayList<Paciente> getListaPacientes(JsonObject clinica) {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        JsonArray arrayPacientes = null;
        arrayPacientes = clinica.getJsonArray("paciente");
        
        for (int numeroPaciente = 0; numeroPaciente < arrayPacientes.size(); numeroPaciente++) {
            JsonObject pacienteJson = arrayPacientes.getJsonObject(numeroPaciente);
            Paciente paciente = jsonAPaciente(pacienteJson);
            pacientes.add(paciente);
        }
        return pacientes;
    }
    
    /**
     * Método que retorna el JsonObject de la clínica a partir del archivo
     * json que lee.
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    @Override
    public JsonObject getClinica() throws FileNotFoundException {
        JsonObject clinica = leerJsonArchivo();
        return clinica;
    }
    
    /**
     * Método que retorna una lista de citas a partir de un jsonArray de
     * pacientes.
     *
     * @param pacienteJson
     * @return Una objeto paciente.
     */
    public Paciente jsonAPaciente(JsonObject pacienteJson) {
        Paciente paciente = new Paciente();
        
        int numeroSS = pacienteJson.getInt("numeroSS");
        String nombre = pacienteJson.getString("nombre");
        String apellido = pacienteJson.getString("apellido");
        JsonArray citas = pacienteJson.getJsonArray("citas");
        paciente.setCitas(this.getCitas(citas));
        JsonArray colegiados = pacienteJson.getJsonArray("especialistasAsignados");
        paciente.setColegiados(this.getColegiados(colegiados));
        
        return paciente;
    }
    
    /**
     * Método que retorna una lista de citas a partir de un jsonArray de pacientes.
     *
     * @param pacientes
     * @return Lista de citas.
     */
    @Override
    public ArrayList<Cita> getCitas(JsonArray pacientes) {
        ArrayList<Cita> citas = new ArrayList<>();
        Cita cita = new Cita();
        
        for (int indexCita = 0; indexCita < pacientes.size(); indexCita++) {
            JsonObject citaJson = pacientes.getJsonObject(indexCita);
            int dia = citaJson.getInt("dia");
            int tramo = citaJson.getInt("tramo");
            int numeroColegiado = citaJson.getInt("numeroColegiado");
            
            cita = new Cita(dia, tramo, numeroColegiado);
            citas.add(cita);
        }
        return citas;
    }
    
    /**
     * Método que retorna una lista de especialistas a partir de un jsonArray de pacientes.
     *
     * @param pacientes
     * @return Lista de colegiados.
     */
    @Override
    public ArrayList<Colegiado> getColegiados(JsonArray pacientes) {
        ArrayList<Colegiado> colegiados = new ArrayList<>();
        Colegiado colegiado = new Colegiado();
        
        for (int indexColegiado = 0; indexColegiado < pacientes.size(); indexColegiado++) {
            JsonObject colegiadoJson = pacientes.getJsonObject(indexColegiado);
            int numeroColegiado = colegiadoJson.getInt("numeroColegiado");
            String especialidad = colegiadoJson.getString("especialidad");
            String nombre = colegiadoJson.getString("nombre");
            String apellido = colegiadoJson.getString("apellido");
            
            colegiado = new Colegiado(numeroColegiado, especialidad, nombre, apellido);
            colegiados.add(colegiado);
        }
        return colegiados;
    }
    
    /**
     * Método que lee un archivo Json y devuelve un JsonArray con el contenido
     *
     * @return JsonArray
     * @throws java.io.FileNotFoundException
     */
    @Override
    public JsonObject leerJsonArchivo() throws FileNotFoundException {
        FileReader entrada = null;
        JsonObject objectPrincipal = null;
        entrada = new FileReader("paciente.json");
        JsonReader jsonReader = Json.createReader(entrada);
        objectPrincipal = jsonReader.readObject();
        return objectPrincipal;
    }

    /**
     * Método que recibe un JsonArray de libros y lo escribe en un archivo de
     * salida
     *
     * @param arrayLibros
     * @throws java.io.IOException
     */
    @Override
    public void escribirJson(JsonArray arrayLibros) throws IOException {
        FileWriter ficheroSalida = null;
        ficheroSalida = new FileWriter("SalidaJson.json");
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        jsonWriter.writeArray(arrayLibros);
        ficheroSalida.flush();
    }


}
