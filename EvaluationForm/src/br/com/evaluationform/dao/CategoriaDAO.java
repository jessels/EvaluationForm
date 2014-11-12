package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CategoriaDAO {
	
	private static final String URL = "http://192.168.240.155:8080/EvaluationWS4/services/CategoriaDAO?wsdl";
	private static final String NAMESPACE = "http://evaluationWS.evaluation.com.br";
	
	private static final String INSERIR = "inserirCategoria";
	private static final String	EXCLUIR = "excluirCategoria";
	private static final String	ATUALIZAR = "atualizarCategoria";
	private static final String BUSCAR_TODOS = "buscarTodasCategorias";
	private static final String BUSCAR_POR_ID = "buscarCategoriaPorId";
	
	public boolean inserirCategoria(Categoria categoria){
		
		SoapObject inserirCategoria = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject cat = new SoapObject(NAMESPACE, "categoria");
		
		cat.addProperty("id", categoria.getId_categoria());
		cat.addProperty("descricao", categoria.getDescricao());
		
		inserirCategoria.addSoapObject(cat);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirCategoria);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + INSERIR, envelope);
			
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
			
			return Boolean.parseBoolean(resposta.toString());
	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}
		
	}
	
	public boolean atualizarCategoria(Categoria categoria){
		
		SoapObject atualizarCategoria = new SoapObject(NAMESPACE, ATUALIZAR);
		
		SoapObject cat = new SoapObject(NAMESPACE, "categoria");
		
		cat.addProperty("id", categoria.getId_categoria());
		
		
		atualizarCategoria.addSoapObject(cat);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(atualizarCategoria);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + ATUALIZAR, envelope);
			
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
			
			return Boolean.parseBoolean(resposta.toString());
	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}
	}
	
	
	public boolean excluirCategoria(int id){
		
		SoapObject excluirCategoria = new SoapObject(NAMESPACE, EXCLUIR);
		
		SoapObject cat = new SoapObject(NAMESPACE, "categoria");
		
		cat.addProperty("id", id);
		
		
		excluirCategoria.addSoapObject(cat);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(excluirCategoria);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + EXCLUIR, envelope);
			
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
			
			return Boolean.parseBoolean(resposta.toString());
	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}
	}
	
	public ArrayList<Categoria> buscarTodasCategorias(){
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		
SoapObject buscarTodasCategorias = new SoapObject(NAMESPACE, BUSCAR_TODOS);
		
		SoapObject usr = new SoapObject(NAMESPACE, "categoria");
		
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodasCategorias);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_TODOS, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				
				Categoria cat = new Categoria();
				cat.setId_categoria(	Integer.parseInt(soapObject.getProperty("id").toString()));
				cat.setDescricao(soapObject.getProperty("descricao").toString());
				
				
				lista.add(cat);
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
		return lista;
	}
	
	public Categoria buscarCategoriaPorId(int id){
		Categoria cat = null;
		
		SoapObject buscarCategoriaPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
		
		SoapObject user = new SoapObject(NAMESPACE, "categoria");
		buscarCategoriaPorId.addProperty("id", id);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarCategoriaPorId);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_POR_ID, envelope);
			
			SoapObject resposta = (SoapObject) envelope.getResponse();
			
				cat = new Categoria();
				
				cat.setId_categoria(	Integer.parseInt(resposta.getProperty("id").toString()));
				cat.setDescricao(resposta.getProperty("descricao").toString());
				
				
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		return cat;
		
		
	}

}
