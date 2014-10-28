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
import android.widget.ListView;
import br.com.evaluationform.dao.Evento;
import br.com.evaluationform.dao.EventoDAO;


public class VisualizarEventos extends Activity{
	
	private EventoDAO eventoDAO;
	private ListView lista;
	private Button btVolta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_evento);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		this.inicializaComponentes();
		
		btVolta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent voltaMenu = new Intent(getApplicationContext(), TelaPrincipal.class);
				startActivity(voltaMenu);
				finish();
				
			}
		});
	}
	
	
	
	
	private void inicializaComponentes() {
		this.eventoDAO = new EventoDAO();
		this.lista = (ListView) findViewById(R.id.listarEvento);
		this.btVolta = (Button) findViewById(R.id.botao_voltarMenu);
		ArrayList<Evento> listaEventos = eventoDAO.buscarTodosEventos();
		if (listaEventos != null) {
			ArrayAdapter<Evento> adapterEventos = new ArrayAdapter<Evento>(getApplicationContext(), 
					android.R.layout.activity_list_item,
					listaEventos);
			
			lista.setAdapter(adapterEventos);
		
		
		
	}
		
		
		
		
		
		
	}
	
	
		
		
		
	

}
