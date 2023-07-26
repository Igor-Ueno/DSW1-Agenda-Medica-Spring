package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// import br.ufscar.dc.dsw.domain.Usuarios;
import br.ufscar.dc.dsw.domain.Consulta;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long>{
    
    Consulta findById(long id);
	
	// Consulta findByCPF(String CPF);

    // Consulta findByCRM(String CRM);

	List<Consulta> findAll();
	
	Consulta save(Consulta consulta);

	void deleteById(Long id);

    // void deleteByConsulta(String Consulta);

}