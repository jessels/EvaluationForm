package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;
import br.com.evaluationform.adapter.MarshalDouble;

public class NotaDAO {
	
	private static final String URL = "http://192.168.1.5:8080/EvaluationWSv2/services/NotaDAO?wsdl";
	private static final String NAMESPACE = "http://evaluationv2.com.br";
	
	private static final String INSERIR = "inserirNota";
	private static final String	EXCLUIR = "excluirNota";
	private static final String	ATUALIZAR = "atualizarNota";
	private static final String BUSCAR_TODOS = "buscarTodosNota";
	private static final String BUSCAR_POR_ID = "buscarNotaPorId";
	private static final String BUSCAR_NOTA1 = "notaFinal1";
	private static final String BUSCAR_NOTA2 = "notaFinal2";
	
public boolean inserirNota(Nota nota){
		
		SoapObject inserirNota = new SoapObject(NAMESPACE, INSERIR);
		SoapObject not = new SoapObject(NAMESPACE, "nota");
		
		not.addProperty("id_nota", nota.getId_nota());
		not.addProperty("id_user", nota.getId_user());
		not.addProperty("id_avaliacao", nota.getId_avaliacao());
		not.addProperty("nota_aluno1", nota.getNota_aluno1());
		not.addProperty("nota_aluno2", nota.getNota_aluno2());
		not.addProperty("id_criterio", nota.getId_criterio());
		
		inserirNota.addSoapObject(not);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirNota);
		envelope.implicitTypes = true;
		MarshalDouble md = new MarshalDouble();
		md.register(envelope);
		
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
	public boolean atualizarNota(Nota nota){
	
	SoapObject atualizarNota = new SoapObject(NAMESPACE, ATUALIZAR);
	SoapObject not = new SoapObject(NAMESPACE, "nota");
	
	not.addProperty("id_nota", nota.getId_nota());
	not.addProperty("nota_aluno1", nota.getNota_aluno1());
	not.addProperty("nota_aluno2", nota.getNota_aluno2());
	not.addProperty("id_user", nota.getId_user());
	not.addProperty("id_criterio", nota.getId_criterio());
	not.addProperty("id_avaliacao", nota.getId_avaliacao());
	
	atualizarNota.addSoapObject(not);
	
	
	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	envelope.setOutputSoapObject(atualizarNota);
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
	public boolean excluirNota(int id){
		
		SoapObject excluirNota = new SoapObject(NAMESPACE, EXCLUIR);
		SoapObject proj = new SoapObject(NAMESPACE, "nota");
		proj.addProperty("id_nota", id);
		excluirNota.addSoapObject(proj);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(excluirNota);
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
	public ArrayList<Nota> buscarTodasNota(){
		ArrayList<Nota> lista = new ArrayList<Nota>();
		SoapObject buscarTodasNotas = new SoapObject(NAMESPACE, BUSCAR_TODOS);
		SoapObject nott = new SoapObject(NAMESPACE, "nota");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodasNotas);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_TODOS, envelope);
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			for (SoapObject soapObject : resposta) {
				
				Nota not = new Nota();
				not.setId_nota(	Integer.parseInt(soapObject.getProperty("id_nota").toString()));
				not.setNota_aluno1(Double.parseDouble(soapObject.getProperty("nota_aluno1").toString()));
				not.setNota_aluno2(Double.parseDouble(soapObject.getProperty("nota_aluno2").toString()));
				not.setId_user(Integer.parseInt(soapObject.getProperty("id_user").toString()));
				not.setId_criterio(Integer.parseInt(soapObject.getProperty("id_criterio").toString()));
				not.setId_avaliacao(Integer.parseInt(soapObject.getProperty("id_avaliacao").toString()));
				lista.add(not);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lista;
	}
	public Nota buscarNotaPorId(int id){
		Nota not = null;
		SoapObject buscarNotaPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
		SoapObject user = new SoapObject(NAMESPACE, "nota");
		buscarNotaPorId.addProperty("id_nota", id);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarNotaPorId);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_POR_ID, envelope);
			SoapObject resposta = (SoapObject) envelope.getResponse();
				not = new Nota();
				
				not.setId_nota(	Integer.parseInt(resposta.getProperty("id_nota").toString()));
				not.setNota_aluno1(Double.parseDouble(resposta.getProperty("nota_aluno1").toString()));
				not.setNota_aluno2(Double.parseDouble(resposta.getProperty("nota_aluno2").toString()));
				not.setId_user(Integer.parseInt(resposta.getProperty("id_user").toString()));
				not.setId_criterio(Integer.parseInt(resposta.getProperty("id_criterio").toString()));
				not.setId_avaliacao(Integer.parseInt(resposta.getProperty("id_avaliacao").toString()));
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		return not;
	}
	
	public double notaFinal1(String nome_avaliacao){
		double nota1 = 0;
		SoapObject buscarNota1 = new SoapObject(NAMESPACE, BUSCAR_NOTA1);
		buscarNota1.addProperty("nome_avaliacao", nome_avaliacao);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarNota1);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn:" + BUSCAR_NOTA1, envelope);
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
			Log.d("Resposta nota", resposta.toString());
			nota1 = Double.valueOf(resposta.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nota1;
	}
	
	public double notaFinal2(String nome_avaliacao){
		double nota2 = 0;
		SoapObject buscarNota1 = new SoapObject(NAMESPACE, BUSCAR_NOTA2);
		buscarNota1.addProperty("nome_avaliacao", nome_avaliacao);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarNota1);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn:" + BUSCAR_NOTA2, envelope);
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
				nota2 = Double.valueOf(resposta.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nota2;
	}
}
