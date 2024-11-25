package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import com.agiles.sorteos.capadatos.capadatos.fachadas.IFachadaSorteos;
import com.agiles.sorteos.capadatos.capadatos.utilis.BOLETOESTADO;
import com.agiles.sorteos.capanegocios.capasnegocios.Exception.NotFoundException;

@Service
public class BoletoService implements IBoletoService {

    @Autowired
    private IFachadaSorteos fachadaSorteos;

    @Autowired
    private IEnvioCorreoService envioCorreoService;

    @Override
    public void generarBoletos(Long rangoMaximo, Long rangoMinimo, float precio, Integer idSorteo) {
        Sorteo sorteo = fachadaSorteos.obtenerSorteoPorId(idSorteo);
        for (int i = 0; i < rangoMaximo; i++) {
            Boleto boleto = new Boleto();

            boleto.setNumeroBoleto((int) (rangoMinimo + i));
            boleto.setIdSorteo(sorteo);
            boleto.setEstado(BOLETOESTADO.LIBRE);

            
            boleto.setPrecio(precio);
            this.guardarBoleto(boleto);

        }
    }

    @Override
    public Boleto guardarBoleto(Boleto boleto) {
        return fachadaSorteos.guardarBoleto(boleto);
    }

    @Override
    public void eliminarBoleto(Integer id) {

        Boleto boletoElim = this.obtenerBoletoPorId(id);

        if (boletoElim == null) {
            throw new NotFoundException("boleto con ID " + id);
        }
        fachadaSorteos.eliminarBoleto(id);
    }

    @Override
    public Boleto obtenerBoletoPorId(Integer id) {
        Boleto boleto = fachadaSorteos.obtenerBoletoPorId(id);
        if (boleto == null) {
            throw new NotFoundException("boleto con ID " + id);
        }
        return boleto;
    }

    @Override
    public List<Boleto> obtenerBoletos() {

        List<Boleto> boletos = fachadaSorteos.obtenerBoletos();

        if (boletos.isEmpty()) {
            throw new NotFoundException("No hay boletos");
        }
        return boletos;
    }

    @Override
    public List<Boleto> obtenerBoletosPorIdSorteo(Integer idSorteo) {
        List<Boleto> boletos = fachadaSorteos.obtenerBoletosPorIdSorteo(idSorteo);
        if (boletos.isEmpty()) {
            throw new NotFoundException("No hay boletos para el sorteo con ID " + idSorteo);
        }
        return boletos;
    }

    @Override
    public Boleto cambiarEstadoLibre(Integer id) {
        Boleto boleto = fachadaSorteos.obtenerBoletoPorId(id);
        if (boleto == null) {
            throw new NotFoundException("boleto con ID " + id);
        } else {
            boleto.setEstado(BOLETOESTADO.LIBRE);
            boleto.setIdCliente(null);
            boleto.setFechaLimApart(null);
            return fachadaSorteos.guardarBoleto(boleto);
        }
    }

    @Override
    public Boleto cambiarEstadoApartado(Integer id, String correo) {
        Boleto boleto = fachadaSorteos.obtenerBoletoPorId(id);
        if (boleto == null) {
            throw new NotFoundException("boleto con ID " + id);
        } else {
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(new Date());
            fecha.add(Calendar.HOUR_OF_DAY, 48);
            boleto.setFechaLimApart(fecha.getTime());
            boleto.setEstado(BOLETOESTADO.APARTADO);
            boleto.setIdCliente(fachadaSorteos.clienteExiste(correo));
            envioCorreoService.enviarCorreoConfirmacionApartado(correo, boleto);
            return fachadaSorteos.guardarBoleto(boleto);
        }
    }

    @Override
    public Boleto cambiarEstadoVendido(Integer id) {
        Boleto boleto = fachadaSorteos.obtenerBoletoPorId(id);
        if (boleto == null) {
            throw new NotFoundException("boleto con ID " + id);
        } else {
            boleto.setEstado(BOLETOESTADO.VENDIDO);
            boleto.setFechaLimApart(null);
            return fachadaSorteos.guardarBoleto(boleto);
        }
    }

    @Override
    public Boleto obtenerBoletoPorNumeroSorteo(Integer numBoleto, Integer idSorteo) {
        Boleto boleto = fachadaSorteos.obtenerBoletoSorteo(numBoleto, idSorteo);
        if (boleto == null) {
            throw new NotFoundException("boleto con numero de boleto " + numBoleto);
        } else {
            boleto.setEstado(BOLETOESTADO.LIBRE);
            return boleto;
        }
    }

    @Override
    public List<Boleto> obtenerBoletosCliente(Integer idCliente) {
        List<Boleto> boleto = fachadaSorteos.obtenerBoletosCliente(idCliente);
        if (boleto.size() == 0) {
            throw new NotFoundException("El boleto de" + idCliente);
        } else {
            return boleto;
        }
    }

    //@Scheduled(fixedRate = 20000) // cada dos segundos 
    @Scheduled(cron = "0 0 0 * * *")
    public void liberarBoletosVencidos() {
        Date fechaLimite = new Date(); 
        fachadaSorteos.liberarBoletosVencidos(fechaLimite);
    }

}
