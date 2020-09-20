package br.com.homine.muninn.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homine.muninn.model.Consultor;
import br.com.homine.muninn.repository.ConsultorRepository;
import br.com.homine.muninn.service.ConsultorService;

@Service
public class ConsultorServiceImpl implements ConsultorService {
	@Autowired
	private ConsultorRepository consultorRepository;
	
	@Override
	public List<Consultor> findAll() {
		return IterableUtils.toList(consultorRepository.findAll());
	}

	@Override
	public Consultor findById(Long id) {
		return consultorRepository.findById(id).orElse(null);
	}

}
