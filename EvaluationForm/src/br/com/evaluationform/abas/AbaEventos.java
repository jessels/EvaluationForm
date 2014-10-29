package br.com.evaluationform.abas;



import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import br.com.evaluationform.CriarEvento;
import br.com.evaluationform.R;
import br.com.evaluationform.dao.Evento;
import br.com.evaluationform.dao.EventoDAO;

public class AbaEventos extends Fragment{
	
	View view;
	private Button criarEvento;
	private TextView texto;
	private ListView listEvento;
	private EventoDAO eventoDAO;
	private ArrayList<Evento> listaEventos;
	private ArrayAdapter<Evento> adapterEventos;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout, container, false);
			
		if(container == null){
			
			return null;
		}
		this.inicializaComponentes();
				
		texto.setText("Eventos");
		criarEvento.setText("Criar Eventos");
		
		
		criarEvento.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaCriarEv = new Intent(getActivity().getApplicationContext(), CriarEvento.class);
				startActivity(irTelaCriarEv);
				
				
				
				
			}
		});
		
			
		
		return view;
		
		
	}

	private void inicializaComponentes() {
		this.criarEvento = (Button) view.findViewById(R.id.bt_Atividade1);
		this.texto = (TextView) view.findViewById(R.id.txInserir);
		this.listEvento = (ListView) view.findViewById(R.id.listFragment);
		this.eventoDAO = new EventoDAO();
		
		listaEventos = eventoDAO.buscarTodosEventos();
		if (listaEventos != null) {
			adapterEventos = new ArrayAdapter<Evento>(
					getActivity(),
					android.R.layout.simple_list_item_1,
					listaEventos);
			
			listEvento.setAdapter(adapterEventos);
		
	
	
	
	}
	
		
	}
		
		
				
	}


