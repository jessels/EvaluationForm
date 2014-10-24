package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class TabelaAvaliativaDAO {
	
	private static final String classeWs = "TabelaAvaliativaDAO" + "?wsdl";
	static ConectaWs conexao = new ConectaWs();
	
	private static final String URL = conexao.getURL() + classeWs;
	private static final String NAMESPACE = conexao.getNamespace();
	
	private static final String INSERIR = "inserirTabelaAvaliativa";
	private static final String	EXCLUIR = "excluirTabelaAvaliativa";
	private static final String	ATUALIZAR = "atualizarTabelaAvaliativa";
	private static final String BUSCAR_TODOS = "buscarTodasTabelas";
	private static final String BUSCAR_POR_ID = "buscarTabelaPorId";
	
	public boolean inserirTabelaAvaliativa(TabelaAvaliativa tabela){
		
		SoapObject inserirTabelaAvaliativa = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject tav = new SoapObject(NAMESPACE, "tabela_avaliativa");
		
		tav.addProperty("id_tabela_av", tabela.getId_tabela_av());
		tav.addProperty("nota_final", tabela.getNota_final());
		tav.addProperty("id_criterio", tabela.getId_criterio());
		
		
		inserirTabelaAvaliativa.addSoapObject(tav);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirTabelaAvaliativa);
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
	
	public boolean atualizarTabelaAvaliativa(TabelaAvaliativa tabela){
		
		SoapObject atualizarTabelaAvaliativa = new SoapObject(NAMESPACE, ATUALIZAR);
		
		SoapObject tav = new SoapObject(NAMESPACE, "tabela_avaliativa");
		
		tav.addProperty("id_tabela_av", tabela.getId_tabela_av());
		tav.addProperty("nota_final", tabela.getNota_final());
		tav.addProperty("id_criterio", tabela.getId_criterio());
				
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
		
		SoapObject tav = new SoapObject(NAMESPACE, "tabela_avaliativa");
		
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
		
		SoapObject tav = new SoapObject(NAMESPACE, "tabela_avaliativa");
		
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodasTabelas);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_TODOS, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				
				TabelaAvaliativa tav2 = new TabelaAvaliativa();
				tav2.setId_tabela_av(	Integer.parseInt(soapObject.getProperty("id_tabel_av").toString()));
				tav2.setNota_final(	Integer.parseInt(soapObject.getProperty("nota_final").toString()));
				tav2.setId_criterio(	Integer.parseInt(soapObject.getProperty("id_criterio").toString()));
				
				
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
		
		SoapObject tav2 = new SoapObject(NAMESPACE, "tabela_avaliativa");
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
				tav.setNota_final(	Integer.parseInt(resposta.getProperty("nota_final").toString()));
				tav.setId_criterio(	Integer.parseInt(resposta.getProperty("id_criterio").toString()));
				
				
				
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		return tav;
		
		
	}

}
