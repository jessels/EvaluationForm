	package br.com.evaluationform.dao;

import java.io.Serializable;
	
	public class Avaliacao implements Serializable{
		
		private static final long serialVersionUID = -278104483980801542L;
		private int id_avaliacao;
		private int id_usuario;
		private int id_projeto;
		private int id_tabela_av;
		private String observacao;
		private String data_av;
		
		
		public Avaliacao(){
			
		}
		public Avaliacao(int id_avaliacao, int id_usuario, int id_projeto, int id_tabela_av, String observacao,
				String data_av) {
			this.id_avaliacao = id_avaliacao;
			this.id_usuario = id_usuario;
			this.id_projeto = id_projeto;
			this.id_tabela_av = id_tabela_av;
			this.observacao = observacao;
			this.data_av = data_av;
		}
		public int getId_avaliacao() {
			return id_avaliacao;
		}
		public void setId_avaliacao(int id_avaliacao) {
			this.id_avaliacao = id_avaliacao;
		}
		public int getId_usuario() {
			return id_usuario;
		}
		public void setId_usuario(int id_usuario) {
			this.id_usuario = id_usuario;
		}
		public int getId_projeto() {
			return id_projeto;
		}
		public void setId_projeto(int id_projeto) {
			this.id_projeto = id_projeto;
		}
		public int getId_tabela_av() {
			return id_tabela_av;
		}
		public void setId_tabela_av(int id_tabela_av) {
			this.id_tabela_av = id_tabela_av;
		}
		public String getObservacao() {
			return observacao;
		}
		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}
		public String getData_av() {
			return data_av;
		}
		public void setData_av(String data_av) {
			this.data_av = data_av;
		}
	
	}
