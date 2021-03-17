/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import modelo.Estudiante;
import modelo.HibernateUtil;
import modelo.Materia;
import org.hibernate.Session;

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Creo estudiantes
        Estudiante estudiante1 = new Estudiante();
        Estudiante estudiante2 = new Estudiante();
        
        estudiante1.setNombre("Pepe");
        estudiante2.setNombre("María");
        
        // Creo materias
        Materia materia1 = new Materia();
        Materia materia2 = new Materia();
        Materia materia3 = new Materia();
        Materia materia4 = new Materia();
        Materia materia5 = new Materia();
        Materia materia6 = new Materia();
        
        materia1.setNombre("AD");
        materia2.setNombre("PMDM");
        materia3.setNombre("SGE");
        materia4.setNombre("EIE");
        materia5.setNombre("DI");
        materia6.setNombre("PSP");
        
        // Asigno materias
        estudiante1.addMateria(materia1);
        estudiante1.addMateria(materia2);
        estudiante1.addMateria(materia3);
        
        estudiante2.addMateria(materia4);
        estudiante2.addMateria(materia5);
        estudiante2.addMateria(materia6);
        
        
        // Inicio sesión y persisto
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        sesion.save(estudiante1);
        sesion.save(estudiante2);
        sesion.getTransaction().commit();
        sesion.close();
        
        // Borro un estudiante
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        sesion.delete(estudiante1);
        sesion.getTransaction().commit();
        sesion.close();
        
                
    }
    
}
