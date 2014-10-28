package br.com.evaluationform;

import java.util.ArrayList;

import br.com.evaluationform.dao.Criterio;
import br.com.evaluationform.dao.CriterioDAO;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class CriarTabela extends Activity{
	
	private Button btMais;
	private EditText edDesc;
	private EditText edPeso;
	private ListView listCrit;
	private CriterioDAO criterioDAO;
	
	
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
		
		btMais.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String descricao = edDesc.getText().toString();
				int peso = Integer.parseInt(edPeso.getText().toString());
				
				criterioDAO.inserirCriterio(new Criterio(0, peso, descricao ));
				
				
			}
		});
	}
	
	
	
	
	
	private void inicializaComponentes(){
		this.btMais = (Button) findViewById(R.id.bt_add);
		this.edDesc = (EditText) findViewById(R.id.ed_desc);
		this.edPeso = (EditText) findViewById(R.id.ed_peso);
		this.listCrit = (ListView) findViewById(R.id.list_crit);
		this.criterioDAO = new CriterioDAO();
		
		ArrayList<Criterio> listaCriterios = criterioDAO.buscarTodosCriterios();
			if(listaCriterios != null){
				ArrayAdapter<Criterio> criterioAdapter = new ArrayAdapter<Criterio>(CriarTabela.this, 
						android.R.layout.simple_list_item_1);
				
				listCrit.setAdapter(criterioAdapter);
				
			}
		
	}
	
	
	
	

}
