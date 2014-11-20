package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class AvaliacaoDAO {
	
	private static final String URL = "http://192.168.240.43:8080/EvaluationWSv2/services/AvaliacaoDAO?wsdl";
	private static final String NAMESPACE = "http://evaluationv2.com.br";
	
	private static final String INSERIR = "inserirAvaliacao";
	private static final String	EXCLUIR = "excluirAvaliacao";
	private static final String	ATUALIZAR = "atualizarAvaliacao";
	private static final String BUSCAR_TODOS = "buscarTodasAvaliacoes";
	private static final String BUSCAR_POR_ID = "buscarAvaliacaoPorId";
	private static final String BUSCAR_USER = "buscarAvaliacaoPorUsuario";
	
	
	public boolean inserirAvaliacao(Avaliacao avaliacao){
		
		SoapObject inserirAvaliacao = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject aval = new SoapObject(NAMESPACE, "avaliacao");
		
		aval.addProperty("id_avaliacao", avaliacao.getId_avaliacao());
		aval.addProperty("data_av", avaliacao.getData_av());
		aval.addProperty("id_projeto", avaliacao.getId_projeto());
		aval.addProperty("id_tabela_av", avaliacao.getId_tabela_av());
		aval.addProperty("id_avaliador", avaliacao.getId_usuario());
		
		inserirAvaliacao.addSoapObject(aval);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirAvaliacao);
		envelope.implicitTypes = true;
		
		new MarshalDate().register(envelope);
		
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
	
	public boolean atualizarAvaliacao(Avaliacao avaliacao){
		
		SoapObject atualizarAvaliacao = new SoapObject(NAMESPACE, ATUALIZAR);
		
		SoapObject aval = new SoapObject(NAMESPACE, "avaliacao");
		
		aval.addProperty("id_avaliacao", avaliacao.getId_avaliacao());
		aval.addProperty("data_av", avaliacao.getData_av());
		aval.addProperty("id_projeto", avaliacao.getId_projeto());
		aval.addProperty("id_tabela_av", avaliacao.getId_tabela_av());
		aval.addProperty("id_usuario", avaliacao.getId_usuario());
		
		
		atualizarAvaliacao.addSoapObject(aval);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(atualizarAvaliacao);
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
	
	
	public boolean excluirAvaliacao(int id){
		
		SoapObject excluirAvaliacao = new SoapObject(NAMESPACE, EXCLUIR);
		
		SoapObject usr = new SoapObject(NAMESPACE, "avaliacao");
		
		usr.addProperty("id_avaliacao", id);
		
		
		excluirAvaliacao.addSoapObject(usr);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(excluirAvaliacao);
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
	
	public ArrayList<Avaliacao> buscarTodasAvaliacoes(){
		ArrayList<Avaliacao> lista = new ArrayList<Avaliacao>();
		
SoapObject buscarTodasAvaliacoes = new SoapObject(NAMESPACE, BUSCAR_TODOS);
		
		SoapObject avalia = new SoapObject(NAMESPACE, "avaliacao");
		
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodasAvaliacoes);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_TODOS, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				
				Avaliacao aval= new Avaliacao();
				aval.setId_avaliacao(	Integer.parseInt(soapObject.getProperty("id_avaliacao").toString()));
				aval.setId_projeto(	  Integer.parseInt(soapObject.getProperty("id_projeto").toString()));
				aval.setId_tabela_av(	Integer.parseInt(soapObject.getProperty("id_tabela_av").toString()));
				aval.setId_usuario(	Integer.parseInt(soapObject.getProperty("id_usuario").toString()));
				aval.setData_av((Date)soapObject.getProperty("data_av"));
				
				lista.add(aval);
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
		return lista;
	}
	
	public ArrayList<Avaliacao> buscarAvaliacaoPorUsuario(int id_user){
		ArrayList<Avaliacao> lista = new ArrayList<Avaliacao>();
		
		SoapObject buscarAvaliacaoPorUsuario = new SoapObject(NAMESPACE, BUSCAR_USER);
		
		SoapObject avalia = new SoapObject(NAMESPACE, "avaliacao");
		buscarAvaliacaoPorUsuario.addProperty("id_user", id_user);
		
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarAvaliacaoPorUsuario);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_USER, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				
				Avaliacao aval= new Avaliacao();
				aval.setId_avaliacao(Integer.parseInt(soapObject.getProperty("id_avaliacao").toString()));
				aval.setId_projeto(Integer.parseInt(soapObject.getProperty("id_projeto").toString()));
				aval.setId_tabela_av(Integer.parseInt(soapObject.getProperty("id_tabela_av").toString()));
				aval.setId_usuario(Integer.parseInt(soapObject.getProperty("id_usuario").toString()));
				aval.setData_av((Date)soapObject.getProperty("data_av"));
				
				lista.add(aval);
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
		return lista;
	}
	public Avaliacao buscarAvaliacaoPorId(int id){
		Avaliacao aval = null;
		
		SoapObject buscarAvaliacaoPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
		
		SoapObject avalia = new SoapObject(NAMESPACE, "avaliacao");
		buscarAvaliacaoPorId.addProperty("id_avaliacao", id);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarAvaliacaoPorId);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_POR_ID, envelope);
			
			SoapObject resposta = (SoapObject) envelope.getResponse();
			
				aval = new Avaliacao();
				
				aval.setId_avaliacao(	Integer.parseInt(resposta.getProperty("id_avaliacao").toString()));
				aval.setId_projeto(	  Integer.parseInt(resposta.getProperty("id_projeto").toString()));
				aval.setId_tabela_av(	Integer.parseInt(resposta.getProperty("id_tabela_av").toString()));
				aval.setId_usuario(	Integer.parseInt(resposta.getProperty("id_usuario").toString()));
				aval.setData_av((Date)resposta.getProperty("data_av"));
				
				
				
				
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		return aval;
		
		
	}

}
