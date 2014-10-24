package br.com.evaluationform.dao;

import java.io.Serializable;

public class TabelaAvaliativa implements Serializable{
	
	
	private static final long serialVersionUID = -1690584357884220897L;
	private int id_tabela_av;
	private int nota_final;
	private int id_criterio;
	
	
	public TabelaAvaliativa(){
		
	}


	public TabelaAvaliativa(int id_tabela_av, int nota_final, int id_criterio) {
		super();
		this.id_tabela_av = id_tabela_av;
		this.nota_final = nota_final;
		this.id_criterio = id_criterio;
	}


	public int getId_tabela_av() {
		return id_tabela_av;
	}


	public void setId_tabela_av(int id_tabela_av) {
		this.id_tabela_av = id_tabela_av;
	}


	public int getNota_final() {
		return nota_final;
	}


	public void setNota_final(int nota_final) {
		this.nota_final = nota_final;
	}


	public int getId_criterio() {
		return id_criterio;
	}


	public void setId_criterio(int id_criterio) {
		this.id_criterio = id_criterio;
	}
	

}
