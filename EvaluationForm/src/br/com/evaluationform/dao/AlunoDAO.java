		package br.com.evaluationform.dao;
		
		import java.util.ArrayList;
import java.util.Vector;
		


		import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
		
		public class AlunoDAO {
			
			private static final String URL = "http://192.168.241.213:8080/EvaluationWS4/services/AlunoDAO?wsdl";
			private static final String NAMESPACE = "http://evaluationWS.evaluation.com.br";
			
			private static final String INSERIR = "inserirAluno";
			private static final String	EXCLUIR = "excluirAluno";
			private static final String	ATUALIZAR = "atualizarAluno";
			private static final String BUSCAR_TODOS = "buscarTodosAlunos";
			private static final String BUSCAR_POR_ID = "buscarAlunoPorId";
			
			public boolean inserirAluno(Aluno aluno){
				
				SoapObject inserirAluno = new SoapObject(NAMESPACE, INSERIR);
				
				SoapObject aln = new SoapObject(NAMESPACE, "aluno");
				
				aln.addProperty("id_aluno", aluno.getId());
				aln.addProperty("nome_aluno", aluno.getNome());
				aln.addProperty("tamanho_camiseta", aluno.getTam_camisa());
				
				
				inserirAluno.addSoapObject(aln);
				
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(inserirAluno);
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
			
			public boolean atualizarAluno(Aluno aluno){
				
				SoapObject atualizarAluno = new SoapObject(NAMESPACE, ATUALIZAR);
				
				SoapObject aln = new SoapObject(NAMESPACE, "aluno");
				
				aln.addProperty("id_aluno", aluno.getId());
				aln.addProperty("nome_aluno", aluno.getNome());
				aln.addProperty("tamanho_camiseta", aluno.getTam_camisa());
				
				
				atualizarAluno.addSoapObject(aln);
				
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(atualizarAluno);
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
			
			
			public boolean excluirAluno(int id){
				
				SoapObject excluirAluno = new SoapObject(NAMESPACE, EXCLUIR);
				
				SoapObject aln = new SoapObject(NAMESPACE, "aluno");
				
				aln.addProperty("id_aluno", id);
				
				
				excluirAluno.addSoapObject(aln);
				
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(excluirAluno);
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
			
			public ArrayList<Aluno> buscarTodosAlunos(){
				ArrayList<Aluno> lista = new ArrayList<Aluno>();
				
		SoapObject buscarTodosAlunos = new SoapObject(NAMESPACE, BUSCAR_TODOS);
				
				SoapObject aln = new SoapObject(NAMESPACE, "aluno");
				
				
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(buscarTodosAlunos);
				envelope.implicitTypes = true;
				
				HttpTransportSE http = new HttpTransportSE(URL);
				try {
					http.call("urn" + BUSCAR_TODOS, envelope);
					
					Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
					
					for (SoapObject soapObject : resposta) {
						
						Aluno aluno = new Aluno();
						aluno.setId(	Integer.parseInt(soapObject.getProperty("id_aluno").toString()));
						aluno.setNome(soapObject.getProperty("nome_aluno").toString());
						aluno.setTam_camisa(soapObject.getPropertyAsString("tamanho_camiseta").toString());
						
						lista.add(aluno);
						
					}
					
						
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				
				}
				
				
				return lista;
			}
			
			public Aluno buscarAlunoPorId(int id){
				Aluno aln = null;
				
				SoapObject buscarAlunoPorId = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
				
				SoapObject aluno = new SoapObject(NAMESPACE, "aluno");
				buscarAlunoPorId.addProperty("id_aluno", id);
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(buscarAlunoPorId);
				envelope.implicitTypes = true;
				
				HttpTransportSE http = new HttpTransportSE(URL);
				try {
					http.call("urn" + BUSCAR_POR_ID, envelope);
					
					SoapObject resposta = (SoapObject) envelope.getResponse();
					
						aln = new Aluno();
						
						aln.setId(	Integer.parseInt(resposta.getProperty("id_aluno").toString()));
						aln.setNome(resposta.getProperty("nome_aluno").toString());
						aln.setTam_camisa(resposta.getProperty("tamanho_camiseta").toString());
						
						
					
						
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				
				}
				
				return aln;
				
				
			}
		
		}
