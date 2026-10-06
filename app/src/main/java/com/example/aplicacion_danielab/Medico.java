package com.example.aplicacion_danielab;

public class Medico extends Empleado {
    private String especialidad;
    private String numeroColegiatura;

    // Constructor
    public Medico(String nombre, String apellido, String tipodni, String dni, int edad, String pais,double estatura, String idEmpleado, double salario, String especialidad, String numeroColegiatura) {
        super(nombre, apellido, tipodni, dni, edad, pais, estatura, idEmpleado, salario);
        this.especialidad = especialidad;
        this.numeroColegiatura = numeroColegiatura;
    }

    // Getters y Setters
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getNumeroColegiatura() { return numeroColegiatura; }
    public void setNumeroColegiatura(String numeroColegiatura) { this.numeroColegiatura = numeroColegiatura; }

    // Método propio del médico
    public void atenderPaciente(String paciente) {
        System.out.println("El Dr./Dra. " + getApellido() + " está atendiendo al paciente: " + paciente);
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\nEspecialidad: " + especialidad + "\nN° Colegiatura: " + numeroColegiatura;
    }
}