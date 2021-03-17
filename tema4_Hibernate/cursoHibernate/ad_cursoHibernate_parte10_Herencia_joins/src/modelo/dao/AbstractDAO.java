/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.dao;

import modelo.dto.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author pablo
 */
public abstract class AbstractDAO {
    
    private Session sesion;
    
    protected void iniciarOperacion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }
    
    protected void terminarOperacion() {
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    protected void manejaExcepcion(HibernateException he) throws HibernateException {
        sesion.getTransaction().rollback();
        throw he;
    }
    
    protected Session getSession() {
        return sesion;
    }
    
    public static void almacenarEntidad(Object entidad) throws HibernateException {
        AbstractDAO dummy = new AbstractDAO() {
        };

        try {
            dummy.iniciarOperacion();
            dummy.getSession().saveOrUpdate(entidad);
            dummy.getSession().flush();
        } catch (HibernateException he) {
            dummy.manejaExcepcion(he);
        } finally {
            dummy.terminarOperacion();
        }
    }

    public static <T> T getEntidad(Serializable id, Class<T> claseEntidad) throws HibernateException {
        AbstractDAO dummy = new AbstractDAO() {
        };

        T objetoRecuperado = null;

        try {
            dummy.iniciarOperacion();
            objetoRecuperado = (T) dummy.getSession().get(claseEntidad, id);
        } catch (HibernateException he) {
            dummy.manejaExcepcion(he);
        } finally {
            dummy.terminarOperacion();
        }

        return objetoRecuperado;
    }

    public static <T> List<T> getListaEntidades(Class<T> claseEntidad) throws HibernateException {
        AbstractDAO dummy = new AbstractDAO() {
        };

        List<T> listaResultado = null;

        try {
            dummy.iniciarOperacion();
            listaResultado = dummy.getSession().createQuery("FROM " + claseEntidad.getSimpleName()).list();
        } catch (HibernateException he) {
            dummy.manejaExcepcion(he);
        } finally {
            dummy.terminarOperacion();
        }

        return listaResultado;
    }
}
