/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import java.util.ArrayList;
import modelo.Barco;
import modelo.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pablo
 */
public class BarcoDAO {
    
    private Session session;
    private Transaction transaction;
    
    /**
     * Método que inicia una factoría.
     *
     *
     */
    private void iniciarOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }
    
    /**
     * Método que maneja las excepciones generadas en las operaciones CRUD
     *
     * @throws HibernateException
     */
    private void manejarExcepcion(HibernateException he) throws HibernateException {
        transaction.rollback();
        throw new HibernateException("Error en la capa de acceso a datos.", he);
    }
    
    // CRUD
    /**
     * Método que permite insertar un barco.
     *
     *
     * @param barco
     */
    public void insertarBarco(Barco barco) {
        try{
            this.iniciarOperacion();
            session.persist(barco);
            transaction.commit();
        } catch(HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }
    
    /**
     * Método que permite hacer un update de un barco.
     *
     *
     * @param barco
     */
    public void actualizarBarco(Barco barco) {
        try {
            this.iniciarOperacion();
            session.update(barco);
            transaction.commit();
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }
    
    /**
     * Método que permite eliminar un barco.
     *
     *
     * @param barco
     */
    public void eliminarBarco(Barco barco) {
        try {
            this.iniciarOperacion();
            session.delete(barco);
            transaction.commit();
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }
    
    /**
     * Método que permite seleccionar un barco a partir de su matrícula.
     *
     *
     * @param matricula
     * @return Barco
     */
    public Barco obtenerBarco(String matricula) {
        Barco barco = null;
        try {
            this.iniciarOperacion();
            barco = (Barco) session.get(Barco.class, matricula);
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return barco;
    }
    
    /**
     * Método que retorna un ArrayList de objetos Barco.
     *
     *
     * @return 
     */
    public ArrayList<Barco> obtenerListaBarcos() {
        ArrayList<Barco> barcos = null;
        try {
            this.iniciarOperacion();
            barcos = (ArrayList<Barco>) session.createQuery("from Barco").list();
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return barcos;
    }
    
    /**
     * Método que retorna un ArrayList de objetos Barco amarrados.
     * TODO rehacer cogiendo el objeto, no con query
     *
     * @return
     */
    public ArrayList<Barco> obtenerListaBarcosAmarrados() {
        ArrayList<Barco> barcos = null;
        try {
            this.iniciarOperacion();
            barcos = (ArrayList<Barco>) session.createQuery("from Barco where amarre_id IS NOT NULL").list();
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return barcos;
    }
    
    public ArrayList<Barco> obtenerListaBarcosAmarrados2() {
        ArrayList<Barco> barcosAmarrados = obtenerListaBarcos();
        
        for (Barco barco : barcosAmarrados) {
            if (barco.getAmarre() == null) {
                barcosAmarrados.remove(barco);
            }
        }
        return barcosAmarrados;
        
    }
}
