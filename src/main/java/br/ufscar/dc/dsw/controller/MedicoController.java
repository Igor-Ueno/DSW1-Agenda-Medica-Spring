package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private IMedicoService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("medicos",service.buscarTodos());
	 	return "medico/lista";
	}

	
	@GetMapping("/cadastrar")
	public String cadastrar(Medico medico) {
		return "medico/cadastro";
	}
	
	
	@PostMapping("/salvar")
	public String salvar(@Valid Medico medico, BindingResult result, RedirectAttributes attr) {
		
        medico.setRole("ROLE_MED");
		service.salvar(medico);
		attr.addFlashAttribute("sucess", "medico.create.sucess");
		return "redirect:/medicos/listar";
	}
	
	@GetMapping("/editar/{CRM}")
	public String preEditar(@PathVariable("CRM") String CRM, ModelMap model) {
		model.addAttribute("medico", service.buscarPorCRM(CRM));
		return "medico/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Medico medico, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "medico/cadastro";
		}
		
		service.salvar(medico);
		attr.addFlashAttribute("sucess", "medico.edit.sucess");
		return "redirect:/medicos/listar";
	}
	
	@GetMapping("/excluir/{CRM}")
	public String excluir(@PathVariable("CRM") String CRM, ModelMap model) {
		service.excluirPorCRM(CRM);
		model.addAttribute("sucess", "medico.delete.sucess");
		return listar(model);
	}

}