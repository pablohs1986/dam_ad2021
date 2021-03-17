/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.Date;
import java.util.List;
import modelo.dao.interfaces.interfaceBilleteDAO;
import modelo.dto.Asiento;
import modelo.dto.Billete;
import modelo.dto.Pasajero;
import modelo.dto.Vuelo;

/**
 *
 * @author Pablo Herrero
 */
public class BilleteDAO extends AbstractDAO implements interfaceBilleteDAO {
    
    /**
     * Método que calcula el precio final para el billete del cual recibe el
     * id como parámetro. Ese calculo se realiza teniendo en cuenta los
     * pasajeros asociados al billete.
     *
     * @param codigoBillete
     * @return Valor del precio final del billete.
     */
    @Override
    public double calcularPrecioFinal(long codigoBillete) {
        Billete billete = retornarBilletePorId(codigoBillete);
        double precioFinal = 0;
        
        List<Pasajero> pasajerosAsociadosAlBillete = retornarPasajerosAsociadosABillete(codigoBillete);
        
        for (Pasajero pasajero : pasajerosAsociadosAlBillete) {
            if(pasajero.isIsNinio()){
                precioFinal += billete.getPrecioPasajero() * ((double) billete.getDescuentoNinios() / 100);
            } else {
                precioFinal += billete.getPrecioPasajero();
            }
        }
        
        iniciarOperacion();
        billete.setPrecioFinal(precioFinal);
        actualizarEntidad(billete);
        terminarOperacion();
        
        return billete.getPrecioFinal();
    }
    
    /**
     * Método que retorna los pasajeros asociados a un billete del que se pasa
     * el id como parámetro.
     *
     * @param codigoBillete
     * @return Lista de pasajeros asociados al billete.
     */
    @Override
    public List<Pasajero> retornarPasajerosAsociadosABillete(long codigoBillete) {
        Billete billete = retornarBilletePorId(codigoBillete);
        List<Pasajero> pasajeros = billete.getPasajeros();
        return pasajeros;
    }
    
    /**
     * Método que retorna el billete asociado al id que se pasa como parámetro.
     *
     * @param codigoBillete
     * @return Billete asociado a la id indicada.
     */
    @Override
    public Billete retornarBilletePorId(long codigoBillete) {
        Billete billete = null;

        iniciarOperacion();
        billete = (Billete) getSession()
                .createQuery(" FROM Billete WHERE codigo = :codigoBillete")
                .setParameter("codigoBillete", codigoBillete)
                .uniqueResult();
        terminarOperacion();
        
        return billete;
    }
    
    /**
     * Método que asigna/reasigna asientos a una familia completa (6 plazas), 
     * recibiendo como parámetros el código del billete asociado a los pasajeros
     * sobre los que se quiere realizar la operación, el tipo de asiento a asignar
     * y la fila deseada para la asignación
     *
     * @param codigoBillete
     * @param tipo
     * @param fila
     */
    @Override
    public void asignarAsientosAPasajerosDeBillete(long codigoBillete, String tipo, int fila) {
        iniciarOperacion();
        Billete billete = getEntidad((long)2, Billete.class);
        List<Pasajero> pasajeros = billete.getPasajeros();
        Vuelo vuelo = getEntidad((long)billete.getVuelo().getCodigo(), Vuelo.class);
        List<Asiento> asientos = vuelo.getAsientos();
        final int contadorPasajerosBillete = (int) pasajeros.stream().distinct().count(); // Cuenta los registros únicos en la lista de pasajeros
        boolean isAsientoDisponible = comprobarTipoYDisponibilidadAsiento(vuelo, tipo, fila, contadorPasajerosBillete);
        
        if(isAsientoDisponible) {
            for (Pasajero pasajero : pasajeros) {
                for (Asiento asiento : asientos) {
                    if (!asiento.isIsOcupado() 
                            && asiento.getTipo().equalsIgnoreCase(tipo) 
                            && asiento.getFila() == fila
                            && pasajero.getAsiento() == null) {
                        System.out.println(pasajero.getNombre() + ">>> NO TIENE asiento, ASIGNANDO");
                        pasajero.setAsiento(asiento);
                        asiento.setPasajero(pasajero);
                        asiento.setIsOcupado(true);
                        actualizarEntidad(asiento);
                        actualizarEntidad(pasajero);
                        System.out.println(">>> ASIGNADO asiento "
                                + asiento.getFila() + asiento.getLetra()
                                + " al pasajero " + pasajero.getNombre()
                                + " con código " + pasajero.getCodigo());
                    }
                }
            }
        } else {
            System.out.println("TIPO de asiento NO ENCONTRADO, inténtelo de nuevo.");
        }
        actualizarEntidad(billete);
        actualizarEntidad(vuelo);
        terminarOperacion();
    }
    
    /**
     * Método que comprueba el tipo y la disponibilidad de asientos para el
     * vuelo que recibe como parámetro, el tipo de asiento, la fila y el número
     * de asientos requeridos, teniendo un límite de plazas a asignar de 6.
     *
     * @param vuelo
     * @param tipo
     * @param fila
     * @param numeroAsientosSolicitados
     * @return Boolean indicando si los asientos están disponibles o no.
     */
    private boolean comprobarTipoYDisponibilidadAsiento(Vuelo vuelo, String tipo, int fila, int numeroAsientosSolicitados) {
        final int LIMITE_ASIENTOS = 6;
        
        if(numeroAsientosSolicitados <= LIMITE_ASIENTOS) {
            switch (tipo.toUpperCase()) {
                case "TURISTA":
                    iniciarOperacion();
                    List<Asiento> asientosTurista = getSession()
                            .createQuery(" FROM Asiento as a WHERE a.tipo = :tipo AND a.fila = :fila")
                            .setParameter("tipo", "Turista")
                            .setParameter("fila", fila)
                            .list();
                    System.out.println(">>> " + asientosTurista.size() + " asientos tipo turista encontrados en la fila " + fila);
                    return asientosTurista.size()>=numeroAsientosSolicitados ? true : false;
                case "BUSINESS":
                    iniciarOperacion();
                    List<Asiento> asientosBusiness = getSession()
                            .createQuery(" FROM Asiento as a WHERE a.tipo = :tipo AND a.fila = :fila")
                            .setParameter("tipo", "Business")
                            .setParameter("fila", fila)
                            .list();
                    return asientosBusiness.size()>=numeroAsientosSolicitados ? true : false;
                default:
                    System.out.println("NO hay asientos disponibles para la fila " + fila + " del tipo " + tipo);
                    return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Método que crea un billete para la lista de pasajeros que recibe,
     * según el precio por pasajero y el descuento a aplicar a niños indicados.
     *
     * @param precioPasajero
     * @param descuentoNinios
     * @param pasajeros
     * @return Código del billete creado.
     */
    @Override
    public long crearBillete(Double precioPasajero, int descuentoNinios, List<Pasajero> pasajeros) {
        iniciarOperacion();
        Billete nuevoBillete = new Billete();
        nuevoBillete.setPrecioPasajero(precioPasajero);
        nuevoBillete.setDescuentoNinios(descuentoNinios);
        nuevoBillete.setFechaEmite(new Date());
        for (Pasajero pasajero : pasajeros) {
            nuevoBillete.addPasajero(pasajero);
        }
        almacenarEntidad(nuevoBillete);
        return nuevoBillete.getCodigo();
    }
}
