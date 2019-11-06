package com.example.app.apresentacao;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.negocio.ClienteNegocio;
import com.example.app.negocio.dominio.ClienteDTO;
import com.example.app.negocio.excecao.ObjetoJaExisteException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClienteController {
	
	private final ClienteNegocio clienteNegocio;
	
	
	@GetMapping("/clientes")
	public ResponseEntity<Set<ClienteDTO>> listar() {
		
		var clientes = clienteNegocio.listar();
		
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	
	@PostMapping("/clientes/criar")
	public BodyBuilder criar(ClienteDTO clienteDTO) {
		
		try {
			clienteNegocio.incluir(clienteDTO);
		} catch (ObjetoJaExisteException e) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, e);
            
            return ResponseEntity.status(HttpStatus.SEE_OTHER);
		}
		
		 return ResponseEntity.status(HttpStatus.CREATED); 
		
	}

}
