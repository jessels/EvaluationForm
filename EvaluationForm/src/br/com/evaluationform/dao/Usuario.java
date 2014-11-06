	package br.com.evaluationform.dao;

import java.io.Serializable;
	
	public class Usuario implements Serializable{
		
		private static final long serialVersionUID = 4411163796390311863L;
		private int id;
		private String nome;
		private String login;
		private String senha;
		
		public Usuario(){
			
		}
	
		public Usuario(int id, String nome, String login, String senha) {
			this.id = id;
			this.nome = nome;
			this.login = login;
			this.senha = senha;
		}
	
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		@Override
		public String toString() {
			return nome;
		}
	
		public String getNome() {
			return nome;
		}
	
		public void setNome(String nome) {
			this.nome = nome;
		}
	
		public String getLogin() {
			return login;
		}
	
		public void setLogin(String login) {
			this.login = login;
		}
	
		public String getSenha() {
			return senha;
		}
	
		public void setSenha(String senha) {
			this.senha = senha;
		}
		
		 
	
	
	}
