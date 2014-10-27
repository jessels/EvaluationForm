package br.com.evaluationform;

import br.com.evaluationform.dao.Evento;
import br.com.evaluationform.dao.EventoDAO;
import br.com.evaluationform.dao.ProjetoDAO;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CriarProjeto extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.criar_projeto);
		
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		final EditText nomeProj = (EditText) findViewById(R.id.nome_projeto);
		final ListView listaEv = (ListView) findViewById(R.id.listEventos);
		final Button btNext = (Button) findViewById(R.id.bt_next);
		
		btNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomePj = nomeProj.getText().toString();
				
				
				ProjetoDAO dao = new ProjetoDAO();
				if(dao.inserirProjeto(0, nomePj,)){
					
					
				}
				
			}
		});
		
	}

}
