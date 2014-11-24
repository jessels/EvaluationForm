package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class EventoDAO {
	
	private static final String URL = "http://192.168.1.5:8080/EvaluationWSv2/services/EventoDAO?wsdl";
	private static final String NAMESPACE = "http://evaluationv2.com.br";
	
	private static final String INSERIR = "inserirEvento";
	private static final String	EXCLUIR = "excluirEvento";
	private static final String	ATUALIZAR = "atualizarEvento";
	private static final String BUSCAR_TODOS = "buscarTodosEventos";
	private static final String BUSCAR_POR_ID = "buscarEventoPorId";
	
	public boolean inserirEvento(Evento evento){
		
		SoapObject inserirEvento = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject even = new SoapObject(NAMESPACE, "evento");
		
		even.addProperty("id_evento", evento.getId_evento());
		even.addProperty("nome", evento.getNome());
		even.addProperty("endereco", evento.getEndereco());
		even.addProperty("instituicao", evento.getInstituicao());
		
		inserirEvento.addSoapObject(even);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirEvento);
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
	
	public boolean atualizarEvento(Evento evento){
		
		SoapObject atualizarEvento = new SoapObject(NAMESPACE, ATUALIZAR);
		
		SoapObject even = new SoapObject(NAMESPACE, "evento");
		
		even.addProperty("id_evento", evento.getId_evento());
		even.addProperty("nome", evento.getNome());
		even.addProperty("endereco", evento.getEndereco());
		even.addProperty("senha", evento.getInstituicao());
		
		atualizarEvento.addSoapObject(even);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(atualizarEvento);
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
	
	
	public boolean excluirEvento(int id){
		
		SoapObject excluirEvento = new SoapObject(NAMESPACE, EXCLUIR);
		
		SoapObject usr = new SoapObject(NAMESPACE, "evento");
		
		usr.addProperty("id_evento", id);
		
		
		excluirEvento.addSoapObject(usr);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(excluirEvento);
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
	
	public ArrayList<Evento> buscarTodosEventos(){
		ArrayList<Evento> lista = new ArrayList<Evento>();
		
SoapObject buscarTodosEventos = new SoapObject(NAMESPACE, BUSCAR_TODOS);
		
		SoapObject even = new SoapObject(NAMESPACE, "evento");
		
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodosEventos);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_TODOS, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				
				Evento event = new Evento();
				event.setId_evento(	Integer.parseInt(soapObject.getProperty("id_evento").toString()));
				event.setNome(soapObject.getProperty("nome").toString());
				event.setEndereco(soapObject.getProperty("endereco").toString());
				event.setInstituicao(soapObject.getPropertyAsString("instituicao").toString());
				
				lista.add(event);
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
		return lista;
	}
	
	public Evento buscarEventoPorId(int id){
		Evento even = null;
		
		SoapObject buscarEventoPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
		
		SoapObject event = new SoapObject(NAMESPACE, "evento");
		buscarEventoPorId.addProperty("id", id);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarEventoPorId);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_POR_ID, envelope);
			
			SoapObject resposta = (SoapObject) envelope.getResponse();
			
				even = new Evento();
				
				even.setId_evento(	Integer.parseInt(resposta.getProperty("id").toString()));
				even.setNome(resposta.getProperty("nome").toString());
				even.setEndereco(resposta.getProperty("endereco").toString());
				even.setInstituicao(resposta.getPropertyAsString("instituicao").toString());
				
				
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		return even;
		
		
	}

}
