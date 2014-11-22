package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class TabelaAvaliativaDAO {
	
	private static final String URL = "http://192.168.1.5:8080/EvaluationWSv2/services/TabelaAvaliativaDAO?wsdl";
	private static final String NAMESPACE = "http://evaluationv2.com.br";
	
	private static final String INSERIR = "inserirTabelaAvaliativa";
	private static final String	EXCLUIR = "excluirTabelaAvaliativa";
	private static final String	ATUALIZAR = "atualizarTabelaAvaliativa";
	private static final String BUSCAR_TODOS = "buscarTodasTabelas";
	private static final String BUSCAR_POR_ID = "buscarTabelaPorId";
	
	public TabelaAvaliativa inserirTabelaAvaliativa(TabelaAvaliativa tabela){
		
		SoapObject inserirTabelaAvaliativa = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject tav = new SoapObject(NAMESPACE, "tabelaAvaliativa");
		
		tav.addProperty("id_tabela_av", tabela.getId_tabela_av());
		tav.addProperty("nome", tabela.getNome());
		
		
		
		inserirTabelaAvaliativa.addSoapObject(tav);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirTabelaAvaliativa);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + INSERIR, envelope);
			
			SoapObject resposta = (SoapObject) envelope.getResponse();
			
			return this.soToTabelaAvaliativa((SoapObject)envelope.getResponse());
	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
	}
	
	private TabelaAvaliativa soToTabelaAvaliativa(SoapObject soTabela) {
		TabelaAvaliativa tabela = null;
		if(soTabela!=null) {
			tabela = new TabelaAvaliativa();
			tabela.setId_tabela_av(Integer.parseInt(soTabela.getProperty("id_tabela_av").toString()));
			tabela.setNome(soTabela.getProperty("nome").toString());
		}
		return tabela;
	}
	
	public boolean atualizarTabelaAvaliativa(TabelaAvaliativa tabela){
		
		SoapObject atualizarTabelaAvaliativa = new SoapObject(NAMESPACE, ATUALIZAR);
		
		SoapObject tav = new SoapObject(NAMESPACE, "tabelaAvaliativa");
		
		tav.addProperty("id_tabela_av", tabela.getId_tabela_av());
		tav.addProperty("nome", tabela.getNome());
		
				
		atualizarTabelaAvaliativa.addSoapObject(tav);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(atualizarTabelaAvaliativa);
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
	
	
	public boolean excluirTabelaAvaliativa(int id){
		
		SoapObject excluirTabelaAvaliativa = new SoapObject(NAMESPACE, EXCLUIR);
		
		SoapObject tav = new SoapObject(NAMESPACE, "tabelaAvaliativa");
		
		tav.addProperty("id_tabela_av", id);
		
		
		excluirTabelaAvaliativa.addSoapObject(tav);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(excluirTabelaAvaliativa);
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
	
	public ArrayList<TabelaAvaliativa> buscarTodasTabelas(){
		ArrayList<TabelaAvaliativa> lista = new ArrayList<TabelaAvaliativa>();
		
SoapObject buscarTodasTabelas = new SoapObject(NAMESPACE, BUSCAR_TODOS);
		
		SoapObject tav = new SoapObject(NAMESPACE, "tabelaAvaliativa");
		
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodasTabelas);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_TODOS, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				
				TabelaAvaliativa tav2 = new TabelaAvaliativa();
				tav2.setId_tabela_av(	Integer.parseInt(soapObject.getProperty("id_tabela_av").toString()));
				tav2.setNome(soapObject.getProperty("nome").toString());
				
				
				
				lista.add(tav2);
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
		return lista;
	}
	
	public TabelaAvaliativa buscarTabelaPorId(int id){
		TabelaAvaliativa tav = null;
		
		SoapObject buscarTabelaPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
		
		SoapObject tav2 = new SoapObject(NAMESPACE, "tabelaAvaliativa");
		buscarTabelaPorId.addProperty("id", id);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTabelaPorId);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_POR_ID, envelope);
			
			SoapObject resposta = (SoapObject) envelope.getResponse();
			
				tav = new TabelaAvaliativa();
				
				tav.setId_tabela_av(	Integer.parseInt(resposta.getProperty("id_tabela_av").toString()));
				tav.setNome(resposta.getProperty("nome").toString());
				
				
				
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		return tav;
		
		
	}

}
