package com.example.prjt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame implements FunctionsDrawing{

    public static int ValActu = 1;
    public static JuliaFractal fractaldraw = new JuliaFractal(1.3,0,0,1000);
    public static Mandel mandeldraw = new Mandel(1,0,0);
    private static Controller c;
    public static void setController(Controller co){
        c = co;
    }
    public static void main(String[] args) throws IOException {
        {
            SwingUtilities.invokeLater(() -> {
                JFrame windowtotal = new JFrame("Fractales");
                Controller c = new Controller(windowtotal);
                setController(c);
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
                JLabel iV = new JLabel("Ittérations");
                JButton generatenew = new JButton("Générer");
                JTextField iterV = new JTextField("1000");
                JButton Color1 = new JButton("Couleur 1");
                JButton Color2 = new JButton("Couleur 2");
                JButton Color3 = new JButton("Couleur 3");
                iterV.setBounds(360,340,60,30);
                juliaButton.setBounds(250,180,100,15);
                mandelButton.setBounds(350,180,100,15);
                buttonzoom.setBounds(50, 442, 50, 50);
                buttonzoomplus.setBounds(450, 442, 50, 50);
                buttondezoomplus.setBounds(400, 442, 50, 50);
                buttondezoom.setBounds(0, 442, 50, 50);
                fractaldraw.setBounds(800,0,800,800);
                mandeldraw.setBounds(800,0,800,800);
                butexport.setBounds(700,710,100,50);
                movedroite.setBounds(752,443,47,47);
                movegauche.setBounds(658,443,47,47);
                moveHaut.setBounds(705,396,47,47);
                moveBas.setBounds(705,443,47,47);
                rT.setBounds(290,240,70,40);
                iT.setBounds(290,290,70,40);
                realV.setBounds(360,240,60,30);
                imagV.setBounds(360,290,60,30);
                iV.setBounds(290,340,70,40);
                generatenew.setBounds(290,390,200,20);
                Color1.setBounds(50,600,180,50);
                Color2.setBounds(250,600,180,50);
                Color3.setBounds(450,600,180,50);

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
                f.add(iterV);
                f.add(iV);
                f.add(Color1);
                f.add(Color2);
                f.add(Color3);
                f.setLayout(null);
                windowtotal.add(f);
                windowtotal.setResizable(false);
                juliaButton.setSelected(true);

                buttonzoom.addActionListener(c.buttonzoom());
                buttondezoom.addActionListener(c.buttondezoom());
                buttondezoomplus.addActionListener(c.buttondezoomplus());
                buttonzoomplus.addActionListener(c.buttonzoomplus());
                butexport.addActionListener(c.butexport());
                movedroite.addActionListener(c.movedroite());
                movegauche.addActionListener(c.movegauche());
                moveHaut.addActionListener(c.moveHaut());
                moveBas.addActionListener(c.moveBas());
                Color1.addActionListener(c.Color1());
                Color2.addActionListener(c.Color2());
                Color3.addActionListener(c.Color3());
                generatenew.addActionListener(c.generatenew(realV,imagV,f,iterV));
                juliaButton.addActionListener(c.juliaButton(juliaButton,f,rT,iT,realV,imagV,generatenew,iV,iterV));
                mandelButton.addActionListener(c.mandelButton(mandelButton,f,rT,iT,realV,imagV,generatenew,iV,iterV));

                windowtotal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windowtotal.repaint();
            });
        }
    }

}
