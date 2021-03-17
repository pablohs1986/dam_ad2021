/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.HibernateUtil;
import modelo.UtilidadFechas;
import modelo.dao.AbstractDAO;
import modelo.dto.Agenda;
import modelo.dto.Cita;
import modelo.dto.Especialista;
import modelo.dto.TarjetaBeneficiario;
import modelo.dto.Titular;
import org.hibernate.Session;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ////// PERSISTENCIA //////
        // Creo titulares
        Titular titular1 = new Titular();
        titular1.setNombre("Titular1");
        titular1.setApellido("Apellido1");
        titular1.setFechaNacimiento(UtilidadFechas.generarFecha(1986, 10, 24));
        
        Titular titular2 = new Titular();
        titular2.setNombre("Titular2");
        titular2.setApellido("Apellido2");
        titular2.setFechaNacimiento(UtilidadFechas.generarFecha(1971, 11, 4));
        
        // Creo tarjetas
        TarjetaBeneficiario tarjeta1 = new TarjetaBeneficiario();
        tarjeta1.setNumeroTarjeta("1");
        tarjeta1.setNombre("Beneficiario1");
        tarjeta1.setApellido("Apellido1");
        tarjeta1.setFechaNacimiento(UtilidadFechas.generarFecha(2000, 10, 10));
        tarjeta1.setRelacionTitular("Hijo");
        
        TarjetaBeneficiario tarjeta2 = new TarjetaBeneficiario();
        tarjeta2.setNumeroTarjeta("2");
        tarjeta2.setNombre("Beneficiario2");
        tarjeta2.setApellido("Apellido2");
        tarjeta2.setFechaNacimiento(UtilidadFechas.generarFecha(1970, 7, 11));
        tarjeta2.setRelacionTitular("Esposo");
        
        // Asigno las tarjetas a los titulares
        titular1.addTarjeta(tarjeta1);
        titular2.addTarjeta(tarjeta2);
        
        // Creo citas
        Cita cita1 = new Cita();
        cita1.setDia(1);
        cita1.setTramo(1);
        
        Cita cita2 = new Cita();
        cita2.setDia(2);
        cita2.setTramo(2);
        
        // Asigno citas a tarjetas
        tarjeta1.addCita(cita1);
        tarjeta2.addCita(cita2);
        
        // Creo agendas y las persisto
        Agenda agenda1 = new Agenda();
        agenda1.setNombre("Agenda1");
        agenda1.setHoraInicio(9);
        agenda1.setTotalTramos(30);
        
        Agenda agenda2 = new Agenda();
        agenda2.setNombre("Agenda2");
        agenda2.setHoraInicio(9);
        agenda2.setTotalTramos(30);
        
//        AbstractDAO.almacenarEntidad(agenda1);
//        AbstractDAO.almacenarEntidad(agenda2);
        
        // Creo especialistas
        Especialista especialista1 = new Especialista();
        especialista1.setnColegiado("1111");
        especialista1.setNombre("Especialista1");
        especialista1.setEspecialidad("Especialidad1");
        
        Especialista especialista2 = new Especialista();
        especialista2.setnColegiado("2222");
        especialista2.setNombre("Especialista2");
        especialista2.setEspecialidad("Especialidad2");
        
        // Asigno agendas a los especialistas y viceversa
        especialista1.setAgenda(agenda1);
        especialista2.setAgenda(agenda2);
        agenda1.setEspecialista(especialista1);
        agenda2.setEspecialista(especialista2);
        
        // Persisto los especialistas, las agendas van en cascada
        AbstractDAO.almacenarEntidad(especialista1);
        AbstractDAO.almacenarEntidad(especialista2);
        
        // Asigno citas a agendas
        agenda1.addCitas(cita1);
        agenda2.addCitas(cita2);
        
        // Persisto
        AbstractDAO.almacenarEntidad(titular1);
        AbstractDAO.almacenarEntidad(titular2);
        
        
        ////// OPERACIONES //////
        // PUNTO 4:	Crear un método main() que pruebe a persistir, modificar y borrar la clase titular en una sesión.
        Session sesion;
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
        
        // Creo titular
        Titular titularPunto4 = new Titular();
        titularPunto4.setNombre("titularPunto4");
        titularPunto4.setApellido("titularPunto4Apellido");
        titularPunto4.setFechaNacimiento(UtilidadFechas.generarFecha(2000, 1, 1));
        
        // Lo persisto
        AbstractDAO.almacenarEntidad(titularPunto4);
        Titular titular = AbstractDAO.getEntidad(titularPunto4.getId(), Titular.class);
        
        // Lo modifico
        titular.setNombre("titularPunto4Modificado");
        
        // Actualizo
        AbstractDAO.actualizarEntidad(titular);
        
        // Finalizo la sesión
        sesion.getTransaction().commit();
        sesion.close();
        
        // NUEVA CITA
        // Inicio los días y tramos de las agendas

    }
    
}
