package com.example.prjt;


import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Mandel extends Fractal {
    public static final double startX = -2;
    public static final double width = 4;
    public static final double startY = 2;
    public static final double height = 4;
    public static double deplX = 0;
    public static double deplY = 0;
    public static final double dx = width/(800-1);
    public static final double dy = height/(800-1);
    public static double zoom;

    public Mandel(double zoomA, double movex, double movey) {
        this.setIter(250);
        deplX = movex;
        deplY = movey;
        zoom = zoomA;
        setPreferredSize(new Dimension(imageSize, imageSize));
        setBackground(Color.black);
    }



    public NombreComplex convertToComplex(int x, int y){

        double real = zoom*(startX + x*dx)+deplacementHorizontal;
        double imaginary = zoom*(2 - y*dy)+deplacementVertical;
        return new NombreComplex(real, imaginary);

    }
    public void MandelSet(Graphics2D g) throws IOException {
        long startTime = System.nanoTime();


        img = new BufferedImage((int)imageSize, (int)imageSize,BufferedImage.TYPE_3BYTE_BGR);


        MandelDrawThread M1 = new MandelDrawThread(this,imageSize/4,0);
        MandelDrawThread M2 = new MandelDrawThread(this,imageSize/2,imageSize/4-1);
        MandelDrawThread M3 = new MandelDrawThread(this,3*(imageSize/4),imageSize/2-1);
        MandelDrawThread M4 = new MandelDrawThread(this,imageSize,3*(imageSize/4)-1);
        executor.execute(M1);
        executor.execute(M2);
        executor.execute(M3);
        executor.execute(M4);
        executor.shutdown();
        while (!executor.isTerminated()) {   }
        executor = Executors.newFixedThreadPool(4);

        //ImageIO.write(img,"PNG", new File("mandel.png"));
        g.drawImage(img, 0, 0, null);
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime)/1000000);
        System.out.println(duration+"ms pour générer l'image (Mandel)");

    }

    public void drawZ(int x){
        for(int y=0; y<imageSize; y++)
        {

            NombreComplex z0 = convertToComplex(x, y);
            NombreComplex z = z0;
            int i= 0;
            while (i<nbitr){
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
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            MandelSet(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void exportImg(String k) {
        try {
            ImageIO.write(img,"PNG", new File(k+".png"));
        } catch (IOException e) {
            System.out.println("Erreur lors de l'exportation de l'image");
        }
    }

    public double getActualZoom() {
        return zoom;
    }

    public void setZoom(double v) {
        zoom = v;
    }
    public void setDeplacement(double v, double v1) {
        deplacementHorizontal = v;
        deplacementVertical = v1;
        this.repaint();
    }
}
