package br.com.homine.muninn.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homine.muninn.model.ControleCliente;
import br.com.homine.muninn.repository.ControleClienteRepository;
import br.com.homine.muninn.service.ControleClienteService;

@Service
public class ControleClienteServiceImpl implements ControleClienteService {
	@Autowired
	private ControleClienteRepository clienteControleClienteRepository; 
	
	@Override
	public List<ControleCliente> findAll() {
		return IterableUtils.toList(clienteControleClienteRepository.findAll());
	}

	@Override
	public ControleCliente save(ControleCliente controleCliente) {
		controleCliente.setDataCriacao(new Date());
		controleCliente.setSituacao("Inicial");
		return clienteControleClienteRepository.save(controleCliente);
	}

}
