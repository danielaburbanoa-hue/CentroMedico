package com.example.aplicacion_danielab;

public class Administrativo extends Empleado {
    private String departamento;
    private String puesto;

    // Constructor
    public Administrativo(String nombre, String apellido, String tipodni, String dni, int edad, String pais,double estatura, String idEmpleado, double salario, String departamento, String puesto) {
        super(nombre, apellido, tipodni, dni, edad, pais, estatura, idEmpleado, salario);
        this.departamento = departamento;
        this.puesto = puesto;
    }

    // Getters y Setters
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }

    // Método propio del administrativo
    public void gestionarDocumento(String documento) {
        System.out.println(getNombre() + " está gestionando el documento: " + documento);
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\nDepartamento: " + departamento + "\nPuesto: " + puesto;
    }
}