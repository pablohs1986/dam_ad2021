package controlador;

import dto.AmarreDAO;
import dto.BarcoDAO;
import java.util.ArrayList;
import modelo.Amarre;
import modelo.Barco;
import org.hibernate.HibernateException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Creo barcos
        Barco barco1 = new Barco();
        barco1.setEslora(40.5);
        barco1.setManga(10);
        barco1.setMatricula("1111AAA");
        barco1.setNombre("blabla1");
        
        Barco barco2 = new Barco();
        barco2.setEslora(45.5);
        barco2.setManga(13);
        barco2.setMatricula("2222ABC");
        barco2.setNombre("blabla2");
        
        Barco barco3 = new Barco();
        barco3.setEslora(35.5);
        barco3.setManga(12);
        barco3.setMatricula("3333BBB");
        barco3.setNombre("blabla3");
        
        Barco barco4 = new Barco();
        barco4.setEslora(35.5);
        barco4.setManga(12);
        barco4.setMatricula("4242BBB");
        barco4.setNombre("blabla4");
        
        // Creo amarres
        Amarre amarre1 = new Amarre();
        amarre1.setCategoria(("A"));
        amarre1.setNumero("1234");
       
        Amarre amarre2 = new Amarre();
        amarre2.setCategoria(("B"));
        amarre2.setNumero("4444");
        
        // Asigno amarres
        barco1.amarrar(amarre1);
        barco2.amarrar(amarre2);
        
        // Persisto y hago CRUD con los DTO
        BarcoDAO barcoDao = new BarcoDAO();
        AmarreDAO amarreDao = new AmarreDAO();
        
        // Inserto, los amarres van en cascada
        barcoDao.insertarBarco(barco1);
        barcoDao.insertarBarco(barco2);
        barcoDao.insertarBarco(barco3);
        barcoDao.insertarBarco(barco4);
        
        // Elimino el barco 3
        barcoDao.eliminarBarco(barco1);
        
        // Actualizo el nombre del barco 2
        barco2.setNombre("nombreActualizado");
        barcoDao.actualizarBarco(barco2);
        
        // Obtengo el barco 2
        Barco barcoSeleccionado = barcoDao.obtenerBarco("2222ABC");
        System.out.println("El nombre del barco seleccionado es " + barcoSeleccionado.getNombre());
        
        // Obtengo una lista de barcos y muestro el número de barcos
        ArrayList<Barco> barcos = barcoDao.obtenerListaBarcos();
        System.out.println("Número de barcos: " + barcos.size());
        
        // Obtengo una lista de barcos y muestro el número de barcos
        ArrayList<Barco> barcosAmarrados = barcoDao.obtenerListaBarcosAmarrados();
        System.out.println("Número de barcosAmarrados: " + barcosAmarrados.size());
        
        // Opción sin DTO
//        // Creo factoría
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        
//        // Persisto
//        session.getTransaction().begin();
//        session.persist(barco1);
//        session.persist(barco2);
//        session.getTransaction().commit();
//        session.close();
//        
//        Session session2 = HibernateUtil.getSessionFactory().openSession();
//        session2.getTransaction().begin();
//        session2.delete(barco2);
//        session2.getTransaction().commit();
//        session2.close();

        
    }
    
}
