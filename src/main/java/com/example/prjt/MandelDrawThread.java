package com.example.prjt;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelDrawThread extends Thread{
    public static final double startX = -2;
    public static final double width = 4;
    public static final double startY = 2;
    public static final double height = 4;
    public static final double dx = width/(800-1);
    public static final double dy = height/(800-1);
    public static final int MAX_RGB_VALUE = 255;
    private final int valToDraw;
    private final int minInt;
    private Mandel mandel;

    public MandelDrawThread(Mandel mandel,int valToDraw,int minInt) {
        this.valToDraw = valToDraw;
        this.mandel = mandel;
        this.minInt = minInt;

    }

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


    @Override
    public void run() {
        for(int k = minInt;k<valToDraw;k++){
            mandel.drawZ(k);
        }
    }
}
