package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import br.com.evaluationform.abas.MenuEvento;
import br.com.evaluationform.dao.CriterioDAO;
import br.com.evaluationform.dao.Evento;
import br.com.evaluationform.dao.TabelaAvaliativa;
import br.com.evaluationform.dao.TabelaAvaliativaDAO;

public class CriarTabela extends Activity {
	
	private EditText nomeTabela;
	private Button cria;
	private Button volta;
	private ListView listaTabela;
	private TabelaAvaliativaDAO tabelaDAO;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.criar_tabela);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		ArrayList<TabelaAvaliativa> listaTabelaAv = tabelaDAO.buscarTodasTabelas();
				if (listaTabelaAv != null) {
					ArrayAdapter<TabelaAvaliativa> adapterEventos = new ArrayAdapter<TabelaAvaliativa>(
							CriarTabela.this,
							android.R.layout.simple_list_item_1,
							listaTabelaAv);
					
					listaTabela.setAdapter(adapterEventos);
			}
		
		cria.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeInsere = nomeTabela.getText().toString();
				
				if(tabelaDAO.inserirTabelaAvaliativa(new TabelaAvaliativa(0, nomeInsere))){
					Toast.makeText(getApplicationContext(), "Proejto Criado Com sucesso", Toast.LENGTH_LONG);
				}
				Intent irTelaCriaCriterio = new Intent(getApplicationContext(), CriarCriterio.class);
				startActivity(irTelaCriaCriterio);
			}
		});
		volta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent voltaMenu = new Intent(getApplicationContext(), TelaPrincipal.class);
				startActivity(voltaMenu);
			}
		});
	}
	
	private void inicializaComponentes(){
		this.nomeTabela = (EditText) findViewById(R.id.edNomeTabela);
		this.cria = (Button) findViewById(R.id.bt_menu_tabela_criar);
		this.volta = (Button) findViewById(R.id.bt_menu_tabela_voltar);
		this.tabelaDAO = new TabelaAvaliativaDAO();
		this.listaTabela = (ListView) findViewById(R.id.lista_menu_tabela);
		
		
	}

}
