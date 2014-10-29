package br.com.evaluationform;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.evaluationform.abas.AbaEventos;
import br.com.evaluationform.dao.Evento;
import br.com.evaluationform.dao.EventoDAO;

public class CriarEvento extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.criar_evento);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		final EditText nomeEvento = (EditText) findViewById(R.id.ed_nome_pj);
		final EditText instituicao = (EditText) findViewById(R.id.ed_instit);
		final EditText endereco = (EditText) findViewById(R.id.ed_end);
		final Button btCria = (Button) findViewById(R.id.bt_cria);
		final Button btGo = (Button) findViewById(R.id.bt_go);
		
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
				Intent irProximaTela = new Intent(getApplicationContext(), AbaEventos.class);
				startActivity(irProximaTela);
				finish();
				
			}
		});
		
		
		
	}
	

}
