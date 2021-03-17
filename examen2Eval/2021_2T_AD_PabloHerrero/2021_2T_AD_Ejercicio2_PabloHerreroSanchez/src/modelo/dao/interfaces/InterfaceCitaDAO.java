/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.interfaces;

import modelo.dto.Agenda;
import modelo.dto.Especialista;
import modelo.dto.TarjetaBeneficiario;

/**
 *
 * @author Pablo Herrero
 */
public interface InterfaceCitaDAO {
    
    public boolean nuevaCita(int dia, int tramo, Agenda agenda, TarjetaBeneficiario tarjeta);
    
    public int buscarHueco(Especialista especialista, int dia);
    
    public boolean nuevaCitaRapida(int dia, Agenda agenda, TarjetaBeneficiario tarjeta);
}
