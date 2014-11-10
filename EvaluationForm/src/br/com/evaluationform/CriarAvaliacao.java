package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import br.com.evaluationform.dao.Projeto;
import br.com.evaluationform.dao.ProjetoDAO;
import br.com.evaluationform.dao.TabelaAvaliativa;
import br.com.evaluationform.dao.TabelaAvaliativaDAO;

public class CriarAvaliacao extends Activity {

	private Button btNext;
	private Spinner spProjeto;
	private Spinner spTabela;
	private ProjetoDAO projetoDAO;
	private Projeto projeto;
	private TabelaAvaliativaDAO tabelaDAO;
	private TabelaAvaliativa tabela;
	private ArrayList<Projeto> listaProjeto;
	private ArrayAdapter<Projeto> adapterProjeto;
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
						
		
		
//		listaProjeto = projetoDAO.buscarTodosProjetos();
//		if (listaProjeto != null) {
//			adapterProjeto = new ArrayAdapter<Projeto>(
//					CriarAvaliacao.this,
//					android.R.layout.simple_spinner_item,
//					listaProjeto);
//
//			spProjeto.setAdapter(adapterProjeto);
		
		btNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			
			
			spProjeto.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					spProjeto.getAdapter().getItem(position);
					Intent irTelaNext = new Intent(getApplicationContext(), CriarAvaliacaoNext.class);
					Bundle selecProj = new Bundle();
					selecProj.putInt("id_projeto", projeto.getId_projeto());
					irTelaNext.putExtras(selecProj);
					startActivity(irTelaNext);
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					
				}
			});
			
			}
		});

		//}
		
		
//			listaTabela = tabelaDAO.buscarTodasTabelas();
//				if(listaTabela != null){
//					adapterTabela = new ArrayAdapter<TabelaAvaliativa>
//					(CriarAvaliacao.this, 
//					android.R.layout.simple_list_item_1, 
//					listaTabela);
//					
//					spTabela.setAdapter(adapterTabela);
				
					spTabela.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						spTabela.getAdapter().getItem(position);
						Intent irTelaNext = new Intent(getApplicationContext(), CriarAvaliacaoNext.class);
						Bundle selecProj = new Bundle();
						selecProj.putInt("id_tabela", tabela.getId_tabela_av());
						irTelaNext.putExtras(selecProj);
						startActivity(irTelaNext);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						
					}
				});
		}
			
		

			
	private void inicializaComponentes() {
		this.btNext = (Button) findViewById(R.id.bt_avaliacao_next);
		this.spProjeto = (Spinner) findViewById(R.id.spinner_projeto);
		this.spTabela = (Spinner) findViewById(R.id.spinner_tabela);
		this.projeto = new Projeto();
		this.projetoDAO = new ProjetoDAO();
		this.tabelaDAO = new TabelaAvaliativaDAO();
		this.tabela = new TabelaAvaliativa();
		
		
		listaProjeto = projetoDAO.buscarTodosProjetos();
		if (listaProjeto != null) {
			adapterProjeto = new ArrayAdapter<Projeto>(
					CriarAvaliacao.this,
					android.R.layout.simple_spinner_item,
					listaProjeto);

			spProjeto.setAdapter(adapterProjeto);

	}	
		listaTabela = tabelaDAO.buscarTodasTabelas();
		if(listaTabela != null){
			adapterTabela = new ArrayAdapter<TabelaAvaliativa>
			(CriarAvaliacao.this, 
			android.R.layout.simple_list_item_1, 
			listaTabela);
			
			spTabela.setAdapter(adapterTabela);
		}
	}
}
