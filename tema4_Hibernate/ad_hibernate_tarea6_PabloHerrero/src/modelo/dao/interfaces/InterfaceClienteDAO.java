/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.interfaces;

import modelo.dto.Cliente;
import modelo.dto.Cuenta;

/**
 *
 * @author Pablo Herrero
 */
public interface InterfaceClienteDAO {

    public boolean transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, double cantidad);

    public void crearCuenta(Cliente cliente, String divisa, double saldoIniciarl);

    public boolean traspaso(Cuenta cuentaOrigen, Cuenta cuentaDestino, double cantidad);

}
