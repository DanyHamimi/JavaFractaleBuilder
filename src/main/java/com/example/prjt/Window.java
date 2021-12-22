package com.example.prjt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame {
    public static double real;
    static JSlider slider;
    public static double imag;
    public static void redraw(JuliaFractal fractaldraw, JFrame windowtotal,boolean val,double valzoo){
        JuliaFractal fractaldraw2;
        if(val){
            fractaldraw2 = new JuliaFractal(fractaldraw.getActualZoom()/valzoo);
        }
        else {
            fractaldraw2 = new JuliaFractal(fractaldraw.getActualZoom()*valzoo);
        }
        System.out.println(fractaldraw.getActualZoom());
        windowtotal.repaint();
    }
    public static void remake(double creal1, double cimag1, JFrame windowtotal){
        System.out.println(creal1);
        JuliaFractal fractaldraw2 = new JuliaFractal(1.3);
        try {
            fractaldraw2.drawJuliaSet(null,1.3,0,0,creal1,cimag1,windowtotal);
            windowtotal.repaint();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    /**public void stateChanged(ChangeEvent e)
    {
        label.setText("La valeur du Slider est : " + slider.getValue());
    }**/
    public static void main(String[] args) throws IOException {
        {
            SwingUtilities.invokeLater(() -> {
                JFrame windowtotal = new JFrame("Fractales");
                windowtotal.setPreferredSize(new Dimension(1600, 800));
                JPanel f = new JPanel();
                windowtotal.pack();
                windowtotal.setVisible(true);
                JButton buttonzoom = new JButton("+");
                JButton buttonzoomplus = new JButton("++");
                buttonzoom.setBounds(400, 742, 400, 20);
                buttonzoom.setBackground(Color.green);
                f.add(buttonzoom);
                buttonzoomplus.setBounds(400, 722, 400, 20);
                buttonzoomplus.setBackground(Color.green);
                f.add(buttonzoom);
                f.add(buttonzoomplus);
                JButton buttondezoom = new JButton("−");
                buttondezoom.setBackground(Color.red);
                buttondezoom.setBounds(0, 742, 400, 20);
                f.add(buttondezoom);
                JuliaFractal fractaldraw = new JuliaFractal(1.3);
                fractaldraw.setBounds(800,0,800,800);
                f.add(fractaldraw, Component.RIGHT_ALIGNMENT, 1);
                f.setVisible(true);
                windowtotal.setResizable(false);
                windowtotal.add(f);
                JTextField realV = new JTextField();
                JTextField imagV = new JTextField();
                realV.setBounds(250,400,60,30);
                imagV.setBounds(250,450,60,30);
                f.add(realV);
                f.add(imagV);
                JButton generatenew = new JButton("Générer");
                generatenew.setBounds(200,500,200,20);
                f.add(generatenew);
                f.setLayout(null);
                buttonzoom.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        redraw(fractaldraw,windowtotal,true,1.1);
                    }
                });
                buttondezoom.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        redraw(fractaldraw,windowtotal,false,1.1);
                    }
                });
                buttonzoomplus.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        redraw(fractaldraw,windowtotal,true,3);
                    }
                });
                generatenew.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        // TODO Régler le pb de si on rentre un string ca fait une erreur
                        remake(Double.parseDouble(realV.getText()),Double.parseDouble(imagV.getText()),windowtotal);
                    }
                });
                windowtotal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            });
        }
    }
}
