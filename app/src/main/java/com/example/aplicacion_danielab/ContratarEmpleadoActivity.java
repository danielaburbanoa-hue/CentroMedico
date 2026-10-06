package com.example.aplicacion_danielab;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Actividad para contratar nuevos empleados.
 * Usa TabLayout para seleccionar el rol y muestra campos dinámicamente.
 */
public class ContratarEmpleadoActivity extends AppCompatActivity {

    private TabLayout tabRoles;
    private TextInputEditText etNombre;
    private TextInputEditText etApellido;
    private TextInputEditText etDni;
    private TextInputEditText etEdad;
    private TextInputEditText etIdEmpleado;
    private TextInputEditText etFechaIngreso;
    private TextInputEditText etSueldo;
    private TextInputEditText etEspecialidad;
    private TextInputEditText etNumeroColegiatura;
    private TextInputEditText etDepartamento;
    private TextInputEditText etPuesto;

    private View layoutDatosMedico;
    private View layoutDatosAdmin;

    private MaterialButton btnContratar;

    private String rolSeleccionado = "MEDICO"; // Por defecto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar_empleado);

        inicializarVistas();
        configurarListeners();

        // Mostrar campos de médico por defecto
        mostrarCamposMedico();
    }

    private void inicializarVistas() {
        tabRoles = findViewById(R.id.tabRoles);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etDni = findViewById(R.id.etDni);
        etEdad = findViewById(R.id.etEdad);
        etIdEmpleado = findViewById(R.id.etIdEmpleado);
        etFechaIngreso = findViewById(R.id.etFechaIngreso);
        etSueldo = findViewById(R.id.etSueldo);
        etEspecialidad = findViewById(R.id.etEspecialidad);
        etNumeroColegiatura = findViewById(R.id.etNumeroColegiatura);
        etDepartamento = findViewById(R.id.etDepartamento);
        etPuesto = findViewById(R.id.etPuesto);

        layoutDatosMedico = findViewById(R.id.layoutDatosMedico);
        layoutDatosAdmin = findViewById(R.id.layoutDatosAdmin);

        btnContratar = findViewById(R.id.btnContratar);
    }

    private void configurarListeners() {
        // Listener para TabLayout
        tabRoles.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0: // Médico
                        rolSeleccionado = "MEDICO";
                        mostrarCamposMedico();
                        break;
                    case 1: // Enfermero
                        rolSeleccionado = "ENFERMERO";
                        ocultarTodosCampos();
                        break;
                    case 2: // Administrativo
                        rolSeleccionado = "ADMINISTRATIVO";
                        mostrarCamposAdmin();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Listener del botón contratar
        btnContratar.setOnClickListener(v -> validarYContratar());
    }

    private void mostrarCamposMedico() {
        layoutDatosMedico.setVisibility(View.VISIBLE);
        layoutDatosAdmin.setVisibility(View.GONE);
    }

    private void mostrarCamposAdmin() {
        layoutDatosMedico.setVisibility(View.GONE);
        layoutDatosAdmin.setVisibility(View.VISIBLE);
    }

    private void ocultarTodosCampos() {
        layoutDatosMedico.setVisibility(View.GONE);
        layoutDatosAdmin.setVisibility(View.GONE);
    }

    private void validarYContratar() {
        // Obtener valores
        String nombre = etNombre.getText() != null ? etNombre.getText().toString().trim() : "";
        String apellido = etApellido.getText() != null ? etApellido.getText().toString().trim() : "";
        String dni = etDni.getText() != null ? etDni.getText().toString().trim() : "";
        String edadStr = etEdad.getText() != null ? etEdad.getText().toString().trim() : "";
        String idEmpleado = etIdEmpleado.getText() != null ? etIdEmpleado.getText().toString().trim() : "";
        String fechaIngreso = etFechaIngreso.getText() != null ? etFechaIngreso.getText().toString().trim() : "";
        String sueldoStr = etSueldo.getText() != null ? etSueldo.getText().toString().trim() : "";

        // Validar campos generales
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() ||
            edadStr.isEmpty() || idEmpleado.isEmpty() || sueldoStr.isEmpty()) {
            mostrarSnackbar("Por favor completa todos los campos");
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            double salario = Double.parseDouble(sueldoStr);

            Empleado empleado = null;

            if ("MEDICO".equals(rolSeleccionado)) {
                String especialidad = etEspecialidad.getText() != null ?
                        etEspecialidad.getText().toString().trim() : "";
                String numeroColegiatura = etNumeroColegiatura.getText() != null ?
                        etNumeroColegiatura.getText().toString().trim() : "";

                if (especialidad.isEmpty() || numeroColegiatura.isEmpty()) {
                    mostrarSnackbar("Por favor completa los datos del médico");
                    return;
                }

                empleado = new Medico(nombre, apellido, "DNI", dni, edad,
                        "Colombiana", 1.70, idEmpleado, salario,
                        especialidad, numeroColegiatura);

            } else if ("ADMINISTRATIVO".equals(rolSeleccionado)) {
                String departamento = etDepartamento.getText() != null ?
                        etDepartamento.getText().toString().trim() : "";
                String puesto = etPuesto.getText() != null ?
                        etPuesto.getText().toString().trim() : "";

                if (departamento.isEmpty() || puesto.isEmpty()) {
                    mostrarSnackbar("Por favor completa los datos administrativos");
                    return;
                }

                empleado = new Administrativo(nombre, apellido, "DNI", dni, edad,
                        "Colombiana", 1.70, idEmpleado, salario,
                        departamento, puesto);

            } else if ("ENFERMERO".equals(rolSeleccionado)) {
                empleado = new Empleado(nombre, apellido, "DNI", dni, edad,
                        "Colombiana", 1.70, idEmpleado, salario);
            }

            if (empleado != null) {
                mostrarSnackbar("✓ Empleado " + nombre + " " + apellido + " contratado exitosamente");
                empleado.mostrarInformacion();

                new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
                    finish();
                }, 1500);
            }

        } catch (NumberFormatException e) {
            mostrarSnackbar("Por favor ingresa valores válidos en edad y sueldo");
        }
    }

    private void mostrarSnackbar(String mensaje) {
        Snackbar.make(btnContratar, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}