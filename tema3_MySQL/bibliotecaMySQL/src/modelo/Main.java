/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Herrero
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String cadenaConexion = "jdbc:mysql://localhost:3306/biblioteca";
            String user = "root";
            String pass = "9874";
            Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass);
            Statement stmt = conexion.createStatement();
            
            ResultSet socios = stmt.executeQuery("SELECT * FROM socio");
            
            String formato = ("\t%-15s | %-30s | %-30s | %-30s\n");
            System.out.format(formato, "Codigo Socio", "Nombre", "Apellidos", "Teléfono");
            
            while(socios.next()) {
                System.out.format(formato, socios.getString("codigoSocio"), socios.getString("nombreSocio"), socios.getString("apellidoSocio"), socios.getString("telefono"));
            }
            
            stmt.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
