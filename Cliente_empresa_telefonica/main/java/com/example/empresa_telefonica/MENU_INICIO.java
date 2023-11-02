package com.example.empresa_telefonica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MENU_INICIO extends AppCompatActivity {
    private Button boton_ver_tarifas;
    private String idEmpresa = null;
    private TextView cabecera;
    private EditText editText_id_empresa;
    private EditText editText_correo_electronico;
    private String error_campo_en_blanco = "Este campo no se puede dejar en blanco";
    private String tipo_correo_electronico = "correo_electronico";
    private String tipo_idEmpresa = "idEmpresa";
    private String correo_electronico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText_id_empresa = findViewById(R.id.editext_id_empresa);
        editText_correo_electronico = findViewById(R.id.editext_correo_electronico);
        boton_ver_tarifas = (Button) findViewById(R.id.button_menu_inicio_ver_tarifas);
        cabecera = findViewById(R.id.menu_inicio_cabecera);

        boton_ver_tarifas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //COMPROBAMOS QUE EL ID DE LA EMPRESA NO SE HA DEJADO EN BLANCO
                if (editText_id_empresa.getText().toString().trim().equalsIgnoreCase(""))
                    editText_id_empresa.setError(error_campo_en_blanco);
                else
                open_ver_tarifas();
            }
        });
    }
    public void open_ver_tarifas(){
        Intent ver_tarifas = new Intent(this, VER_TARIFAS.class);
        idEmpresa = editText_id_empresa.getText().toString(); //Guardamos el texto introducido por la empresa
        correo_electronico = editText_correo_electronico.getText().toString();

        ver_tarifas.putExtra(tipo_idEmpresa, idEmpresa);
        ver_tarifas.putExtra(tipo_correo_electronico, correo_electronico);
        startActivity(ver_tarifas);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
