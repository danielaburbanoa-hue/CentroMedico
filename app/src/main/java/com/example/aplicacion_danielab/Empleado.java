package com.example.aplicacion_danielab;

public class Empleado extends Persona {
    private String idEmpleado;
    private double salario;

    // Constructor que llama al constructor de Persona (super)
    public Empleado(String nombre, String apellido, String tipodni, String dni, int edad, String pais, double estatura, String idEmpleado, double salario) {
        super(nombre, apellido, tipodni, dni, edad, pais, estatura);
        this.idEmpleado = idEmpleado;
        this.salario = salario;
    }

    // Getters y Setters
    public String getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(String idEmpleado) { this.idEmpleado = idEmpleado; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    // Sobrescritura de metodo
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\nID Empleado: " + idEmpleado + "\nSalario: $" + salario;
    }
}