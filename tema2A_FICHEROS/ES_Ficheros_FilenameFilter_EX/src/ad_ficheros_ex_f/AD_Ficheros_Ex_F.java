/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_ficheros_ex_f;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author Noja
 */
public class AD_Ficheros_Ex_F {
    public static void salto(int n){
        for(int i=0;i<n;i++){
            System.out.println("");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File carpeta=new File("files");
        File prueba=new File("files\\nombre.xml");
        System.out.println(prueba.getName().subSequence(0,prueba.getName().lastIndexOf(".")));
        //LISTAR LA CARPETA ENTERA
        String listado[]=carpeta.list();
        System.out.println("Listado de la carpeta "+carpeta.getAbsolutePath());
        for (String arg : listado) {
            System.out.println(arg);
        }
        salto(3);
        //filtros para sacar los archivos con extension xml y xsd
        FilenameFilter filtroXML=new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        };
        
        FilenameFilter filtroXSD=new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xsd");
            }
        };
        //sacamos el listado de los XML
        
        String listadodeXML[]=carpeta.list(filtroXML);
        System.out.println("Estos son los xml");
        for (String string : listadodeXML) {
            System.out.println(string);
        }
        salto(3);
        //sacamos el listado de los XSD
        String listadodeXSD[]=carpeta.list(filtroXSD);
        System.out.println("Estos son los xsd");
        for (String string : listadodeXSD) {
            System.out.println(string);
        }
        salto(3);
        //comprobamos que xsd's tienen su equivalente xml
        for (String xml : listadodeXML) {
            String nombreXml = xml.substring(0,xml.lastIndexOf("."));
            System.out.println(nombreXml);
            for (String xsd : listadodeXSD) {
                String nombreXsd=xsd.substring(0,xsd.lastIndexOf("."));
                if(nombreXml.equalsIgnoreCase(nombreXsd)){
                    System.out.println("Hay un xml por cada xsd llamado "+nombreXml);
                }else{
                    System.out.println("No hay archivo xml para el xsd llamado "+nombreXsd);
                }
            }
        }
        
        
        //Estructura map 
        
        Map <String,ArrayList<String>> mapa;
        mapa = new HashMap<>();
        
       
        
        File[] archivos=carpeta.listFiles();
        ArrayList<String> listaNombres;
        ArrayList<String> listaFilesConMismoNombre;
        String nombreArchivo="";
        for (File archivo : archivos) {
            listaFilesConMismoNombre= new ArrayList<>();
            listaNombres= new ArrayList<>();
            nombreArchivo= archivo.getName().substring(0,archivo.getName().lastIndexOf("."));
            if(listaNombres.isEmpty()||!listaNombres.contains(nombreArchivo)){
                listaNombres.add(nombreArchivo);
                listaFilesConMismoNombre.add(archivo.getName());
            }else{
                for (String listaNombre : listaNombres) {
                    if(nombreArchivo.equalsIgnoreCase(listaNombre)){
                        listaFilesConMismoNombre.add(archivo.getName());
                    }
                }
            }
            
            
            mapa.put(nombreArchivo, listaFilesConMismoNombre);
            
        }
        
        salto(5);
        for (Map.Entry<String,ArrayList<String>> entry : mapa.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> val = entry.getValue();
            System.out.println(key+" => tiene los archivos:");
            for (String archivo : val) {
                System.out.println("\t"+archivo);
            }
            
        }
    }
    
    
    
}
