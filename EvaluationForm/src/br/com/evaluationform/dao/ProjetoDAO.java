package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ProjetoDAO {
	
	private static final String URL = "http://192.168.1.5:8080/EvaluationWSv2/services/ProjetoDAO?wsdl";
	private static final String NAMESPACE = "http://evaluationv2.com.br";
	
	private static final String INSERIR = "inserirProjeto";
	private static final String	EXCLUIR = "excluirProjeto";
	private static final String	ATUALIZAR = "atualizarProjeto";
	private static final String BUSCAR_TODOS = "buscarTodosProjetos";
	private static final String BUSCAR_POR_ID = "buscarProjetoPorId";
	
	public boolean inserirProjeto(Projeto projeto){
		
		SoapObject inserirProjeto = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject proj = new SoapObject(NAMESPACE, "projeto");
		
		proj.addProperty("id_projeto", projeto.getId_projeto());
		proj.addProperty("nome", projeto.getNome());
		proj.addProperty("id_evento", projeto.getId_evento());
		
		
		inserirProjeto.addSoapObject(proj);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirProjeto);
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
	
	public boolean atualizarProjeto(Projeto projeto){
		
		SoapObject atualizarProjeto = new SoapObject(NAMESPACE, ATUALIZAR);
		
		SoapObject proj = new SoapObject(NAMESPACE, "projeto");
		
		proj.addProperty("id_projeto", projeto.getId_projeto());
		proj.addProperty("nome", projeto.getNome());
		proj.addProperty("id_evento", projeto.getId_evento());
		
		
		atualizarProjeto.addSoapObject(proj);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(atualizarProjeto);
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
	
	
	public boolean excluirProjeto(int id){
		
		SoapObject excluirProjeto = new SoapObject(NAMESPACE, EXCLUIR);
		
		SoapObject proj = new SoapObject(NAMESPACE, "projeto");
		
		proj.addProperty("id_projeto", id);
		
		
		excluirProjeto.addSoapObject(proj);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(excluirProjeto);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn:" + EXCLUIR, envelope);
			
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
			
			return Boolean.parseBoolean(resposta.toString());
	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}
	}
	
	public ArrayList<Projeto> buscarTodosProjetos(){
		ArrayList<Projeto> lista = new ArrayList<Projeto>();
		
SoapObject buscarTodosProjetos = new SoapObject(NAMESPACE, BUSCAR_TODOS);
		
		SoapObject proje = new SoapObject(NAMESPACE, "projeto");
		
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodosProjetos);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_TODOS, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				
				Projeto proj = new Projeto();
				proj.setId_projeto(Integer.parseInt(soapObject.getProperty("id_projeto").toString()));
				proj.setNome(soapObject.getProperty("nome").toString());
				proj.setId_evento(Integer.parseInt(soapObject.getProperty("id_evento").toString()));
				
				
				lista.add(proj);
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
		return lista;
	}
	
	public Projeto buscarProjetoPorId(int id){
		Projeto proj = null;
		
		SoapObject buscarProjetoPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
		
		SoapObject user = new SoapObject(NAMESPACE, "projeto");
		buscarProjetoPorId.addProperty("id_projeto", id);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarProjetoPorId);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_POR_ID, envelope);
			
			SoapObject resposta = (SoapObject) envelope.getResponse();
			
				proj = new Projeto();
				
				proj.setId_projeto(	Integer.parseInt(resposta.getProperty("id_projeto").toString()));
				proj.setNome(resposta.getProperty("nome").toString());
				proj.setId_evento(Integer.parseInt(resposta.getProperty("login").toString()));
				
				
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		return proj;
		
		
	}

}
