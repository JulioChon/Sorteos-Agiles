package com.agiles.sorteos.capadatos.capadatos.fachadas;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.DAOS.IAdminDAO;
import com.agiles.sorteos.capadatos.capadatos.DAOS.IBoletosDAO;
import com.agiles.sorteos.capadatos.capadatos.DAOS.IClienteDAO;
import com.agiles.sorteos.capadatos.capadatos.DAOS.IConfiguracionEnvioDAO;
import com.agiles.sorteos.capadatos.capadatos.DAOS.ISorteosDAO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Administrador;
import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.dominio.Cliente;
import com.agiles.sorteos.capadatos.capadatos.dominio.ConfiguracionEnvio;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import com.agiles.sorteos.capadatos.capadatos.utilis.BOLETOESTADO;

import org.springframework.transaction.annotation.Transactional;

@Service
public class FachadaSorteos implements IFachadaSorteos {

    private ISorteosDAO sorteosDAO;

    private IBoletosDAO boletosDAO;

    
    private IAdminDAO adminDAO;

    private IClienteDAO clienteDao;

    private PasswordEncoder passwordEncoder;

    private IConfiguracionEnvioDAO configuracionEnvioDAO;


    public FachadaSorteos(ISorteosDAO sorteosDAO, IBoletosDAO boletosDAO, IAdminDAO adminDAO, IClienteDAO clienteDao,
            PasswordEncoder passwordEncoder, IConfiguracionEnvioDAO configuracionEnvioDAO) {
        this.sorteosDAO = sorteosDAO;
        this.boletosDAO = boletosDAO;
        this.adminDAO = adminDAO;
        this.clienteDao = clienteDao;
        this.passwordEncoder = passwordEncoder;
        this.configuracionEnvioDAO = configuracionEnvioDAO;
    }

    @Override
    @Transactional
    public Sorteo guardarSorteo(Sorteo sorteo) {
        return sorteosDAO.save(sorteo);
    }

    @Override
    @Transactional
    public Sorteo actualizarSorteo(Integer id, Sorteo sorteo) {
        if(sorteosDAO.existsById(id)) {
            sorteo.setId(id);
            return sorteosDAO.save(sorteo);
        }
        return null;
        
    }

    @Override
    @Transactional
    public void eliminarSorteo(Integer id) {
        if(sorteosDAO.existsById(id)) {
            sorteosDAO.deleteById(id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Sorteo obtenerSorteoPorId(Integer id) {

        return sorteosDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sorteo> obtenerSorteos() {
        Iterable<Sorteo> iterable = sorteosDAO.findAll();
        List<Sorteo> lista = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Sorteo> findSorteosByAdministradorId(Integer idAdministrador) {
        return sorteosDAO.findSorteosByAdministradorId(idAdministrador);
    }
    

    @Override
    @Transactional
    public Boleto guardarBoleto(Boleto boleto) {
        return boletosDAO.save(boleto);
    }

    @Override
    @Transactional
    public Boleto cambiarEstado(Integer id, Boleto boleto) {
        if(boletosDAO.existsById(id)) {
            boleto.setNumeroBoleto(id);
            return boletosDAO.save(boleto);
        }
        return null;
    }

    @Override
    @Transactional
    public void eliminarBoleto(Integer id) {
        if(boletosDAO.existsById(id)) {
            boletosDAO.deleteById(id);
           
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boleto obtenerBoletoPorId(Integer id) {
        return boletosDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleto> obtenerBoletos() {
        Iterable<Boleto> iterable = boletosDAO.findAll();
        List<Boleto> lista = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleto> obtenerBoletosPorIdSorteo(Integer idSorteo) {
        return boletosDAO.obtenerBoletosPorIdSorteo(idSorteo);
    }

    @Override
    public Administrador obteAdministradorPorId(Integer id) {
        return adminDAO.findById(id).orElse(null);
    }

    @Override
    public Boleto obtenerBoletoSorteo(Integer numBoleto, Integer idSorteo) {
       return boletosDAO.obtenerBoletoSorteo(numBoleto, idSorteo);
    }

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        String contraseniaHasheada = passwordEncoder.encode(cliente.getContrasenia());
        cliente.setContrasenia(contraseniaHasheada);
        return clienteDao.save(cliente);
    }

    @Override
    public Boolean verificarCliente(String correo, String cotrasenia) {
        Cliente guardado = clienteDao.findByCorreo(correo);
        if(guardado!=null){
            return passwordEncoder.matches(cotrasenia, guardado.getContrasenia());

        }
        return false;
    }

    @Override
    public List<Boleto> obtenerBoletosCliente(Integer idCliente) {
       return boletosDAO.obtenerBoletosPorIdCliente(idCliente);
    }

    @Override
    public Cliente clienteExiste(String correo) {
       Cliente buscado = clienteDao.findByCorreo(correo);

       return buscado;
    }

    @Override
    public Administrador verificarAdmin(String correo, String contrasenia) {
        Administrador administrador = adminDAO.findByEmailAndContrasena(correo, contrasenia);
        
        return administrador;
    }

    @Override
    public ConfiguracionEnvio guardarConfiguracionEnvio(ConfiguracionEnvio configuracionEnvio) {
        configuracionEnvio.setId(1L);
        return configuracionEnvioDAO.save(configuracionEnvio);
    }

    @Override
    public ConfiguracionEnvio obtenerConfiguracionEnvio() {
        return configuracionEnvioDAO.findById(1L).orElse(null);
    }


    @Override
    public List<Boleto> obtenerBoletosApartados() {
        return boletosDAO.findByEstado(BOLETOESTADO.APARTADO);
    }

    @Override
    @Transactional
    public void liberarBoletosVencidos(Date fechaLimite) {
        boletosDAO.liberarBoletosVencidos(fechaLimite);
    }

}
