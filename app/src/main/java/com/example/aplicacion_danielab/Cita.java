package com.example.aplicacion_danielab;

/**
 * Modelo de datos para una Cita médica.
 * Contiene la hora, el paciente, el doctor asignado, la especialidad,
 * el motivo y el estado de la cita.
 */
public class Cita {

    private String hora;           // "09:00"
    private String nombrePaciente;
    private String doctorAsignado;
    private String especialidad;
    private String motivo;
    private String estado;         // "Pendiente" | "Confirmada" | "Atendida"

    public Cita(String hora, String nombrePaciente, String doctorAsignado,
                String especialidad, String motivo, String estado) {
        this.hora = hora;
        this.nombrePaciente = nombrePaciente;
        this.doctorAsignado = doctorAsignado;
        this.especialidad = especialidad;
        this.motivo = motivo;
        this.estado = estado;
    }

    public String getHora() { return hora; }
    public String getNombrePaciente() { return nombrePaciente; }
    public String getDoctorAsignado() { return doctorAsignado; }
    public String getEspecialidad() { return especialidad; }
    public String getMotivo() { return motivo; }
    public String getEstado() { return estado; }
}