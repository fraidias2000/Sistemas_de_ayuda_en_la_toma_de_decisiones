using System.Collections.Generic;
using System.Web.Http;
using Microsoft.Azure.Mobile.Server.Config;
using Servidor.Models;
using Servidor.DataObjects;
using System.Linq;
using System;

namespace Servidor.Controllers
{
    // Use the MobileAppController attribute for each ApiController you want to use  
    // from your mobile clients 
    [MobileAppController]
    public class ValuesController : ApiController
    {
        // GET api/values
        public string Get()
        {
           
            return "ye locoo que funcionaaa GET";
        }

        // POST api/values
        public LineasEmpresa Post(Texto mensaje)
        {
            /* MobileServiceContext context = new MobileServiceContext();

             TOTAL_POR_LINEA tarifas = new TOTAL_POR_LINEA();

             List<TOTAL_POR_LINEA> totalPorLineas =
                 context.Database.SqlQuery<TOTAL_POR_LINEA>("SELECT *  FROM['TOTAL POR LINEA$'] WHERE LINEA = '660688009';").ToList();

             tarifas.setLista(totalPorLineas);

             //  return tarifas;*/
            


            TotalPorLinea linea1 = new TotalPorLinea();
            linea1.numeroDeLinea = mensaje.toString();
            linea1.consumoPrevistoMesProximo = 1111;
            linea1.tarifaRecomendada = "1";
            TotalPorLinea linea2 = new TotalPorLinea();
            linea2.numeroDeLinea = "222222222";
            linea2.consumoPrevistoMesProximo = 2222;
            linea2.tarifaRecomendada = "2";
            TotalPorLinea linea3 = new TotalPorLinea();
            linea3.numeroDeLinea = "333333333";
            linea3.consumoPrevistoMesProximo = 3333;
            linea3.tarifaRecomendada = "3";
            TotalPorLinea linea4 = new TotalPorLinea();
            linea4.numeroDeLinea = "444444444";
            linea4.consumoPrevistoMesProximo = 4444;
            linea4.tarifaRecomendada = "4";
            TotalPorLinea linea5 = new TotalPorLinea();
            linea5.numeroDeLinea = "555555555";
            linea5.consumoPrevistoMesProximo = 5555;
            linea5.tarifaRecomendada = "5";
            TotalPorLinea linea6 = new TotalPorLinea();
            linea6.numeroDeLinea = "666666666";
            linea6.consumoPrevistoMesProximo = 6666;
            linea6.tarifaRecomendada = "6";

            LineasEmpresa miEmpresa = new LineasEmpresa();

            miEmpresa.lista.Add(linea1);
            miEmpresa.lista.Add(linea2);
            miEmpresa.lista.Add(linea3);
            miEmpresa.lista.Add(linea4);
            miEmpresa.lista.Add(linea5);
            miEmpresa.lista.Add(linea6);

            return miEmpresa;
        }
    }
}
