package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import java.util.List;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;

public interface IBoletoService {
     void generarBoletos(Long rangoMaximo, Long rangoMinimo, float precio, Integer idSorteo);

    Boleto guardarBoleto(Boleto boleto);

    Boleto cambiarEstado(Integer id, Boleto boleto);

    void eliminarBoleto(Integer id);

    Boleto obtenerBoletoPorId(Integer id);

    List<Boleto> obtenerBoletos();

    List<Boleto> obtenerBoletosPorIdSorteo(Integer idSorteo);
}
