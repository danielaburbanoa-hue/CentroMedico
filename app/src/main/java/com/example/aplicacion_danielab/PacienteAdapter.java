package com.example.aplicacion_danielab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter para la lista de pacientes del fragment Pacientes.
 * Muestra datos personales, tratamiento y médico asignado.
 */
public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder> {

    private final List<Paciente> pacientes;

    public PacienteAdapter(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @NonNull
    @Override
    public PacienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paciente, parent, false);
        return new PacienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PacienteViewHolder holder, int position) {
        holder.bind(pacientes.get(position));
    }

    @Override
    public int getItemCount() {
        return pacientes != null ? pacientes.size() : 0;
    }

    static class PacienteViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvInicialesPaciente;
        private final TextView tvNombrePaciente;
        private final TextView tvDniPaciente;
        private final TextView tvEdadPaciente;
        private final TextView tvTratamientoPaciente;
        private final TextView tvDoctorPaciente;

        public PacienteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInicialesPaciente = itemView.findViewById(R.id.tvInicialesPaciente);
            tvNombrePaciente = itemView.findViewById(R.id.tvNombrePaciente);
            tvDniPaciente = itemView.findViewById(R.id.tvDniPaciente);
            tvEdadPaciente = itemView.findViewById(R.id.tvEdadPaciente);
            tvTratamientoPaciente = itemView.findViewById(R.id.tvTratamientoPaciente);
            tvDoctorPaciente = itemView.findViewById(R.id.tvDoctorPaciente);
        }

        public void bind(Paciente paciente) {
            tvInicialesPaciente.setText(paciente.getIniciales());
            tvNombrePaciente.setText(paciente.getNombreCompleto());
            tvDniPaciente.setText("DNI: " + paciente.getDni());
            tvEdadPaciente.setText(paciente.getEdad() + " años");
            tvTratamientoPaciente.setText(paciente.getTratamiento());
            tvDoctorPaciente.setText("Dr. " + paciente.getDoctorAsignado());
        }
    }
}