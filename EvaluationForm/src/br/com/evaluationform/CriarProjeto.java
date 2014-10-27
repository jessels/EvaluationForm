package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import br.com.evaluationform.dao.Evento;
import br.com.evaluationform.dao.EventoDAO;
import br.com.evaluationform.dao.ProjetoDAO;

public class CriarProjeto extends Activity {

	private EditText nomeProj;
	private ListView listaEv;
	private Button btNext;
	private ProjetoDAO projetoDAO;
	private EventoDAO eventoDAO;

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

		this.inicializaComponentes();

		btNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String nomePj = nomeProj.getText().toString();
				ArrayList<Evento> listaEventos = eventoDAO.buscarTodosEventos();
				if (listaEventos != null) {
					ArrayAdapter<Evento> adapterEventos = new ArrayAdapter<Evento>(
							CriarProjeto.this,
							android.R.layout.simple_list_item_1,
							listaEventos);

					listaEv.setAdapter(adapterEventos);
				}
				

				// String listEv = listaEv.g
				//
				//
				// if(dao.inserirProjeto(0, nomePj, )){
				//
				//
				// }

			}
		});

	}

	private void inicializaComponentes() {
		this.nomeProj = (EditText) findViewById(R.id.nome_projeto);
		this.listaEv = (ListView) findViewById(R.id.listEventos);
		this.btNext = (Button) findViewById(R.id.bt_next);
		this.projetoDAO = new ProjetoDAO();
		this.eventoDAO = new EventoDAO();
	}

}
