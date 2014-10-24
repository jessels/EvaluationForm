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
import br.com.evaluationform.dao.Usuario;
import br.com.evaluationform.dao.UsuarioDAO;

public class TelaLogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_login);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		final EditText edLogin = (EditText) findViewById(R.id.ed_login);
		final EditText edSenha = (EditText) findViewById(R.id.ed_senha);
		final Button btEntrar = (Button) findViewById(R.id.bt_entrar);
		final Button btRegistro = (Button) findViewById(R.id.bt_registro);
		final Sessao sessao = new Sessao(getApplicationContext());

		btEntrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String login = edLogin.getText().toString();
				String senha = edSenha.getText().toString();
				String codSenha = null;

				try {
					MessageDigest md = MessageDigest.getInstance("MD5");
					md.update(senha.getBytes("UTF-8"));
					BigInteger hash = new BigInteger(1, md.digest());
					codSenha = hash.toString(16);
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				if (login.trim().length() > 0 && codSenha.trim().length() > 0) {
					UsuarioDAO dao = new UsuarioDAO();
					Usuario dados = dao.buscarUsuarioPorLogin(login);

					if (dados != null) {

						if (login.equals(dados.getLogin())
								&& codSenha.equals(dados.getSenha())) {
							sessao.CriarSessao(dados.getId(), dados.getNome(),
									dados.getLogin());

							Intent irTelaPrincipal = new Intent(
									getApplicationContext(),
									TelaPrincipal.class);
							startActivity(irTelaPrincipal);
							finish();
						}
					}

				}

			}

		});
		btRegistro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent irTelaCadastro = new Intent(getApplicationContext(),
						TelaCadastro.class);
				startActivity(irTelaCadastro);
				finish();

			}

		});

	}

}
