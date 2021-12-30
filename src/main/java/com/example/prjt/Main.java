package com.example.prjt;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static void julia(double r, double i, int it, int dim, String nom) throws IOException, InterruptedException {
        JuliaFractal j = new JuliaFractal(1.3,0,0,it);
        j.setImageSize(dim);
        j.setReAndIm((float) r,(float) i);
        j.drawJuliaSet(j.ggglo, 1.3, 0,0,0,0,null);
        j.exportImg(nom);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez chosir entre la version graphique(1) ou terminal(2)");
        int v = scanner.nextInt();
        if(v==1){
            Window win = new Window();
            win.win();
        }else{
            System.out.println("Version terminal:\n");
            System.out.println("Veuillez choisir un nom pour votre fichier");
            String nom = scanner.next();
            System.out.println("Veuillez rentrer la taille que vous voulez pour l'image");
            int dim = scanner.nextInt();
            System.out.println("Veuillez chosir entre Julia(1) ou Mandelbrot(2)");
            int ver = scanner.nextInt();
            if(ver==1){
                System.out.println("Veuillez rentrer le réel");
                double r = scanner.nextDouble();
                System.out.println("Veuillez rentrer l'imaginaire");
                double i = scanner.nextDouble();
                System.out.println("Veuillez rentrer l'itération");
                int it = scanner.nextInt();
                julia(r,i,it,dim,nom);
            }else if(ver==2){
                Mandel m = new Mandel(1,0,0);
                m.setS(dim);
                m.MandelSet(m.ggglo);
                m.exportImg(nom);
            }





        }


    }
}
