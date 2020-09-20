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

import br.com.homine.muninn.model.Cliente;
import br.com.homine.muninn.repository.ClienteRepository;
import br.com.homine.muninn.service.ControleClienteService;

@Controller
@RequestMapping("pages")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("new-cliente")
	public String newCliente(Cliente cliente) {
		return "/pages/add-cliente";
	}
	
	@GetMapping("list-cliente")
	public String listCliente(Model model) {
		model.addAttribute("clientes", clienteRepository.findAll());
		return "/pages/cliente";
	}

	@PostMapping("add-cliente")
	public String addCliente(@Valid Cliente cliente, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/pages/add-cliente";
		}

		clienteRepository.save(cliente);
		return "redirect:list";
	}

	@GetMapping("edit-cliente/{id}")
	public String editCliente(@PathVariable("id") long id, Model model) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid cliente Id:" + id));
		model.addAttribute("cliente", cliente);
		return "/pages/update-cliente";
	}

	@PostMapping("update-cliente/{id}")
	public String updateCliente(@PathVariable("id") long id, @Valid Cliente cliente, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			cliente.setId(id);
			return "/pages/update-cliente";
		}

		clienteRepository.save(cliente);
		model.addAttribute("clientes", clienteRepository.findAll());
		return "/pages/cliente";
	}

	@GetMapping("delete-cliente/{id}")
	public String deleteCliente(@PathVariable("id") long id, Model model) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid cliente Id:" + id));
		clienteRepository.delete(cliente);
		model.addAttribute("clientes", clienteRepository.findAll());
		return "/pages/cliente";
	}
	
}
