package br.com.evaluationform;

import br.com.evaluationform.abas.MenuEvento;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TelaPrincipal extends Activity  {
	
	private Button menuEvento;
	private Button menuProjeto;
	private Button menuTabela;
	private Button menuAvaliacao;
	private Button config;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_principal);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		menuEvento.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaEvento = new Intent(getApplicationContext(), MenuEvento.class);
				startActivity(irTelaEvento);
			}
		});
		menuProjeto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaProjeto = new Intent(getApplicationContext(), CriarProjeto.class);
				startActivity(irTelaProjeto);
			}
		});
		menuTabela.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaTabela = new Intent(getApplicationContext(), CriarTabela.class);
				startActivity(irTelaTabela);
			}
		});
		menuAvaliacao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Intent irTelaAvaliacao = new Intent(getApplicationContext(), )
			}
		});
		config.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent irTelaConfig = new Intent(getApplicationContext(),);
			}
		});
		
	}
	
	private void inicializaComponentes(){
		this.menuEvento = (Button) findViewById(R.id.menu_evento);
		this.menuProjeto = (Button) findViewById(R.id.menu_projeto);
		this.menuTabela = (Button) findViewById(R.id.menu_tabela);
		this.menuAvaliacao = (Button) findViewById(R.id.menu_avaliacao);
		this.config = (Button) findViewById(R.id.menu_config);
		
	}
	
}