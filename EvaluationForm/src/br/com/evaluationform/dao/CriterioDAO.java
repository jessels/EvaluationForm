package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CriterioDAO {
	
	private static final String URL = "http://192.168.241.140:8080/EvaluationWSv2/services/CriterioDAO?wsdl";
	private static final String NAMESPACE = "http://evaluationv2.com.br";
	
	private static final String INSERIR = "inserirCriterio";
	private static final String	EXCLUIR = "excluirCriterio";
	private static final String	ATUALIZAR = "atualizarCriterio";
	private static final String BUSCAR_TODOS = "buscarTodosCriterios";
	private static final String BUSCAR_POR_ID = "buscarCriterioPorId";
	private static final String BUSCAR_TAB = "buscarCriterioPorTab";
	
	public boolean inserirCriterio(Criterio criterio){
		
		SoapObject inserirCriterio = new SoapObject(NAMESPACE, INSERIR);
		SoapObject crit = new SoapObject(NAMESPACE, "criterio");
		
		crit.addProperty("id_criterio", criterio.getId_criterio());
		crit.addProperty("peso", criterio.getPeso());
		crit.addProperty("descricao", criterio.getDescricao());
		crit.addProperty("id_tabela_av", criterio.getId_tabela_av());
		inserirCriterio.addSoapObject(crit);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirCriterio);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn:" + INSERIR, envelope);
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
				crit.setPeso(Double.parseDouble(soapObject.getProperty("peso").toString()));
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
	public ArrayList<Criterio> buscarCriterioPorTab(int id_tabela_av){
		ArrayList<Criterio> lista = new ArrayList<Criterio>();
		
		SoapObject buscarCriterioPorTab = new SoapObject(NAMESPACE, BUSCAR_TAB);
		buscarCriterioPorTab.addProperty("id_tabela_av", id_tabela_av);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarCriterioPorTab);
		envelope.implicitTypes = true;
		HttpTransportSE http = new HttpTransportSE(URL);
		Vector<SoapObject> resposta;
		
		try{
			http.call("urn:" + BUSCAR_TAB, envelope);
			SoapObject teste = (SoapObject) envelope.bodyIn;
			if(teste.getPropertyCount() == 1){
			SoapObject soapObject = (SoapObject) envelope.getResponse();
			
			Criterio crit = new Criterio();
			crit.setId_criterio(Integer.parseInt(soapObject.getProperty("id_criterio").toString()));
			crit.setPeso(Double.parseDouble(soapObject.getProperty("peso").toString()));
			crit.setDescricao(soapObject.getProperty("descricao").toString());
			crit.setId_tabela_av(Integer.parseInt(soapObject.getProperty("id_tabela_av").toString()));
			crit.setNota(new Nota(0, 0, 0, crit.getId_criterio(), 0, 0));
			lista.add(crit);
			}else {
				resposta = (Vector<SoapObject>) envelope.getResponse();
				if(teste.getPropertyCount() != 0){
				for (SoapObject soapObject : resposta) {
					Criterio crit = new Criterio();
					crit.setId_criterio(Integer.parseInt(soapObject.getProperty("id_criterio").toString()));
					crit.setPeso(Double.parseDouble(soapObject.getProperty("peso").toString()));
					crit.setDescricao(soapObject.getProperty("descricao").toString());
					crit.setId_tabela_av(Integer.parseInt(soapObject.getProperty("id_tabela_av").toString()));
					
					crit.setNota(new Nota(0, 0, 0, crit.getId_criterio(), 0, 0));
					
					lista.add(crit);
				}
				
		}else {
			return null;
			}
		}
		}catch (Exception e) {
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
				crit.setId_criterio(Integer.parseInt(resposta.getProperty("id").toString()));
				crit.setPeso(Double.parseDouble(resposta.getProperty("peso").toString()));
				crit.setDescricao(resposta.getProperty("descricao").toString());
				crit.setId_tabela_av(Integer.parseInt(resposta.getProperty("id_tabela_av").toString()));
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return crit;
	}

}
