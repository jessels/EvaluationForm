package br.com.evaluationform.dao;

public class ConectaWs {
	
	private static final String URL = "http://172.20.10.5:8080/EvaluationWS4/services";
	private static final String NAMESPACE = "http://evaluationWS.evaluation.com.br";
	
	public String getURL() {
		return URL;
	}

	public String getNamespace() {
		return NAMESPACE;
	}

}
