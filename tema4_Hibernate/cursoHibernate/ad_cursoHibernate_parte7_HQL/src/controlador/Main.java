/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author pablo
 */
public class Main {

    private Session sesion;

    public Main() {
        obtenNombres();
    }
    
    
    
    public static void main(String[] args) {
        new Main();
    }
    
    private void iniciaOperacion() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        sesion = sessionFactory.openSession();
        sesion.getTransaction().begin();
    }

    private void terminaOperacion() {
        sesion.getTransaction().commit();
        sesion.close();
    }

    private void obtenNombres() {
        iniciaOperacion();

        org.hibernate.Query query = sesion.createQuery("SELECT u.nombre FROM Usuario as u");

        List<String> listaResultados = query.list();

        for (int i = 0; i < listaResultados.size(); i++) {
            System.out.println("Nombre " + i + ": " + listaResultados.get(i));
        }

        terminaOperacion();
    }
    
    private void obtenNombresYPasswords() {
        iniciaOperacion();

        org.hibernate.Query query = sesion.createQuery("SELECT u.nombre, u.password FROM Usuario as u ");

        List<Object[]> listaResultados = query.list();

        for (int i = 0; i < listaResultados.size(); i++) {
            System.out.println("Nombre " + i + ": " + listaResultados.get(i)[0] + ", password: " + listaResultados.get(i)[1]);
        }

        terminaOperacion();
    }
}
