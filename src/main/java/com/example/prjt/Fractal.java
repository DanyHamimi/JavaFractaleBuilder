package com.example.prjt;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fractal extends JPanel {
    static double deplacementVertical = 0;
    double deplacementHorizontal = 0;
    public static final int MAX_RGB_VALUE = 255;
    public static int imageSize = 800;
    int nbitr;
    static BufferedImage img;
    ExecutorService executor = Executors.newFixedThreadPool(4);
    static int ValColor;


    public static Color invert(Color c) {
        //  TODO : improve
        int a = c.getAlpha();
        int r = MAX_RGB_VALUE - c.getRed();
        int g = MAX_RGB_VALUE - c.getGreen();
        int b = MAX_RGB_VALUE - c.getBlue();

        // if the resulting color is to light (e.g. initial color is black, resulting color is white...)
        if ((r + g + b > 740) || (r + g + b < 20)) {
            // return a standard yellow
            return new Color(MAX_RGB_VALUE, MAX_RGB_VALUE, 40, a);
        } else {
            return new Color(r, g, b, a);
        }
    }
    public Color setColor(Color c){
        if(ValColor==0){
            return invert(c);
        }
        else if(ValColor==1){
            return c;
        }
        int r = (int)(c.getRed() * 0.299);
        int g = (int)(c.getGreen() * 0.587);
        int b = (int)(c.getBlue() *0.114);
        return new Color(r+g+b, r+g+b,r+g+b);
    }
    public void setIter(int i){
        this.nbitr=i;
    }
}
