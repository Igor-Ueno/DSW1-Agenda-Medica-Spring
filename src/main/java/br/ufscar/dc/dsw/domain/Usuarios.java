package br.ufscar.dc.dsw.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// import org.hibernate.validator.constraints.br.CPF;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuarios extends AbstractEntity<Long> {
  
	@NotBlank
    @Size(min = 14, max = 14, message = "{Size.usuario.CPF}")
    @Column(nullable = true, length = 20, unique = true)
    private String CPF;
    
	@NotBlank
    @Size(min = 20, max = 20, message = "{Size.usuario.CRM}")
    @Column(nullable = true, length = 64, unique = true)
    private String CRM;
       
    @NotBlank
    @Column(nullable = false, length = 60)
    private String login;
    
    @NotBlank
    @Column(nullable = false, length = 14)
    private String senha;

	@NotBlank
    @Column(nullable = false, length = 60)
    private String nome;
    
    @NotBlank
    @Size(min = 20, max = 20, message = "{Size.usuario.telefone}")
    @Column(nullable = true, length = 60)
    private String telefone;
    
    @NotBlank
    @Column(nullable = true, length = 14)
    private Character sexo;

    @NotBlank
    @Column(nullable = true, length = 14)
    private Date data_nascimento;

	@NotBlank
    @Column(nullable = true, length = 14)
    private String especialidade;

    @NotBlank
    @Column(nullable = false, length = 14)
    private String papel;

    @Column(nullable = false)
    private boolean enabled;
		
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public String getCRM() {
		return CRM;
	}
	
	public void setCRM(String CRM) {
		this.CRM = CRM;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}	
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Character getSexo() {
		return sexo;
	}

	public void setData_nascimento(String telefone) {
		this.telefone = telefone;
	}

    public Date getData_nascimento() {
		return data_nascimento;
	}

    public Date setData_nascimento(Date data_nascimento) {
		return data_nascimento;
	}

	public String getEspecialidade() {
		return especialidade;
	}

    public String setEspecialidade(String especialidade) {
		return especialidade;
	}

    public String getPapel() {
		return papel;
	}

    public String setPapel(String papel) {
		return papel;
	}

    public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}