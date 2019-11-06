package com.example.app.negocio;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.app.negocio.dominio.ClienteDTO;
import com.example.app.negocio.dominio.PaisDTO;
import com.example.app.negocio.excecao.ObjetoJaExisteException;
import com.example.app.persistencia.ClienteDAO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClienteNegocio implements INegocio<ClienteDTO>{

    private final ClienteDAO clienteDAO;
    
    @Override
    public void incluir(ClienteDTO cliente) throws ObjetoJaExisteException {
    	
    	if(clienteDAO.findById(Long.valueOf(cliente.getId())).isPresent())
            throw new ObjetoJaExisteException();
    	
    	clienteDAO.save(ClienteDTO.entityFromDTO(cliente));
    }

    @Override
    public Set<ClienteDTO> listar() {
        return ClienteDTO.DTOsFromEntities(clienteDAO.findAll());
    }
}
