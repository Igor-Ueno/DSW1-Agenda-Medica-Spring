package br.ufscar.dc.dsw.controller;
import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.Time;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IPacienteService;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private Usuario usuario;
    private Paciente paciente;
	
	@Autowired
	private IConsultaService consultaService;

	@Autowired
	private IPacienteService pacienteService;

	@Autowired
	private IMedicoService medicoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String CPF = consultaService.buscaCFPPorEmail(email);

		if (CPF == null) {
			String CRM = consultaService.buscaCRMPorEmail(email);

			model.addAttribute("consultas", consultaService.buscarPorCRM(CRM));

			System.out.println("------------> " + CRM);
			System.out.println(consultaService.buscarPorCRM(CRM));
			
			return "consulta/lista";
		}
		else {
			model.addAttribute("consultas", consultaService.buscarPorCPF(CPF));

			System.out.println("------------> " + CPF);
			System.out.println(consultaService.buscarPorCPF(CPF));
			
			// model.addAttribute("consultas",consultaService.buscarPorCPF(this.paciente.getCPF()));

			// this.paciente.setEmail(authentication.getName());
			// this.paciente.setCPF(consultaService.buscaCFPPorEmail(this.paciente.getEmail()));		
			// model.addAttribute("consultas",consultaService.buscarPorCPF(this.paciente.getCPF()));
			// System.out.println(CPF);
			return "consulta/lista";
		}
	}

	@PostMapping("/listarPorEspecialidade")
    public String listarMedicosPorEspecialidade(@RequestParam(name = "especialidade", required = false) String especialidadeSelecionada, ModelMap model) {
        List<Medico> medicosFiltrados;

        if (especialidadeSelecionada != null && !especialidadeSelecionada.isEmpty()) {
            medicosFiltrados = medicoService.buscarTodosPorEspecialidade(especialidadeSelecionada);
        } else {
            medicosFiltrados = medicoService.buscarTodos();
        }

		model.addAttribute("especialidades", medicoService.buscarTodosEspecialidade());
        model.addAttribute("medicos", medicosFiltrados);
        return "consulta/listaMedicos";
    }

	
	@GetMapping("/cadastrar")
	public String listarMedicos(Consulta consulta, ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String CPF = consultaService.buscaCFPPorEmail(email);
		model.addAttribute("consultas",consultaService.buscarPorCPF(CPF));

		// this.paciente.setEmail(authentication.getName());
		// this.paciente.setCPF(consultaService.buscaCFPPorEmail(this.paciente.getEmail()));
		// model.addAttribute("consultas",consultaService.buscarPorCPF(this.paciente.getCPF()));

		model.addAttribute("especialidades", medicoService.buscarTodosEspecialidade());
		model.addAttribute("medicos", medicoService.buscarTodos());
		return "consulta/listaMedicos";
	}

	@PostMapping("/listarHorarios")
	// public String listarHorarios(@PathVariable("id_medico") String id_medico, @PathVariable("data_consulta") String stringData_consulta,
	// 	Consulta consulta, ModelMap model) {
	public String listarHorarios(@RequestParam(name="id_medico") String id_medico, @RequestParam(name="data_consulta") String stringData_consulta,
		Consulta consulta, ModelMap model) {
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(stringData_consulta);
		System.out.println(id_medico);

		List<Time> intervalos = new ArrayList<>();
		Time inicioExpediente = Time.valueOf("08:00:00");
		Time fimExpediente = Time.valueOf("18:00:00");

		intervalos.add(inicioExpediente);

		Calendar cal = Calendar.getInstance();
		cal.setTime(inicioExpediente);
		while (cal.getTime().before(fimExpediente)) {
			cal.add(Calendar.MINUTE, 30);
			intervalos.add(new java.sql.Time(cal.getTimeInMillis()));
		}

		List<String> stringIntervalos = new ArrayList<String> ();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		for (java.sql.Time time : intervalos) {
			// System.out.println(sdf.format(time));
			stringIntervalos.add(sdf.format(time));
		}

		// Set<String> horariosTotais = new HashSet<String> (Arrays.asList(stringIntervalos));
		// Set<String> horariosTotais = new HashSet<String>();
		// Collections.addAll(horariosTotais, stringIntervalos);

		List<String> horariosFiltrados = new ArrayList<>();

		if (stringData_consulta != null && !stringData_consulta.isEmpty()) {
			Date data = Date.valueOf(stringData_consulta);
            List<Time> horarios = consultaService.buscaPorData(data);

			List<String> Stringhorarios = new ArrayList<>();
			for(Time horario : horarios) {
				Stringhorarios.add(sdf.format(horario));
			}
			horariosFiltrados = stringIntervalos.stream()
				.filter(elemento -> !Stringhorarios.contains(elemento))
                .collect(Collectors.toList());

        } else {
            // horariosFiltrados = stringIntervalos;
            horariosFiltrados = null;
        }

		System.out.println(stringData_consulta);

		Date dataVerificacao = Date.valueOf(stringData_consulta);

		long millisAtual = System.currentTimeMillis();
        Date dataAtual = new Date(millisAtual);

		System.out.println(dataVerificacao);
		System.out.println(dataAtual);

		System.out.println("--------ascasssssssssssssssssssssssssssssssssss");
		System.out.println(String.valueOf(dataVerificacao));
		System.out.println(String.valueOf(dataAtual));

		if (String.valueOf(dataVerificacao) == String.valueOf(dataAtual)) {
		// if (dataVerificacao.equals(dataAtual)) {
			// LocalTime horarioAtual = LocalTime.now();

			System.out.println("--------ascasssssssssssssssssssssssssssssssssss");

			List<Time> horariosFiltradosTime = new ArrayList<>();

			for (String hora : horariosFiltrados) {
				// System.out.println(sdf.format(time));
				horariosFiltradosTime.add(Time.valueOf(hora));
			}

			Calendar calend = Calendar.getInstance();
        	Time horarioAtual = new Time(calend.getTime().getTime());

			List<Time> horariosFiltrados2 = new ArrayList<>();
			for (Time horario : horariosFiltradosTime) {
				if (horario.after(horarioAtual)) {
					horariosFiltrados2.add(horario);
				}
			}
			horariosFiltrados.clear();
			for(Time horario : horariosFiltrados2) {
				horariosFiltrados.add(sdf.format(horario));
			}
		}

		System.out.println(horariosFiltrados);

		// Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// String email = authentication.getName();
		// String CPF = consultaService.buscaCFPPorEmail(email);
		// model.addAttribute("consultas", consultaService.buscarPorCPF(CPF));
		model.addAttribute("data_consulta", stringData_consulta);
		model.addAttribute("horarios", horariosFiltrados);
		// model.addAttribute("medico", usuarioService.buscarPorId(Long.valueOf(id_medico)));
		model.addAttribute("id_medico", String.valueOf(id_medico));

		return "consulta/cadastro2";
	}

	@GetMapping("/cadastrar/{id}")
	public String cadastrar(@PathVariable("id") Long id, Consulta consulta, ModelMap model) {

		// List<Time> intervalos = new ArrayList<>();
		// These constructors are deprecated and are used only for example
		// Time startTime = new java.sql.Time(11, 0, 0);
		// Time endTime = new java.sql.Time(12, 0, 0);
		// Time inicioExpediente = Time.valueOf("08:00:00");
		// Time fimExpediente = Time.valueOf("18:00:00");

		// intervalos.add(inicioExpediente);

		// Calendar cal = Calendar.getInstance();
		// cal.setTime(inicioExpediente);
		// while (cal.getTime().before(fimExpediente)) {
		// 	cal.add(Calendar.MINUTE, 30);
		// 	intervalos.add(new java.sql.Time(cal.getTimeInMillis()));
		// }

		// List<String> stringIntervalos = new ArrayList<String> ();

		// SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		// for (java.sql.Time time : intervalos) {
		// 	// System.out.println(sdf.format(time));
		// 	stringIntervalos.add(sdf.format(time));
		// }

		// Set<String> horariosTotais = new HashSet<String> (Arrays.asList(stringIntervalos));
		// Set<String> horariosTotais = new HashSet<String>();
		// Collections.addAll(horariosTotais, stringIntervalos);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String CPF = consultaService.buscaCFPPorEmail(email);
		model.addAttribute("paciente",pacienteService.buscarPorCPF(CPF));
		model.addAttribute("medico", usuarioService.buscarPorId(id));
		model.addAttribute("id_medico", String.valueOf(id));

		System.out.println("------------> " + CPF);

		// DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
        // String stringDataAtual = dateFormat.format(LocalDateTime.now());
		// model.addAttribute("data_atual", stringDataAtual);
		return "consulta/cadastro2";
	}
	
	@PostMapping("/salvar")
	public String salvar(@RequestParam(name="id_medico") String id, @RequestParam(name="data_consulta") String data, @RequestParam(name="horario") String hora, Consulta consulta, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "consulta/cadastro2";
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String CPF = consultaService.buscaCFPPorEmail(email);

		Medico medico = medicoService.buscaPorId(id);
		String CRM = medico.getCRM();

		System.out.println(CPF);
		System.out.println(CRM);
		System.out.println(data);
		System.out.println(hora);

		consulta = new Consulta();
		consulta.setCPFpaciente(CPF);
		consulta.setCRMmedico(CRM);
		consulta.setStringData_consulta(data);
		consulta.setStringHora(hora);
		consultaService.salvar(consulta);
		
		attr.addFlashAttribute("sucess", "consulta.create.sucess");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		// return "redirect:/consulta/cadastro";
		return "redirect:/consultas/listar";
	}
	
	// @PostMapping("/salvar")
	// public String salvar(@Valid Consulta consulta, @RequestParam(name="CPF", required=true) String CPF, @RequestParam(name="CRM", required=true) String CRM, 
	// 					 BindingResult result, RedirectAttributes attr) {
		
	// 	System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	// 	System.out.println(CPF);
	// 	System.out.println(CRM);
	// 	consulta.setCPFpaciente(CPF);
	// 	consulta.setCRMmedico(CRM);
    //     consultaService.salvar(consulta);
		
	// 	attr.addFlashAttribute("sucess", "consulta.create.sucess");
	// 	return "redirect:/consultas/listar";
	// }
	
	@PostMapping("/editar")
	public String editar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {
		consultaService.salvar(consulta);
		attr.addFlashAttribute("sucess", "consulta.edit.sucess");
		return "redirect:/consultas/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("consulta", usuarioService.buscarPorId(id));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String CPF = consultaService.buscaCFPPorEmail(email);
		model.addAttribute("paciente",pacienteService.buscarPorCPF(CPF));
		model.addAttribute("medico", usuarioService.buscarPorId(id));
		model.addAttribute("id_medico", String.valueOf(id));

		System.out.println("------------> " + CPF);

		return "consulta/cadastro";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		consultaService.excluirPorId(id);
		model.addAttribute("sucess", "consulta.delete.sucess");
		return "redirect:/consultas/listar";
	}

}
