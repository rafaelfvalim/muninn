package br.com.homine.muninn.form;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.homine.muninn.model.Cliente;
import br.com.homine.muninn.model.Consultor;
import br.com.homine.muninn.model.ControleCliente;
import br.com.homine.muninn.service.ClienteService;
import br.com.homine.muninn.service.ConsultorService;
import lombok.Data;

@Data
public class ControleClienteForm {

	public Long clienteId;
	public String numeroChamado;
	public String motivo;
	public String usuarioAtuante;
	public String status;
	public String situacao;
	public String core;
	public Long consultorId;
	public String crAplicada;

	public ControleCliente convert(ConsultorService consultorService, ClienteService clienteService) {
		return new ControleCliente(clienteService.findById(clienteId), this.numeroChamado, this.motivo,
				this.usuarioAtuante, new Date(), "Inicial", this.situacao, this.core,
				consultorService.findById(consultorId), this.crAplicada);
	}
	
	

}
	