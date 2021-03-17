/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import modelo.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pablo Herrero
 */
public abstract class AbstractDAO {

    private Session sesion;

    /**
     * Método que abre la sesión e inicia la transancción.
     *
     */
    protected void iniciarOperacion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }

    /**
     * Método que hace el commit de la transacción y finaliza la sesión.
     *
     */
    protected void terminarOperacion() {
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Método que realiza un rollback si se detecta una excepción.
     *
     * @param hibernateException HibernateException
     */
    protected void manejarExcepcion(HibernateException hibernateException) throws HibernateException {
        sesion.getTransaction().rollback();
        throw hibernateException;
    }

    /**
     * Método que retorna la sesión en curso.
     *
     * @return Session
     */
    protected Session getSession() {
        return sesion;
    }

    /**
     * Método que almacena/actualiza una entidad.
     *
     * @param entidad Objeto a almacenar.
     */
    public static void almacenarEntidad(Object entidad) throws HibernateException {
        AbstractDAO dummy = new AbstractDAO() {
        };

        try {
            dummy.iniciarOperacion();
            dummy.getSession().persist(entidad);
        } catch (HibernateException he) {
            dummy.manejarExcepcion(he);
        } finally {
            dummy.terminarOperacion();
        }
    }

    /**
     * Método que retorna la entidad deseada a partir del id y de la clase de
     * entidad que se pasa como parámetro.
     *
     * @param <T> Tipo genérico.
     * @param id Id a buscar.
     * @param claseEntidad Clase a buscar.
     * @return La entiedad deseada.
     */
    public static <T> T getEntidad(Serializable id, Class<T> claseEntidad) throws HibernateException {
        AbstractDAO dummy = new AbstractDAO() {
        };
        T objetoRecuperado = null;

        try {
            dummy.iniciarOperacion();
            objetoRecuperado = (T) dummy.getSession().get(claseEntidad, id);
        } catch (HibernateException he) {
            dummy.manejarExcepcion(he);
        } finally {
            dummy.terminarOperacion();
        }
        return objetoRecuperado;
    }

    /**
     * Método que elimina la entidad que se pasa como parámetro.
     *
     * @param object
     */
    public static void deleteEntidad(Object object) throws HibernateException {
        AbstractDAO dummy = new AbstractDAO() {
        };

        try {
            dummy.iniciarOperacion();
            dummy.getSession().delete(object);
        } catch (HibernateException he) {
            dummy.manejarExcepcion(he);
        } finally {
            dummy.terminarOperacion();
        }
    }

    /**
     * Método que retorna un lista de entidades del tipo que se pasa como
     * parámetro.
     *
     * @param <T> Tipo genérico.
     * @param claseEntidad Clase a buscar.
     * @return Lista de entidades del tipo buscado.
     */
    public static <T> List<T> getListaEntidades(Class<T> claseEntidad) throws HibernateException {
        AbstractDAO dummy = new AbstractDAO() {
        };
        List<T> listaResultado = null;

        try {
            dummy.iniciarOperacion();
            listaResultado = dummy.getSession().createQuery("FROM " + claseEntidad.getSimpleName()).list();
        } catch (HibernateException he) {
            dummy.manejarExcepcion(he);
        } finally {
            dummy.terminarOperacion();
        }
        return listaResultado;
    }
}
