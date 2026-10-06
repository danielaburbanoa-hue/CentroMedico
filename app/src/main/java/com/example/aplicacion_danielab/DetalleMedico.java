package com.example.aplicacion_danielab;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Segunda pantalla: muestra el detalle del Médico recibido desde MainActivity.
 */
public class DetalleMedico extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_medico);

        // Recuperamos el objeto Medico que enviamos desde MainActivity
        Medico medico = (Medico) getIntent().getSerializableExtra("medico");

        TextView tvDetalleMedico = findViewById(R.id.tvDetalleMedico);

        if (medico != null) {
            // Mostramos la información del médico
            tvDetalleMedico.setText(medico.mostrarInformacion());
        } else {
            tvDetalleMedico.setText("No se recibió información del médico.");
        }
    }
}
