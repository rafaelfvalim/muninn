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

import br.com.homine.muninn.model.Funcao;
import br.com.homine.muninn.repository.FuncaoRepository;

@Controller
@RequestMapping("pages")
public class FuncaoController {

	@Autowired
	private FuncaoRepository funcaoRepository;

	@GetMapping("new-fucao")
	public String newFuncao(Funcao funcao) {
		return "/pages/add-funcao";
	}
	
	@GetMapping("list-fucao")
	public String listFuncao(Model model) {
		model.addAttribute("funcoes", funcaoRepository.findAll());
		return "/pages/funcao";
	}

	@PostMapping("add-fucao")
	public String addFuncao(@Valid Funcao funcao, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/pages/add-funcao";
		}

		funcaoRepository.save(funcao);
		return "redirect:list";
	}

	@GetMapping("edit-fucao/{id}")
	public String editFuncao(@PathVariable("id") long id, Model model) {
		Funcao funcao = funcaoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Função Id:" + id));
		model.addAttribute("funcao", funcao);
		return "/pages/update-funcoes";
	}

	@PostMapping("update-fucao/{id}")
	public String updateFuncao(@PathVariable("id") long id, @Valid Funcao funcao, BindingResult result, Model model) {
		if (result.hasErrors()) {
			funcao.setId(id);
			return "/pages/update-funcoes";
		}

		funcaoRepository.save(funcao);
		model.addAttribute("funcoes", funcaoRepository.findAll());
		return "funcao";
	}

	@GetMapping("delete-fucao/{id}")
	public String deleteFuncao(@PathVariable("id") long id, Model model) {
		Funcao funcao = funcaoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Controle Cliente Id:" + id));
		funcaoRepository.delete(funcao);
		model.addAttribute("funcao", funcaoRepository.findAll());
		return "/pages/funcao";
	}
}
