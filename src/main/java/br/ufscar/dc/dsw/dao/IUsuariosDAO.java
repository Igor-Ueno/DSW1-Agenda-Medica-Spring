package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Usuarios;

@SuppressWarnings("unchecked")
public interface IUsuariosDAO extends CrudRepository<Usuarios, Long> {
	
	Usuarios findById(long id);

    // Usuarios findByCPF(String CPF);

    // Usuarios findByCRM(String CRM);

	List<Usuarios> findAll();
	
	Usuarios save(Usuarios usuarios);

	void deleteById(Long id);

    // void deleteByLogin(String Login);

    // void update(Usuarios usuarios);

    // @Query("SELECT u FROM Usuarios u WHERE u.papel = :papel")
    // public Usuarios getPacientes(@Param("papel") String papel);

    // @Query("SELECT u FROM Usuarios u WHERE u.papel = :papel")
    // public Usuarios getMedicos(@Param("papel") String papel);

    @Query("SELECT u FROM Usuarios u WHERE u.papel = :papel")
    public Usuarios getByPapel(@Param("papel") String papel);

    @Query("SELECT u FROM Usuarios u WHERE u.especialidade = :especialidade")
    public Usuarios getByEspecialidades(@Param("especialidade") String especialidade);

    @Query("SELECT u FROM Usuarios u WHERE u.login = :login")
    public Usuarios getByLogin(@Param("login") String login);
}