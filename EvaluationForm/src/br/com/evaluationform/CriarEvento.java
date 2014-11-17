package br.com.evaluationform;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.evaluationform.abas.MenuEvento;
import br.com.evaluationform.dao.Evento;
import br.com.evaluationform.dao.EventoDAO;
import br.com.evaluationform.dao.Usuario;

public class CriarEvento extends Activity{
	
	private EditText nomeEvento;
	private EditText instituicao;
	private EditText endereco;
	private Button btCria;
	private Button btGo;
	private Usuario usuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.criar_evento);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		btCria.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeEv = nomeEvento.getText().toString();
				String enderecoTx = endereco.getText().toString();
				String instituicaoTx = instituicao.getText().toString();
				
				
				EventoDAO dao = new EventoDAO();
				if(dao.inserirEvento(new Evento(0, nomeEv, enderecoTx, instituicaoTx))){
					Toast.makeText(getApplicationContext(), "Evento Criado Com Sucesso", Toast.LENGTH_LONG)
								.show();
				}
			}
		});
		
		btGo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irProximaTela = new Intent(getApplicationContext(), MenuEvento.class);
				startActivity(irProximaTela);
				finish();
			}
		});
	}
	private void inicializaComponentes(){
		this.nomeEvento = (EditText) findViewById(R.id.ed_nome_pj);
		this.instituicao = (EditText) findViewById(R.id.ed_instit);
		this.endereco = (EditText) findViewById(R.id.ed_end);
		this.btCria = (Button) findViewById(R.id.bt_cria);
		this.btGo = (Button) findViewById(R.id.bt_go);
		SharedPreferences preferencia = getSharedPreferences(TelaLogin.NOME_PREFERENCIA, MODE_APPEND);
		this.usuario.setId(preferencia.getInt("id", 0));
		this.usuario.setLogin(preferencia.getString("login", "login falso"));
	}
}
