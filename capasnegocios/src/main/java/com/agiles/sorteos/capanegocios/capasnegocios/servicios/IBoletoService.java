package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import java.util.List;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;

public interface IBoletoService {
    void generarBoletos(Long rangoMaximo, Long rangoMinimo, float precio, Integer idSorteo);

    Boleto guardarBoleto(Boleto boleto);

    Boleto cambiarEstadoLibre(Integer id);

    Boleto cambiarEstadoApartado(Integer id, String correo);

    Boleto cambiarEstadoVendido(Integer id,String correo);

    void eliminarBoleto(Integer id);

    Boleto obtenerBoletoPorId(Integer id);

    Boleto obtenerBoletoPorNumeroSorteo(Integer numBoleto, Integer idSorteo);

    List<Boleto> obtenerBoletos();

    List<Boleto> obtenerBoletosPorIdSorteo(Integer idSorteo);

    List<Boleto> obtenerBoletosCliente(Integer idCliente);

    void liberarBoletosVencidos();

    List<Boleto> obtenerBoletosApartadosPorSorteo(Integer idSorteo);

}
