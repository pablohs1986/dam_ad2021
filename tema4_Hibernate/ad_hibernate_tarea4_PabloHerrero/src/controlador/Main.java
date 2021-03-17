/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.AbstractDAO;
import modelo.dao.BilleteDAO;
import modelo.dao.LogicaVueloDAO;
import modelo.dto.Aeropuerto;
import modelo.dto.Asiento;
import modelo.dto.Billete;
import modelo.dto.Cliente;
import modelo.dto.Pasajero;
import modelo.dto.Vuelo;

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
        // Creo billetes
        Billete billete1 = new Billete();
        billete1.setPrecioPasajero(100);
        billete1.setDescuentoNinios(10);
        billete1.setFechaEmite(new Date());
        
        // Creo clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Cliente1");
        cliente1.seteMail("cliente1@gmail.com");
        
        // Creo Pasajeros
        Pasajero pasajero1 = new Pasajero();
        pasajero1.setDni("11111111A");
        pasajero1.setNombre("Pasajero1");
        pasajero1.setFechaNacimiento(generarFecha(1986, 10, 24));
        pasajero1.setIsNinio(false);
        
        // Creo Asientos
        Asiento asiento1 = new Asiento();
        asiento1.setFila(1);
        asiento1.setLetra("A");
        asiento1.setTipo("Turista");
        
        // Asigno asientos a pasajeros y viceversa
        pasajero1.setAsiento(asiento1);
        asiento1.setPasajero(pasajero1);
        
        // Asigno pasajeros a billetes
        billete1.addPasajero(pasajero1);
        
        // Asigno billetes a clientes
        cliente1.addBillete(billete1);
        
        // Creo vuelos
        Vuelo vuelo1 = new Vuelo();
        vuelo1.setFechaVuelo(generarFecha(2021, 03, 10));
        
        // Asigno vuelos a billetes
        billete1.setVuelo(vuelo1);
        
        // Asigno asientos a vuelos
        vuelo1.addAsiento(asiento1);
        
        // Creo aeropuertos
        Aeropuerto aeropuerto1 = new Aeropuerto();
        aeropuerto1.setCiudad("Ciudad1");
        
        Aeropuerto aeropuerto2 = new Aeropuerto();
        aeropuerto2.setCiudad("Ciudad2");
        
        Aeropuerto aeropuerto3 = new Aeropuerto();
        aeropuerto3.setCiudad("Ciudad3");
        
        Aeropuerto aeropuerto4 = new Aeropuerto();
        aeropuerto4.setCiudad("Ciudad4");
        
        // Asigno aeropuertos a vuelos y viceversa
        vuelo1.setOrigen(aeropuerto1);
        aeropuerto1.setVueloSalida(vuelo1);
        vuelo1.setDestino(aeropuerto2);
        aeropuerto2.setVueloLlegada(vuelo1);
        
        // Persisto
        AbstractDAO.almacenarEntidad(cliente1);
        
        ////// OPERACIONES //////
        // Creo cliente para la familia
        Cliente clienteFamiliaHerrero = new Cliente();
        clienteFamiliaHerrero.setNombre("Familia Herrero");
        clienteFamiliaHerrero.seteMail("familiaherrero@gmail.com");
        AbstractDAO.almacenarEntidad(clienteFamiliaHerrero);
        
        // Creo billete para familia de 6 personas, incluidos 2 niños
        BilleteDAO billeteDAO = new BilleteDAO();
        
        // Creo lista de pasajeros para la familia
        List<Pasajero> pasajerosFamiliaHerrero = new ArrayList<Pasajero>();
        Pasajero padreHerrero = new Pasajero();
        padreHerrero.setDni("22222222Z");
        padreHerrero.setNombre("Padre Herrero");
        padreHerrero.setFechaNacimiento(generarFecha(1972, 10, 24));
        padreHerrero.setIsNinio(false);
        
        Pasajero madreHerrero = new Pasajero();
        madreHerrero.setDni("33333333X");
        madreHerrero.setNombre("Madre Herrero");
        madreHerrero.setFechaNacimiento(generarFecha(1975, 1, 14));
        madreHerrero.setIsNinio(false);
        
        Pasajero abueloHerrero = new Pasajero();
        abueloHerrero.setDni("44444444C");
        abueloHerrero.setNombre("Abuelo Herrero");
        abueloHerrero.setFechaNacimiento(generarFecha(1952, 12, 10));
        abueloHerrero.setIsNinio(false);
        
        Pasajero abuelaHerrero = new Pasajero();
        abuelaHerrero.setDni("55555555V");
        abuelaHerrero.setNombre("Abuela Herrero");
        abuelaHerrero.setFechaNacimiento(generarFecha(1960, 9, 18));
        abuelaHerrero.setIsNinio(false);
        
        Pasajero ninioHerrero = new Pasajero();
        ninioHerrero.setDni("66666666B");
        ninioHerrero.setNombre("Niño Herrero");
        ninioHerrero.setFechaNacimiento(generarFecha(2015, 11, 28));
        ninioHerrero.setIsNinio(true);
        
        Pasajero niniaHerrero = new Pasajero();
        niniaHerrero.setDni("77777777N");
        niniaHerrero.setNombre("Niña Herrero");
        niniaHerrero.setFechaNacimiento(generarFecha(1972, 10, 24));
        niniaHerrero.setIsNinio(true);
        
        pasajerosFamiliaHerrero.add(padreHerrero);
        pasajerosFamiliaHerrero.add(madreHerrero);
        pasajerosFamiliaHerrero.add(abueloHerrero);
        pasajerosFamiliaHerrero.add(abuelaHerrero);
        pasajerosFamiliaHerrero.add(ninioHerrero);
        pasajerosFamiliaHerrero.add(niniaHerrero);
        
        // Creo el billete para la familia
        long codigoBilleteFamiliaHerrero= billeteDAO.crearBillete(120.00, 20, pasajerosFamiliaHerrero);
        Billete billeteFamiliaHerrero = billeteDAO.retornarBilletePorId(codigoBilleteFamiliaHerrero);
        
        // Asigno el billete al clienteFamiliaHerrero
        clienteFamiliaHerrero.addBillete(billeteFamiliaHerrero);
        AbstractDAO.actualizarEntidad(clienteFamiliaHerrero);
        System.out.println(">>> Billete creado para " + clienteFamiliaHerrero.getNombre() + " con código " + codigoBilleteFamiliaHerrero);
        
        // Calcular precio final de un billete, aplicando un descuento por ser un niño
        double precioBilleteNinio = billeteDAO.calcularPrecioFinal(codigoBilleteFamiliaHerrero);
        System.out.println(">>> El precio final para el billete del niño es " + precioBilleteNinio + "€");
        
        // Crear un vuelo y sus asientos disponibles.
        LogicaVueloDAO logicaVuelo = new LogicaVueloDAO();
        Vuelo vuelo737 = logicaVuelo.crearVuelo737(aeropuerto3, aeropuerto4, generarFecha(2021, 03, 20));
        
        // Asigno vuelo a billete
        billeteFamiliaHerrero.setVuelo(vuelo737);
        AbstractDAO.actualizarEntidad(billeteFamiliaHerrero);
        
        // Asigno asientos al billete de la Familia Herrero para el tipo y fila indicados
        billeteDAO.asignarAsientosAPasajerosDeBillete(billeteFamiliaHerrero.getCodigo(), "turista", 10);
        
    }
    
    /**
     * Método auxiliar que genera una fecha a partir del año, mes y día 
     * que se le pasa como parámetro
     *
     * @param anio
     * @param mes
     * @param dia
     * @return Date con la fecha deseada
     */
    public static Date generarFecha(int anio, int mes, int dia) {
        SimpleDateFormat auxDate = new SimpleDateFormat("yyyy/MM/dd");
        Date nuevaFecha = null;
        try {
            nuevaFecha = auxDate.parse(anio+"/"+mes+"/"+dia);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nuevaFecha;
    }
}
