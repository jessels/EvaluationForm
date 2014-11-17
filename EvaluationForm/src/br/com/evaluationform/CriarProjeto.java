package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import br.com.evaluationform.dao.Evento;
import br.com.evaluationform.dao.EventoDAO;
import br.com.evaluationform.dao.Projeto;
import br.com.evaluationform.dao.ProjetoDAO;
import br.com.evaluationform.dao.Usuario;

public class CriarProjeto extends Activity {

	private EditText nomeProj;
	private ListView listaEv;
	private Button btNext;
	private Button btVolta;
	private ProjetoDAO projetoDAO;
	private EventoDAO eventoDAO;
	private Evento eventoSelecionado;
	private Usuario usuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
				
				
				if(verificaPreenchimento()){
					projetoDAO.inserirProjeto(new Projeto(0, nomePj, eventoSelecionado.getId_evento()));
					
				}
			}
		});
		btVolta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaVolta = new Intent(getApplicationContext(), TelaPrincipal.class);
				startActivity(irTelaVolta);
			}
		});

	}

	private void inicializaComponentes() {
		this.nomeProj = (EditText) findViewById(R.id.nome_projeto);
		this.listaEv = (ListView) findViewById(R.id.listEventos);
		this.btNext = (Button) findViewById(R.id.bt_projeto_criar);
		this.btVolta = (Button) findViewById(R.id.bt_projeto_voltar);
		this.projetoDAO = new ProjetoDAO();
		this.eventoDAO = new EventoDAO();
		SharedPreferences preferencia = getSharedPreferences(TelaLogin.NOME_PREFERENCIA, MODE_APPEND);
		this.usuario.setId(preferencia.getInt("id", 0));
		this.usuario.setLogin(preferencia.getString("login", "login falso"));
		
		ArrayList<Evento> listaEventos = eventoDAO.buscarTodosEventos();
		if (listaEventos != null) {
			ArrayAdapter<Evento> adapterEventos = new ArrayAdapter<Evento>(
					CriarProjeto.this,
					android.R.layout.simple_list_item_1,
					listaEventos);

			listaEv.setAdapter(adapterEventos);
			
			listaEv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					int itemPosition = position;
					eventoSelecionado = (Evento)listaEv.getItemAtPosition(position);
					
				}
			});
			
					}
	}
	private boolean verificaPreenchimento(){
		boolean preencheu = false;
		if(!this.nomeProj.getText().toString().equals("") && eventoSelecionado!=null) {
			preencheu = true;
		}
		return preencheu;
	}

}
