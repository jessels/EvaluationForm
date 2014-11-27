package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import br.com.evaluationform.dao.Criterio;
import br.com.evaluationform.dao.CriterioDAO;
import br.com.evaluationform.dao.Usuario;

public class CriarCriterio extends Activity {

	private Button btMais;
	private EditText edDesc;
	private EditText edPeso;
	private ListView listCrit;
	private CriterioDAO criterioDAO;
	private Usuario usuario;
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
		this.recuperaPreferencia();
		
		Intent intent = getIntent();
		Bundle informa = intent.getExtras();
		final Integer id = informa.getInt("id");
		
		Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_LONG).show();

		btMais.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String descricao = edDesc.getText().toString();
				Double peso = Double.parseDouble(edPeso.getText().toString());
				Criterio c = new Criterio(0, peso, descricao, id);
				criterioDAO.inserirCriterio(c);
				atualizaAdapter();

			}
		});
	}
	private void recuperaPreferencia(){
		SharedPreferences spPreferencias = getApplicationContext().getSharedPreferences(TelaLogin.NOME_PREFERENCIA, MODE_APPEND);
		this.usuario = new Usuario();
		this.usuario.setId(spPreferencias.getInt("id", 0));
		this.usuario.setLogin(spPreferencias.getString("usuario", "0"));
		this.usuario.setLogin(spPreferencias.getString("senha", "0"));
	}

	private void inicializaComponentes() {
		this.btMais = (Button) findViewById(R.id.bt_add);
		this.edDesc = (EditText) findViewById(R.id.ed_desc);
		this.edPeso = (EditText) findViewById(R.id.ed_peso);
		this.listCrit = (ListView) findViewById(R.id.list_crit);
		this.criterioDAO = new CriterioDAO();
		atualizaAdapter();

	}

	private void atualizaAdapter() {
		listaCriterios = criterioDAO.buscarTodosCriterios();
		if (listaCriterios != null) {
			if (criterioAdapter == null) {
				criterioAdapter = new ArrayAdapter<Criterio>(
						CriarCriterio.this,
						android.R.layout.simple_list_item_1, listaCriterios);
				listCrit.setAdapter(criterioAdapter);
			} else {
				criterioAdapter.clear();
				criterioAdapter.addAll(listaCriterios);
				criterioAdapter.notifyDataSetChanged();
			}
		}
	}
}
