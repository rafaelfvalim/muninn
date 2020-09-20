package br.com.homine.muninn.service;

import java.util.List;

import br.com.homine.muninn.model.Consultor;

public interface ConsultorService {
	public List<Consultor> findAll();
	public Consultor findById(Long id);
}
