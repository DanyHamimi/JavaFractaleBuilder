package com.example.prjt;


import com.example.prjt.NombreComplex;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static java.lang.System.out;




public class Mandel extends JPanel {
    public static final double startX = -2;
    public static final double width = 4;
    public static final double startY = 2;
    public static final double height = 4;
    public static final double dx = width/(800-1);
    public static final double dy = height/(800-1);
    public static final int MAX_RGB_VALUE = 255;

    public Mandel() {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.black);
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


    public static NombreComplex convertToComplex(int x, int y){

        double real = startX + x*dx;
        double imaginary = 2 - y*dy;
        return new NombreComplex(real, imaginary);

    }
    public void drawJuliaSet(Graphics2D g) throws IOException {
        double imageSize = 800;

        BufferedImage img = new BufferedImage((int)imageSize, (int)imageSize,BufferedImage.TYPE_3BYTE_BGR);


        int max_iter = 1000;

        for(int x=0; x<imageSize; x++)
        {
            for(int y=0; y<imageSize; y++)
            {

                NombreComplex z0 = convertToComplex(x, y);
                NombreComplex z = z0;
                int i= 0;
                while (i<max_iter){
                    z = z.times(z).add(z0);
                    if (z.abs()>2.0){
                        break;
                    }
                    i++;

                }
                float Hued = Math.abs((((i%256)/255.0f)*6));


                Color color = Color.getHSBColor(Hued, 0.75f, 1.0f);
                if(i>10){
                    img.setRGB(x,y,color.getRGB());
                }else{
                    img.setRGB(x,y,Color.BLACK.getRGB());
                }
            }
        }
        ImageIO.write(img,"PNG", new File("mandel.png"));
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            drawJuliaSet(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[])throws IOException
    {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Mandel");
            f.setResizable(false);
            f.add(new Mandel(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
