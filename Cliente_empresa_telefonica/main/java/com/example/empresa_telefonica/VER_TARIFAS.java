package com.example.empresa_telefonica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;
import java.util.List;

public class VER_TARIFAS extends AppCompatActivity {
    private String idEmpresa = null;
    private TextView mostrar_id_empresa, texto_scroll;
    private MobileServiceClient conexionServidorApi;
    private Button boton_calcular_ahorro,boton_calcular_bonos;
    private String URL_SERVIDOR = "http://teleahorroserver2.azurewebsites.net/";
    private String API = "OptimizadorTarifas";
    Texto mensaje_peticion;
    private String variable_tipo_string = "variable_string";
    private int gasto_actual = 0;
    private int gasto_previsto = 0;
    private String error_tarifas = "No hay ninguna tarifa para la empresa propuesta, pruebe con otra";
    private String mensaje_gasto_previsto = "mensaje_gasto_previsto";
    private String mensaje_gasto_actual = "mensaje_gasto_actual";
    private String tipo_correo_electronico = "correo_electronico";
    private String tipo_idEmpresa = "idEmpresa";
    private String correo_electronico;
    private String variable_tipo_gasto_actual ="mensaje_gasto_actual";
    private String getVariable_tipo_gasto_previsto = "mensaje_gasto_previsto";
    private String error = "Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__tarifas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mensaje_peticion = new Texto (getIntent().getStringExtra(tipo_idEmpresa)); //Obtenemos el id de la empresa para enviarlo al servidor
        correo_electronico = getIntent().getStringExtra(tipo_correo_electronico);

        idEmpresa = mensaje_peticion.toString();
        mostrar_id_empresa = findViewById(R.id.textView_ver_tarifas_cabecera_2);
        texto_scroll = findViewById(R.id.texto_scroll_tarifas);

        mostrar_id_empresa.setText("CUYA ID ES: " + mensaje_peticion);

        //CONEXIÓN CON LA API
        conexionServidorApi = null;
        try{
            conexionServidorApi = new MobileServiceClient(URL_SERVIDOR,this);
        }catch (MalformedURLException e){

        }
        llamadaApi();

        //DECLARACION BOTON SALIR APLICACION
        boton_calcular_ahorro = (Button) findViewById(R.id.button_calcular_ahorro);
        boton_calcular_ahorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalcularAhorro();
            }
        });

        boton_calcular_bonos = (Button) findViewById(R.id.boton_calcular_bonos);
        boton_calcular_bonos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVerBonos();
            }
        });
    }


    public void openCalcularAhorro(){
        Intent calcularAhorro = new Intent(this, CALCULAR_AHORRO.class);
        calcularAhorro.putExtra(variable_tipo_string, idEmpresa);
        calcularAhorro.putExtra(variable_tipo_gasto_actual, String.valueOf(gasto_actual));
        calcularAhorro.putExtra(getVariable_tipo_gasto_previsto, String.valueOf(gasto_previsto));
        startActivity(calcularAhorro);
    }

    public void openVerBonos(){
        Intent verBonos = new Intent(this, VER_BONOS.class);
        verBonos.putExtra(variable_tipo_string, idEmpresa);
        verBonos.putExtra(tipo_correo_electronico, correo_electronico);
        startActivity(verBonos);
    }

    private void llamadaApi(){;
        //Creamos peticion
        final ListenableFuture <LineasEmpresa> peticion =
                conexionServidorApi.invokeApi(API,mensaje_peticion , LineasEmpresa.class);

        //Lanzamos peticion
       /* Futures.addCallback(peticion, new FutureCallback<LineasEmpresa>() {
            @Override
            // Acciones a realizar si la llamada devuelve un error
            public void onFailure(Throwable exc) {
                texto_scroll.setText(error + exc);
            }
            @Override
            // Acciones a realizar si la llamada devuelve un ok
            public void onSuccess(LineasEmpresa resultadoCorrecto) {

                if(resultadoCorrecto.getLista().size() != 0){
                    texto_scroll.setText(resultadoCorrecto.recorrerLista(resultadoCorrecto.getLista()));
                    //calcular_tarifa(resultadoCorrecto.getLista()); //Calculamos precios tarifas
                }else{
                    texto_scroll.setText(error_tarifas);
                }
            }
        });*/
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

    private void calcular_tarifa(List<TotalPorLinea> lista){
        String NaN = "";
        while(!lista.isEmpty()) { //RECORRE LA LISTA
            //NaN = String.valueOf(lista.get(0).precioTarifaMesAnterior);
            if(NaN.equals("NaN")){
                gasto_actual += 0;
            }else{
             //   gasto_actual += lista.get(0).precioTarifaMesAnterior;
            }

            if(NaN.equals("NaN")){
                gasto_previsto += 0;
            }else{
              //  gasto_previsto += lista.get(0).precioTarifa;
            }
            lista = lista.subList(1,lista.size());
        }
    }
}
