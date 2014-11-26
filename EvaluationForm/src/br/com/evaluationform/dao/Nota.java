package br.com.evaluationform.dao;

import java.io.Serializable;

public class Nota implements Serializable{

	private static final long serialVersionUID = 5652066474937446819L;
	
	private int id_nota;
	private double nota_aluno1;
	private double nota_aluno2;
	private int id_user;
	private int id_avaliacao;
	private int id_criterio;
	
	public Nota(){
		
	}
	
	public Nota(int id_nota, double nota_aluno1, double nota_aluno2, int id_criterio, int id_user, int id_avaliacao) {
		super();
		this.id_nota = id_nota;
		this.nota_aluno1 = nota_aluno1;
		this.nota_aluno2 = nota_aluno2;
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
	public int getId_criterio() {
		return id_criterio;
	}
	public void setId_criterio(int id_criterio) {
		this.id_criterio = id_criterio;
	}

	public double getNota_aluno1() {
		return nota_aluno1;
	}

	public void setNota_aluno1(double nota_aluno1) {
		this.nota_aluno1 = nota_aluno1;
	}

	public double getNota_aluno2() {
		return nota_aluno2;
	}

	public void setNota_aluno2(double nota_aluno2) {
		this.nota_aluno2 = nota_aluno2;
	}

	@Override
	public String toString() {
		return "Nota [nota_aluno1=" + nota_aluno1 + ", nota_aluno2=" + nota_aluno2 + "]";
	}
	
}
