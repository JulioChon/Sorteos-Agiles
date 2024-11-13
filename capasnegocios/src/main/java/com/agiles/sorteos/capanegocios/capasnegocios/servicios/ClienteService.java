package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.DTOs.User;
import com.agiles.sorteos.capadatos.capadatos.dominio.Administrador;
import com.agiles.sorteos.capadatos.capadatos.dominio.Cliente;
import com.agiles.sorteos.capadatos.capadatos.fachadas.IFachadaSorteos;
import com.agiles.sorteos.capanegocios.capasnegocios.Exception.NotFoundException;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IFachadaSorteos fachadaSorteos;

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        Cliente busCliente = fachadaSorteos.clienteExiste(cliente.getCorreo());
        if (busCliente!=null) {
            throw new NotFoundException("Ese correo ya existe");
        }
        return fachadaSorteos.agregarCliente(cliente);
    }

    @Override
    public User verificarCliente(String correo, String contrasenia) {
        User user = new User();
        Cliente existe = fachadaSorteos.clienteExiste(correo);
        if (existe!=null) {
            if(!fachadaSorteos.verificarCliente(correo, contrasenia)){
                throw new NotFoundException("El correo o contrasenia son incorrectos");
            }
           user.setId(existe.getId());
           user.setCorreo(existe.getCorreo());
           user.setNombre(existe.getNombre());
           user.setAdmin(false);
           return user;
        }
        Administrador admin = fachadaSorteos.verificarAdmin(correo, contrasenia);
        if (admin!=null) {
            user.setId(admin.getId());
            user.setCorreo(admin.getEmail());
            user.setNombre(admin.getNombre());
            user.setAdmin(true);
            return user;
         }
         throw new NotFoundException("El correo o contrasenia son incorrectos");
    }



}
