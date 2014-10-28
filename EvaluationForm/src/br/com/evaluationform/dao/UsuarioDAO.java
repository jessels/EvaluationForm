	package br.com.evaluationform.dao;
	
	import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
	
	public class UsuarioDAO {
		
		private static final String URL = "http://172.20.10.5:8080/EvaluationWS4/services/UsuarioDAO?wsdl";
		private static final String NAMESPACE = "http://evaluationWS.evaluation.com.br";
		
		private static final String INSERIR = "inserirUsuario";
		private static final String	EXCLUIR = "excluirUsuario";
		private static final String	ATUALIZAR = "atualizarUsuario";
		private static final String BUSCAR_TODOS = "buscarTodosUsuarios";
		private static final String BUSCAR_POR_ID = "buscarUsuarioPorId";
		private static final String BUSCAR_POR_LOGIN = "buscarUsuarioPorLogin";
		
		public boolean inserirUsuario(Usuario usuario){
			
			SoapObject inserirUsuario = new SoapObject(NAMESPACE, INSERIR);
			
			SoapObject usr = new SoapObject(NAMESPACE, "usuario");
			
			usr.addProperty("id", usuario.getId());
			usr.addProperty("nome", usuario.getNome());
			usr.addProperty("login", usuario.getLogin());
			usr.addProperty("senha", usuario.getSenha());
			
			inserirUsuario.addSoapObject(usr);
			
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(inserirUsuario);
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
		
		public boolean atualizarUsuario(Usuario usuario){
			
			SoapObject atualizarUsuario = new SoapObject(NAMESPACE, ATUALIZAR);
			
			SoapObject usr = new SoapObject(NAMESPACE, "usuario");
			
			usr.addProperty("id", usuario.getId());
			usr.addProperty("nome", usuario.getNome());
			usr.addProperty("login", usuario.getLogin());
			usr.addProperty("senha", usuario.getSenha());
			
			atualizarUsuario.addSoapObject(usr);
			
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(atualizarUsuario);
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
		
		
		public boolean excluirUsuario(int id){
			
			SoapObject excluirUsuario = new SoapObject(NAMESPACE, EXCLUIR);
			
			SoapObject usr = new SoapObject(NAMESPACE, "usuario");
			
			usr.addProperty("id", id);
			
			
			excluirUsuario.addSoapObject(usr);
			
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(excluirUsuario);
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
		
		public ArrayList<Usuario> buscarTodosUsuarios(){
			ArrayList<Usuario> lista = new ArrayList<Usuario>();
			
	SoapObject buscarTodosUsuarios = new SoapObject(NAMESPACE, BUSCAR_TODOS);
			
			SoapObject usr = new SoapObject(NAMESPACE, "usuario");
			
			
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(buscarTodosUsuarios);
			envelope.implicitTypes = true;
			
			HttpTransportSE http = new HttpTransportSE(URL);
			try {
				http.call("urn" + BUSCAR_TODOS, envelope);
				
				Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
				
				for (SoapObject soapObject : resposta) {
					
					Usuario user = new Usuario();
					user.setId(	Integer.parseInt(soapObject.getProperty("id").toString()));
					user.setNome(soapObject.getProperty("nome").toString());
					user.setLogin(soapObject.getProperty("login").toString());
					user.setSenha(soapObject.getPropertyAsString("senha").toString());
					
					lista.add(user);
					
				}
				
					
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			
			}
			
			
			return lista;
		}
		
		public Usuario buscarUsuarioPorId(int id){
			Usuario usr = null;
			
			SoapObject buscarUsuarioPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
			
			SoapObject user = new SoapObject(NAMESPACE, "usuario");
			buscarUsuarioPorId.addProperty("id", id);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(buscarUsuarioPorId);
			envelope.implicitTypes = true;
			
			HttpTransportSE http = new HttpTransportSE(URL);
			try {
				http.call("urn" + BUSCAR_POR_ID, envelope);
				
				SoapObject resposta = (SoapObject) envelope.getResponse();
				
					usr = new Usuario();
					
					usr.setId(	Integer.parseInt(resposta.getProperty("id").toString()));
					usr.setNome(resposta.getProperty("nome").toString());
					usr.setLogin(resposta.getProperty("login").toString());
					usr.setSenha(resposta.getPropertyAsString("senha").toString());
					
					
				
					
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			
			}
			
			return usr;
			
			
		}
		public Usuario buscarUsuarioPorLogin(String login){
			Usuario usr = null;
			
			SoapObject buscarUsuarioPorLogin = new SoapObject(NAMESPACE, BUSCAR_POR_LOGIN);
			
			buscarUsuarioPorLogin.addProperty("login", login);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(buscarUsuarioPorLogin);
			envelope.implicitTypes = true;
			
			HttpTransportSE http = new HttpTransportSE(URL);
			try {
				http.call("urn" + BUSCAR_POR_LOGIN, envelope);
				
				SoapObject resposta = (SoapObject) envelope.getResponse();
				
					usr = new Usuario();
					
					usr.setId(	Integer.parseInt(resposta.getProperty("id").toString()));
					usr.setNome(resposta.getProperty("nome").toString());
					usr.setLogin(resposta.getProperty("login").toString());
					usr.setSenha(resposta.getPropertyAsString("senha").toString());
					
					
				
					
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			
			}
			
			return usr;
			
			
		}
	}
