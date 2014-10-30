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

public class CriarCriterio extends Activity{
	
	private Button btMais;
	private EditText edDesc;
	private EditText edPeso;
	private ListView listCrit;
	private CriterioDAO criterioDAO;
	private ArrayList<Criterio> listaCriterios;
	private ArrayAdapter<Criterio> criterioAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.criar_criterio);
		
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
				Criterio c = new Criterio(0, peso, descricao );
				criterioDAO.inserirCriterio(c);
				atualizaAdapter();
				
				
			}
		});
	}
	
	private void inicializaComponentes(){
		this.btMais = (Button) findViewById(R.id.bt_add);
		this.edDesc = (EditText) findViewById(R.id.ed_desc);
		this.edPeso = (EditText) findViewById(R.id.ed_peso);
		this.listCrit = (ListView) findViewById(R.id.list_crit);
		this.criterioDAO = new CriterioDAO();
		atualizaAdapter();
		
	}
	
	private void atualizaAdapter() {
		listaCriterios = criterioDAO.buscarTodosCriterios();
		if(listaCriterios!=null){
			if(criterioAdapter==null) {
				criterioAdapter = new ArrayAdapter<Criterio>(CriarCriterio.this, android.R.layout.simple_list_item_1, listaCriterios);
				listCrit.setAdapter(criterioAdapter);
			}else {
				criterioAdapter.clear();
				criterioAdapter.addAll(listaCriterios);
				criterioAdapter.notifyDataSetChanged();
			}
		}		
	}
	
	
	
	

}
