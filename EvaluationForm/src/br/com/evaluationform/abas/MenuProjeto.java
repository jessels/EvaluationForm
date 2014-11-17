package br.com.evaluationform.abas;

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
import android.widget.ListView;
import br.com.evaluationform.CriarProjeto;
import br.com.evaluationform.R;
import br.com.evaluationform.TelaLogin;
import br.com.evaluationform.dao.Projeto;
import br.com.evaluationform.dao.ProjetoDAO;
import br.com.evaluationform.dao.Usuario;

public class MenuProjeto extends Activity{
	
	private Button criaProjeto;
	private ListView listProjeto;
	private ProjetoDAO projetoDAO;
	private Usuario usuario;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_projeto);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		ArrayList<Projeto> listaProjeto = projetoDAO.buscarTodosProjetos();
			if(listaProjeto != null){
				ArrayAdapter<Projeto> adapterProjeto = new ArrayAdapter<Projeto>(
						MenuProjeto.this,
						android.R.layout.simple_list_item_1,
						listaProjeto);
				
				listProjeto.setAdapter(adapterProjeto);
			}
		
		
		criaProjeto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaCriar = new Intent(getApplicationContext(), CriarProjeto.class);
				startActivity(irTelaCriar);
			}
		});
	}
	
	private void inicializaComponentes(){
		this.criaProjeto = (Button) findViewById(R.id.bt_menu_projeto_criar);
		this.listProjeto = (ListView) findViewById(R.id.lista_menu_projeto);
		SharedPreferences preferencia = getSharedPreferences(TelaLogin.NOME_PREFERENCIA, MODE_APPEND);
		this.usuario.setId(preferencia.getInt("id", 0));
		this.usuario.setLogin(preferencia.getString("login", "login falso"));
		this.projetoDAO = new ProjetoDAO();
	}

}
