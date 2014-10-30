package br.com.evaluationform.dao;

import java.io.Serializable;

public class Projeto implements Serializable{
	

	

	private static final long serialVersionUID = 3881452565391239160L;
	private int id_projeto;
	private String nome;
	private int id_evento;
	
	
	public Projeto(int id_projeto, String nome, int id_evento) {
		super();
		this.id_projeto = id_projeto;
		this.nome = nome;
		this.id_evento = id_evento;
	}


	public int getId_evento() {
		return id_evento;
	}


	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}


	public Projeto(){
		
	}


	public Projeto(int id_projeto, String nome) {
		super();
		this.id_projeto = id_projeto;
		this.nome = nome;
	}


	public int getId_projeto() {
		return id_projeto;
	}


	public void setId_projeto(int id_projeto) {
		this.id_projeto = id_projeto;
	}


	public String getNome() {
		return this.nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return nome;
	}

	

}
