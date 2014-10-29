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
import br.com.evaluationform.CriarProjeto;
import br.com.evaluationform.R;
import br.com.evaluationform.dao.Projeto;
import br.com.evaluationform.dao.ProjetoDAO;
	
	public class AbaProjetos extends Fragment{
		
		View view;
		private Button criarProjeto;
		private TextView texto;
		private ListView listProjeto;
		private ProjetoDAO projetoDAO;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.fragment_layout, container, false);
			
			if(container == null){
				
				return null;
			}
			
			this.inicializaComponentes();
			texto.setText("Projetos");
			criarProjeto.setText("Criar Projeto");
			
			
			
			criarProjeto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
						Intent irTelaCriarPj = new Intent(getActivity().getApplicationContext(), CriarProjeto.class);
						startActivity(irTelaCriarPj);
						
					
					
				}
			});
			
			
			return view;
			
		}
		private void inicializaComponentes(){
			this.criarProjeto = (Button) view.findViewById(R.id.bt_Atividade1);
			this.texto = (TextView) view.findViewById(R.id.txInserir);
			this.listProjeto = (ListView) view.findViewById(R.id.listFragment);
			this.projetoDAO = new ProjetoDAO();
			
			ArrayList<Projeto> listaProjeto = projetoDAO.buscarTodosProjetos();
			if (listaProjeto != null) {
				ArrayAdapter<Projeto> adapterProjetos = new ArrayAdapter<Projeto>(
						getActivity(),
						android.R.layout.simple_list_item_1, listaProjeto);
				
				listProjeto.setAdapter(adapterProjetos);
		}
		}
	
	}
