package br.com.evaluationform;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.evaluationform.dao.Usuario;

public class TelaLogin extends Activity {

	private EditText ed_login, ed_Senha;
	private Button btAutenticar;
	private Usuario usuario;
	public final static String NOME_PREFERENCIA = "preferencias_usuario";
	private SharedPreferences spPreferencias;
	private Editor editarPreferencias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		spPreferencias = getApplicationContext().getSharedPreferences(NOME_PREFERENCIA, MODE_APPEND);
		editarPreferencias = spPreferencias.edit();
		this.usuario = new Usuario();
		
		if (verificaSeUsuarioJaLogou()) {
			// USUÁRIO JÁ LOGOU, PULAR PARA OUTRA ACTIVITY
			this.chamaTelaUsuarioLogado();

		} else {
			// USUÁRIO NUNCA LOGOU, MOSTRAR ACTIVITY DE LOGIN E SENHA
			setContentView(R.layout.tela_login);
			this.inicializaComponentes();						
			this.btAutenticar.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// CHAMAR METODO NO WEB SERVICE(Através do UsuarioDAO) QUE
					// RETORNA UM USUARIO CASO ESTEJA CORRETA A AUTENTICACAO
					// CASO CONTRARIO RETORNA NULL
					usuario.setLogin(ed_login.getText().toString());
					usuario.setSenha(ed_Senha.getText().toString());
					if(verificaPreenchimento()) {						
						editarPreferencias.putString("usuario", usuario.getLogin());
						editarPreferencias.putString("senha", usuario.getSenha());
						editarPreferencias.putInt("id", usuario.getId());
						editarPreferencias.commit();
						chamaTelaUsuarioLogado();
					}else {
						Toast.makeText(TelaLogin.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
					}
					
				}
			});

		}
	}

	private boolean verificaSeUsuarioJaLogou() {
		boolean logou = false;
		String strLogin = spPreferencias.getString("login", null);
		String strSenha = spPreferencias.getString("senha", null);
		if (strLogin != null && strSenha != null) {
			this.usuario.setLogin(strLogin);
			this.usuario.setSenha(strSenha);
			logou = true;
		}
		return logou;
	}

	private void inicializaComponentes() {
		this.ed_login = (EditText) findViewById(R.id.ed_login);
		this.ed_Senha = (EditText) findViewById(R.id.ed_senha);
		this.btAutenticar = (Button) findViewById(R.id.bt_entrar);		
	}

	private void chamaTelaUsuarioLogado() {
		Intent intentUsuarioLogado = new Intent(this, TelaPrincipal.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("usuario", this.usuario);
		intentUsuarioLogado.putExtras(bundle);
		startActivity(intentUsuarioLogado);
		finish();
	}
	
	private boolean verificaPreenchimento() {
		boolean preencheu = true;
		if(this.usuario.getLogin().equals("")||this.usuario.getSenha().equals("")) {
			return false;
		}		
		return preencheu;
	}
	
}
