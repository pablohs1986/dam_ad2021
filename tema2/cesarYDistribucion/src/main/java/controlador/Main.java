/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CifradoCesar;
import modelo.DistribucionLetras;
import vista.Menu;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File directorio = new File("textos");
        CifradoCesar cesar = new CifradoCesar();

        File archivoTextoLazarilloCastellano = new File("textos" + File.separator + "lazarilloDeTormes_CASTELLANO.txt");
        File archivoTextoLazarilloIngles = new File("textos" + File.separator + "lazarilloDeTormes_INGLES.txt");
        DistribucionLetras distribucion = new DistribucionLetras();
        HashMap<Character, Integer> distribucionLetrasLazarilloCastellano;
        HashMap<Character, Integer> distribucionLetrasLazarilloIngles;

        String nombreUsuario = Menu.pedirNombre();
        Menu.mostrarMenuPrincipal(nombreUsuario);
        int opcionMenuPrincipal = Menu.pedirOpcion();
        int opcionCesar;
        int opcionDistribucion;

        while (opcionMenuPrincipal != 0) {
            switch (opcionMenuPrincipal) {
                case 1:
                    Menu.mostrarMenuCesar(nombreUsuario);
                    opcionCesar = Menu.pedirOpcion();

                    while (opcionCesar != 0) {
                        switch (opcionCesar) {
                            case 1:
                                cesar.cifrarArchivosEnDirectorioConCesar(directorio, 1);
                                Menu.mostrarMenuCesar(nombreUsuario);
                                opcionCesar = Menu.pedirOpcion();
                                break;
                            case 2: {
                                try {
                                    cesar.descifrarArchivosEnDirectorioConCesar(directorio, 1);
                                } catch (Exception ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            Menu.mostrarMenuCesar(nombreUsuario);
                            opcionCesar = Menu.pedirOpcion();
                            break;
                            default:
                                System.out.println("\nOpción incorrecta.");
                                Menu.mostrarMenuCesar(nombreUsuario);
                                opcionCesar = Menu.pedirOpcion();
                                break;
                        }
                    }
                    Menu.mostrarMenuPrincipal(nombreUsuario);
                    opcionMenuPrincipal = Menu.pedirOpcion();
                    break;
                case 2:
                    Menu.mostrarMenuDistribucion(nombreUsuario);
                    opcionDistribucion = Menu.pedirOpcion();
                    while (opcionDistribucion != 0) {
                        switch (opcionDistribucion) {
                            case 1: {
                                try {
                                    distribucionLetrasLazarilloCastellano = distribucion.calcularDistribucionLetrasTexto(archivoTextoLazarilloCastellano);
                                    System.out.println(distribucionLetrasLazarilloCastellano);
                                } catch (Exception ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                             {
                                try {
                                    distribucionLetrasLazarilloIngles = distribucion.calcularDistribucionLetrasTexto(archivoTextoLazarilloIngles);
                                    System.out.println(distribucionLetrasLazarilloIngles);
                                } catch (Exception ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            Menu.mostrarMenuDistribucion(nombreUsuario);
                            opcionCesar = Menu.pedirOpcion();
                            break;

                            case 2:
                                System.out.println("HISTORIGRAMA");
                                Menu.mostrarMenuDistribucion(nombreUsuario);
                                opcionCesar = Menu.pedirOpcion();
                                break;
                            default:
                                System.out.println("\nOpción incorrecta.");
                                Menu.mostrarMenuCesar(nombreUsuario);
                                opcionCesar = Menu.pedirOpcion();
                                break;
                        }
                    }

                    Menu.mostrarMenuPrincipal(nombreUsuario);
                    opcionMenuPrincipal = Menu.pedirOpcion();
                    break;

                default:
                    System.out.println("\nOpción incorrecta.");
                    Menu.mostrarMenuPrincipal(nombreUsuario);
                    opcionMenuPrincipal = Menu.pedirOpcion();

//                    opcionUsuario = Menu.pedirOpcion();
                    break;
            }

        }

        System.out.println("Gracias, hasta la próxima!");

//        cesar.cifrarArchivosEnDirectorioConCesar(directorio, 1);
//        try {
//            cesar.descifrarArchivosEnDirectorioConCesar(directorio, 1);
//        } catch (Exception ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        // Apartado Distribución Letras
//        File archivoTextoLazarilloCastellano = new File("textos" + File.separator + "lazarilloDeTormes_CASTELLANO.txt");
//        File archivoTextoLazarilloIngles= new File("textos" + File.separator + "lazarilloDeTormes_INGLES.txt");
//        DistribucionLetras distribucion = new DistribucionLetras();
//        HashMap<Character, Integer> distribucionLetrasLazarilloCastellano;
//        HashMap<Character, Integer> distribucionLetrasLazarilloIngles;
//        
//        try {
//            distribucionLetrasLazarilloCastellano = distribucion.calcularDistribucionLetrasTexto(archivoTextoLazarilloCastellano);
//            System.out.println(distribucionLetrasLazarilloCastellano);
//        } catch (Exception ex) {
//            ex.getMessage();
//        }
//        
//        try {
//            distribucionLetrasLazarilloIngles = distribucion.calcularDistribucionLetrasTexto(archivoTextoLazarilloIngles);
//            System.out.println(distribucionLetrasLazarilloIngles);
//        } catch (Exception ex) {
//            ex.getMessage();
//        }
    }
}
