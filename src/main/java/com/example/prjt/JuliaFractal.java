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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class JuliaFractal extends JPanel{

    private static Graphics2D ggglo;
    public static final int MAX_RGB_VALUE = 255;
    public static double zoomv;
    public static int imgnbr;
    public double imageSize = 800;
    public double real;
    public double imag;
    static BufferedImage img;
    double cReal = -0.8;
    double cImag = 0.156;
    static double deplacementVertical = 0;
    static double deplacementHorizontal = 0;

    private static final int crunchifyThreads = 30;
    ExecutorService executor = Executors.newFixedThreadPool(4);
    public JuliaFractal(double zoomv,double deplacement1,double deplacement2) {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.white);
        this.zoomv = zoomv;
        imgnbr++;
        //System.out.println(imgnbr);
        this.real = 0.285;
        this.imag = 0.01;
        deplacementHorizontal = deplacement1;
        deplacementVertical = deplacement2;
    }


    public  void setReAndIm(float r, float i){
        this.cReal = r;
        this.cImag = i;
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

    synchronized public void drawJuliaSet(Graphics2D g, double zoom, double posx, double posy,double real,double imag, JFrame windowtotal) throws IOException, InterruptedException {
        long startTime = System.nanoTime();
        img = new BufferedImage((int)imageSize, (int)imageSize,BufferedImage.TYPE_INT_RGB);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));



        NombreComplex constant = new NombreComplex(cReal,cImag);


        int max_iter = 255;
        ThreadDraw D1 = new ThreadDraw(zoom,0,constant,(int)imageSize,0,(int)imageSize/4,img,this,windowtotal);
        ThreadDraw D2 = new ThreadDraw(zoom,1,constant,(int)imageSize,(int)imageSize/4-1,(int)imageSize/2,img,this,windowtotal);
        ThreadDraw D3 = new ThreadDraw(zoom,1,constant,(int)imageSize,(int)imageSize/2-1,3*(int)imageSize/4,img,this,windowtotal);
        ThreadDraw D4 = new ThreadDraw(zoom,2,constant,(int)imageSize,(3*(int)imageSize/4)-1,(int)imageSize,img,this,windowtotal);
        executor.execute(D1);
        executor.execute(D2);
        executor.execute(D3);
        executor.execute(D4);

        executor.shutdown();
        while (!executor.isTerminated()) {   }

        executor = Executors.newFixedThreadPool(4);
        ImageIO.write(img,"PNG", new File(imgnbr+".png"));
        //System.out.println(imgnbr+" a été fait");
        ImageIO.write(img,"PNG", new File("julia.png"));
        if(g != null){
            g.drawImage(img, 0, 0, null);
        }
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime)/1000000);

        if(windowtotal !=null){
            repaintng(windowtotal);
        }
        //System.out.println(duration+"ms pour générer l'image");
    }

    private void repaintng(JFrame windowtotal) {
        windowtotal.repaint();
    }

    @Override
    public void paintComponent(Graphics gg) {
        ggglo = (Graphics2D) gg;
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            drawJuliaSet(g,zoomv,0,0,0,0,null);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static double getActualZoom(){
        return zoomv;
    }


    public void drawz(double zoom, int x, NombreComplex nbcpl,int imageSize,int yinit,int sizemax){
        for(int y=yinit; y<sizemax; y++)
        {
            NombreComplex z0 = new NombreComplex();

            NombreComplex zn = new NombreComplex((zoom*(x-imageSize/2)/(imageSize/2))+this.deplacementHorizontal, (zoom*(y-imageSize/2)/(imageSize/2)+this.deplacementVertical));
            int i =0;
            while(i<1000 && zn.mod() <= 2)
            {
                z0 = zn;
                zn = zn.times(zn).add(nbcpl);
                i++;
            }


            float Hued = Math.abs((((i%2000)/1999.0f)*6));


            Color color = Color.getHSBColor(Hued, 0.75f, 1.0f);
            if(i>10){
                img.setRGB(x,y,invert(color).getRGB());
            }else{
                img.setRGB(x,y,Color.BLACK.getRGB());
            }
        }
    }
    public static void exportImg(String k) {
        try {
            ImageIO.write(img,"PNG", new File(k+".png"));
        } catch (IOException e) {
            System.out.println("Erreur lors de l'exportation de l'image");
        }
    }

}
