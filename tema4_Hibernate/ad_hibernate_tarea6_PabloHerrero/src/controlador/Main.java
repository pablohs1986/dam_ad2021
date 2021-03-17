/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.UtilidadFechas;
import modelo.dao.AbstractDAO;
import modelo.dao.ClienteDAO;
import modelo.dto.Cliente;
import modelo.dto.Cuenta;
import modelo.dto.Direccion;
import modelo.dto.Movimiento;
import modelo.dto.Sucursal;

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
        // Creo cuentas
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setDivisa("€");
        cuenta1.setNumeroCC("1111111111111111");
        cuenta1.setSaldo(800000);
        
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setDivisa("€");
        cuenta2.setNumeroCC("2222222222222222");
        cuenta2.setSaldo(6000);
        
        Cuenta cuenta3 = new Cuenta();
        cuenta3.setDivisa("€");
        cuenta3.setNumeroCC("3333333333333333");
        cuenta3.setSaldo(50000);
        
        Cuenta cuenta4 = new Cuenta();
        cuenta4.setDivisa("€");
        cuenta4.setNumeroCC("4444444444444444");
        cuenta4.setSaldo(12000);
        
        // Creo clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Nombre1");
        cliente1.setApellido("Apellido1");
        
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Nombre2");
        cliente2.setApellido("Apellido2");
        
        // Asigno cuentas a clientes
        cliente1.addCuenta(cuenta1);
        cliente1.addCuenta(cuenta2);
        
        cliente2.addCuenta(cuenta3);
        cliente2.addCuenta(cuenta4);
        
        // Creo movimientos
        Movimiento movimiento1 = new Movimiento();
        movimiento1.setCantidad(1000);
        movimiento1.setConcepto("Concepto1");
        movimiento1.setFecha(UtilidadFechas.generarFecha(2021, 3, 5));
        
        // Asigno movimientos a cuentas
        cuenta2.addMovimiento(movimiento1);
        
        // Creo sucursales
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setNumero(1);
        sucursal1.setTelefono("111111111");
        sucursal1.seteMail("sucursal1@gmail.com");
        sucursal1.setFax("01010101");
        
        Sucursal sucursal2 = new Sucursal();
        sucursal2.setNumero(2);
        sucursal2.setTelefono("222222222");
        sucursal2.seteMail("sucursal2@gmail.com");
        sucursal2.setFax("02020202");
        
        // Asigno clientes a sucursales
        sucursal1.addCliente(cliente1);
        sucursal2.addCliente(cliente2);
        
        // Creo direcciones
        Direccion direccion1 = new Direccion();
        direccion1.setCalle("direccion1");
        direccion1.setCp("33006");
        direccion1.setCiudad("Ciudad1");
        direccion1.setProvincia("Provincia1");
        
        Direccion direccion2 = new Direccion();
        direccion2.setCalle("direccion2");
        direccion2.setCp("33007");
        direccion2.setCiudad("Ciudad2");
        direccion2.setProvincia("Provincia2");
        
        // Asocio sucursales a direcciones y persisto estas últimas (no van en cascada
        // con ninguna otra entidad
        direccion1.setSucursal(sucursal1);
        direccion2.setSucursal(sucursal2);
        
        // Persisto
        AbstractDAO.almacenarEntidad(sucursal1); // En cascada se almacenan cuentas y clientes
        AbstractDAO.almacenarEntidad(sucursal2); // En cascada se almacenan cuentas y clientes
        AbstractDAO.almacenarEntidad(direccion1); // Persisten aparte por no ir en cascada con ninguna otra entidad 
        AbstractDAO.almacenarEntidad(direccion2); // Persisten aparte por no ir en cascada con ninguna otra entidad
        
        ////// OPERACIONES //////
        ClienteDAO clienteDAO = new ClienteDAO();
        // Traspaso de cuenta1 a cuenta2
        clienteDAO.traspaso(cuenta1, cuenta2, 1000);
        
        // Transferencia de cuenta1 a cuenta 4
        clienteDAO.transferencia(cuenta1, cuenta4, 10000);
        
        // Creo nueva cuenta para el cliente 2
        clienteDAO.crearCuenta(cliente2, "$", 5);

    }
    
}
