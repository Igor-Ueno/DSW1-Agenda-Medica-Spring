package br.ufscar.dc.dsw.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;



@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Paciente extends Usuario {
    
    @NotBlank
    @Column(nullable = true, length = 11, unique = true)
    private String CPF;

    @NotBlank
    @Column(nullable = true, length = 11, unique = true)
    private String telefone;
    
    @NotBlank
    @Column(nullable = true, length = 1, unique = false)
    private String sexo;

    @NotBlank
    @Column(nullable = true, length = 64, unique = false)
    private Date data_nascimento;

    public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

    public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

    public Date getData_Nascimento(){
        return data_nascimento;
    }

    public void setData_Nascimento(Date data_nascimento){
        this.data_nascimento = data_nascimento;
    }

    public String getStringData_Nascimento(){ 
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
        String strDate = dateFormat.format(data_nascimento);
        return strDate;
    }

    public void setStringData_Nascimento(String data_nascimento_str){
        LocalDate localDate = LocalDate.parse(data_nascimento_str);
        this.data_nascimento = Date.valueOf(localDate);
    }

}
