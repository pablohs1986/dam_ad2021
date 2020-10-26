/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 * Clase con las excepciones personalizadas para el proyecto.
 * @version 1.1, 25/10/2020
 * @author Pablo Herrero
 */
public class Excepciones extends Exception {
    /**
     * Excepción lanzada si se detecta un directorio está vacío.
     *
     * @version 1.1, 25/10/2020
     * @author Pablo Herrero
     */
    public static class carpetaVacia extends Exception {
        public carpetaVacia() {
        }
        
        public carpetaVacia(String mensajeError) {
            super(mensajeError);
        }

        @Override
        public String getMessage() {
            String mensaje = "La carpeta está vacía";
            return mensaje;
        }
    }
    
    /**
     * Excepción lanzada si el archivo que se recibe no es un directorio.
     *
     * @version 1.1, 25/10/2020
     * @author Pablo Herrero
     */
    public static class noEsUnDirectorioNoSePuedeListar extends Exception {
        public noEsUnDirectorioNoSePuedeListar() {
        }

        @Override
        public String getMessage() {
            String mensaje = "La ruta indicada no es un directorio, no se puede listar.";
            return mensaje;
        }
    }
}
