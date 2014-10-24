	package br.com.evaluationform.dao;

import java.io.Serializable;
	
	public class Categoria implements Serializable{
	
		private static final long serialVersionUID = 5385581644320648153L;
		private int id_categoria;
		private String descricao;
		
		
		public Categoria(){
			
		}
	
	
		public Categoria(int id_categoria, String descricao) {
			super();
			this.id_categoria = id_categoria;
			this.descricao = descricao;
		}
	
	
		public int getId_categoria() {
			return id_categoria;
		}
	
	
		public void setId_categoria(int id_categoria) {
			this.id_categoria = id_categoria;
		}
	
	
		public String getDescricao() {
			return descricao;
		}
	
	
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
	
	}
