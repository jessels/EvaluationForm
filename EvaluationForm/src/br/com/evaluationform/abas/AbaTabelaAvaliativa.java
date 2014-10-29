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
import br.com.evaluationform.CriarCriterio;
import br.com.evaluationform.R;
import br.com.evaluationform.dao.TabelaAvaliativa;
import br.com.evaluationform.dao.TabelaAvaliativaDAO;

public class AbaTabelaAvaliativa extends Fragment{
	
	View view;
	private Button criarTabela;
	private TextView texto;
	private ListView listTabela;
	private TabelaAvaliativaDAO tabelaDAO;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout, container, false);
		
		if(container == null){
			
			return null;
		}
		this.inicializaComponentes();
		
		texto.setText("Tabela Avaliativa");
		criarTabela.setText("Criar Tabela");
		
		
		criarTabela.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irCriarTabela = new Intent(getActivity().getApplicationContext(), CriarCriterio.class);
				startActivity(irCriarTabela);
								
				
				
				
			}
		});
		
		
		
		return view;
		
	}
	private void inicializaComponentes(){
		this.criarTabela = (Button) view.findViewById(R.id.bt_Atividade1);
		this.texto = (TextView) view.findViewById(R.id.txInserir);
		this.listTabela = (ListView) view.findViewById(R.id.listFragment);
		this.tabelaDAO = new TabelaAvaliativaDAO();
	
		ArrayList<TabelaAvaliativa> listaTabela = tabelaDAO.buscarTodasTabelas();
		
		if (listaTabela != null) {
			ArrayAdapter<TabelaAvaliativa> adapterTabela = new ArrayAdapter<TabelaAvaliativa>(
					getActivity(),
					android.R.layout.simple_list_item_1, listaTabela);
			
			listTabela.setAdapter(adapterTabela);
	}
	}	

}
