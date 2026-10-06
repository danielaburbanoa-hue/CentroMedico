package com.example.aplicacion_danielab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

/**
 * Adapter para mostrar la lista de empleados en el RecyclerView.
 * Muestra avatar con iniciales, nombre, cargo, especialidad/turno e ID.
 */
public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.EmpleadoViewHolder> {

    private List<Empleado> empleados;
    private OnEmpleadoClickListener listener;
    private int posicionSeleccionada = -1;

    public interface OnEmpleadoClickListener {
        void onEmpleadoClick(Empleado empleado, int posicion);
    }

    public EmpleadoAdapter(List<Empleado> empleados, OnEmpleadoClickListener listener) {
        this.empleados = empleados;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_empleado, parent, false);
        return new EmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        Empleado empleado = empleados.get(position);
        holder.bind(empleado, position);
    }

    @Override
    public int getItemCount() {
        return empleados != null ? empleados.size() : 0;
    }

    public void actualizarLista(List<Empleado> nuevaLista) {
        empleados = nuevaLista;
        posicionSeleccionada = -1;
        notifyDataSetChanged();
    }

    public void seleccionarEmpleado(int posicion) {
        int posicionAnterior = posicionSeleccionada;
        posicionSeleccionada = posicion;

        if (posicionAnterior >= 0) {
            notifyItemChanged(posicionAnterior);
        }
        if (posicion >= 0) {
            notifyItemChanged(posicion);
        }
    }

    class EmpleadoViewHolder extends RecyclerView.ViewHolder {

        private FrameLayout avatarFrame;
        private TextView tvInicialesAvatar;
        private TextView tvNombreEmpleado;
        private TextView tvCargoEmpleado;
        private TextView tvEspecialidadTurno;
        private TextView tvIdEmpleado;
        private MaterialCardView cardEmpleado;

        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarFrame = itemView.findViewById(R.id.avatarFrame);
            tvInicialesAvatar = itemView.findViewById(R.id.tvInicialesAvatar);
            tvNombreEmpleado = itemView.findViewById(R.id.tvNombreEmpleado);
            tvCargoEmpleado = itemView.findViewById(R.id.tvCargoEmpleado);
            tvEspecialidadTurno = itemView.findViewById(R.id.tvEspecialidadTurno);
            tvIdEmpleado = itemView.findViewById(R.id.tvIdEmpleado);
            cardEmpleado = itemView.findViewById(R.id.cardEmpleado);
        }

        public void bind(Empleado empleado, int posicion) {
            // Avatar con iniciales
            String iniciales = obtenerIniciales(empleado.getNombre(), empleado.getApellido());
            tvInicialesAvatar.setText(iniciales);

            // Información básica
            tvNombreEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
            tvIdEmpleado.setText(empleado.getIdEmpleado());

            // Cargo según tipo
            if (empleado instanceof Medico) {
                Medico medico = (Medico) empleado;
                tvCargoEmpleado.setText("Médico");
                tvEspecialidadTurno.setText(medico.getEspecialidad());
            } else if (empleado instanceof Administrativo) {
                Administrativo admin = (Administrativo) empleado;
                tvCargoEmpleado.setText("Administrativo");
                tvEspecialidadTurno.setText(admin.getPuesto());
            } else {
                tvCargoEmpleado.setText("Empleado");
                tvEspecialidadTurno.setText(empleado.getIdEmpleado());
            }

            // Efecto de selección
            if (posicion == posicionSeleccionada) {
                cardEmpleado.setStrokeColor(itemView.getContext().getColor(R.color.verde_primario_oscuro));
                cardEmpleado.setStrokeWidth(2);
            } else {
                cardEmpleado.setStrokeWidth(0);
            }

            // Click listener
            itemView.setOnClickListener(v -> {
                seleccionarEmpleado(posicion);
                if (listener != null) {
                    listener.onEmpleadoClick(empleado, posicion);
                }
            });
        }

        private String obtenerIniciales(String nombre, String apellido) {
            String iniciales = "";
            if (nombre != null && !nombre.isEmpty()) {
                iniciales += nombre.charAt(0);
            }
            if (apellido != null && !apellido.isEmpty()) {
                iniciales += apellido.charAt(0);
            }
            return iniciales.toUpperCase();
        }
    }
}