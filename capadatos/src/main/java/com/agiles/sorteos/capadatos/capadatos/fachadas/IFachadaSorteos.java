package com.agiles.sorteos.capadatos.capadatos.fachadas;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.dominio.Administrador;
import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.dominio.Cliente;
import com.agiles.sorteos.capadatos.capadatos.dominio.ConfiguracionEnvio;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;

@Service
public interface IFachadaSorteos {

    Sorteo guardarSorteo(Sorteo sorteo);

    Sorteo actualizarSorteo(Integer id, Sorteo sorteo);

    void eliminarSorteo(Integer id);

    Sorteo obtenerSorteoPorId(Integer id);

    List<Sorteo> obtenerSorteos();

   
    List<Sorteo> findSorteosByAdministradorId( Integer idAdministrador);

    Boleto guardarBoleto(Boleto boleto);

    Boleto cambiarEstado(Integer id, Boleto boleto);

    void eliminarBoleto(Integer id);

    Boleto obtenerBoletoPorId(Integer id);

    List<Boleto> obtenerBoletos();

    List<Boleto> obtenerBoletosPorIdSorteo(Integer idSorteo);

    Administrador obteAdministradorPorId(Integer id);

    Boleto obtenerBoletoSorteo(Integer numBoleto, Integer idSorteo);

    Cliente agregarCliente(Cliente cliente);

    Boolean verificarCliente(String correo, String contrasenia);

    List<Boleto> obtenerBoletosCliente(Integer idCliente);

    Cliente clienteExiste(String correo);

    Administrador verificarAdmin(String correo, String contrasenia);

    ConfiguracionEnvio guardarConfiguracionEnvio(ConfiguracionEnvio configuracionEnvio);

    ConfiguracionEnvio obtenerConfiguracionEnvio();

    List<Boleto> obtenerBoletosApartados();


    void liberarBoletosVencidos(Date fechaLimite);
}
