package br.com.evaluationform.dao;

import java.io.Serializable;

public class Criterio implements Serializable{
	
	
	private static final long serialVersionUID = 199110351886408214L;
	private int id_criterio;
	private int peso;
	private String descricao;
	private int id_tabela_av;
	
	
	public Criterio(){
		
	}
	public Criterio(int id_criterio, int peso, String descricao, int id_tabela_av) {
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
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
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
	@Override
	public String toString() {
		return this.descricao;
	}

}
