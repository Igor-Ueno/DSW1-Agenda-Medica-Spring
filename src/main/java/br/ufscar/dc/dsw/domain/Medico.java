package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Medico extends Usuario{
    @NotBlank
    @Column(nullable = true, length = 13, unique = true)
    private String CRM;

    @NotBlank
    @Column(nullable = true, length = 64, unique = false)
    private String especialidade;
    
    public String getCRM() {
		return CRM;
	}
	
	public void setCRM(String CRM) {
		this.CRM = CRM;
	}

    public String getEspecialidade() {
		return especialidade;
	}
	
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
  
}
