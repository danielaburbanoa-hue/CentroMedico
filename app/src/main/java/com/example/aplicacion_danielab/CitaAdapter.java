package com.example.aplicacion_danielab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter para la lista completa de citas del fragment Citas.
 * Muestra hora, estado, paciente, doctor asignado, especialidad y motivo.
 */
public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.CitaViewHolder> {

    private final List<Cita> citas;

    public CitaAdapter(List<Cita> citas) {
        this.citas = citas;
    }

    @NonNull
    @Override
    public CitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cita, parent, false);
        return new CitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitaViewHolder holder, int position) {
        holder.bind(citas.get(position));
    }

    @Override
    public int getItemCount() {
        return citas != null ? citas.size() : 0;
    }

    static class CitaViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvHoraCitaCompleta;
        private final TextView tvEstadoCitaCompleta;
        private final TextView tvPacienteCita;
        private final TextView tvDoctorAsignado;
        private final TextView tvEspecialidadCita;
        private final TextView tvMotivoCitaCompleta;

        public CitaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHoraCitaCompleta = itemView.findViewById(R.id.tvHoraCitaCompleta);
            tvEstadoCitaCompleta = itemView.findViewById(R.id.tvEstadoCitaCompleta);
            tvPacienteCita = itemView.findViewById(R.id.tvPacienteCita);
            tvDoctorAsignado = itemView.findViewById(R.id.tvDoctorAsignado);
            tvEspecialidadCita = itemView.findViewById(R.id.tvEspecialidadCita);
            tvMotivoCitaCompleta = itemView.findViewById(R.id.tvMotivoCitaCompleta);
        }

        public void bind(Cita cita) {
            tvHoraCitaCompleta.setText(cita.getHora());
            tvEstadoCitaCompleta.setText(cita.getEstado());
            tvPacienteCita.setText(cita.getNombrePaciente());
            tvDoctorAsignado.setText("Dr. " + cita.getDoctorAsignado());
            tvEspecialidadCita.setText(cita.getEspecialidad());
            tvMotivoCitaCompleta.setText(cita.getMotivo());
        }
    }
}