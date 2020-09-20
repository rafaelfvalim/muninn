package br.com.homine.muninn.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.homine.muninn.model.Cliente;

public interface ClienteRepository  extends  CrudRepository<Cliente, Long> {

}
