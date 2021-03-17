/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.connect;

import java.sql.*;


/**
 *
 * @author Noja
 */
public final class ConexionMySQL {
    
    private String bbdd,host,puerto,user,pass,driver,url;
    private Connection conn = null;
    
    public ConexionMySQL() {
        this.bbdd="jsontosql";
        this.host="192.168.1.56";
        this.puerto="3306";
        this.user="kno";
        this.pass="cocodrilo";
        this.driver="com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://" + host + ":" + puerto + "/" + bbdd;
        this.conn = this.conectarMySQL();
    }   

    public ConexionMySQL(String bbdd, String host, String puerto, String user, String pass) {
        this.bbdd = bbdd;
        this.host = host;
        this.puerto = puerto;
        this.user = user;
        this.pass = pass;
        this.driver="com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://" + host + ":" + puerto + "/" + bbdd;
    }
    
    

    public Connection conectarMySQL() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            System.out.println("NO SE ENCUENTRA EN DRIVER");
        } catch (SQLException ex) {
            System.out.println("LA CONEXIÓN NO SE HA PODIDO ESTABLECER");
        }
        return conn;
    }
    
    public ResultSet hacerConsulta(String query){
        ResultSet resultado=null;
        try {
            Statement sentencia=this.conn.createStatement();            
            resultado=sentencia.executeQuery(query);
            //sentencia.close();
            
        } catch (SQLException ex) {
            System.out.println("LA CONSULTA NO SE HA PODIDO GENERAR");
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    public void hacerInsert(String query){
        try {
            Statement sentencia=this.conn.createStatement();
            sentencia.executeUpdate(query);
            sentencia.close();
            
        } catch (SQLException ex) {
            System.out.println("LA INSERCIÓN NO SE HA PODIDO EFECTUAR");
            System.out.println(ex.getMessage());         
        }
    }
    
    public void cerrarConexion(){
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.out.println("LA CONEXION HA SIDO CERRADA");
        }
    }

    public String getBbdd() {
        return bbdd;
    }

    public void setBbdd(String bbdd) {
        this.bbdd = bbdd;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    

}

