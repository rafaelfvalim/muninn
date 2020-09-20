package br.com.homine.muninn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.homine.muninn.form.ControleClienteForm;
import br.com.homine.muninn.model.ControleCliente;
import br.com.homine.muninn.repository.ControleClienteRepository;
import br.com.homine.muninn.service.ClienteService;
import br.com.homine.muninn.service.ConsultorService;
import br.com.homine.muninn.service.ControleClienteService;
import br.com.homine.muninn.service.FuncaoService;

@Controller
@RequestMapping("pages")
public class ControleClienteController {

	@Autowired
	private ControleClienteRepository controleClienteRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private FuncaoService funcaoService;
	@Autowired
	private ConsultorService consultorService;
	@Autowired
	private ControleClienteService controleClienteService;

	@GetMapping("new-controle-cliente")
	public String newControleCliente(ControleClienteForm controleClienteForm, Model model) {

		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("funcoes", funcaoService.findAll());
		model.addAttribute("consultores", consultorService.findAll());
		return "/pages/add-controle-cliente";
	}

	@GetMapping("controle-cliente")
	public String listControleCliente(Model model) {
		model.addAttribute("controleClienteList", controleClienteRepository.findAll());
		return "/pages/controle-cliente";
	}

	@PostMapping("add-controle-cliente")
	public String addControleCliente(@Valid ControleClienteForm controleClienteForm, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "/pages/add-controle-cliente";
		}
		ControleCliente controleClienteNew = controleClienteService.save(controleClienteForm.convert(consultorService,clienteService ));
		model.addAttribute("controle-cliente", controleClienteNew);
		return "redirect:controle-cliente";
	}

	@GetMapping("edit-controle-cliente/{id}")
	public String editConsultorCliente(@PathVariable("id") long id, Model model) {
		ControleCliente controleCliente = controleClienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consultor Id:" + id));
		model.addAttribute("controleCliente", controleCliente);
		return "/pages/update-contole-cliente";
	}

	@PostMapping("update-controle-cliente/{id}")
	public String updateControleCliente(@PathVariable("id") long id, @Valid ControleCliente controleCliente,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			controleCliente.setId(id);
			return "/pages/update-controle-cliente";
		}

		controleClienteRepository.save(controleCliente);
		model.addAttribute("controleClienteList", controleClienteRepository.findAll());
		return "/pages/controle-cliente";
	}

	@GetMapping("delete-controle-cliente/{id}")
	public String deleteControleCliente(@PathVariable("id") long id, Model model) {
		ControleCliente controleCliente = controleClienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Controle Cliente Id:" + id));
		controleClienteRepository.delete(controleCliente);
		model.addAttribute("controleCliente", controleClienteRepository.findAll());
		return "/pages/controle-cliente";
	}
}
