/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.fitness.ExerciseType;
import jaxb.fitness.Fitness;
import modelo.excepciones.UsuarioNoExisteException;

/**
 *
 * @author Pablo Herrero
 */
public interface InterfaceFitness {
    
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException;

    public void marshalizar(JAXBElement jaxbElement, File archivoSalida) throws JAXBException;

    public boolean asignarEntrenamientoDeDificultadAleatoria(Fitness nodoFitness, int userID) throws UsuarioNoExisteException;
    
    public boolean asignar5EntrenamientosDeDificultadAleatoriaAUsuario(Fitness nodoFitness, int userID) throws UsuarioNoExisteException;
    
    public boolean aniadirAUnUsuarioEntrenamientosPorCalorias(Fitness nodoFitness, String nombreUsuario, int maxCalorias, int minCalorias) throws UsuarioNoExisteException;

    public boolean marcarHechosEntrenamientosDeDificultadXDeUsuario(Fitness nodoFitness, String nombreUsuario, int dificultadEntrenamiento) throws UsuarioNoExisteException;
    
    public List<ExerciseType> generarListaEntrenamientosOrdenadaCalorias(Fitness nodoFitness);
    
    public boolean aniadirFavoritosEjerciciosDeUnWorkout(Fitness nodoFitness, int userId, String nombreEntrenamiento) throws UsuarioNoExisteException;
}
