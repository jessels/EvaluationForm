package br.com.evaluationform.dao;

import java.io.Serializable;

public class Nota implements Serializable{

	private static final long serialVersionUID = 5652066474937446819L;
	
	private int id_nota;
	private int nota;
	private int id_user;
	private int id_avaliacao;
	private int id_criterio;
	
	public Nota(){
		
	}
	
	public Nota(int id_nota, int nota, int id_criterio, int id_user, int id_avaliacao) {
		super();
		this.id_nota = id_nota;
		this.nota = nota;
		this.id_criterio = id_criterio;
		this.id_avaliacao = id_avaliacao;
		this.id_user = id_user;
	}
	
	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_avaliacao() {
		return id_avaliacao;
	}

	public void setId_avaliacao(int id_avaliacao) {
		this.id_avaliacao = id_avaliacao;
	}

	public int getId_nota() {
		return id_nota;
	}
	public void setId_nota(int id_nota) {
		this.id_nota = id_nota;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public int getId_criterio() {
		return id_criterio;
	}
	public void setId_criterio(int id_criterio) {
		this.id_criterio = id_criterio;
	}
	@Override
	public String toString() {
		return "Nota [nota=" + nota + "]";
	}
	
}
