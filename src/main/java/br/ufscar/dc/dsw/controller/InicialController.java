package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Usuario;
// import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/inicial")
public class InicialController {
	
	// @Autowired
	// private IUsuarioService service;
	
	// @Autowired
	// private BCryptPasswordEncoder encoder;
	
	@GetMapping("/medicos")
	public String listar() {
		return "inicial/medicos";
	}
	
	// @GetMapping("/listar")
	// public String listar(ModelMap model) {
	// 	model.addAttribute("usuarios",service.buscarTodos());
	// 	return "login/lista";
	// }
	
}
