package com.example.aplicacion_danielab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter para la lista de citas resumidas del fragment Inicio.
 * Muestra hora, nombre del paciente, doctor asignado y estado.
 */
public class CitaResumenAdapter extends RecyclerView.Adapter<CitaResumenAdapter.CitaResumenViewHolder> {

    private final List<Cita> citas;

    public CitaResumenAdapter(List<Cita> citas) {
        this.citas = citas;
    }

    @NonNull
    @Override
    public CitaResumenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cita_resumen, parent, false);
        return new CitaResumenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitaResumenViewHolder holder, int position) {
        holder.bind(citas.get(position));
    }

    @Override
    public int getItemCount() {
        return citas != null ? citas.size() : 0;
    }

    static class CitaResumenViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvHoraCita;
        private final TextView tvNombrePacienteCita;
        private final TextView tvDoctorCita;
        private final TextView tvMotivoCita;
        private final TextView tvEstadoCita;

        public CitaResumenViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHoraCita = itemView.findViewById(R.id.tvHoraCita);
            tvNombrePacienteCita = itemView.findViewById(R.id.tvNombrePacienteCita);
            tvDoctorCita = itemView.findViewById(R.id.tvDoctorCita);
            tvMotivoCita = itemView.findViewById(R.id.tvMotivoCita);
            tvEstadoCita = itemView.findViewById(R.id.tvEstadoCita);
        }

        public void bind(Cita cita) {
            tvHoraCita.setText(cita.getHora());
            tvNombrePacienteCita.setText(cita.getNombrePaciente());
            tvDoctorCita.setText("Dr. " + cita.getDoctorAsignado());
            tvMotivoCita.setText(cita.getMotivo());
            tvEstadoCita.setText(cita.getEstado());
        }
    }
}