package br.com.evaluationform;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.evaluationform.dao.Usuario;
import br.com.evaluationform.dao.UsuarioDAO;

public class TelaCadastro extends Activity {
	
	private EditText nome;
	private EditText login;
	private EditText senha;
	private EditText confSenha;
	private Button usar;
	private Button registro;
	private Sessao sessao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_cadastro);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();

		registro.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				String nomeTx = nome.getText().toString();
				String loginTx = login.getText().toString();
				String senhaTx = senha.getText().toString();
				String confSenhaTx = confSenha.getText().toString();
				String codSenha = null;

				if (senhaTx.equals(confSenhaTx)) {
					try {
						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(senhaTx.getBytes("UTF-8"));
						BigInteger hash = new BigInteger(1, md.digest());
						codSenha = hash.toString(16);
					} catch (NoSuchAlgorithmException e1) {
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

					UsuarioDAO dao = new UsuarioDAO();
					if (dao.inserirUsuario(new Usuario(0, nomeTx, loginTx,
							codSenha))) {
						Toast.makeText(getApplicationContext(),
								"Cadastrado, com Sucesso!", Toast.LENGTH_LONG)
								.show();
					}

				}

			}

		});
		usar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent irTelaPrincipal = new Intent(getApplicationContext(),
						TelaPrincipal.class);
				startActivity(irTelaPrincipal);
				finish();

			}
		});

	}
	private void inicializaComponentes(){
		this.nome = (EditText) findViewById(R.id.ed_nome);
		this.login = (EditText) findViewById(R.id.ed_login);
		this.senha = (EditText) findViewById(R.id.ed_senha);
		this.confSenha = (EditText) findViewById(R.id.ed_con_senha);
		this.usar = (Button) findViewById(R.id.btUsar);
		this.registro = (Button) findViewById(R.id.bt_registro);
		this.sessao = new Sessao(getApplicationContext());
	}
}
