package com.example.aplicacion_danielab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Pantalla de login con selección de rol.
 * Permite al usuario ingresar sus credenciales y seleccionar su rol
 * (Médico, Enfermero o Administrativo) para navegar a la pantalla principal.
 */
public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_ROLE = "ROLE";
    public static final String ROLE_MEDICO = "MEDICO";
    public static final String ROLE_ENFERMERO = "ENFERMERO";
    public static final String ROLE_ADMINISTRATIVO = "ADMINISTRATIVO";

    private TextInputEditText etIdEmpleado;
    private TextInputEditText etContrasena;
    private MaterialCardView cardMedico;
    private MaterialCardView cardEnfermero;
    private MaterialCardView cardAdministrativo;
    private MaterialButton btnIngresar;

    private String rolSeleccionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarVistas();
        configurarListeners();
    }

    private void inicializarVistas() {
        etIdEmpleado = findViewById(R.id.etIdEmpleado);
        etContrasena = findViewById(R.id.etContrasena);
        cardMedico = findViewById(R.id.cardMedico);
        cardEnfermero = findViewById(R.id.cardEnfermero);
        cardAdministrativo = findViewById(R.id.cardAdministrativo);
        btnIngresar = findViewById(R.id.btnIngresar);
    }

    private void configurarListeners() {
        // Listener para tarjeta de Médico
        cardMedico.setOnClickListener(v -> {
            seleccionarRol(ROLE_MEDICO, cardMedico);
        });

        // Listener para tarjeta de Enfermero
        cardEnfermero.setOnClickListener(v -> {
            seleccionarRol(ROLE_ENFERMERO, cardEnfermero);
        });

        // Listener para tarjeta de Administrativo
        cardAdministrativo.setOnClickListener(v -> {
            seleccionarRol(ROLE_ADMINISTRATIVO, cardAdministrativo);
        });

        // Listener para botón Ingresar
        btnIngresar.setOnClickListener(v -> {
            validarYIngresar();
        });
    }

    /**
     * Resalta la tarjeta seleccionada y guarda el rol.
     */
    private void seleccionarRol(String rol, MaterialCardView tarjetaSeleccionada) {
        rolSeleccionado = reiniciarSeleccion();
        rolSeleccionado = rol;

        // Resetear bordes de todas las tarjetas
        cardMedico.setStrokeColor(getColor(R.color.verde_menta_claro));
        cardEnfermero.setStrokeColor(getColor(R.color.verde_menta_claro));
        cardAdministrativo.setStrokeColor(getColor(R.color.verde_menta_claro));

        // Resaltar la tarjeta seleccionada con verde oscuro
        tarjetaSeleccionada.setStrokeColor(getColor(R.color.verde_primario_oscuro));
    }

    private String reiniciarSeleccion() {
        return null;
    }

    /**
     * Valida las credenciales y el rol seleccionado,
     * luego navega a MainActivity con el rol.
     */
    private void validarYIngresar() {
        String idEmpleado = etIdEmpleado.getText() != null ?
                etIdEmpleado.getText().toString().trim() : "";
        String contrasena = etContrasena.getText() != null ?
                etContrasena.getText().toString().trim() : "";

        // Validar que se haya seleccionado un rol
        if (rolSeleccionado == null) {
            Snackbar.make(
                    btnIngresar,
                    "Por favor selecciona un rol",
                    Snackbar.LENGTH_SHORT
            ).show();
            return;
        }

        // Validar credenciales (simplificado para demostración)
        if (idEmpleado.isEmpty() || contrasena.isEmpty()) {
            Snackbar.make(
                    btnIngresar,
                    "Por favor ingresa ID y contraseña",
                    Snackbar.LENGTH_SHORT
            ).show();
            return;
        }

        // Navegar a MainActivity pasando el rol
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(EXTRA_ROLE, rolSeleccionado);
        intent.putExtra("ID_EMPLEADO", idEmpleado);
        startActivity(intent);
        finish();
    }
}