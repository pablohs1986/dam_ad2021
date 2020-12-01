package controlador;


import modelo.Aemet;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Aemet.calcularDiaTemperaturaMaxima("/Users/pherrero/Documents/Repositorios/dam_ad2021/tema2/ficheros_6_CSV_AeropuertoAsturias2019/aeropuertoAsturias2019.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
