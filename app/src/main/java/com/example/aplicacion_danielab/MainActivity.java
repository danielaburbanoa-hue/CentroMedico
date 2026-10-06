package com.example.aplicacion_danielab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Pantalla principal que configura dinámicamente el BottomNavigationView.
 * Solo 4 fragments: Inicio, Pacientes, Citas y Personal.
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private String rolUsuario;
    private String idEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rolUsuario = getIntent().getStringExtra(LoginActivity.EXTRA_ROLE);
        idEmpleado = getIntent().getStringExtra("ID_EMPLEADO");

        inicializarVistas();
        configurarToolbar();
        configurarBottomNavigation();

        if (savedInstanceState == null) {
            cargarFragmentInicial();
        }
    }

    private void inicializarVistas() {
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void configurarToolbar() {
        com.google.android.material.appbar.MaterialToolbar toolbar =
                findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void configurarBottomNavigation() {
        if (rolUsuario == null) rolUsuario = LoginActivity.ROLE_ADMINISTRATIVO;

        switch (rolUsuario) {
            case LoginActivity.ROLE_MEDICO:
                bottomNavigation.inflateMenu(R.menu.menu_bottom_medico);
                break;
            case LoginActivity.ROLE_ENFERMERO:
                bottomNavigation.inflateMenu(R.menu.menu_bottom_enfermero);
                break;
            case LoginActivity.ROLE_ADMINISTRATIVO:
            default:
                bottomNavigation.inflateMenu(R.menu.menu_bottom_admin);
                break;
        }

        bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String titulo = "";

        int itemId = item.getItemId();

        if (itemId == R.id.nav_inicio) {
            fragment = new InicioFragment();
            titulo = "Inicio";
        } else if (itemId == R.id.nav_pacientes) {
            fragment = new PacientesFragment();
            titulo = "Pacientes";
        } else if (itemId == R.id.nav_citas) {
            fragment = new CitasFragment();
            titulo = "Citas";
        } else if (itemId == R.id.nav_personal) {
            fragment = new PersonalFragment();
            titulo = "Personal";
        }

        if (fragment != null) {
            if (getSupportActionBar() != null) getSupportActionBar().setTitle(titulo);
            cargarFragment(fragment);
            return true;
        }

        return false;
    }

    private void cargarFragmentInicial() {
        Fragment fragment = new InicioFragment();
        String titulo = "Inicio";

        if (LoginActivity.ROLE_MEDICO.equals(rolUsuario)) {
            titulo = "Médico - " + (idEmpleado != null ? idEmpleado : "");
        } else if (LoginActivity.ROLE_ENFERMERO.equals(rolUsuario)) {
            titulo = "Enfermero - " + (idEmpleado != null ? idEmpleado : "");
        } else if (LoginActivity.ROLE_ADMINISTRATIVO.equals(rolUsuario)) {
            titulo = "Administrativo - " + (idEmpleado != null ? idEmpleado : "");
        }

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(titulo);
        cargarFragment(fragment);
    }

    private void cargarFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    // ==================== Fragment Inicio ====================
    public static class InicioFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_inicio, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            // Setear fecha de hoy
            TextView tvFechaHoy = view.findViewById(R.id.tvFechaHoy);
            SimpleDateFormat format = new SimpleDateFormat("EEEE, d 'de' MMMM", new Locale("es", "ES"));
            tvFechaHoy.setText(format.format(new Date()).toUpperCase());

            // Configurar recycler de citas del día
            RecyclerView recycler = view.findViewById(R.id.recyclerCitasHoy);
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));

            // Datos simulados
            List<Cita> citasHoy = new ArrayList<>();
            citasHoy.add(new Cita("08:00", "Carlos Mendez", "Elena Sánchez",
                    "Cardiología", "Chequeo rutina", "Confirmada"));
            citasHoy.add(new Cita("09:30", "Ana Torres", "Elena Sánchez",
                    "Cardiología", "Control mensual", "Pendiente"));
            citasHoy.add(new Cita("11:00", "Luis Gómez", "Juan Rodríguez",
                    "Pediatría", "Vacunación", "Pendiente"));

            recycler.setAdapter(new CitaResumenAdapter(citasHoy));

            // Actualizar contadores
            TextView tvContadorCitas = view.findViewById(R.id.tvContadorCitas);
            tvContadorCitas.setText(String.valueOf(citasHoy.size()));
        }
    }

    // ==================== Fragment Pacientes ====================
    public static class PacientesFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_pacientes, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            RecyclerView recycler = view.findViewById(R.id.recyclerPacientes);
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));

            // Datos simulados
            List<Paciente> pacientes = new ArrayList<>();
            pacientes.add(new Paciente("Carlos", "Mendez", "12345678", 45,
                    "Hipertensión arterial - Control mensual", "Elena Sánchez"));
            pacientes.add(new Paciente("Ana", "Torres", "87654321", 32,
                    "Tratamiento arritmia leve", "Elena Sánchez"));
            pacientes.add(new Paciente("Luis", "Gómez", "11223344", 8,
                    "Esquema de vacunación regular", "Juan Rodríguez"));
            pacientes.add(new Paciente("Maria", "Pérez", "44332211", 55,
                    "Fisioterapia post-operatoria", "Elena Sánchez"));

            recycler.setAdapter(new PacienteAdapter(pacientes));
        }
    }

    // ==================== Fragment Citas ====================
    public static class CitasFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_citas, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            RecyclerView recycler = view.findViewById(R.id.recyclerCitas);
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));

            // Datos simulados
            List<Cita> citas = new ArrayList<>();
            citas.add(new Cita("08:00", "Carlos Mendez", "Elena Sánchez",
                    "Cardiología", "Chequeo rutina", "Confirmada"));
            citas.add(new Cita("09:30", "Ana Torres", "Elena Sánchez",
                    "Cardiología", "Control mensual", "Pendiente"));
            citas.add(new Cita("11:00", "Luis Gómez", "Juan Rodríguez",
                    "Pediatría", "Vacunación", "Pendiente"));
            citas.add(new Cita("14:00", "Maria Pérez", "Elena Sánchez",
                    "Cardiología", "Evaluación post-cirugía", "Confirmada"));
            citas.add(new Cita("16:30", "Roberto Ruiz", "Juan Rodríguez",
                    "Pediatría", "Fiebre persistente", "Pendiente"));

            recycler.setAdapter(new CitaAdapter(citas));
        }
    }
}