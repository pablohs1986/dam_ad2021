/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;

/**
 *
 * @author Pablo Herrero
 */
public class FileConColor extends File {

    private int color;
    private int estrellas;

    public FileConColor(int color, int estrellas, String string) {
        super(string);
        this.color = color;
        this.estrellas = estrellas;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
