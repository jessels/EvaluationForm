package br.com.evaluationform;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.evaluationform.dao.Usuario;
import br.com.evaluationform.dao.UsuarioDAO;

public class TelaLogin extends Activity {

	private EditText edLogin, edSenha;
	private Button btEntrar;
	private Button btRegistro;
	private Usuario usuario;
	public final static String NOME_PREFERENCIA = "preferencias_usuario";
	private SharedPreferences spPreferencias;
	private Editor editarPreferencias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		spPreferencias = getApplicationContext().getSharedPreferences(NOME_PREFERENCIA, MODE_APPEND);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		editarPreferencias = spPreferencias.edit();
		this.usuario = new Usuario();
		if(verificaSeUsuarioJaLogou()){
			chamaTelaUsuarioLogado();
		} else {
				setContentView(R.layout.tela_login);
				this.inicializaComponentes();
				btEntrar.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						usuario.setLogin(edLogin.getText().toString());
						usuario.setSenha(edSenha.getText().toString());
						if(verificaPreenchimento()){							
							String codSenha = null;
							try {
								MessageDigest md = MessageDigest.getInstance("MD5");
								md.update(usuario.getSenha().getBytes("UTF-8"));
								BigInteger hash = new BigInteger(1, md.digest());
								codSenha = hash.toString(16);
							} catch (NoSuchAlgorithmException e1) {
								e1.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							UsuarioDAO dao = new UsuarioDAO();
							Usuario dados = dao.buscarUsuarioPorLogin(usuario.getLogin());
							if (dados != null) {
								if (usuario.getLogin().equals(dados.getLogin()) && codSenha.equals(dados.getSenha())) {
									editarPreferencias.putString("usuario", dados.getLogin());
									editarPreferencias.putString("senha", dados.getSenha());
									editarPreferencias.putInt("id", dados.getId());
									editarPreferencias.commit();
									chamaTelaUsuarioLogado();
								} else {
									Toast.makeText(TelaLogin.this, "Usuário e/ou senha incorretos",	Toast.LENGTH_LONG).show();
							}

						} else {
							Toast.makeText(TelaLogin.this, "Usuário não encontrado", Toast.LENGTH_LONG).show();
						}

					} else {
						Toast.makeText(TelaLogin.this, "Preencha todos os campos.",	Toast.LENGTH_LONG).show();
					}
				}

			});
			btRegistro.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent irTelaCadastro = new Intent(getApplicationContext(),	TelaCadastro.class);
						startActivity(irTelaCadastro);
					}

			});
		}
		

	}
	
	private void inicializaComponentes(){
		this.edLogin = (EditText) findViewById(R.id.ed_login);
		this.edSenha = (EditText) findViewById(R.id.ed_senha);
		this.btEntrar = (Button) findViewById(R.id.bt_entrar);
		this.btRegistro = (Button) findViewById(R.id.bt_registro);	
	}
	
	private boolean verificaSeUsuarioJaLogou() {
		boolean logou = false;
		String strUsuario = spPreferencias.getString("usuario", null);
		String strSenha = spPreferencias.getString("senha", null);
		if (strUsuario != null && strSenha != null) {
			this.usuario.setLogin(strUsuario);
			this.usuario.setSenha(strSenha);
			logou = true;
		}
		return logou;
	}

	private void chamaTelaUsuarioLogado() {
		Intent intentUsuarioLogado = new Intent(this, TelaPrincipal.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("usuario", this.usuario.getLogin());
		bundle.putSerializable("senha", this.usuario.getSenha());
		bundle.putSerializable("id", this.usuario.getId());
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
