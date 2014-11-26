package br.com.evaluationform;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.evaluationform.dao.Usuario;
import br.com.evaluationform.dao.UsuarioDAO;

public class Configuracao extends Activity{
	
	private EditText edNome, edLogin, edSenha, edConfSenha;
	private Button btAltera;
	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_configuracao);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		this.inicializaComponentes();
		recuperaPreferencia();
		
		btAltera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String txNome = edNome.getText().toString();
				String txLogin = edLogin.getText().toString();
				String txSenha = edSenha.getText().toString();
				String txConfSenha = edConfSenha.getText().toString();
				String codSenha = null;
				
				if (txSenha.equals(txConfSenha)) {
					try {
						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(txSenha.getBytes("UTF-8"));
						BigInteger hash = new BigInteger(1, md.digest());
						codSenha = hash.toString(16);
					} catch (NoSuchAlgorithmException e1) {
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					usuarioDAO = new UsuarioDAO();
					if(usuarioDAO.atualizarUsuario(new Usuario(usuario.getId(), txNome, txLogin, codSenha))){
						Toast.makeText(getApplicationContext(), "Dados alterados com sucesso!", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		
	}
	
	private void recuperaPreferencia(){
		SharedPreferences spPreferencias = getApplicationContext().getSharedPreferences(TelaLogin.NOME_PREFERENCIA, MODE_APPEND);
		this.usuario = new Usuario();
		this.usuario.setId(spPreferencias.getInt("id", 0));
		this.usuario.setLogin(spPreferencias.getString("usuario", "0"));
		this.usuario.setLogin(spPreferencias.getString("senha", "0"));
	}	
	private void inicializaComponentes(){
		this.edNome = (EditText) findViewById(R.id.ed_config_nome);
		this.edLogin = (EditText) findViewById(R.id.ed_config_login);
		this.edSenha = (EditText) findViewById(R.id.ed_config_senha);
		this.edConfSenha = (EditText) findViewById(R.id.ed_config_confsenha);
		this.btAltera = (Button) findViewById(R.id.bt_config_alterar);
		this.usuarioDAO = new UsuarioDAO();
	}

}
