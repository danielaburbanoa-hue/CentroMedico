package com.example.aplicacion_danielab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Fragment para gestionar el personal del hospital.
 * Muestra lista de empleados filtrada por rol con TabLayout,
 * y detalle del empleado seleccionado.
 */
public class PersonalFragment extends Fragment implements EmpleadoAdapter.OnEmpleadoClickListener {

    private TabLayout tabLayout;
    private RecyclerView recyclerPersonal;
    private MaterialCardView cardDetalleEmpleado;
    private MaterialTextView tvFechaIngreso;
    private MaterialTextView tvSueldo;
    private MaterialButton btnContratar;
    private EmpleadoAdapter adapter;

    private List<Empleado> todosEmpleados;
    private List<Empleado> empleadosActuales;
    private String filtroActual = "TODOS";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializarVistas(view);
        cargarDatosEmpleados();
        configurarTabLayout();
        configurarRecyclerView();
        configurarBotones();
    }

    private void inicializarVistas(View view) {
        tabLayout = view.findViewById(R.id.tabLayoutPersonal);
        recyclerPersonal = view.findViewById(R.id.recyclerPersonal);
        cardDetalleEmpleado = view.findViewById(R.id.cardDetalleEmpleado);
        tvFechaIngreso = view.findViewById(R.id.tvFechaIngreso);
        tvSueldo = view.findViewById(R.id.tvSueldo);
        btnContratar = view.findViewById(R.id.btnContratar);
    }

    private void cargarDatosEmpleados() {
        todosEmpleados = new ArrayList<>();

        // Médicos
        todosEmpleados.add(new Medico("Elena", "Sánchez", "DNI", "88877766",
                42, "Colombiana", 1.68, "M-001", 4500.0,
                "Cardiología", "COL-9921"));

        todosEmpleados.add(new Medico("Juan", "Rodríguez", "DNI", "77766655",
                35, "Colombiana", 1.75, "M-002", 4200.0,
                "Pediatría", "COL-9922"));

        // Administrativos
        todosEmpleados.add(new Administrativo("Ricardo", "Gómez", "DNI", "11122233",
                35, "Mexicano", 1.80, "A-505", 2200.0,
                "Recursos Humanos", "Analista"));

        todosEmpleados.add(new Administrativo("Carolina", "López", "DNI", "99988877",
                28, "Colombiana", 1.65, "A-506", 2100.0,
                "Facturación", "Coordinadora"));

        empleadosActuales = new ArrayList<>(todosEmpleados);
    }

    private void configurarTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                filtrarEmpleados(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void filtrarEmpleados(int posicion) {
        switch (posicion) {
            case 0: // Todos
                filtroActual = "TODOS";
                empleadosActuales = new ArrayList<>(todosEmpleados);
                break;
            case 1: // Médicos
                filtroActual = "MEDICO";
                empleadosActuales = todosEmpleados.stream()
                        .filter(e -> e instanceof Medico)
                        .collect(Collectors.toList());
                break;
            case 2: // Enfermeros
                filtroActual = "ENFERMERO";
                empleadosActuales = todosEmpleados.stream()
                        .filter(e -> !(e instanceof Medico) && !(e instanceof Administrativo))
                        .collect(Collectors.toList());
                break;
            case 3: // Administrativos
                filtroActual = "ADMINISTRATIVO";
                empleadosActuales = todosEmpleados.stream()
                        .filter(e -> e instanceof Administrativo)
                        .collect(Collectors.toList());
                break;
        }

        adapter.actualizarLista(empleadosActuales);
        cardDetalleEmpleado.setVisibility(View.GONE);
    }

    private void configurarRecyclerView() {
        recyclerPersonal.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EmpleadoAdapter(empleadosActuales, this);
        recyclerPersonal.setAdapter(adapter);
    }

    private void configurarBotones() {
        btnContratar.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ContratarEmpleadoActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onEmpleadoClick(Empleado empleado, int posicion) {
        // Mostrar card de detalle
        cardDetalleEmpleado.setVisibility(View.VISIBLE);

        // Mostrar fecha de ingreso (simulada)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        tvFechaIngreso.setText("2023-" + (Math.random() * 12 + 1) + "-" + (Math.random() * 28 + 1));

        // Mostrar sueldo
        tvSueldo.setText(String.format(Locale.getDefault(), "$%.2f", empleado.getSalario()));
    }
}