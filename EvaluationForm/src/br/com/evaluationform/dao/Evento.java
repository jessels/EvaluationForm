package br.com.evaluationform.dao;

import java.io.Serializable;

public class Evento implements Serializable {
	

	private static final long serialVersionUID = -4203384102409089128L;
	private int id_evento;
	private String nome;
	private String endereco;
	private String instituicao;
	
	
	public Evento(){
		
	}


	public Evento(int id_evento, String nome, String endereco, String instituicao) {
		super();
		this.id_evento = id_evento;
		this.nome = nome;
		this.endereco = endereco;
		this.instituicao = instituicao;
	}


	public int getId_evento() {
		return id_evento;
	}


	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}


	public String getNome() {
		return this.nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getInstituicao() {
		return instituicao;
	}


	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}

}
