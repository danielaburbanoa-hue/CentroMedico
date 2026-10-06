package com.example.aplicacion_danielab;

public class Persona {
    private String nombre;
    private String apellido;
    private String tipodni;
    private String dni;
    private int edad;
    private String pais;
    private double estatura;

    // Constructor
    public Persona(String nombre, String apellido,String tipodni, String dni, int edad, String pais, double estatura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipodni = tipodni;
        this.dni = dni;
        this.edad = edad;
        this.pais = pais;
        this.estatura = estatura;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getTipodni() { return tipodni; }
    public void setTipodni(String tipodni) { this.tipodni = tipodni; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public double getEstatura() { return estatura; }
    public void setEstatura(double estatura) { this.estatura = estatura; }

    // Método general
    public String mostrarInformacion() {
        return "Nombre: " + nombre + " " + apellido + "\nTipo DNI: " + tipodni + "\nDNI: " + dni + "\nEdad: " + edad + " años\nPaís: " + pais + "\nEstatura: " + estatura + " m";
    }
}