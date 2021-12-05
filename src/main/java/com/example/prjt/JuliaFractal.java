package com.example.prjt;
import com.example.prjt.NombreComplex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static java.lang.System.out;
public class JuliaFractal extends JPanel {

    private static Graphics2D ggglo;
    public static final int MAX_RGB_VALUE = 255;
    public static double zoomv;
    public static int imgnbr;
    public double imageSize = 400;

    public JuliaFractal(double zoomv) {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.white);
        this.zoomv = zoomv;
        imgnbr++;
        System.out.println(imgnbr);
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

    public void drawJuliaSet(Graphics2D g, double zoom, double posx, double posy) throws IOException {
        BufferedImage img = new BufferedImage((int)imageSize, (int)imageSize,BufferedImage.TYPE_3BYTE_BGR);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        out.print("R: ");
        double cReal = 0.285;
        out.print("I): ");
        double cImag = 0.013;

        NombreComplex constant = new NombreComplex(cReal,cImag);


        int max_iter = 2000;

        for(int x=0; x<imageSize; x++)
        {
            for(int y=0; y<imageSize; y++)
            {

                NombreComplex z0 = new NombreComplex();

                NombreComplex zn = new NombreComplex(zoom*(x-imageSize/2)/(imageSize/2), zoom*(y-imageSize/2)/(imageSize/2) );

                int i =0;
                while(i<max_iter && zn.mod() <= 2)
                {
                    z0 = zn;
                    zn = zn.times(zn).add(constant);
                    i++;
                }


                float Hued = Math.abs((((i%2000)/1999.0f)*6));


                Color color = Color.getHSBColor(Hued, 0.75f, 1.0f);
                if(i>10){
                    img.setRGB(x,y,invert(color).getRGB());
                }else{
                    img.setRGB(x,y,Color.BLACK.getRGB());
                }
                //System.out.println(i);
            }
        }

        ImageIO.write(img,"PNG", new File(imgnbr+".png"));
        System.out.println(imgnbr+" a été fait");
        ImageIO.write(img,"PNG", new File("julia.png"));
        g.drawImage(img, 0, 0, null);
    }
    public void drawJuliaSetBis(double zoom, double posx, double posy) throws IOException {
        BufferedImage img = new BufferedImage((int)imageSize, (int)imageSize,BufferedImage.TYPE_3BYTE_BGR);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        out.print("R: ");
        double cReal = 0.285;
        out.print("I): ");
        double cImag = 0.013;

        NombreComplex constant = new NombreComplex(cReal,cImag);


        int max_iter = 2000;

        for(int x=0; x<imageSize; x++)
        {
            for(int y=0; y<imageSize; y++)
            {

                NombreComplex z0 = new NombreComplex();

                NombreComplex zn = new NombreComplex(zoom*(x-imageSize/2)/(imageSize/2), zoom*(y-imageSize/2)/(imageSize/2) );

                int i =0;
                while(i<max_iter && zn.mod() <= 2)
                {
                    z0 = zn;
                    zn = zn.times(zn).add(constant);
                    i++;
                }


                float Hued = Math.abs((((i%2000)/1999.0f)*6));


                Color color = Color.getHSBColor(Hued, 0.75f, 1.0f);
                if(i>10){
                    img.setRGB(x,y,invert(color).getRGB());
                }else{
                    img.setRGB(x,y,Color.BLACK.getRGB());
                }
                //System.out.println(i);
            }
        }

        ImageIO.write(img,"PNG", new File(imgnbr+".png"));
        System.out.println(imgnbr+" a été fait");
        ImageIO.write(img,"PNG", new File("julia.png"));
    }

    @Override
    public void paintComponent(Graphics gg) {
        ggglo = (Graphics2D) gg;
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            drawJuliaSet(g,zoomv,0,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[])throws IOException
    {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            JButton buttonzoom = new JButton("c");
            buttonzoom.setBounds(0, 0, 150, 10);
            f.add(buttonzoom);
            buttonzoom.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    while(zoomv>0.000001){
                        JuliaFractal fractaldraw = new JuliaFractal(zoomv/1.1);
                        System.out.println(zoomv);
                        try {
                            fractaldraw.drawJuliaSetBis(zoomv,0,0);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            Thread.sleep(10);

                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }


                }
            });
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Julia Set");
            f.setResizable(false);
            JuliaFractal fractaldraw = new JuliaFractal(1.3);
            f.add(fractaldraw, BorderLayout.CENTER,1);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);

        });
    }
}
