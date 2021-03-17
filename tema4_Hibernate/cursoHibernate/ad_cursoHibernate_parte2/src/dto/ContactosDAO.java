/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import java.util.List;
import modelo.Contacto;
import modelo.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pablo
 */
public class ContactosDAO {
    private Session session;
    private Transaction transaction;
    
    private void iniciarOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }
    
    private void manejarExcepcion(HibernateException he) throws HibernateException {
        transaction.rollback();
        throw new HibernateException("Ocurrió un error.");
    }
    
    // CRUD
    
    public long guardarContacto(Contacto contacto) {
        long id = 0;
        iniciarOperacion();
        id = (long) session.save(contacto);
        transaction.commit();
        session.close();
        return id;
    }
    
    public void actualizarContacto(Contacto contacto) throws HibernateException {
        iniciarOperacion();
        session.update(contacto);
        transaction.commit();
        session.close();
    }
    
    public void eliminarContacto(Contacto contacto) throws HibernateException {
        iniciarOperacion();
        session.delete(contacto);
        transaction.commit();
        session.close();
    }
    
    public Contacto obtenerContacto(long idContacto) throws HibernateException {
        Contacto contacto = null;
        iniciarOperacion();
        contacto = (Contacto) session.get(Contacto.class, idContacto);
        session.close();
        return contacto;
    }
    
    public List<Contacto> obtenerListaContactos() throws HibernateException {
        List<Contacto> listaContactos = null;
        iniciarOperacion();
        listaContactos = session.createQuery("from Contacto").list();
        session.close();
        return listaContactos;
    }
}
