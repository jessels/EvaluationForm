package br.com.evaluationform.abas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.evaluationform.CriarTabela;
import br.com.evaluationform.R;

public class AbaTabelaAvaliativa extends Fragment{
	
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout, container, false);
		
		if(container == null){
			
			return null;
		}
		final Button criarTabela = (Button) view.findViewById(R.id.bt_Atividade1);
		final Button visualTabela = (Button) view.findViewById(R.id.bt_Atividade2);
		
		criarTabela.setText("Criar Tabela");
		visualTabela.setText("Visualizar Tabela");
		
		criarTabela.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irCriarTabela = new Intent(getActivity().getApplicationContext(), CriarTabela.class);
				startActivity(irCriarTabela);
								
				
				
				
			}
		});
		
		visualTabela.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		return view;
		
	}

}
