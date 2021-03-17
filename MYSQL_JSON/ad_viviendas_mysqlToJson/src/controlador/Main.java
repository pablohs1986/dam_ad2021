/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonObject;
import modelo.dao.SegurosDAO;
import modelo.dto.Cliente;
import modelo.dto.Poliza;
import modelo.dto.Vivienda;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SegurosDAO segurosDAO = new SegurosDAO();
        try {
            ResultSet rsClientes = segurosDAO.getResultSetTabla("cliente");
            JsonArray jsonArrayClientes = segurosDAO.resultSetClienteToJsonArray(rsClientes);
            System.out.println(jsonArrayClientes);
            
            
//        ResultSet rs;
//        try {
//            rs = segurosDAO.getResultSetTabla("cliente");
//            String json = segurosDAO.getJSONFromResultSet(rs, "Cliente");
//            System.out.println(json);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        // Creo lista que será el json global
//
//        // Genero viviendas
//        ArrayList<Vivienda> viviendasCliente1 = null;
//        try {
//            ResultSet resultSetViviendas = segurosDAO.getResultSetTabla("vivienda");
//            viviendasCliente1 = segurosDAO.pasarResultSetAListaViviendasPorIdCliente(resultSetViviendas, 1);
//            
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        // Genero pólizas
//        try {
//            ResultSet resultSetPolizas = segurosDAO.getResultSetTabla("poliza");
//            Poliza polizaVivienda1 = segurosDAO.pasarResultSetAPoliza(resultSetPolizas, 1);
//            segurosDAO.asignarPolizaAVivienda(polizaVivienda1, viviendasCliente1, 1);
//            
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        
//        // Genero clientes
//        try {
//            // Cliente 1
//            ResultSet resultSetClientes = segurosDAO.getResultSetTabla("cliente");
//            Cliente cliente1 = segurosDAO.pasarResultSetACliente(resultSetClientes, 1);
//            cliente1.setViviendas(viviendasCliente1);
//            JsonObject jsonObjectCliente1 = segurosDAO.pasarClienteAJsonObject(cliente1);
//            System.out.println(jsonObjectCliente1);
//
//            
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
// Genero viviendas
//        try {
//            ResultSet resultSetViviendas = segurosDAO.retornarResultSet("vivienda");
//            List<Vivienda> listaViviendas = segurosDAO.pasarResultSetAListaViviendas(resultSetViviendas);
//            List<JsonObject> listaJObjectViviendas = segurosDAO.pasarViviendasAJsonObject(listaViviendas);
//            for (JsonObject listaJObjectVivienda : listaJObjectViviendas) {
//                System.out.println(listaJObjectVivienda);
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }


// Genero viviendas
//        try {
//            ResultSet resultSetViviendas = segurosDAO.retornarResultSet("vivienda");
//            List<Vivienda> listaViviendas = segurosDAO.pasarResultSetAListaViviendas(resultSetViviendas);
//            List<JsonObject> listaJObjectViviendas = segurosDAO.pasarViviendasAJsonObject(listaViviendas);
//            for (JsonObject listaJObjectVivienda : listaJObjectViviendas) {
//                System.out.println(listaJObjectVivienda);
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
