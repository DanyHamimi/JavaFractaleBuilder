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
public class JuliaFractal extends JPanel {

    public static final int MAX_RGB_VALUE = 255;

    public JuliaFractal() {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.white);
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

    public void drawJuliaSet(Graphics2D g) throws IOException {
        double imageSize = 800;

        BufferedImage img = new BufferedImage((int)imageSize, (int)imageSize,BufferedImage.TYPE_3BYTE_BGR);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        out.print("R: ");
        double cReal = Double.parseDouble(reader.readLine());
        out.print("I): ");
        double cImag = Double.parseDouble(reader.readLine());

        NombreComplex constant = new NombreComplex(cReal,cImag);


        int max_iter = 256;

        // Looping through every pixel of image
        for(int x=0; x<imageSize; x++)
        {
            for(int y=0; y<imageSize; y++)
            {

                NombreComplex z0 = new NombreComplex();

                NombreComplex zn = new NombreComplex(1.3*(x-imageSize/2)/(imageSize/2), 1.3*(y-imageSize/2)/(imageSize/2) );

                int i =0;
                while(i<max_iter && zn.mod() <= 2)
                {
                    z0 = zn;
                    zn.multiply(zn);
                    zn.add(constant);
                    i++;
                }


                float Hued = Math.abs((((i%256)/255.0f)*6));


                Color color = Color.getHSBColor(Hued, 0.75f, 1.0f);
                if(i>10){
                    img.setRGB(x,y,invert(color).getRGB());
                }else{
                    img.setRGB(x,y,Color.BLACK.getRGB());
                }
                //System.out.println(i);
            }
        }
        ImageIO.write(img,"PNG", new File("julia.png"));
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
            f.setTitle("Julia Set");
            f.setResizable(false);
            f.add(new JuliaFractal(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
