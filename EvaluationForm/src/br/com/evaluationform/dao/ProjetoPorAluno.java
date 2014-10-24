package br.com.evaluationform.dao;

import java.io.Serializable;

public class ProjetoPorAluno implements Serializable{
	
	
	private static final long serialVersionUID = 6041897488549831993L;
	private int id_projeto_aluno;
	private int id_aluno;
	private int id_projeto;
	
	
	public ProjetoPorAluno(){
		
	}


	public ProjetoPorAluno(int id_projeto_aluno, int id_aluno, int id_projeto) {
		this.id_projeto_aluno = id_projeto_aluno;
		this.id_aluno = id_aluno;
		this.id_projeto = id_projeto;
	}


	public int getId_projeto_aluno() {
		return id_projeto_aluno;
	}


	public void setId_projeto_aluno(int id_projeto_aluno) {
		this.id_projeto_aluno = id_projeto_aluno;
	}


	public int getId_aluno() {
		return id_aluno;
	}


	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}


	public int getId_projeto() {
		return id_projeto;
	}


	public void setId_projeto(int id_projeto) {
		this.id_projeto = id_projeto;
	}
	


}
