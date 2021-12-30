package com.example.prjt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame {

    public static int ValActu = 1;
    public static JuliaFractal fractaldraw = new JuliaFractal(1.3,0,0);
    public static Mandel mandeldraw = new Mandel(1,0,0);

    public static void redraw(JFrame windowtotal,boolean val,double valzoo,int type){
        if(type == 1){
            if(val){
                fractaldraw.setZoom(fractaldraw.getActualZoom()/valzoo);
            }
            else {
                fractaldraw.setZoom(fractaldraw.getActualZoom()*valzoo);
            }
        }
        else {
            if(val){
                mandeldraw.setZoom(mandeldraw.getActualZoom()/valzoo);
            }
            else {
                mandeldraw.setZoom(mandeldraw.getActualZoom()*valzoo);
            }
        }
        windowtotal.repaint();
    }

    public static JuliaFractal remake(float creal1, float cimag1, JFrame windowtotal){
        JuliaFractal fractaldraw2 = new JuliaFractal(1.3,0,0);
        fractaldraw2.setReAndIm(creal1, cimag1);

        try {
            fractaldraw2.drawJuliaSet(null,1.3,0,0,creal1,cimag1,windowtotal);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return fractaldraw2;
    }
    private static void exportImg(JuliaFractal fractaldraw, String k) {
        fractaldraw.exportImg(k);
        System.out.println("L'image a bien été exportée sous le nom : "+k+".png");
    }
    private static void exportImg(Mandel mandel, String k) {
        mandel.exportImg(k);
        System.out.println("L'image a bien été exportée sous le nom : "+k+".png");
    }

    public static void main(String[] args) throws IOException {
        {
            SwingUtilities.invokeLater(() -> {
                JFrame windowtotal = new JFrame("Fractales");
                windowtotal.setPreferredSize(new Dimension(1600, 800));
                windowtotal.pack();
                windowtotal.setVisible(true);
                JPanel f = new JPanel();
                JRadioButton juliaButton = new JRadioButton ("Julia");
                JRadioButton mandelButton = new JRadioButton ("Mandelbrot");
                ButtonGroup G1 = new ButtonGroup();
                JButton buttonzoom = new JButton("+");
                JButton buttonzoomplus = new JButton("++");
                JButton buttondezoom = new JButton("-");
                JButton buttondezoomplus = new JButton("--");
                JButton butexport = new JButton("Exporter");
                JButton movedroite = new JButton("→");
                JButton movegauche = new JButton("←");
                JButton moveHaut = new JButton("↑");
                JButton moveBas = new JButton("↓");
                JTextField realV = new JTextField();
                JTextField imagV = new JTextField();
                JLabel rT = new JLabel("Réel");
                JLabel iT = new JLabel("Imaginaire");
                JButton generatenew = new JButton("Générer");
                juliaButton.setBounds(250,180,100,15);
                mandelButton.setBounds(350,180,100,15);
                buttonzoom.setBounds(50, 442, 50, 50);
                buttonzoomplus.setBounds(450, 442, 50, 50);
                buttondezoomplus.setBounds(400, 442, 50, 50);
                buttondezoom.setBounds(0, 442, 50, 50);
                fractaldraw.setBounds(800,0,800,800);
                mandeldraw.setBounds(800,0,800,800);
                butexport.setBounds(700,0,100,20);
                movedroite.setBounds(752,443,47,47);
                movegauche.setBounds(658,443,47,47);
                moveHaut.setBounds(705,396,47,47);
                moveBas.setBounds(705,443,47,47);
                rT.setBounds(290,240,70,40);
                iT.setBounds(290,290,70,40);
                realV.setBounds(360,240,60,30);
                imagV.setBounds(360,290,60,30);
                generatenew.setBounds(290,360,200,20);

                G1.add(juliaButton);
                G1.add(mandelButton);
                f.add(juliaButton);
                f.add(mandelButton);
                f.add(buttonzoom);
                f.add(buttonzoom);
                f.add(buttonzoomplus);
                f.add(buttondezoomplus);
                f.add(buttondezoom);
                f.add(fractaldraw, Component.RIGHT_ALIGNMENT, 1);
                f.add(butexport);
                f.add(movedroite);
                f.add(movegauche);
                f.add(moveHaut);
                f.add(moveBas);
                f.setVisible(true);
                f.add(realV);
                f.add(imagV);
                f.add(rT);
                f.add(iT);
                f.add(generatenew);
                f.setLayout(null);
                windowtotal.add(f);
                windowtotal.setResizable(false);
                juliaButton.setSelected(true);
                buttonzoom.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        redraw(windowtotal,true,1.1,ValActu);
                    }
                });
                buttondezoom.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        redraw(windowtotal,false,1.1,ValActu);
                    }
                });
                buttondezoomplus.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        redraw(windowtotal,false,3,ValActu);
                    }
                });
                buttonzoomplus.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        redraw(windowtotal,true,3,ValActu);
                    }
                });
                butexport.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane j = new JOptionPane();
                        j.setName("Exporter");
                        String k = j.showInputDialog("Veuillez entrer le nom voulu pour l'image","MonFractale");
                        if(k == null){
                            return;
                        }
                        while (k==null || k.length()==0){
                            k = j.showInputDialog("Veuillez entrer le nom voulu pour l'image","MonFractale");
                        }
                        if(ValActu == 0){
                            exportImg(mandeldraw,k);
                        }
                        else {
                            exportImg(fractaldraw,k);
                        }
                    }
                });
                movedroite.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveonplan(windowtotal,0.2,0);
                    }
                });
                movegauche.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveonplan(windowtotal,-0.2,0);
                    }
                });
                moveHaut.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveonplan(windowtotal,0,-0.2);
                    }
                });
                moveBas.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveonplan(windowtotal,0,+0.2);
                    }
                });

                generatenew.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        RedrawJuliaB(windowtotal,realV,imagV,f,true);
                    }
                });
                juliaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(juliaButton.isSelected() && ValActu ==0){
                            ValActu = 1;
                            f.remove(mandeldraw);
                            rT.setVisible(true);
                            iT.setVisible(true);
                            realV.setVisible(true);
                            imagV.setVisible(true);
                            generatenew.setVisible(true);
                            RedrawJuliaB(windowtotal,null,null,f,false);
                        }
                    }
                });
                mandelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(mandelButton.isSelected() && ValActu ==1){
                            ValActu = 0;
                            f.remove(fractaldraw);
                            f.add(mandeldraw);
                            mandeldraw.setZoom(1);
                            rT.setVisible(false);
                            iT.setVisible(false);
                            realV.setVisible(false);
                            imagV.setVisible(false);
                            generatenew.setVisible(false);
                            windowtotal.repaint();
                        }
                    }
                });
                windowtotal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windowtotal.repaint();
            });
        }
    }

    private static void RedrawJuliaB(JFrame windowtotal, JTextField realV, JTextField imagV, JPanel f,boolean type){
        float re = (float) -0.8;
        float im = (float) 0.156;
        if(type){
            try {
                re = Float.parseFloat(realV.getText());
                im = Float.parseFloat(imagV.getText());
            } catch (NumberFormatException exp) {
                System.out.println("Erreur merci d'entrer une valeur correcte, valeurs par défaut appliquées.");
            }
        }
        f.remove(fractaldraw);
        fractaldraw = remake(re,im,windowtotal);
        windowtotal.repaint();
        fractaldraw.setBounds(800,0,800,800);
        f.add(fractaldraw, Component.RIGHT_ALIGNMENT, 1);
    }
    private static void moveonplan(JFrame windowtotal,double val1,double val2) {
        if(ValActu == 0){
            mandeldraw.setDeplacement(mandeldraw.deplacementHorizontal+val1*mandeldraw.getActualZoom(),mandeldraw.deplacementVertical-val2*mandeldraw.getActualZoom());
        }
        else {
            fractaldraw.setDeplacement(fractaldraw.deplacementHorizontal+val1*fractaldraw.getActualZoom(),fractaldraw.deplacementVertical+val2*fractaldraw.getActualZoom());
        }
        windowtotal.repaint();
    }
}
