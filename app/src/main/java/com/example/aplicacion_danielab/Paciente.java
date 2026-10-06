package com.example.aplicacion_danielab;

/**
 * Modelo de datos para un Paciente.
 * Contiene datos personales, tratamiento y médico asignado.
 */
public class Paciente {

    private String nombre;
    private String apellido;
    private String dni;
    private int edad;
    private String tratamiento;
    private String doctorAsignado; // Nombre del médico responsable

    public Paciente(String nombre, String apellido, String dni, int edad,
                    String tratamiento, String doctorAsignado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.tratamiento = tratamiento;
        this.doctorAsignado = doctorAsignado;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getNombreCompleto() { return nombre + " " + apellido; }
    public String getDni() { return dni; }
    public int getEdad() { return edad; }
    public String getTratamiento() { return tratamiento; }
    public String getDoctorAsignado() { return doctorAsignado; }

    /** Devuelve las dos iniciales del nombre y apellido. */
    public String getIniciales() {
        String ini = "";
        if (nombre != null && !nombre.isEmpty()) ini += nombre.charAt(0);
        if (apellido != null && !apellido.isEmpty()) ini += apellido.charAt(0);
        return ini.toUpperCase();
    }
}