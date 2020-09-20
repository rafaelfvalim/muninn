package br.com.homine.muninn.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homine.muninn.model.Cliente;
import br.com.homine.muninn.repository.ClienteRepository;
import br.com.homine.muninn.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository; 
	
	@Override
	public List<Cliente> findAll() {
		return IterableUtils.toList(clienteRepository.findAll());
	}

	@Override
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

}
