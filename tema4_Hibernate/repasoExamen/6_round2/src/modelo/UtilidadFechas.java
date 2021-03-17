/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Herrero
 */
public class UtilidadFechas {
    
    /**
     * Método auxiliar que genera una fecha a partir del año, mes y día que se
     * le pasa como parámetro
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
            nuevaFecha = auxDate.parse(anio + "/" + mes + "/" + dia);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nuevaFecha;
    }
    
}
