package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.dominio.ConfiguracionEnvio;

public interface IEnvioCorreoService {
    void verificarDatosYProgramarEnvio();
    void programarEnvioDinamico(long intervalo);
    void enviarRecordatorios();
    void enviarCorreo(String correo, Boleto boleto);
    void guardarConfiguracionEnvio(ConfiguracionEnvio configuracionEnvio);

}
