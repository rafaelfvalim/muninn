package br.com.homine.muninn.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.homine.muninn.model.Funcao;
import br.com.homine.muninn.repository.FuncaoRepository;
import br.com.homine.muninn.service.FuncaoService;

@Service
public class FuncaoServiceImpl implements FuncaoService {
	@Autowired
	private FuncaoRepository funcaoRepository;
	
	@Override
	public List<Funcao> findAll() {
		return IterableUtils.toList(funcaoRepository.findAll());
	}

}
