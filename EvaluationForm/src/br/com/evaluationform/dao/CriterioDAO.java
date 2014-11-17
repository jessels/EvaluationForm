package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CriterioDAO {
	
	private static final String URL = "http://192.168.241.187:8080/EvaluationWS4/services/CriterioDAO?wsdl";
	private static final String NAMESPACE = "http://evaluationWS.evaluation.com.br";
	
	private static final String INSERIR = "inserirCriterio";
	private static final String	EXCLUIR = "excluirCriterio";
	private static final String	ATUALIZAR = "atualizarCriterio";
	private static final String BUSCAR_TODOS = "buscarTodosCriterios";
	private static final String BUSCAR_POR_ID = "buscarCriterioPorId";
	
	public boolean inserirCriterio(Criterio criterio){
		
		SoapObject inserirCriterio = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject crit = new SoapObject(NAMESPACE, "criterio");
		
		crit.addProperty("id", criterio.getId_criterio());
		crit.addProperty("peso", criterio.getPeso());
		crit.addProperty("descricao", criterio.getDescricao());
		crit.addProperty("id_tabela_av", criterio.getId_tabela_av());
		
		inserirCriterio.addSoapObject(crit);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirCriterio);
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
	
	public boolean atualizarCriterio(Criterio criterio){
		
		SoapObject atualizarCriterio = new SoapObject(NAMESPACE, ATUALIZAR);
		
		SoapObject crit = new SoapObject(NAMESPACE, "criterio");
		
		crit.addProperty("id", criterio.getId_criterio());
		crit.addProperty("peso", criterio.getPeso());
		crit.addProperty("descricao", criterio.getDescricao());
		crit.addProperty("id_tabela_av", criterio.getId_tabela_av());
		
		
		
		atualizarCriterio.addSoapObject(crit);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(atualizarCriterio);
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
	
	
	public boolean excluirCriterio(int id){
		
		SoapObject excluirCriterio = new SoapObject(NAMESPACE, EXCLUIR);
		
		SoapObject crit = new SoapObject(NAMESPACE, "criterio");
		
		crit.addProperty("id", id);
		
		
		excluirCriterio.addSoapObject(crit);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(excluirCriterio);
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
	
	public ArrayList<Criterio> buscarTodosCriterios(){
		ArrayList<Criterio> lista = new ArrayList<Criterio>();
		
SoapObject buscarTodosCriterios = new SoapObject(NAMESPACE, BUSCAR_TODOS);
		
		SoapObject crite = new SoapObject(NAMESPACE, "criterio");
		
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodosCriterios);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_TODOS, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				
				Criterio crit = new Criterio();
				crit.setId_criterio(Integer.parseInt(soapObject.getProperty("id_criterio").toString()));
				crit.setPeso(Integer.parseInt(soapObject.getProperty("peso").toString()));
				crit.setDescricao(soapObject.getProperty("descricao").toString());
				crit.setId_tabela_av(Integer.parseInt(soapObject.getProperty("id_tabela_av").toString()));
				lista.add(crit);
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
		return lista;
	}
	
	public Criterio buscarCriterioPorId(int id){
		Criterio crit = null;
		
		SoapObject buscarCriterioPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
		
		SoapObject user = new SoapObject(NAMESPACE, "criterio");
		buscarCriterioPorId.addProperty("id", id);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarCriterioPorId);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_POR_ID, envelope);
			
			SoapObject resposta = (SoapObject) envelope.getResponse();
			
				crit = new Criterio();
				
				crit.setId_criterio(	Integer.parseInt(resposta.getProperty("id").toString()));
				crit.setPeso(Integer.parseInt(resposta.getProperty("peso").toString()));
				crit.setDescricao(resposta.getProperty("descricao").toString());
				crit.setId_tabela_av(Integer.parseInt(resposta.getProperty("id_tabela_av").toString()));
				
				
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		return crit;
		
		
	}

}
