/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.util.HashMap;
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
     * Clase Main, controlador.
     *
     * @version 1.3, 09/11/2020
     * @author Pablo Herrero
     * @param args
     */
    public static void main(String[] args) {
        File directorio = new File("textos");
        CifradoCesar cesar = new CifradoCesar();

        File archivoTextoLazarilloCastellano = new File("textos" + File.separator + "lazarilloDeTormes_CASTELLANO.txt");
        File archivoTextoLazarilloIngles = new File("textos" + File.separator + "lazarilloDeTormes_INGLES.txt");
        DistribucionLetras distribucion = new DistribucionLetras();
        HashMap<Character, Integer> distribucionLetrasLazarilloCastellano;
        HashMap<Character, Integer> distribucionLetrasLazarilloIngles;

        // Interfaz de usuario
        Menu.mostrarBienvenida();

        String nombreUsuario = Menu.pedirNombre();
        Menu.mostrarMenuPrincipal(nombreUsuario);
        
        int opcionMenuPrincipal = Menu.escucharOpcion();
        int opcionCesar;
        int opcionDistribucion;

        while (opcionMenuPrincipal != 0) {
            switch (opcionMenuPrincipal) {
                // Menú Cifrado Cesar
                case 1:
                    Menu.mostrarMenuCesar(nombreUsuario);
                    opcionCesar = Menu.escucharOpcion();
                    while (opcionCesar != 0) {
                        switch (opcionCesar) {
                            
                            case 1:
                                cesar.cifrarArchivosEnDirectorioConCesar(directorio, 1);
                                Menu.mostrarMenuCesar(nombreUsuario);
                                opcionCesar = Menu.escucharOpcion();
                                break;
                            
                            case 2: {
                                    try {
                                        cesar.descifrarArchivosEnDirectorioConCesar(directorio, 1);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                Menu.mostrarMenuCesar(nombreUsuario);
                                opcionCesar = Menu.escucharOpcion();
                                break;
                            
                            default:
                                System.out.println("\nOpción incorrecta.");
                                Menu.mostrarMenuCesar(nombreUsuario);
                                opcionCesar = Menu.escucharOpcion();
                                break;
                        }
                    }
                    Menu.mostrarMenuPrincipal(nombreUsuario);
                    opcionMenuPrincipal = Menu.escucharOpcion();
                    break;
                // Menú Cifrado Cesar
                case 2:
                    Menu.mostrarMenuDistribucion(nombreUsuario);
                    opcionDistribucion = Menu.escucharOpcion();
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
                                opcionDistribucion = Menu.escucharOpcion();
                                break;

                            case 2: {
                                    try {
                                        distribucionLetrasLazarilloCastellano = distribucion.calcularDistribucionLetrasTexto(archivoTextoLazarilloCastellano);
                                        DistribucionLetras.generarHistograma(distribucionLetrasLazarilloCastellano);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                {
                                    try {
                                        distribucionLetrasLazarilloIngles = distribucion.calcularDistribucionLetrasTexto(archivoTextoLazarilloIngles);
                                        DistribucionLetras.generarHistograma(distribucionLetrasLazarilloIngles);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                Menu.mostrarMenuDistribucion(nombreUsuario);
                                opcionDistribucion = Menu.escucharOpcion();
                                break;
                            
                            default:
                                System.out.println("\nOpción incorrecta.");
                                Menu.mostrarMenuPrincipal(nombreUsuario);
                                opcionDistribucion = Menu.escucharOpcion();
                                break;
                        }
                    }
                    Menu.mostrarMenuPrincipal(nombreUsuario);
                    opcionMenuPrincipal = Menu.escucharOpcion();
                    break;

                default:
                    System.out.println("\nOpción incorrecta.");
                    Menu.mostrarMenuPrincipal(nombreUsuario);
                    opcionMenuPrincipal = Menu.escucharOpcion();
                    break;
            }

        }
        // Despedida
        System.out.println("Gracias, " + nombreUsuario + ",  hasta la próxima!");
    }
}
