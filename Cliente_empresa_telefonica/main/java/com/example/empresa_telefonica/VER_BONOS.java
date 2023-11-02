package com.example.empresa_telefonica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;




import java.net.MalformedURLException;

public class VER_BONOS extends AppCompatActivity {
    private TextView texto_scroll;
    private String URL_SERVIDOR = "http://teleahorroserver2.azurewebsites.net/";
    private String API = "CalcularBonoMinutos";
    Texto mensaje_peticion;
    private String error_bonos = "No hay ningun bono para la empresa propuesta, pruebe con otra";
    private String correo_electronico = null;
    private String correo = "soportetecnicoteleahorro@gmail.com";
    private String contrasenia = "Unizar1234";
    private String correo_introducido_usuario;
    private Button exportar_bonos;
    private String mensaje_bonos = error_bonos;
    private String cuerpo_correo;
    private String cabeceraEmail = "Estos son los bonos para la empresa ";
    private MobileServiceClient conexionServidorApi;
    private String mensaje_acierto = "El correo ha sido enviado correctamente.";
    private String mensaje_error = "Ha habído un problema al enviar el correo.";
    private String error_envio_correo = "Could not send email";
    private String tipo_correo_electronico = "correo_electronico";
    private String variable_tipo_string = "variable_string";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__bonos);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        texto_scroll = findViewById(R.id.texto_scroll_bonos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mensaje_peticion = new Texto (getIntent().getStringExtra(variable_tipo_string));

        //CONEXIÓN CON LA API
        conexionServidorApi = null;
        try{
            conexionServidorApi = new MobileServiceClient(URL_SERVIDOR,this);
        }catch (MalformedURLException e){

        }
        llamadaApi();

        //ENVIAR CORREO ELECTRONICO
        exportar_bonos = (Button) findViewById(R.id.boton_exportar_bonos);
        exportar_bonos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //COMPROBAMOS QUE SE HA INTRODUCIDO UN CORREO ELECTRONICO
                correo_electronico = getIntent().getStringExtra(tipo_correo_electronico); //Guardamos el correo electronico introducido por el usuario
                Mail m = new Mail(correo, contrasenia);

                String[] toArr = {correo_electronico};
                m.set_to(toArr);
                m.set_from(correo); //FROM
                m.set_subject(cabeceraEmail + mensaje_peticion); //CABECERA CORREO
                m.setBody(mensaje_bonos); //CUERPO EMAIL

                try {
                    //  m.addAttachment("/sdcard/filelocation"); SIRVE PARA MANDAR ARCHIVOS

                    if (m.send()) {
                        Toast.makeText(VER_BONOS.this,mensaje_acierto , Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(VER_BONOS.this,mensaje_error , Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.e("MailApp",error_envio_correo , e);
                }

            }
        });
    }

    private void llamadaApi(){
        //Creamos peticion
        final ListenableFuture<LineasEmpresasMinutos> peticion =
                conexionServidorApi.invokeApi(API,mensaje_peticion , LineasEmpresasMinutos.class);

        //Lanzamos peticion
        Futures.addCallback(peticion, new FutureCallback<LineasEmpresasMinutos>() {
            @Override
            // Acciones a realizar si la llamada devuelve un error
            public void onFailure(Throwable exc) {
                texto_scroll.setText(error_bonos);
            }
            @Override
            // Acciones a realizar si la llamada devuelve un ok
            public void onSuccess(LineasEmpresasMinutos resultadoCorrecto) {
                if(resultadoCorrecto.getLista().size() != 0) {
                    mensaje_bonos = resultadoCorrecto.recorrerLista(resultadoCorrecto.getLista());
                    texto_scroll.setText(mensaje_bonos);
                }else{
                      texto_scroll.setText(error_bonos);
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
}
