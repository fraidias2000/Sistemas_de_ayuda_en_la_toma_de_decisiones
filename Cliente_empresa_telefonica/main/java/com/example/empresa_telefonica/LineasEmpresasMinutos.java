package com.example.empresa_telefonica;

import java.util.List;

public class LineasEmpresasMinutos {

    public List<MinutosPorLinea> lista;

    public List<MinutosPorLinea> getLista() {
        return lista;
    }

    public String recorrerLista(List<MinutosPorLinea> lista){
        String mensaje = "";
        int i = 1;
        while(!lista.isEmpty()) { //RECORRE LA LISTA
            mensaje += "BONO " + i + '\n' +
                    "NUMERO LINEA = " + lista.get(0).numeroDeLinea + '\n' +
                    "MEDIA MINUTOS = " + lista.get(0).mediaMinutos + '\n' +
                    "CONTRATAR BONO = " + lista.get(0).contratarBono  + '\n' +
                    "PRECIO CON BONO (en euros) = " + lista.get(0).precioConBonoEnEuros + '\n' +
                    "PRECIO SIN BONO (en euros) = " + lista.get(0).precioSinBonoEnEuros + " \n \n \n";
            i++;
            lista = lista.subList(1,lista.size());
        }
        return mensaje;
    }
}
