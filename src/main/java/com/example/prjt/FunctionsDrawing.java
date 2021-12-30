package com.example.prjt;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public interface FunctionsDrawing {

    public static void redraw(JFrame windowtotal, boolean val, double valzoo, int type){
        if(type == 1){
            if(val){
                Window.fractaldraw.setZoom(Window.fractaldraw.getActualZoom()/valzoo);
            }
            else {
                Window.fractaldraw.setZoom(Window.fractaldraw.getActualZoom()*valzoo);
            }
        }
        else {
            if(val){
                Window.mandeldraw.setZoom(Window.mandeldraw.getActualZoom()/valzoo);
            }
            else {
                Window.mandeldraw.setZoom(Window.mandeldraw.getActualZoom()*valzoo);
            }
        }
        windowtotal.repaint();
    }
    public static JuliaFractal remake(float creal1, float cimag1, JFrame windowtotal,int NbIter){
        JuliaFractal fractaldraw2 = new JuliaFractal(1.3,0,0,NbIter);
        fractaldraw2.setReAndIm(creal1, cimag1);

        try {
            fractaldraw2.drawJuliaSet(null,1.3,0,0,creal1,cimag1,windowtotal);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return fractaldraw2;
    }
    static void exportImg(JuliaFractal fractaldraw, String k) {
        fractaldraw.exportImg(k);
        System.out.println("L'image a bien été exportée sous le nom : "+k+".png");
    }
     static void exportImg(Mandel mandel, String k) {
        mandel.exportImg(k);
        System.out.println("L'image a bien été exportée sous le nom : "+k+".png");
    }
    public static void setColor(int i) {
        Window.fractaldraw.ValColor = i;
    }

    public static void RedrawJuliaB(JFrame windowtotal, JTextField realV, JTextField imagV, JPanel f,boolean type, JTextField Iter){
        float re = (float) -0.8;
        float im = (float) 0.156;
        int nbIter = 1000;
        if(type){
            try {
                re = Float.parseFloat(realV.getText());
                im = Float.parseFloat(imagV.getText());
                nbIter = Integer.parseInt(Iter.getText());
            } catch (NumberFormatException exp) {
                JOptionPane.showMessageDialog(null, "Erreur merci d'entrer une valeur correcte, valeurs par défaut appliquées.");
                f.remove(Window.fractaldraw);
                Window.fractaldraw = FunctionsDrawing.remake((float)-0.8,(float) 0.156,windowtotal,1000);
                windowtotal.repaint();
                Window.fractaldraw.setBounds(800,0,800,800);
                f.add(Window.fractaldraw, Component.RIGHT_ALIGNMENT, 1);
                return;
            }
        }
        f.remove(Window.fractaldraw);
        Window.fractaldraw = FunctionsDrawing.remake(re,im,windowtotal,nbIter);
        windowtotal.repaint();
        Window.fractaldraw.setBounds(800,0,800,800);
        f.add(Window.fractaldraw, Component.RIGHT_ALIGNMENT, 1);
    }
    public static void moveonplan(JFrame windowtotal,double val1,double val2) {
        if(Window.ValActu == 0){
            Window.mandeldraw.setDeplacement(Window.mandeldraw.deplacementHorizontal+val1*Window.mandeldraw.getActualZoom(),Window.mandeldraw.deplacementVertical-val2*Window.mandeldraw.getActualZoom());
        }
        else {
            Window.fractaldraw.setDeplacement(Window.fractaldraw.deplacementHorizontal+val1*Window.fractaldraw.getActualZoom(),Window.fractaldraw.deplacementVertical+val2*Window.fractaldraw.getActualZoom());
        }
        windowtotal.repaint();
    }

}
