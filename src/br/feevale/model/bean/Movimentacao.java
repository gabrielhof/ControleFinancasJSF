package br.feevale.model.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="movimentacoes")
public class Movimentacao {

	private Integer id;
	private String nome;
	private Date data;
	private String tipo;
	private Natureza natureza;
	private Usuario usuario;
	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@ManyToOne
	@JoinColumn(name="natureza_id", nullable=false)
	public Natureza getNatureza() {
		return natureza;
	}
	
	public void setNatureza(Natureza natureza) {
		this.natureza = natureza;
	}
	
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
