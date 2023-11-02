package com.example.empresa_telefonica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CALCULAR_AHORRO extends AppCompatActivity {
    private Button boton_si;
    private Button boton_enviar_correo;
    private TextView texto_introducir_correo;
    private TextView cabecera;
    private TextView texto_diferencia;
    private EditText correo_introducido_usuario;
    private String correo_electronico = null;
    private String correo = "soportetecnicoteleahorro@gmail.com";
    private String contrasenia = "Unizar1234";
    private String mensaje_acierto = "El correo ha sido enviado correctamente.";
    private String mensaje_error = "Ha habído un problema al enviar el correo.";
    private String gasto_actual;
    private String gasto_previsto;
    private String texto_gasto_actual = "El gasto actual es de ";
    private String texto_gasto_previsto = "El gasto previsto asciende a ";
    private TextView[] arrayTextView;
    private String idEmpresa;
    private String cabeceraEmail = "GASTO PREVISTO PARA LA EMPRESA ";
    private String EURO = "€";
    private int numEntero1;
    private int numEntero2;
    private int diferencia;
    private String CABECERA_MEJORA_SERVICIOS = "HEMOS VISTO QUE NECESITAS MEJORAR TUS SERVICIOS";
    private String CABECERA_MEJORA_PRECIO ="HEMOS VISTO QUE PUEDES AHORRAR";
    private String MEJORA_PRESTACIONES = "LAS MEJORAS HAN AUMENTADO ELEVANDO EL PRECIO A ";
    private String MEJORA_AHORRO = "HEMOS VISTO QUE ELIGIENDO ESTAS NUEVAS TARIFAS PUEDES AHORRAR ";
    private String caso_ahorro;
    private String caso_mejora_prestaciones;
    private String error_campo_blanco = "Este campo no se puede dejar en blanco";
    private String error_envio_correo = "Could not send email";
    private String mensaje_gasto_previsto = "mensaje_gasto_previsto";
    private String mensaje_gasto_actual = "mensaje_gasto_actual";
    private String variable_tipo_string = "variable_string";
    private String MailApp = "MailApp";
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_calcular__ahorro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        idEmpresa = getIntent().getStringExtra(variable_tipo_string); //Obtenemos el ide de la empresa desde la actividad anterior

        cabecera = findViewById(R.id.calcular_ahorro_cabecera);
        texto_introducir_correo = findViewById(R.id.pregunta_introdrucir_correo);
        correo_introducido_usuario = findViewById(R.id.editText_introducir_correo);
        boton_si = (Button) findViewById(R.id.boton_si_correo);
        texto_diferencia = findViewById(R.id.mejora_ahorro_prestaciones);
        boton_enviar_correo = (Button) findViewById(R.id.boton_enviar_correo);


        gasto_actual = getIntent().getStringExtra(mensaje_gasto_actual);
        gasto_previsto = getIntent().getStringExtra(mensaje_gasto_previsto);

        arrayTextView = new TextView[2];
        arrayTextView[0] = findViewById(R.id.gasto_actual);
        arrayTextView[1] = findViewById(R.id.gasto_previsto);

        numEntero1 = Integer.parseInt(gasto_actual);
        numEntero2 = Integer.parseInt(gasto_previsto);
        diferencia = (numEntero1 - numEntero2);

        //PONEMOS LA CABECERA EN BASE AL AHORRO O MEJORA DEL SERVICIO
        if (diferencia > 0){ //CASO AHORRO
            cabecera.setText(CABECERA_MEJORA_PRECIO);
            texto_diferencia.setText(MEJORA_AHORRO + diferencia + EURO);
        }else{
            diferencia = (numEntero2 - numEntero1);
            cabecera.setText(CABECERA_MEJORA_SERVICIOS);
            texto_diferencia.setText(MEJORA_PRESTACIONES + diferencia + EURO);
        }

        arrayTextView[0].setText(texto_gasto_actual + gasto_actual + EURO );
        arrayTextView[1].setText(texto_gasto_previsto + gasto_previsto + EURO);


        boton_si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HACEMOS VISIBLE LOS OBJETOS OCULTOS
                texto_introducir_correo.setVisibility(View.VISIBLE);
                boton_enviar_correo.setVisibility(View.VISIBLE);
                correo_introducido_usuario.setVisibility(View.VISIBLE);
            }
        });

        boton_enviar_correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //COMPROBAMOS QUE SE HA INTRODUCIDO UN CORREO ELECTRONICO
                if (correo_introducido_usuario.getText().toString().trim().equalsIgnoreCase("")) {
                    correo_introducido_usuario.setError(error_campo_blanco);
                }
                else {
                    correo_electronico = correo_introducido_usuario.getText().toString(); //Guardamos el correo electronico introducido por el usuario
                    Mail m = new Mail(correo, contrasenia);

                    String[] toArr = {correo_electronico};
                    m.set_to(toArr);
                    m.set_from(correo); //FROM
                    m.set_subject(cabeceraEmail + idEmpresa); //CABECERA CORREO
                    m.setBody(generarCuerpoEmail()); //CUERPO EMAIL

                    try {
                        //  m.addAttachment("/sdcard/filelocation"); SIRVE PARA MANDAR ARCHIVOS

                        if (m.send()) {
                            Toast.makeText(CALCULAR_AHORRO.this,mensaje_acierto , Toast.LENGTH_LONG).show();
                            openActivityDespedida();
                        } else {
                            Toast.makeText(CALCULAR_AHORRO.this,mensaje_error , Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                        Log.e(MailApp,error_envio_correo , e);
                    }
                }
            }
        });
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

    private String generarCuerpoEmail(){

        if(diferencia > 0){ //Caso ahorro
            caso_ahorro = "El gasto actual para la empresa con ID: " + idEmpresa + " es de: " + gasto_actual + EURO +
                    " y el gasto previsto es de: " + gasto_previsto + EURO + " por lo que ha habido un ahorro de: " + diferencia + EURO;;
            return caso_ahorro;
        }else{
            diferencia = (numEntero2 - numEntero1);
            caso_mejora_prestaciones  = "El gasto actual para la empresa con ID: " + idEmpresa + " es de: " + gasto_actual + EURO +
                    " y el gasto previsto es de: " + gasto_previsto + EURO + " por lo que ha habido una mejora del servicio por un valor de: " + diferencia + EURO;
            return caso_mejora_prestaciones;
        }

    }

    private void openActivityDespedida(){
        Intent despedida = new Intent(this, DESPEDIDA.class);
        startActivity(despedida);

    }
}



