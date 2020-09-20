package br.com.homine.muninn.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class ControleCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@OneToOne
	public Cliente cliente;
	public String numeroChamado;
	public String motivo;
	public String usuarioAtuante;
	public Date dataCriacao;
	public String status;
	public String situacao;
	public String core;
	@OneToOne
	public Consultor consultor;
	public String crAplicada;

	public ControleCliente(Cliente cliente, String numeroChamado, String motivo, String usuarioAtuante,
			Date dataCriacao, String status, String situacao, String core, Consultor consultor, String crAplicada) {
		this.cliente = cliente;
		this.numeroChamado = numeroChamado;
		this.motivo = motivo;
		this.usuarioAtuante = usuarioAtuante;
		this.dataCriacao = dataCriacao;
		this.status = status;
		this.situacao = situacao;
		this.core = core;
		this.consultor = consultor;
		this.crAplicada = crAplicada;
	}

	public ControleCliente() {
	}

}
