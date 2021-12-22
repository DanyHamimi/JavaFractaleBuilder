package com.example.prjt;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ThreadDraw extends Thread{
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
    public ThreadDraw(double zoom, int x1, NombreComplex constant, int imageSize, int i, int i1, BufferedImage img, JuliaFractal fractalB, JFrame windowtotal) {
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
        for(int k = 0;k<800;k++){
            fractalB.drawz(zoom,k,constant,imageSize,i,i1);
        }

        /**if(windowtotal!=null){
                windowtotal.repaint();
            }**/

    }
}
