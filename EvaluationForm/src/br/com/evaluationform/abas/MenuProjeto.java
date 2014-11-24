package br.com.evaluationform.abas;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
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
	private ArrayList<Projeto> listaProjeto;
	private ArrayAdapter<Projeto> adapterProjeto;
	private Projeto projetoSelecionado;
	private Projeto projeto;
	
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
		this.recuperaPreferencia();
		
		listProjeto.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				projetoSelecionado = (Projeto) listProjeto.getAdapter().getItem(position);
				AlertDialog.Builder dialogo = new AlertDialog.Builder(MenuProjeto.this);
				dialogo.setTitle("Projeto");
				dialogo.setMessage("Deseja excluir o projeto");
				dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.i("Teste Pj", "Projeto: " + projetoSelecionado.getId_projeto());
						if(projetoDAO.excluirProjeto(projetoSelecionado.getId_projeto())){
							Toast.makeText(getApplicationContext(), "Projeto deletado com sucesso!", Toast.LENGTH_LONG).show();
						}
						dialog.cancel();
					}
					
				});
				dialogo.setNeutralButton("Não", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});	
					dialogo.create();
					dialogo.show();
			}
		});
		
		
		criaProjeto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaCriar = new Intent(getApplicationContext(), CriarProjeto.class);
				startActivity(irTelaCriar);
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
	
	
	private void inicializaComponentes(){
		this.criaProjeto = (Button) findViewById(R.id.bt_menu_projeto_criar);
		this.listProjeto = (ListView) findViewById(R.id.lista_menu_projeto);
		this.projetoDAO = new ProjetoDAO();
		this.projeto = new Projeto();
		
		listaProjeto = projetoDAO.buscarTodosProjetos();
		if(listaProjeto != null){
			adapterProjeto = new ArrayAdapter<Projeto>(
					MenuProjeto.this,
					android.R.layout.simple_list_item_1,
					listaProjeto);
			
			listProjeto.setAdapter(adapterProjeto);
		}
	}

}
