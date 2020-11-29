/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Scanner;

/**
 * Clase con métodos que muestran las diversas opciones del menú con el que
 * interactúa el usuario y le permite visualizar los distintos ejercicios.
 *
 * @version 1.0, 09/11/2020
 * @author Pablo Herrero
 */
public class Menu {

    /**
     * Método que pide el nombre al usuario.
     *
     * @return String con el nombre del usuario.
     */
    public static String pedirNombre() {
        Scanner sc = new Scanner(System.in);
        String usuario;

        System.out.println("\nBienvenido, introduce tu nombre, por favor:");
        usuario = sc.nextLine();
        return usuario;
    }

    /**
     * Método que escucha la opción que teclea el usuario.
     *
     * @return Int con el valor de la opción.
     */
    public static int escucharOpcion() {
        Scanner sc = new Scanner(System.in);
        int opcionUsuario;
        opcionUsuario = sc.nextInt();
        return opcionUsuario;
    }

    /**
     * Método que muestra una cabecera y un mensaje de bienvenida.
     *
     */
    public static void mostrarBienvenida() {
        System.out.println("*******************************************************");
        System.out.println("***EJERCICIOS CÓDIGO CESAR Y DISTRIBUCIÓN DE LETRAS ***");
        System.out.println("*******************************************************");
    }

    /**
     * Método que muestra el menú principal.
     *
     * @param usuario Cadena con el nombre del usuario.
     */
    public static void mostrarMenuPrincipal(String usuario) {
        System.out.println("\n****** MENÚ PRINCIPAL ******");
        System.out.println(usuario + ", escoge el ejercicio que quieres revisar:");
        System.out.println("1. Cifrado Cesar.");
        System.out.println("2. Distribución de letras.");
        System.out.println("0. Salir.");
    }

    /**
     * Método que muestra el menú del ejercicio Cifrado Cesar.
     *
     *
     * @param usuario Cadena con el nombre del usuario.
     */
    public static void mostrarMenuCesar(String usuario) {
        System.out.println("\n*** CIFRADO CESAR ***");
        System.out.println("Escoge una opción:");
        System.out.println("1. Cifrar archivos en directorio.");
        System.out.println("2. Descifrar archivos en directorio.");
        System.out.println("0. Volver al menú principal.");
    }

    /**
     * Método que muestra el menú del ejercicio Distribución de Letras
     *
     *
     * @param usuario Cadena con el nombre del usuario.
     */
    public static void mostrarMenuDistribucion(String usuario) {
        System.out.println("\n*** DISTRIBUCIÓN DE LETRAS ***");
        System.out.println("Escoge una opción:");
        System.out.println("1. Calcular distribución");
        System.out.println("2. Histograma.");
        System.out.println("0. Volver al menú principal.");
    }
}
