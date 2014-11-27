package br.com.evaluationform.dao;

import java.io.Serializable;

public class Criterio implements Serializable{
	
	
	private static final long serialVersionUID = 199110351886408214L;
	private int id_criterio;
	private double peso;
	private String descricao;
	private int id_tabela_av;
	private Nota nota;
	
	
	public Criterio(){
		
	}
	public Criterio(int id_criterio, double peso, String descricao, int id_tabela_av, Nota nota) {
		super();
		this.id_criterio = id_criterio;
		this.peso = peso;
		this.descricao = descricao;
		this.id_tabela_av = id_tabela_av;
		this.nota = nota;
	}
	public Criterio(int id_criterio, double peso, String descricao, int id_tabela_av) {
		super();
		this.id_criterio = id_criterio;
		this.peso = peso;
		this.descricao = descricao;
		this.id_tabela_av = id_tabela_av;
	}
	
	public int getId_criterio() {
		return id_criterio;
	}
	public void setId_criterio(int id_criterio) {
		this.id_criterio = id_criterio;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public int getId_tabela_av() {
		return id_tabela_av;
	}
	public void setId_tabela_av(int id_tabela_av) {
		this.id_tabela_av = id_tabela_av;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Nota getNota() {
		return nota;
	}
	public void setNota(Nota nota) {
		this.nota = nota;
	}
//	@Override
//	public String toString() {
//		return this.descricao;
//	}
	@Override
	public String toString() {
		return descricao;
	}
	
	

}
