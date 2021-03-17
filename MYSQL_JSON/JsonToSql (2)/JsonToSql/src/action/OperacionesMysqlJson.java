/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Modelo.connect.ConexionMySQL;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import jsontosql.JsonToSql;
/**
 *
 * @author Noja
 */
public class OperacionesMysqlJson {
    ConexionMySQL con;

    public OperacionesMysqlJson() {
        this.con = new ConexionMySQL();
        
    }
    
    public String crearJsonFromMySql(){
        JsonArray arrayLibros=null;
        try {
            JsonArrayBuilder builder = Json.createArrayBuilder();
            ResultSet resultado = con.hacerConsulta("Select * from Libro");
            while (resultado.next()) {
                System.out.println(resultado.getString("titulo"));
                builder.add(Json.createObjectBuilder()
                        .add("titulo", resultado.getString("titulo"))
                        .add("totalPaginas", resultado.getInt("totalpaginas"))
                        .add("precio", resultado.getInt("precio"))
                        .add("autores", getArrayAutores(resultado.getInt("id_libro")))
                        .add("generos", getArrayGenero(resultado.getInt("id_libro")))
                        .build()
                );
            }
            arrayLibros = builder.build();
           
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesMysqlJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayLibros.toString();
    }
    
    private JsonArray getArrayAutores(int pk_libro) {
        JsonArray arrayAutores=null ;
        try {
            ConexionMySQL con = new ConexionMySQL();
            ResultSet resultado = con.hacerConsulta("Select * from Autor where Libro_id_libro="+pk_libro);
            JsonArrayBuilder builder = Json.createArrayBuilder();
            while (resultado.next()) {
                builder.add(Json.createObjectBuilder()
                        .add("nombre", resultado.getString("nombre"))
                        .add("apellido", resultado.getString("apellido"))
                        .build()
                );                        
            }
            arrayAutores = builder.build();
        } catch (SQLException ex) {
            Logger.getLogger(JsonToSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayAutores;
    }
    
    private JsonArray getArrayGenero(int pk_libro) {
        JsonArray arrayGenero=null ;
        try {
            ConexionMySQL con = new ConexionMySQL();
            ResultSet resultado = con.hacerConsulta("Select * from Generos where Libro_id_libro="+pk_libro);
            JsonArrayBuilder builder = Json.createArrayBuilder();
            while (resultado.next()) {
                builder.add(Json.createObjectBuilder()
                        .add("genero", resultado.getString("genero"))
                        .build()
                );                        
            }
            arrayGenero = builder.build();
        } catch (SQLException ex) {
            Logger.getLogger(JsonToSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayGenero;
    }
    
    
}
