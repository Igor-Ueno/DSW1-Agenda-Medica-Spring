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
@RequestMapping("/inicial")
public class InicialController {
	
	@Autowired
	private IMedicoService service;
	
	// @Autowired
	// private BCryptPasswordEncoder encoder;
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("medicos",service.buscarTodos());
	 	return "inicial/lista";
	}
	

	@PostMapping("/listar")
    public String preListar(@RequestParam(name = "especialidade", required = false) String especialidadeSelecionada, ModelMap model) {
        List<Medico> medicosFiltrados;

        if (especialidadeSelecionada != null && !especialidadeSelecionada.isEmpty()) {
            medicosFiltrados = service.buscarTodosPorEspecialidade(especialidadeSelecionada);
        } else {
            medicosFiltrados = service.buscarTodos();
        }

        model.addAttribute("medicos", medicosFiltrados);
        return "inicial/lista";
    }


	@ModelAttribute("especialidades")
	public List<String> listaEspecialidades() {
		return service.buscarTodosEspecialidade();
	}
}
