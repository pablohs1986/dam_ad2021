/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.interfaces;

import java.util.List;
import modelo.dto.Billete;
import modelo.dto.Pasajero;

/**
 *
 * @author Pablo Herrero
 */
public interface interfaceBilleteDAO {

    public double calcularPrecioFinal(long codigoBillete);

    public List<Pasajero> retornarPasajerosAsociadosABillete(long codigoBillete);

    public Billete retornarBilletePorId(long codigoBillete);

    public void asignarAsientosAPasajerosDeBillete(long codigoBillete, String tipo, int fila);

    public long crearBillete(Double precioPasajero, int descuentoNinios, List<Pasajero> pasajeros);

}
