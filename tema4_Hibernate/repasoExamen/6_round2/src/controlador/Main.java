/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;
import modelo.UtilidadFechas;
import modelo.dao.AbstractDAO;
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
        
        // Creo clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Nombre1");
        cliente1.setApellido("Apellido1");
        cliente1.setDni("11111111A");
        
        // Creo cuentas
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setNumeroCuenta("1111111111111111");
        cuenta1.setSaldo(5000);
        cuenta1.setDivisa("€");
       
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setNumeroCuenta("2222222222222222");
        cuenta2.setSaldo(25000);
        cuenta2.setDivisa("€");
        
        // Asigno cuentas a clientes
        cliente1.addCuenta(cuenta1);
        cliente1.addCuenta(cuenta2);
        
        // Creo movimientos
        Movimiento movimiento1 = new Movimiento();
        movimiento1.setCantidad(1000);
        movimiento1.setConcepto("Movimiento1");
        movimiento1.setFecha(new Date());
 
        Movimiento movimiento2 = new Movimiento();
        movimiento2.setCantidad(2000);
        movimiento2.setConcepto("Movimiento2");
        movimiento2.setFecha(new Date());
        
        // Asigno movimientos a cuentas
        cuenta1.addMovimiento(movimiento1);
        cuenta2.addMovimiento(movimiento2);
        
        // Creo sucursales
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setNumero("1");
        sucursal1.setTelefono("111111111");
        sucursal1.setEmail("1@gmail.com");
        sucursal1.setFax("111111");
        AbstractDAO.almacenarEntidad(sucursal1);
        
        // Asigno clientes a sucursal
        sucursal1.addCliente(cliente1);
        
        // Creo direcciones
        Direccion direccion1 = new Direccion();
        direccion1.setCalle("Calle1");
        direccion1.setCp("33001");
        direccion1.setCiudad("Ciudad1");
        direccion1.setProvincia("Provincia1");
        
        // Persisto
        AbstractDAO.almacenarEntidad(direccion1);
        AbstractDAO.almacenarEntidad(cliente1);
        // Asigno sucursales a direcciones
        direccion1.setSucursal(sucursal1);
        AbstractDAO.actualizarEntidad(direccion1);
        
       

        
        
    }
    
}
