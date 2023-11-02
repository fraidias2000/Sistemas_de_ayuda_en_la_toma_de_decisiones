using Microsoft.Owin;
using Owin;

[assembly: OwinStartup(typeof(Servidor.Startup))]

namespace Servidor
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureMobileApp(app);
        }
    }
}