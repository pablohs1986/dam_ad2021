/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.Date;
import java.util.List;
import modelo.dao.interfaces.InterfaceClienteDAO;
import modelo.dto.Cliente;
import modelo.dto.Cuenta;
import modelo.dto.Movimiento;

/**
 *
 * @author Pablo Herrero
 */
public class ClienteDAO extends AbstractDAO implements InterfaceClienteDAO {
    
    /**
     * Método que realiza una traspaso entre cuentas del mismo cliente,
     * recibiendo la cuenta origen/destino y la cantidad a traspasar. El método
     * comprueba que las cuentas sean del mismo cliente para poder realizar
     * la operación, además de generar un nuevo movimiento para la operación.
     *
     * @param cuentaOrigen
     * @param cuentaDestino
     * @param cantidad
     * @return boolean indicando el éxito/fracaso de la operación
     */
    @Override
    public boolean traspaso(Cuenta cuentaOrigen, Cuenta cuentaDestino, double cantidad) {
        boolean isTraspaso;
        boolean isTraspasoRealizado;
        String tipoOperacion = "Traspaso";
        
        if(cuentaOrigen.getCliente().getId() == cuentaDestino.getCliente().getId()) {
            isTraspaso = true;
            if (isTraspaso && cuentaOrigen.getSaldo() >= cantidad) {
                cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidad);
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidad);
                isTraspasoRealizado = true;
                actualizarEntidad(cuentaOrigen);
                actualizarEntidad(cuentaDestino);
                generarMovimiento(tipoOperacion, cuentaOrigen, cuentaDestino, cantidad);
            } else {
                isTraspasoRealizado = false;
            }
        } else {
            isTraspaso = false;
            isTraspasoRealizado = false;
        }
        return isTraspasoRealizado;
    }
    
    /**
     * Método que realiza una transferencia entre cuentas de distintos clientes,
     * recibiendo la cuenta origen/destino y la cantidad a transferir. El método
     * comprueba que las cuentas sean de clientes distintos para poder realizar
     * la operación, además de generar un nuevo movimiento para la operación.
     *
     * @param cuentaOrigen
     * @param cuentaDestino
     * @param cantidad
     * @return boolean indicando el éxito/fracaso de la operación
     */
    @Override
    public boolean transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, double cantidad) {
        boolean isTransferencia;
        boolean isTraspasoRealizado;
        String tipoOperacion = "Transferencia";

        if (cuentaOrigen.getCliente().getId() != cuentaDestino.getCliente().getId()) {
            isTransferencia = true;
            if (isTransferencia && cuentaOrigen.getSaldo() >= cantidad) {
                cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidad);
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidad);
                isTraspasoRealizado = true;
                actualizarEntidad(cuentaOrigen);
                actualizarEntidad(cuentaDestino);
                generarMovimiento(tipoOperacion, cuentaOrigen, cuentaDestino, cantidad);
            } else {
                isTraspasoRealizado = false;
            }
        } else {
            isTransferencia = false;
            isTraspasoRealizado = false;
        }
        return isTraspasoRealizado;
    }
    
    /**
     * Método que genera un movimiento a partir de los parámetros recibidos.
     *
     * @param tipoOperacion
     * @param cuentaOrigen
     * @param cuentaDestino
     * @param cantidad
     * @return Movimiento generado
     */
    private Movimiento generarMovimiento(String tipoOperacion, Cuenta cuentaOrigen, Cuenta cuentaDestino, double cantidad) {
        iniciarOperacion();
        Movimiento nuevoMovimiento = new Movimiento();
        nuevoMovimiento.setCantidad(cantidad);
        nuevoMovimiento.setConcepto(tipoOperacion + " de " 
                + cuentaOrigen.getNumeroCC() + " a " 
                + cuentaDestino.getNumeroCC());
        nuevoMovimiento.setFecha(new Date());
        cuentaOrigen.addMovimiento(nuevoMovimiento);
        cuentaDestino.addMovimiento(nuevoMovimiento);
        terminarOperacion();
        return nuevoMovimiento;
    }
    
    /**
     * Método que crea una cuenta para el cliente indicado, asociando una
     * divisa y un saldo inicial.
     *
     * @param cliente
     * @param divisa
     * @param saldoIniciarl
     */
    @Override
    public void crearCuenta(Cliente cliente, String divisa, double saldoIniciarl) {
        iniciarOperacion();
        Cuenta cuenta = new Cuenta();
        cuenta.setDivisa(divisa);
        cuenta.setNumeroCC(generarNumeroCuenta());
        cuenta.setSaldo(saldoIniciarl);
        cliente.addCuenta(cuenta);
        actualizarEntidad(cliente);
        terminarOperacion();
    }
    
    /**
     * Método que genera un nuevo número de cuenta a partir del último número
     * de cuenta detectado en la base de datos.
     *
     * @return Nuevo número de cuenta
     */
    private String generarNumeroCuenta() {
        List<Cuenta> cuentas = getListaEntidades(Cuenta.class);
        String ultimoNumeroDeCuenta = cuentas.get(cuentas.size()-1).getNumeroCC();
        long ultimoNumeroDeCuentaNumber= Long.parseLong(ultimoNumeroDeCuenta);
        long nuevoNumeroCuenta = ultimoNumeroDeCuentaNumber+1;
        String nuevoNumeroDeCuenta = String.valueOf(nuevoNumeroCuenta);
        return nuevoNumeroDeCuenta;
    }
}
