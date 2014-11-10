package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import br.com.evaluationform.dao.Projeto;
import br.com.evaluationform.dao.Usuario;
import br.com.evaluationform.dao.UsuarioDAO;

public class CriarAvaliacaoNext extends Activity{
	
	private Spinner spAvaliador;
	private ListView listAvaliador;
	private Button btSalva;
	private Button btMais;
	private UsuarioDAO avaliadorDAO;
	private Usuario avaliador;
	private ArrayList<Usuario> listaAvaliador;
	private ArrayAdapter<Usuario> adapterAvaliador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avaliacao_next);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
	}
	private void inicializaComponentes(){
		this.spAvaliador = (Spinner) findViewById(R.id.spinner_de_avaliadores);
		this.listAvaliador = (ListView) findViewById(R.id.lista_avaliadores);
		this.btSalva = (Button) findViewById(R.id.bt_avaliacao_next_salva);
		this.avaliadorDAO = new UsuarioDAO();
		this.avaliador = new Usuario();
		this.btMais = (Button) findViewById(R.id.bt_avaliacao_next_mais);
		atualizaAdapter();
		
		listaAvaliador = avaliadorDAO.buscarTodosUsuarios();
		if(listaAvaliador != null){
			adapterAvaliador = new ArrayAdapter<Usuario>
			(CriarAvaliacaoNext.this, 
			android.R.layout.simple_list_item_1, 
			listaAvaliador);
			
			spAvaliador.setAdapter(adapterAvaliador);
		}
	}
	private void atualizaAdapter(){
		listaAvaliador = avaliadorDAO.buscarTodosUsuarios();
			if(listaAvaliador != null){
				if(adapterAvaliador == null){
					adapterAvaliador = new ArrayAdapter<Usuario>(
							CriarAvaliacaoNext.this, android.R.layout.simple_list_item_1, listaAvaliador);
					listAvaliador.setAdapter(adapterAvaliador);
				}else{
					adapterAvaliador.clear();
					adapterAvaliador.addAll(listaAvaliador);
					adapterAvaliador.notifyDataSetChanged();
				}
				
			}
		
	}

}
