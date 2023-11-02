using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Servidor.DataObjects
{
    public class Texto
    {
        public String texto;

        public Texto(String texto)
        {
            this.texto = texto;
        }


        
    public String toString()
        {
            return "Texto{" +
                    "texto='" + texto + '\'' +
                    '}';
        }
    }
}