package br.com.evaluationform.dao;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ProjetoPorAlunoDAO {
	
	private static final String URL = "http://192.168.241.100:8080/EvaluationWS4/services/ProjetoPorAlunoDAO?wsdl";
	private static final String NAMESPACE = "http://evaluationWS.evaluation.com.br";
	
	private static final String INSERIR = "inserirProjetoPorAluno";
	private static final String	EXCLUIR = "excluirProjetoPorAluno";
	private static final String	ATUALIZAR = "atualizarProjetoPorAluno";
	private static final String BUSCAR_TODOS = "buscarTodosProjetosPorAluno";
	private static final String BUSCAR_POR_ID = "buscarProjetoPorId";
	
	public boolean inserirProjetoPorAluno(ProjetoPorAluno projetoPorAluno){
		
		SoapObject inserirProjetoPorAluno = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject ppa = new SoapObject(NAMESPACE, "projeto_aluno");
		
		ppa.addProperty("id_projeto_aluno", projetoPorAluno.getId_projeto_aluno());
		ppa.addProperty("id_aluno", projetoPorAluno.getId_aluno());
		ppa.addProperty("id_projeto", projetoPorAluno.getId_projeto());
		
		
		inserirProjetoPorAluno.addSoapObject(ppa);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(inserirProjetoPorAluno);
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
	
	public boolean atualizarProjetoPorAluno(ProjetoPorAluno projetoPorAluno){
		
		SoapObject atualizarProjetoPorAluno = new SoapObject(NAMESPACE, ATUALIZAR);
		
		SoapObject ppa = new SoapObject(NAMESPACE, "projeto_aluno");
		
		ppa.addProperty("id_projeto_aluno", projetoPorAluno.getId_projeto_aluno());
		ppa.addProperty("id_aluno", projetoPorAluno.getId_aluno());
		ppa.addProperty("id_projeto", projetoPorAluno.getId_projeto());
		
		
		atualizarProjetoPorAluno.addSoapObject(ppa);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(atualizarProjetoPorAluno);
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
	
	
	public boolean excluirProjetoPorAluno(int id){
		
		SoapObject excluirProjetoPorAluno = new SoapObject(NAMESPACE, EXCLUIR);
		
		SoapObject ppa = new SoapObject(NAMESPACE, "projeto_aluno");
		
		ppa.addProperty("id_projeto_aluno", id);
		
		
		excluirProjetoPorAluno.addSoapObject(ppa);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(excluirProjetoPorAluno);
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
	
	public ArrayList<ProjetoPorAluno> buscarTodosProjetosPorAluno(){
		ArrayList<ProjetoPorAluno> lista = new ArrayList<ProjetoPorAluno>();
		
SoapObject buscarTodosProjetosPorAluno = new SoapObject(NAMESPACE, BUSCAR_TODOS);
		
		SoapObject ppa = new SoapObject(NAMESPACE, "projeto_aluno");
		
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarTodosProjetosPorAluno);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_TODOS, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				
				ProjetoPorAluno ppa1 = new ProjetoPorAluno();
				ppa1.setId_projeto_aluno(	Integer.parseInt(soapObject.getProperty("id_projeto_aluno").toString()));
				ppa1.setId_aluno(	Integer.parseInt(soapObject.getProperty("id_aluno").toString()));
				ppa1.setId_projeto_aluno(	Integer.parseInt(soapObject.getProperty("id_projeto").toString()));
				
				
				lista.add(ppa1);
				
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
		return lista;
	}
	
	public ProjetoPorAluno buscarProjetoPorId(int id){
		ProjetoPorAluno ppa = null;
		
		SoapObject buscarProjetoPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
		
		SoapObject user = new SoapObject(NAMESPACE, "projeto_aluno");
		buscarProjetoPorId.addProperty("id_projeto_aluno", id);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarProjetoPorId);
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call("urn" + BUSCAR_POR_ID, envelope);
			
			SoapObject resposta = (SoapObject) envelope.getResponse();
			
			ppa = new ProjetoPorAluno();
				
			ppa.setId_projeto_aluno(	Integer.parseInt(resposta.getProperty("id_projeto_aluno").toString()));
			ppa.setId_aluno(	Integer.parseInt(resposta.getProperty("id_aluno").toString()));
			ppa.setId_projeto(	Integer.parseInt(resposta.getProperty("id_projeto").toString()));
			
				
				
			
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		return ppa;
		
		
	}

}
