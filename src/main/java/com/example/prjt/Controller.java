package com.example.prjt;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Controller {
    private JFrame windowtotal;
    public Controller(JFrame windowtotal){
        this.windowtotal = windowtotal;
    }

    public ActionListener buttonzoom(){
        return e -> {
            FunctionsDrawing.redraw(windowtotal,true,1.1,Window.ValActu);
        };
    };

    public ActionListener buttondezoom(){
        return e -> {
            FunctionsDrawing.redraw(windowtotal,false,1.1,Window.ValActu);
        };
    };

    public ActionListener buttondezoomplus(){
        return e -> {
            FunctionsDrawing.redraw(windowtotal,false,3,Window.ValActu);
        };
    };

    public ActionListener buttonzoomplus(){
        return e -> {
            FunctionsDrawing.redraw(windowtotal,true,3,Window.ValActu);
        };
    };

    public ActionListener butexport(){
        return e -> {
            JOptionPane j = new JOptionPane();
            j.setName("Exporter");
            String k = j.showInputDialog("Veuillez entrer le nom voulu pour l'image","MonFractale");
            if(k == null){
                return;
            }
            while (k==null || k.length()==0){
                k = j.showInputDialog("Veuillez entrer le nom voulu pour l'image","MonFractale");
            }
            if(Window.ValActu == 0){
                FunctionsDrawing.exportImg(Window.mandeldraw,k);
            }
            else {
                FunctionsDrawing.exportImg(Window.fractaldraw,k);
            }
        };
    };

    public ActionListener movedroite(){
        return e -> {
            FunctionsDrawing.moveonplan(windowtotal,0.2,0);
        };
    };

    public ActionListener movegauche(){
        return e -> {
            FunctionsDrawing.moveonplan(windowtotal,-0.2,0);
        };
    };

    public ActionListener moveHaut(){
        return e -> {
            FunctionsDrawing.moveonplan(windowtotal,0,-0.2);
        };
    };

    public ActionListener moveBas(){
        return e -> {
            FunctionsDrawing.moveonplan(windowtotal,0,+0.2);
        };
    };

    public ActionListener Color1(){
        return e -> {
            FunctionsDrawing.setColor(0);
            Window.fractaldraw.repaint();
            Window.mandeldraw.repaint();
        };
    };

    public ActionListener Color2(){
        return e -> {
            FunctionsDrawing.setColor(1);
            Window.fractaldraw.repaint();
            Window.mandeldraw.repaint();
        };
    };

    public ActionListener Color3(){
        return e -> {
            FunctionsDrawing.setColor(2);
            Window.fractaldraw.repaint();
            Window.mandeldraw.repaint();
        };
    };
    public ActionListener generatenew(JTextField realV,JTextField imagV,JPanel f,JTextField iterV){
        return e -> {
            FunctionsDrawing.RedrawJuliaB(windowtotal,realV,imagV,f,true,iterV);
        };
    };
    public ActionListener juliaButton(JRadioButton juliaButton,JPanel f,JLabel rT,JLabel iT,JTextField realV, JTextField imagV,JButton generatenew,JLabel iV,JTextField iterV){
        return e -> {
            if(juliaButton.isSelected() && Window.ValActu ==0){
                Window.ValActu = 1;
                f.remove(Window.mandeldraw);
                rT.setVisible(true);
                iT.setVisible(true);
                realV.setVisible(true);
                imagV.setVisible(true);
                generatenew.setVisible(true);
                iV.setVisible(true);
                iterV.setVisible(true);
                FunctionsDrawing.RedrawJuliaB(windowtotal,null,null,f,false,null);
            }
        };
    };

    public ActionListener mandelButton(JRadioButton mandelButton,JPanel f,JLabel rT,JLabel iT,JTextField realV, JTextField imagV,JButton generatenew,JLabel iV,JTextField iterV){
        return e -> {
            if(mandelButton.isSelected() && Window.ValActu ==1){
                Window.ValActu = 0;
                f.remove(Window.fractaldraw);
                f.add(Window.mandeldraw);
                Window.mandeldraw.setZoom(1);
                rT.setVisible(false);
                iT.setVisible(false);
                realV.setVisible(false);
                imagV.setVisible(false);
                iV.setVisible(false);
                iterV.setVisible(false);
                generatenew.setVisible(false);
                windowtotal.repaint();
            }
        };
    };


}
