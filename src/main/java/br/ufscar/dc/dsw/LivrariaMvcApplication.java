package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.ufscar.dc.dsw.conversor.StringToDateConverter;
import br.ufscar.dc.dsw.dao.IMedicoDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.dao.IPacienteDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class LivrariaMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaMvcApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner demo(IMedicoDAO medicoDAO, IUsuarioDAO usuarioDAO, IPacienteDAO pacienteDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
			Medico m1 = new Medico();
			m1.setEmail("alexandre.santos.gualberto@gmail.com");
			m1.setPassword(encoder.encode("123"));
			m1.setRole("ROLE_MEDICO");
			m1.setName("Alexandre");
			m1.setCRM("SP123456");
			m1.setEspecialidade("Cardiologista");
			m1.setEnabled(true);
			medicoDAO.save(m1);

			Medico m2 = new Medico();
			m2.setEmail("eduardo.santos.gualberto@gmail.com");
			m2.setPassword(encoder.encode("123"));
			m2.setRole("ROLE_MEDICO");
			m2.setName("Eduardo");
			m2.setCRM("SP123457");
			m2.setEspecialidade("Endocrinologista");
			m2.setEnabled(true);
			medicoDAO.save(m2);

			Usuario u1 = new Usuario();
			u1.setEmail("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);

			Usuario u2 = new Usuario();
			u2.setEmail("admin2");
			u2.setPassword(encoder.encode("admin"));
			u2.setName("Administrador");
			u2.setRole("ROLE_ADMIN");
			u2.setEnabled(true);
			usuarioDAO.save(u2);

			Paciente p1 = new Paciente();
			p1.setEmail("paciente1@example.com");
			p1.setPassword(encoder.encode("123"));
			p1.setRole("ROLE_PACIENTE");
			p1.setName("Paciente 1");
			p1.setCPF("12345678901");
			p1.setTelefone("99999999999");
			p1.setSexo("M");
			// Defina a data de nascimento utilizando o método setStringData_Nascimento
			System.out.println("pre data");
			p1.setStringData_Nascimento("2000-01-01");
			System.out.println("pos data");
			p1.setEnabled(true);
			System.out.println("pre save");
			pacienteDAO.save(p1);
			System.out.println("pos save");
		};
	}
	/* 
	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IEditoraDAO editoraDAO, ILivroDAO livroDAO) {
		return (args) -> {
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setCPF("012.345.678-90");
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
			
			Usuario u2 = new Usuario();
			u2.setUsername("beltrano");
			u2.setPassword(encoder.encode("123"));
			u2.setCPF("985.849.614-10");
			u2.setName("Beltrano Andrade");
			u2.setRole("ROLE_USER");
			u2.setEnabled(true);
			usuarioDAO.save(u2);
			
			Usuario u3 = new Usuario();
			u3.setUsername("fulano");
			u3.setPassword(encoder.encode("123"));
			u3.setCPF("367.318.380-04");
			u3.setName("Fulano Silva");
			u3.setRole("ROLE_USER");
			u3.setEnabled(true);
			usuarioDAO.save(u3);
			
			Editora e1 = new Editora();
			e1.setCNPJ("55.789.390/0008-99");
			e1.setNome("Companhia das Letras");
			editoraDAO.save(e1);
			
			Editora e2 = new Editora();
			e2.setCNPJ("71.150.470/0001-40");
			e2.setNome("Record");
			editoraDAO.save(e2);
			
			Editora e3 = new Editora();
			e3.setCNPJ("32.106.536/0001-82");
			e3.setNome("Objetiva");
			editoraDAO.save(e3);
			
			Livro l1 = new Livro();
			l1.setTitulo("Ensaio sobre a Cegueira");
			l1.setAutor("José Saramago");
			l1.setAno(1995);
			l1.setPreco(BigDecimal.valueOf(54.9));
			l1.setEditora(e1);
			livroDAO.save(l1);
			
			Livro l2 = new Livro();
			l2.setTitulo("Cem anos de Solidão");
			l2.setAutor("Gabriel Garcia Márquez");
			l2.setAno(1977);
			l2.setPreco(BigDecimal.valueOf(59.9));
			l2.setEditora(e2);
			livroDAO.save(l2);
			
			Livro l3 = new Livro();
			l3.setTitulo("Diálogos Impossíveis");
			l3.setAutor("Luis Fernando Verissimo");
			l3.setAno(2012);
			l3.setPreco(BigDecimal.valueOf(22.9));
			l3.setEditora(e3);
			livroDAO.save(l3);
		};
	}
	*/
}
