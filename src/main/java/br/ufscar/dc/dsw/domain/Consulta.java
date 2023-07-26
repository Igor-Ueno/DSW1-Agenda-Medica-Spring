package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Consulta")
public class Consulta extends AbstractEntity<Long> {
  
	@NotBlank
    @Size(min = 14, max = 14, message = "{Size.consulta.CPF}")
    @Column(nullable = false, length = 60)
    private String CPFpaciente;
    
	@NotBlank
    @Size(min = 20, max = 20, message = "{Size.consulta.CRM}")
    @Column(nullable = false, length = 60)
    private String CRMmedico;
       
    @NotBlank
    @Column(nullable = false, length = 60)
    private Date data_consulta;
    
    @NotBlank
    @Column(nullable = false, length = 60)
    private Time hora;
		
	public String getCPFpaciente() {
		return CPFpaciente;
	}
	
	public void setCPFpaciente(String CPFpaciente) {
		this.CPFpaciente = CPFpaciente;
	}
	
	public String getCRMmedico() {
		return CRMmedico;
	}
	
	public void setCRMmedico(String CRMmedico) {
		this.CRMmedico = CRMmedico;
	}
	
	public Date getdata_consulta() {
		return data_consulta;
	}
	
	public void setdata_consulta(Date data_consulta) {
		this.data_consulta = data_consulta;
	}	
	
	public Time gethora() {
		return hora;
	}

	public void sethora(Time hora) {
		this.hora = hora;
	}
}