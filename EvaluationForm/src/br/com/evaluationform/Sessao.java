package br.com.evaluationform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import br.com.evaluationform.dao.Usuario;

public class Sessao {
	
	private Usuario usuario;
	private SharedPreferences sharedPr;
	private Editor edit;
	private Context context;
	
	int PRIVATE_MODE = 0;
	
	public static final String NOME_PREF = "EvaluationPref";
	private static final String LOGADO = "loga";
	public static final String ID = "id_user";
	public static final String NOME = "nome";
	public static final String LOGIN = "login";
	
	public Sessao(Context context){
		this.context = context;
		sharedPr = context.getSharedPreferences(NOME_PREF, PRIVATE_MODE);
		edit = sharedPr.edit();
		
	}
	public void CriarSessao(int id, String nome, String login){
		edit.putBoolean(LOGADO, true);
		edit.putInt(ID, id);
		edit.putString(NOME, nome);
		edit.putString(LOGIN, login);
		edit.commit();
		
	}
	public void VerificaLogin(){
		if(!this.LOGADO()){
			Intent voltaTelaLogin = new Intent(context, TelaLogin.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("usuario", usuario);
			
			voltaTelaLogin.putExtras(bundle);
			voltaTelaLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			voltaTelaLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			context.startActivity(voltaTelaLogin);
			
			
			
		}
	}
	public int getIdUsuario() {
    	int id = sharedPr.getInt(ID, 0);
    	return id;
    }
	public void deslogaUsuario(){
        edit.clear();
        edit.commit();

        Intent voltaTelaLogin = new Intent(context, TelaLogin.class);
        voltaTelaLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        voltaTelaLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(voltaTelaLogin);
    }

	
	boolean LOGADO() {
		return sharedPr.getBoolean(LOGADO, false);
	}

}
