/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsontosql;

import DTO.*;
import action.*;
import java.util.ArrayList;

/**
 *
 * @author Noja
 */
public class JsonToSql {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
            OperacionesJson op = new OperacionesJson();
            ArrayList<Libro> listaLibros = op.getListaLibros();
            for (Libro libro : listaLibros) {
               System.out.println(libro);
            }
        
            OperacionesMySql opms=new OperacionesMySql();
            System.out.println(opms.insertarLibro(listaLibros));
            
            OperacionesMysqlJson opmsjs=new OperacionesMysqlJson();
            System.out.println(opmsjs.crearJsonFromMySql());
       
    }

    

}
