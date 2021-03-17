/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import java.util.ArrayList;
import modelo.Amarre;
import modelo.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pablo
 */
public class AmarreDAO {
    
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
    * Método que permite insertar un amarre
    *
    *
     * @param amarre
    */
    public void insertarAmarre(Amarre amarre) {
        try {
            this.iniciarOperacion();
            session.persist(amarre);
            transaction.commit();
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }
    
    /**
     * Método que permite hacer un update de un amarre
     *
     *
     * @param amarre
     */
    public void actualizarAmarre(Amarre amarre) {
        try {
            this.iniciarOperacion();
            session.update(amarre);
            transaction.commit();
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    /**
     * Método que permite eliminar un amarre
     *
     *
     * @param amarre
     */
    public void eliminarAmarre(Amarre amarre) {
        try {
            this.iniciarOperacion();
            session.delete(amarre);
            transaction.commit();
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    /**
     * Método que permite hacer un select de un amarre por su id.
     *
     *
     * @param id
     * @return Amarre
     */
    public Amarre obtenerAmarre(int id) {
        Amarre amarre = null;
        try {
            this.iniciarOperacion();
            amarre = (Amarre) session.get(Amarre.class, id);
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return amarre;
    }

    /**
     * Método que retorna un ArrayList con todos los objetos amarre.
     *
     *
     * @return 
     */
    public ArrayList<Amarre> obtenerListaAmarres() {
        ArrayList<Amarre> amarres = null;
        try {
            this.iniciarOperacion();
            amarres = (ArrayList<Amarre>) session.createQuery("from Amarre").list();
        } catch (HibernateException he) {
            manejarExcepcion(he);
            throw he;
        } finally {
            session.close();
        } 
        return amarres;
    }
}
