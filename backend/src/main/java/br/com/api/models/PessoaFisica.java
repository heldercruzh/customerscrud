package br.com.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoafisica")
public class PessoaFisica {

	public PessoaFisica() { }
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
		
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String rg;
	
	@Column(nullable = false)
	private String ssp;
		
	@Column(nullable = false)
	private String dataNacimento;
	
	@Column(nullable = false)
	private boolean genero;
		
	@OneToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoaId")
	private Pessoa pessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSsp() {
		return ssp;
	}

	public void setSsp(String ssp) {
		this.ssp = ssp;
	}

	public String getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(String dataNacimento) {
		this.dataNacimento = dataNacimento;
	}

	public boolean isGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
