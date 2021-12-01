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

    public void add(NombreComplex complex_number)
    {
        this.real = this.real + complex_number.real;
        this.imaginary = this.imaginary + complex_number.imaginary;
    }


    public double mod()
    {
        return Math.sqrt(Math.pow(this.real,2) + Math.pow(this.imaginary,2));
    }

    public void multiply(NombreComplex complex_number)
    {
        double _real = this.real*complex_number.real - this.imaginary*complex_number.imaginary;
        double _imaginary = this.real*complex_number.imaginary + this.imaginary*complex_number.real;

        this.real = _real;
        this.imaginary = _imaginary;
    }

}
