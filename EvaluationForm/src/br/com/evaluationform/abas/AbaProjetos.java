	package br.com.evaluationform.abas;
	
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.evaluationform.CriarEvento;
import br.com.evaluationform.CriarProjeto;
import br.com.evaluationform.R;
	
	public class AbaProjetos extends Fragment{
		
		View view;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.fragment_layout, container, false);
			
			if(container == null){
				
				return null;
			}
			final Button criarProjeto = (Button) view.findViewById(R.id.bt_Atividade1);
			final Button visualProjeto = (Button) view.findViewById(R.id.bt_Atividade2);
			
			criarProjeto.setText("Criar Projeto");
			visualProjeto.setText("Visualizar Projeto");
			
			criarProjeto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
						Intent irTelaCriarPj = new Intent(getActivity().getApplicationContext(), CriarProjeto.class);
						startActivity(irTelaCriarPj);
						
					
					
				}
			});
			
			visualProjeto.setOnClickListener(new OnClickListener() {
					
				
				@Override
				public void onClick(View v) {
					
					
				}
			});
			return view;
			
		}
	
	}
