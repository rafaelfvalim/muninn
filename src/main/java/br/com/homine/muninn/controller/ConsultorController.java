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
import br.com.homine.muninn.model.Consultor;
import br.com.homine.muninn.repository.ConsultorRepository;

@Controller
@RequestMapping("pages")
public class ConsultorController {

	@Autowired
	private ConsultorRepository consultorRepository;
	
	@GetMapping("new-consultor")
	public String newConsultor(Cliente cliente) {
		return "/pages/add-consultor";
	}
	
	@GetMapping("list-consultor")
	public String listConsultor(Model model) {
		model.addAttribute("consultores", consultorRepository.findAll());
		return "/pages/consultor";
	}

	@PostMapping("add-consultor")
	public String addConsultor(@Valid Consultor consultor, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/pages/add-consultor";
		}

		consultorRepository.save(consultor);
		return "redirect:list";
	}

	@GetMapping("edit-consultor/{id}")
	public String editConsultor(@PathVariable("id") long id, Model model) {
		Consultor consultor = consultorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consultor Id:" + id));
		model.addAttribute("consultor", consultor);
		return "/pages/update-consultor";
	}

	@PostMapping("update-consultor/{id}")
	public String updateConsultor(@PathVariable("id") long id, @Valid Consultor consultor, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			consultor.setId(id);
			return "/pages/update-consultor";
		}

		consultorRepository.save(consultor);
		model.addAttribute("Consultores", consultorRepository.findAll());
		return "/pages/consultor";
	}

	@GetMapping("delete-consultor/{id}")
	public String deleteConsutor(@PathVariable("id") long id, Model model) {
		Consultor Consultor = consultorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consultor Id:" + id));
		consultorRepository.delete(Consultor);
		model.addAttribute("Consultores", consultorRepository.findAll());
		return "/pages/consultor";
	}
}
