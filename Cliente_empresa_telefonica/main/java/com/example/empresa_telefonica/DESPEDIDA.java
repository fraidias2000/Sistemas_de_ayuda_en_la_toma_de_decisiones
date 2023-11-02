package com.example.empresa_telefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DESPEDIDA extends AppCompatActivity {
    private Button  boton_finalizar_aplicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despedida);

        boton_finalizar_aplicacion = (Button) findViewById(R.id.boton_finalizar);
        boton_finalizar_aplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

    }
}
