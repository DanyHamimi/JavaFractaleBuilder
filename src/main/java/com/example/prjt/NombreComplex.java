package com.example.prjt;

public class NombreComplex {

    private double real;
    private double imaginary;

    public NombreComplex()
    {
        real = 0.0;
        imaginary = 0.0;
    }


    public NombreComplex(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }






    public double mod()
    {
        return Math.sqrt(Math.pow(this.real,2) + Math.pow(this.imaginary,2));
    }

    public double abs(){
        return Math.hypot(this.real, this.imaginary);
    }

    public NombreComplex times(NombreComplex number){

        double a = this.real*number.real;
        double b = this.imaginary*number.real;
        double c = this.real*number.imaginary;
        double d = this.imaginary*number.imaginary*-1;

        double newReal = a+d;
        double newImaginary = b+c;


        NombreComplex newNombreComplex = new NombreComplex(newReal, newImaginary);
        return newNombreComplex;
    }

    public NombreComplex add(NombreComplex number){

        double newReal = this.real+number.real;
        double newImaginary = this.imaginary+number.imaginary;

        return new NombreComplex(newReal, newImaginary);

    }

    public void multiply(NombreComplex complex_number)
    {
        double _real = this.real*complex_number.real - this.imaginary*complex_number.imaginary;
        double _imaginary = this.real*complex_number.imaginary + this.imaginary*complex_number.real;

        this.real = _real;
        this.imaginary = _imaginary;
    }

}
