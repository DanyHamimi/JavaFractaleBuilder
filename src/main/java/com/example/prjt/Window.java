package com.example.prjt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame {
    public static double real;
    public static double imag;
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
                JRadioButton mandelButton = new JRadioButton ("Mandel");
                juliaButton.setBounds(0,40,100,15);
                mandelButton.setBounds(0,80,100,15);

                ButtonGroup G1 = new ButtonGroup();
                G1.add(juliaButton);
                G1.add(mandelButton);
                f.add(juliaButton);
                f.add(mandelButton);
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
                JButton buttondezoomplus = new JButton("- -");
                buttondezoom.setBackground(Color.red);
                buttondezoomplus.setBackground(Color.red);
                buttondezoomplus.setBounds(0, 722, 400, 20);
                buttondezoom.setBounds(0, 742, 400, 20);
                f.add(buttondezoomplus);
                f.add(buttondezoom);
                fractaldraw.setBounds(800,0,800,800);
                mandeldraw.setBounds(800,0,800,800);
                //f.add(mandeldraw,Component.RIGHT_ALIGNMENT, 1);
                f.add(fractaldraw, Component.RIGHT_ALIGNMENT, 1);
                JButton butexport = new JButton("Exporter");
                butexport.setBounds(700,0,100,20);
                f.add(butexport);
                JButton movedroite = new JButton(">");
                JButton movegauche = new JButton("<");
                movedroite.setBounds(400,702,400,20);
                movegauche.setBounds(0,702,400,20);
                f.add(movedroite);
                f.add(movegauche);
                JButton moveHaut = new JButton("UP");
                JButton moveBas = new JButton("DOWN");
                moveHaut.setBounds(0,682,400,20);
                moveBas.setBounds(400,682,400,20);
                f.add(moveHaut);
                f.add(moveBas);
                f.setVisible(true);
                windowtotal.setResizable(false);
                windowtotal.add(f);
                JTextField realV = new JTextField();
                JTextField imagV = new JTextField();
                JLabel rT = new JLabel("Réel");
                JLabel iT = new JLabel("Imaginaire");
                rT.setBounds(200,400,70,40);
                iT.setBounds(200,450,70,40);
                realV.setBounds(270,400,60,30);
                imagV.setBounds(270,450,60,30);
                f.add(realV);
                f.add(imagV);
                f.add(rT);
                f.add(iT);
                juliaButton.setSelected(true);
                JButton generatenew = new JButton("Générer");
                generatenew.setBounds(200,500,200,20);
                f.add(generatenew);
                f.setLayout(null);
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
                            System.out.println("Julia");
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
                            System.out.println("Mandel");
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
                /**Mandel mandele = new Mandel();
                mandele.setLayout(null);
                windowtotal.add(mandele);**/
                //windowtotal.add(checkb);

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
        System.out.println("Je suis passé ici");
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


    private static void ShowJulia(JFrame windowtotal) {
    }


}
