package com.example.prjt;

import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;


public class JuliaFractal extends Fractal{

    public static double zoomv;
    double cReal = -0.8;
    double cImag = 0.156;

    public JuliaFractal(double zoomv,double deplacement1,double deplacement2,int nbITT) {
        this.setIter(nbITT);
        setPreferredSize(new Dimension(imageSize, imageSize));
        setBackground(Color.white);
        this.zoomv = zoomv;
        deplacementHorizontal = deplacement1;
        deplacementVertical = deplacement2;
    }


    public  void setReAndIm(float r, float i){
        this.cReal = r;
        this.cImag = i;
    }

    synchronized public void drawJuliaSet(Graphics2D g, double zoom, double posx, double posy,double real,double imag, JFrame windowtotal) throws IOException, InterruptedException {
        img = new BufferedImage((int)imageSize, (int)imageSize,BufferedImage.TYPE_INT_RGB);
        NombreComplex constant = new NombreComplex(cReal,cImag);

        JuliaDrawThread D1 = new JuliaDrawThread(zoom,0,constant,(int)imageSize,0,(int)imageSize/4,img,this,windowtotal);
        JuliaDrawThread D2 = new JuliaDrawThread(zoom,1,constant,(int)imageSize,(int)imageSize/4-1,(int)imageSize/2,img,this,windowtotal);
        JuliaDrawThread D3 = new JuliaDrawThread(zoom,1,constant,(int)imageSize,(int)imageSize/2-1,3*(int)imageSize/4,img,this,windowtotal);
        JuliaDrawThread D4 = new JuliaDrawThread(zoom,2,constant,(int)imageSize,(3*(int)imageSize/4)-1,(int)imageSize,img,this,windowtotal);
        executor.execute(D1);
        executor.execute(D2);
        executor.execute(D3);
        executor.execute(D4);

        executor.shutdown();
        while (!executor.isTerminated()) {   }

        executor = Executors.newFixedThreadPool(4);
        if(g != null){
            g.drawImage(img, 0, 0, null);
        }
        if(windowtotal !=null){
            repaintng(windowtotal);
        }
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
            while(i<nbitr && zn.mod() <= 2)
            {
                z0 = zn;
                zn = zn.times(zn).add(nbcpl);
                i++;
            }


            float Hued = Math.abs((((i%2000)/1999.0f)*6));


            Color color = Color.getHSBColor(Hued, 0.75f, 1.0f);
            if(i>10){
                img.setRGB(x,y,setColor(color).getRGB());
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
    public void setZoom(double zoom){
        zoomv = zoom;
        this.repaint();
    }

    public void setDeplacement(double v, double v1) {
        deplacementHorizontal = v;
        deplacementVertical = v1;
        this.repaint();
    }
}
