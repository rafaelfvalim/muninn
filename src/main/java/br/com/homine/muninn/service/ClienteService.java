package br.com.homine.muninn.service;

import java.util.List;

import br.com.homine.muninn.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> findAll();
	public Cliente findById(Long id);
}
