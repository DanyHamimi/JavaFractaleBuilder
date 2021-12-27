package com.example.prjt;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JuliaDrawThread extends Thread{
    public static final int MAX_RGB_VALUE = 255;
    private BufferedImage img;
    private int imageSize;
    private int i;
    private int i1;
    private NombreComplex constant;
    private int x1;
    private double zoom;
    private JuliaFractal fractalB;
    private JFrame windowtotal;

    public JuliaDrawThread(double zoom, int x1, NombreComplex constant, int imageSize, int i, int i1, BufferedImage img, JuliaFractal fractalB, JFrame windowtotal) {
        this.windowtotal = windowtotal;
        this.fractalB = fractalB;
        this.zoom = zoom;
        this.x1 = x1;
        this.constant = constant;
        this.imageSize = imageSize;
        this.i = i;
        this.i1 =i1;
        this.img = img;
    }
    @Override
    public synchronized void run() {
        for(int k = 0;k<fractalB.imageSize;k++){
            fractalB.drawz(zoom,k,constant,imageSize,i,i1);
        }
    }
}
