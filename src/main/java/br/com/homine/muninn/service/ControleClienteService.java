package br.com.homine.muninn.service;

import java.util.List;

import br.com.homine.muninn.model.ControleCliente;

public interface ControleClienteService {
	public List<ControleCliente> findAll();
	public ControleCliente save(ControleCliente controleCliente);
}
