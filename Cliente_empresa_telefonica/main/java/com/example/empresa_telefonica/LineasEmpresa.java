package com.example.empresa_telefonica;

import java.util.List;

import static java.lang.Double.NaN;

public class LineasEmpresa {
    public List<TotalPorLinea> lista;

    public List<TotalPorLinea> getLista() {
        return lista;
    }

    public String recorrerLista(List<TotalPorLinea> lista){
        String mensaje = "";
        int i = 1;
        String NaN = "";
        double consumo_previsto_mes_proximo;
        while(!lista.isEmpty()) { //RECORRE LA LISTA
            NaN = String.valueOf(lista.get(0).consumoPrevistoMesProximo);
            if(NaN.equals("NaN")){
                consumo_previsto_mes_proximo = 0.0;
            }else{
                consumo_previsto_mes_proximo = lista.get(0).consumoPrevistoMesProximo;
            }

            mensaje += " TARIFA " + i + '\n' +
                    "NUMERO LINEA = " + lista.get(0).numeroDeLinea + '\n' +
                    "CONSUMO PREVISTO MES PROXIMO= " + consumo_previsto_mes_proximo + '\n' +
                    "TARIFA RECOMENDADA= " + lista.get(0).tarifaRecomendada + '\n' +
                    "PRECIO TARIFA= " + lista.get(0).precioTarifa + '\n' +
                    "PRECIO TARIFA MES ANTERIOR= " + lista.get(0).precioTarifaMesAnterior + " \n \n \n";
            i++;
            lista = lista.subList(1,lista.size());
        }
        return mensaje;
    }
}
