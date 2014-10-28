package br.com.evaluationform.abas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.evaluationform.R;

public class AbaMinhasAvaliacoes extends Fragment{
	
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.fragment_layout, container, false);
			
			if(container == null){
				
				return null;
			}
			
			final Button criaAvaliacao = (Button) view.findViewById(R.id.bt_Atividade1);
			final Button visualAvaliacao = (Button) view.findViewById(R.id.bt_Atividade2);
			criaAvaliacao.setText("Criar Avaliação");
			visualAvaliacao.setText("Visualizar Avaliação");			
			
			
			
			
			
			return view;
			
	}
	
	
	

}
