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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_cadastro);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		final EditText nome = (EditText) findViewById(R.id.ed_nome);
		final EditText login = (EditText) findViewById(R.id.ed_login);
		final EditText senha = (EditText) findViewById(R.id.ed_senha);
		final EditText confSenha = (EditText) findViewById(R.id.ed_con_senha);
		final Button usar = (Button) findViewById(R.id.btUsar);
		final Button registro = (Button) findViewById(R.id.bt_registro);
		final Sessao sessao = new Sessao(getApplicationContext());

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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
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
}
