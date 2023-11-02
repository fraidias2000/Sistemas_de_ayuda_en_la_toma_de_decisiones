package com.example.empresa_telefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;

public class PANTALLA_PRINCIPAL extends AppCompatActivity {
    private Button entrar_aplicacion;
    private Button salir_aplicacion;
    private CheckBox terminos_condiciones;
    private String mensaje_error = "No se puede acceder a la aplicación sin aceptar los términos y condiciones";
    private TextView textView_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        //CheckBox TERMINOS Y CONDICIONES
        terminos_condiciones = (CheckBox) findViewById(R.id.checkbox_terminos_y_condiciones);

        textView_error = findViewById(R.id.textView_error);

        //DECLARACION BOTON ENTRAR APLICACION
        entrar_aplicacion = (Button) findViewById(R.id.button_entrar_aplicacion);
        entrar_aplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(terminos_condiciones.isChecked()){
                    openMenuInicio();
                }else{
                    //Toast.makeText(PANTALLA_PRINCIPAL.this,mensaje_error , Toast.LENGTH_LONG).show();
                    textView_error.setText(mensaje_error);
                }
            }
        });

        //DECLARACION BOTON SALIR APLICACION
        salir_aplicacion = (Button) findViewById(R.id.button_salir_aplicacion);
        salir_aplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

public void openMenuInicio(){
    Intent menuInicio = new Intent(this, MENU_INICIO.class);
    startActivity(menuInicio);
}
    public void openTerminosCondiciones(){
        Intent terminosCondiciones = new Intent(this, TERMINOS_Y_CONDICIONES.class);
        startActivity(terminosCondiciones);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_terminos_y_condiciones:
                if (checked)
                    openTerminosCondiciones();
                break;

            // TODO: Veggie sandwich
        }
    }
}
