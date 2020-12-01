package modelo;

import java.io.*;
import java.util.ArrayList;

public class Aemet {
    public static void calcularDiaTemperaturaMaxima(String rutaArchivo) throws IOException {
        File archivoCsv = new File(rutaArchivo);
        FileReader fr = new FileReader(archivoCsv);
        BufferedReader br = new BufferedReader(fr);
        String aux = br.readLine();
        ArrayList<String> listadoDias = new ArrayList<String>();
        String fechaTemperaturaMaxima = null;
        double temperaturaMaxima = 0.0;

        while ((aux=br.readLine()) != null) {
            listadoDias.add(aux);
        }

        for (String dia : listadoDias) {
            String[] arrayCampos = dia.split("\\*");
            String fecha = arrayCampos[0];
            String temperatura = arrayCampos[9].replace(",", ".");

            if ((fecha.length()!=0) && (temperatura.length()!=0)){
                double t = Double.parseDouble(temperatura);
                    System.out.println(t);

                if (t>temperaturaMaxima) {
                    fechaTemperaturaMaxima = fecha;
                    temperaturaMaxima = t;
                    System.out.println("Fecha temperatura maxima: " + fechaTemperaturaMaxima);
                    System.out.println("Temperatura máxima: " + temperaturaMaxima);
                }
            }
        }
        System.out.println("Día: " + fechaTemperaturaMaxima);
        System.out.println("Temperatura máxima: " + temperaturaMaxima);
    }
}
