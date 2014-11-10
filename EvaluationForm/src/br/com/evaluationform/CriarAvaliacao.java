package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import br.com.evaluationform.dao.Avaliacao;
import br.com.evaluationform.dao.Projeto;
import br.com.evaluationform.dao.ProjetoDAO;
import br.com.evaluationform.dao.TabelaAvaliativa;
import br.com.evaluationform.dao.TabelaAvaliativaDAO;
import br.com.evaluationform.dao.Usuario;
import br.com.evaluationform.dao.UsuarioDAO;

public class CriarAvaliacao extends Activity {

	private Spinner spProjeto;
	private Spinner spAvaliador;
	private Spinner spTabela;
	private ProjetoDAO projetoDAO;
	private UsuarioDAO avaliadorDAO;
	private TabelaAvaliativaDAO tabelaDAO;
	private ArrayList<Projeto> listaProjeto;
	private ArrayAdapter<Projeto> adapterProjeto;
	private ArrayList<Usuario> listaAvaliador;
	private ArrayAdapter<Usuario> adapterAvaliador;
	private ArrayList<TabelaAvaliativa> listaTabela;
	private ArrayAdapter<TabelaAvaliativa> adapterTabela;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.criar_avaliacao);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		listaProjeto = projetoDAO.buscarTodosProjetos();
		if (listaProjeto != null) {
			adapterProjeto = new ArrayAdapter<Projeto>(
					CriarAvaliacao.this,
					android.R.layout.simple_selectable_list_item,
					listaProjeto);

			spProjeto.setAdapter(adapterProjeto);
			
			spProjeto.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					Projeto projeto = new Projeto();
					projeto = (Projeto) spProjeto.getAdapter().getItem(position);
					Intent intent = new Intent(getApplicationContext(), Avaliacao.class);
					intent.putExtra("id_projeto", projeto.getId_projeto());
					startActivity(intent);
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					
				}
			});
			
		

		}
		listaAvaliador = avaliadorDAO.buscarTodosUsuarios();
		if (listaAvaliador != null) {
			adapterAvaliador = new ArrayAdapter<Usuario>(
					CriarAvaliacao.this,
					android.R.layout.simple_selectable_list_item, 
					listaAvaliador);
			
			spAvaliador.setAdapter(adapterAvaliador);
			
			spAvaliador.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					
				}
			});
		}
		
			listaTabela = tabelaDAO.buscarTodasTabelas();
				if(listaTabela != null){
					adapterTabela = new ArrayAdapter<TabelaAvaliativa>
					(CriarAvaliacao.this, 
					android.R.layout.simple_list_item_1, 
					listaTabela);
					
					spTabela.setAdapter(adapterTabela);
				
					spTabela.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						
					}
				});
		}

	}
	private void inicializaComponentes() {
		this.spProjeto = (Spinner) findViewById(R.id.spinner_projeto);
		this.spAvaliador = (Spinner) findViewById(R.id.spinner_avaliador);
		this.spTabela = (Spinner) findViewById(R.id.spinner_tabela);
		this.projetoDAO = new ProjetoDAO();
		this.avaliadorDAO = new UsuarioDAO();
		this.tabelaDAO = new TabelaAvaliativaDAO();

	}	

}
