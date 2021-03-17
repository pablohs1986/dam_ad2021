/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import modelo.dto.Cliente;
import modelo.dto.Poliza;
import modelo.dto.Vivienda;

/**
 *
 * @author Pablo Herrero
 */
public class SegurosDAO {

    public JsonArray resultSetClienteToJsonArray(ResultSet rs) throws SQLException {
        JsonArray arrayJson;
        JsonArrayBuilder builder = Json.createArrayBuilder();
        ResultSetMetaData metaData = rs.getMetaData();
        
        while(rs.next()) {
            builder.add(metaData.getColumnName(0), rs.getString("nombre"));
            builder.add(rs.getString("apellido"));
        }
        arrayJson = builder.build();
        return arrayJson;
    }
    
    
//    public String getJSONFromResultSet(ResultSet rs, String keyName) throws SQLException {
//        Map json = new HashMap();
//        List list = new ArrayList();
//        if (rs != null){
//            ResultSetMetaData metaData = rs.getMetaData();
//            while (rs.next()) {
//                Map<String, Object> columnMap = new HashMap<String, Object>();
//                for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++){
//                    String val = rs.getString(metaData.getColumnName(columnIndex));
//                    String key = metaData.getColumnLabel(columnIndex);
//                    if (val == null) {
//                        columnMap.put(key, "");
//                    } else if (val.chars().allMatch(Character::isDigit)) {
//                        columnMap.put(key, 1111);
//                    } else {
//                        columnMap.put(key, 2222);
//                    }
//                }
//                list.add(columnMap);
//            }
//            json.put(keyName, list);  
//        }
//        return  json.toString();
//    }
        
    ////// CLIENTE //////
    public JsonObject pasarClienteAJsonObject(Cliente cliente) {
        JsonObject jsonObjectCliente = Json.createObjectBuilder()
                .add("id", cliente.getId())
                .add("nombre", cliente.getNombre())
                .add("apellido", cliente.getApellido())
                .add("vip", cliente.isVip())
                .add("viviendas", pasarListaViviendasAJsonArray(cliente.getViviendas()))
                .build();
        return jsonObjectCliente;
    }

    public Cliente pasarResultSetACliente(ResultSet resultSet, int idCliente) throws SQLException {
        Cliente cliente = null;
        while (resultSet.next()) {
            if (resultSet.getInt("id") == idCliente) {
                cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("nombre"),
                        resultSet.getString("apellido"), resultSet.getBoolean("vip"));
            }
        }
        return cliente;
    }
    
    ////// VIVIENDA //////
    public JsonArray pasarListaViviendasAJsonArray(List<Vivienda> viviendas){
        JsonArrayBuilder constructorArray = Json.createArrayBuilder();
        JsonArray jsonArrayViviendas;

        for (Vivienda vivienda : viviendas) {
            constructorArray.add(pasarViviendaAJsonObject(vivienda));
        }
        jsonArrayViviendas = constructorArray.build();
        return jsonArrayViviendas;
    }
    
    public JsonObject pasarViviendaAJsonObject(Vivienda vivienda) {
        JsonArrayBuilder polizas = Json.createArrayBuilder();

        JsonObject jsonObjectVivienda = Json.createObjectBuilder()
                .add("id", vivienda.getId())
                .add("calle", vivienda.getCalle())
                .add("cp", vivienda.getCp())
                .add("metrosCuadrados", vivienda.getMetrosCuadrados())
                .add("poliza", polizas
                    .add(vivienda.getPoliza().getId())
                    .add(vivienda.getPoliza().getFechaVencimiento().toString())
                    .add(vivienda.getPoliza().getPrecioActual())
                    .add(vivienda.getPoliza().getPrecioRenovacion())
                    .add(vivienda.getPoliza().getDescuento())
                    .build())
                .build();
        return jsonObjectVivienda;
    }

    public ArrayList<Vivienda> pasarResultSetAListaViviendasPorIdCliente(ResultSet resultSet, int idCliente) throws SQLException {
        ArrayList<Vivienda> viviendas = new ArrayList<Vivienda>();
        while (resultSet.next()) {
            if(resultSet.getInt("Cliente_id") == idCliente) {
                viviendas.add(new Vivienda(resultSet.getInt("id"), resultSet.getString("calle"),
                resultSet.getString("cp"), resultSet.getDouble("metrosCuadrados")));
            }
        }
        return viviendas;
    }
    
    ////// PÓLIZA //////
    
    public void asignarPolizaAVivienda(Poliza poliza, ArrayList<Vivienda> viviendas, int id) {
        for (Vivienda vivienda : viviendas) {
            if (vivienda.getId() == id) {
                vivienda.setPoliza(poliza);
            }
        }
    }
    
    public JsonObject pasarPolizaAJsonObject(Poliza poliza) {
        JsonObject jsonObjectCliente = Json.createObjectBuilder()
                .add("id", poliza.getId())
                .add("fechaVencimiento", poliza.getFechaVencimiento().toString())
                .add("precioActual", poliza.getPrecioActual())
                .add("precioRenovacion", poliza.getPrecioRenovacion())
                .add("descuento", poliza.getDescuento())
                .build();
        return jsonObjectCliente;
    }

    public Poliza pasarResultSetAPoliza(ResultSet resultSet, int idVivienda) throws SQLException {
        Poliza poliza = null;
        while (resultSet.next()) {
            if (resultSet.getInt("id") == idVivienda) {
                poliza = new Poliza(resultSet.getInt("id"), resultSet.getDate("fechaVencimiento"),
                        resultSet.getDouble("precioActual"), resultSet.getDouble("precioRenovacion"),
                        resultSet.getInt("descuento"));
            }
        }
        return poliza;
    }
    
    ////// Métodos auxiliares //////
    public ResultSet getResultSetTabla(String tablaARetornar) throws ClassNotFoundException, SQLException { // aádir id cliente
        Connection conexion = this.conexion();
        Statement statement = conexion.createStatement();
        ResultSet clientes = statement.executeQuery("SELECT * FROM " + tablaARetornar);
        return clientes;
    }
    
    /**
     * Método que muestra el ResultSet que recibe.
     *
     * @param resultSet
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public void mostrarResultSet(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int numeroColumnas = rsmd.getColumnCount();

        while (resultSet.next()) {
            for (int i = 1; i <= numeroColumnas; i++) {
                String valorColumna = resultSet.getString(i);
                System.out.print(valorColumna + " | ");
            }
            System.out.println("");
        }
    }

    /**
     * Método que realiza la conexión a la BBDD MySql.
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Connection conexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String cadenaConexion = "jdbc:mysql://localhost:3306/viviendas";
        String user = "root";
        String pass = "9874";
        Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass);
        return conexion;
    }
}
