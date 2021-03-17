/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.fitness.ExerciseType;
import jaxb.fitness.Fitness;
import jaxb.fitness.WorkoutType;
import modelo.excepciones.UsuarioNoExisteException;

/**
 *
 * @author Pablo Herrero
 */
public class MetodosFitness implements InterfaceFitness {

    /**
     * Método para unmarshalizar (leer).
     *
     * @param ficheroXML
     * @return JAXBElement
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public JAXBElement unmarshalizar(File ficheroXML) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("jaxb.fitness");
        Unmarshaller unmarshalizador = contexto.createUnmarshaller();
        JAXBElement elemento = unmarshalizador.unmarshal(new StreamSource(ficheroXML), Fitness.class);
        return elemento;
    }

    /**
     * Método para marshalizar (escribir).
     *
     * @param jaxbElement
     * @param archivoSalida
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public void marshalizar(JAXBElement jaxbElement, File archivoSalida) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance("jaxb.fitness");
        Marshaller marshalizador = contexto.createMarshaller();
        marshalizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshalizador.marshal(jaxbElement, archivoSalida);
    }

    // --> Esta es la primera aproximación al apartado 1, es errónea porque no hace lo que se pide (lo entendí mal), la dejo aun así.
    /**
     * Asigna un entrenamiento de dificultad aleatoria al usuario con la id que
     * se pasa como parámetro.
     *
     * @param nodoFitness Nodo general del xml
     * @param userID Id a buscar
     * @return boolean Confirmando haber encontrado al usuario
     * @throws UsuarioNoExisteException
     */
    @Override
    public boolean asignarEntrenamientoDeDificultadAleatoria(Fitness nodoFitness, int userID) throws UsuarioNoExisteException {
        List<Fitness.Users.User> listaUsuarios = nodoFitness.getUsers().getUser();
        boolean isUsuarioEncontrado = false;

        for (Fitness.Users.User usuario : listaUsuarios) {
            if (userID == usuario.getUserID()) {
                isUsuarioEncontrado = true;
                List<Fitness.Users.User.AsignedWorkouts> workoutsAsignadosAUsuario = usuario.getAsignedWorkouts();
                for (Fitness.Users.User.AsignedWorkouts ejerciciosAsignados : workoutsAsignadosAUsuario) {
                    List<ExerciseType> listaEjerciciosAsignados = ejerciciosAsignados.getExercise();
                    List<WorkoutType> workoutsDisponibles = nodoFitness.getWorkouts().getWorkout();

                    for (WorkoutType workout : workoutsDisponibles) {
                        List<ExerciseType> listaEjerciciosDisponibles = workout.getExercise();
                        for (ExerciseType ejercicio : listaEjerciciosDisponibles) {
                            if (ejercicio.getDificulty() == generarDificultadAleatoria()) {
                                listaEjerciciosAsignados.add(ejercicio);
                            }
                        }
                    }
                }
            } else {
                isUsuarioEncontrado = false;
            }
        }

        if (!isUsuarioEncontrado) {
            throw new UsuarioNoExisteException("El usuario con ID " + userID + " no encontrado.");
        }
        return isUsuarioEncontrado;
    }

    /**
     * Genera una dificultad aleatoria (int) de 0 al 5.
     *
     * @return int
     */
    private int generarDificultadAleatoria() {
        return (int) (Math.random() * 10) / 2;
    }

    /**
     * Asigna un 5 entrenamientos, uno por cada nivel de dificultad, al usuario
     * con la id que se pasa como parámetro.
     *
     * @param nodoFitness Nodo general del xml
     * @param userID Id a buscar
     * @return boolean Confirmando haber encontrado al usuario
     * @throws UsuarioNoExisteException
     */
    @Override
    public boolean asignar5EntrenamientosDeDificultadAleatoriaAUsuario(Fitness nodoFitness, int userID) throws UsuarioNoExisteException {
        List<Fitness.Users.User> listaUsuarios = nodoFitness.getUsers().getUser();  // Tomo la lista de usuario
        List<WorkoutType> listaWorkouts = nodoFitness.getWorkouts().getWorkout();   // Tomo la lista de workouts disponibles
        boolean isUsuarioEncontrado = false;

        for (Fitness.Users.User usuario : listaUsuarios) {
            if (userID == usuario.getUserID()) {    // Busco al usuario
                isUsuarioEncontrado = true;
                List<Fitness.Users.User.AsignedWorkouts> workoutsAsignadosAUsuario = usuario.getAsignedWorkouts();  // Tomo la lista de workouts asignados al usuario
                int ultimaDificultadAsignada = 0;   // Variable para comparar con la última dificultad asignada
                for (int i = 0; i < 4; i++) {
                    for (WorkoutType workout : listaWorkouts) {
                        ultimaDificultadAsignada = generarDificultadAleatoria();
                        if (workout.getDifficulty() == ultimaDificultadAsignada && workout.getDifficulty() != ultimaDificultadAsignada) {    // Si la dificultad coincide y no hay un ejercicio asignado con esa dificultad, se añade.
                            workoutsAsignadosAUsuario.add((Fitness.Users.User.AsignedWorkouts) workout);
                        }
                    }
                }
            } else {
                isUsuarioEncontrado = false;
            }
        }
        if (!isUsuarioEncontrado) {
            throw new UsuarioNoExisteException("El usuario con ID " + userID + " no encontrado.");
        }
        return isUsuarioEncontrado;
    }

    /**
     * Añade a un usuario los entrenamientos que están incluidos en un rango de
     * calorías
     *
     * @param nodoFitness Nodo general del xml
     * @param nombreUsuario Nombre del usuario a buscar
     * @param maxCalorias Calorías máximas
     * @param minCalorias Calorías mínimas
     * @return boolean Confirmando haber encontrado al usuario
     * @throws UsuarioNoExisteException
     */
    @Override
    public boolean aniadirAUnUsuarioEntrenamientosPorCalorias(Fitness nodoFitness, String nombreUsuario, int maxCalorias, int minCalorias) throws UsuarioNoExisteException {
        List<Fitness.Users.User> listaUsuarios = nodoFitness.getUsers().getUser();  // Tomo la lista de usuarios.
        boolean isUsuarioEncontrado = false;

        for (Fitness.Users.User usuario : listaUsuarios) {
            if (nombreUsuario.equalsIgnoreCase(usuario.getName())) {    // Busco al usuario
                isUsuarioEncontrado = true;
                List<Fitness.Users.User.AsignedWorkouts> workoutsAsignadosAlUsuario = usuario.getAsignedWorkouts();  // Tomo la lista de workouts asignados
                for (Fitness.Users.User.AsignedWorkouts workoutsAsignado : workoutsAsignadosAlUsuario) {
                    List<ExerciseType> ejerciciosWorkoutAsignado = workoutsAsignado.getExercise();   // Dentro de cada workout, tomo la lista de ejercicios

                    List<WorkoutType> workoutsDisponibles = nodoFitness.getWorkouts().getWorkout(); // Tomo la lista de workouts disponibles
                    for (WorkoutType workout : workoutsDisponibles) {
                        List<ExerciseType> listaEjerciciosWorkout = workout.getExercise();  // Tomo la lista de ejercicios del workout sobre el que itero y recorro esa lista
                        for (ExerciseType ejercicio : listaEjerciciosWorkout) {
                            if (ejercicio.getCalories() < maxCalorias && workout.getTotalCalories() > minCalorias) {    // Si la dificultad del ejercicio está en el rango establecido, se añade a la lista de workouts asignados al usuario
                                ejerciciosWorkoutAsignado.add(ejercicio);
                            }
                        }
                    }
                }
            }
        }
        if (!isUsuarioEncontrado) {
            throw new UsuarioNoExisteException("Usuario con nombre " + nombreUsuario + " no encontrado");
        }
        return isUsuarioEncontrado;
    }

    /**
     * Marca como hechos los entrenamientos de la dificultad indicada para un
     * usuario dado.
     *
     * @param nodoFitness Nodo general del xml
     * @param nombreUsuario Nombre del usuario a buscar
     * @param dificultadEntrenamiento Dificultad del entrenamiento a marcar
     * @return boolean Confirmando haber encontrado al usuario
     * @throws UsuarioNoExisteException
     */
    @Override
    public boolean marcarHechosEntrenamientosDeDificultadXDeUsuario(Fitness nodoFitness, String nombreUsuario, int dificultadEntrenamiento) throws UsuarioNoExisteException {
        List<Fitness.Users.User> listaUsuarios = nodoFitness.getUsers().getUser();  // Tomo la lista de usuarios
        boolean isUsuarioEncontrado = false;

        for (Fitness.Users.User usuario : listaUsuarios) {
            if (nombreUsuario.equalsIgnoreCase(usuario.getName())) {    // Busco al usuario
                isUsuarioEncontrado = true;
                List<Fitness.Users.User.AsignedWorkouts> listaWorkoutsAsignados = usuario.getAsignedWorkouts(); // Tomo la lista de workouts asignados al usuario
                for (Fitness.Users.User.AsignedWorkouts workoutAsignado : listaWorkoutsAsignados) { // Recorro la lista
                    if (dificultadEntrenamiento == workoutAsignado.getDifficulty()) {   // Si la dificultad del workout coincide con la buscada, lo marco como done.
                        workoutAsignado.setWorkoutDone(true);
                    }
                }
            }
        }
        return isUsuarioEncontrado;
    }

    /**
     * Genera una lista de todos los entrenamientos, ordenada por calorías.
     *
     * @param nodoFitness Nodo general del xml
     * @return List<ExerciseType> Lista de entrenamientos ordenada por calorías.
     */
    @Override
    public List<ExerciseType> generarListaEntrenamientosOrdenadaCalorias(Fitness nodoFitness) {
        List<ExerciseType> listaEjerciciosOrdenadaCalorias = new ArrayList<>();     // Lista de ExerciseType a retornar
        List<WorkoutType> listaWorkouts = nodoFitness.getWorkouts().getWorkout();   // Tomo la lista de workouts.

        for (WorkoutType workout : listaWorkouts) { // Recorro la lista
            List<ExerciseType> listaEjerciciosDelWorkout = workout.getExercise(); // Tomo la lista de ejercicios disponibles para cada workout.
            for (ExerciseType ejercicio : listaEjerciciosDelWorkout) {  // Itero y añado el ejercicio a la lista que quiero retornar
                listaEjerciciosOrdenadaCalorias.add(ejercicio);
            }
        }
        ordenarListaPorCalorias(listaEjerciciosOrdenadaCalorias);   // Ordeno con método propio que usa un Comparator personalizado para la lista.
        return listaEjerciciosOrdenadaCalorias;
    }

    /**
     * Ordena por calorías la lista de ejercicios que recibe
     *
     * @param listaEjercicios Lista de ejercicios a ordenar
     * @throws UsuarioNoExisteException
     */
    private void ordenarListaPorCalorias(List<ExerciseType> listaEjercicios) {
        Collections.sort(listaEjercicios, new Comparator<ExerciseType>() {
            @Override
            public int compare(ExerciseType o1, ExerciseType o2) {
                return o1.getCalories() - o2.getCalories();
            }
        });
    }

    /**
     * Añade como favoritos al usuario que se indica por id el entrenamiento
     * indicado.
     *
     * @param nodoFitness Nodo general del xml
     * @param userId Id del usuario a buscar
     * @param nombreEntrenamiento Nombre del entrenamiento a añadir
     * @return boolean Confirmando haber encontrado al usuario
     * @throws UsuarioNoExisteException
     */
    @Override
    public boolean aniadirFavoritosEjerciciosDeUnWorkout(Fitness nodoFitness, int userId, String nombreEntrenamiento) throws UsuarioNoExisteException {
        List<Fitness.Users.User> listaUsuarios = nodoFitness.getUsers().getUser();  // Tomo la lista de usuarios
        List<WorkoutType> listaWorkouts = nodoFitness.getWorkouts().getWorkout();   // Tomo la lista de workouts
        boolean isUsuarioEncontrado = false;

        for (Fitness.Users.User usuario : listaUsuarios) {
            if (userId == usuario.getUserID()) {    // Busco el usuario
                isUsuarioEncontrado = true;
                List<Fitness.Users.User.FavoriteExecises> listaEjerciciosFavoritosUsuario = usuario.getFavoriteExecises(); // Tomo la lista de ejercicios favoritos de ese usuario

                for (WorkoutType workout : listaWorkouts) {  // Tomo la lista de workouts e itero sobre ella
                    if (nombreEntrenamiento.equalsIgnoreCase(workout.getWorkoutName())) {   // Si coincide el nombre del workout,...
                        List<ExerciseType> listaEjerciciosWorkout = workout.getExercise(); // ...tomo la lista de ejercicios de dicho workout...
                        for (ExerciseType ejercicio : listaEjerciciosWorkout) {    // ... e itero sobre la misma copiando los archivos (añadiéndolos a la lista de ejercicios favoritos)
                            listaEjerciciosFavoritosUsuario.add((Fitness.Users.User.FavoriteExecises) ejercicio);
                        }
                    }
                }
            }
        }

        if (!isUsuarioEncontrado) {
            throw new UsuarioNoExisteException("El usuario con ID " + userId + " no existe.");
        }
        return isUsuarioEncontrado;
    }

}
