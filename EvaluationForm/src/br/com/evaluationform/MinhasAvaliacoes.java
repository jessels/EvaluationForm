package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.evaluationform.dao.Avaliacao;
import br.com.evaluationform.dao.AvaliacaoDAO;
import br.com.evaluationform.dao.Usuario;

public class MinhasAvaliacoes extends Activity{
	
	private ListView lista_avaliacao;
	private ArrayList<Avaliacao> listaDeAvaliacao;
	private ArrayAdapter<Avaliacao> adapterAvaliacao;
	private AvaliacaoDAO avaliacaoDAO;
	private Usuario usuario;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.minhas_avaliacoes);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		lista_avaliacao.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(getBaseContext(), "", Toast.LENGTH_LONG).show();
				return false;
			}
		});
	}
	
	private void inicializaComponentes(){
		this.lista_avaliacao = (ListView) findViewById(R.id.lista_minhas_avaliacoes);
		this.usuario = new Usuario();
		SharedPreferences preferencia = getSharedPreferences(TelaLogin.NOME_PREFERENCIA, MODE_APPEND);
		this.usuario.setId(preferencia.getInt("id", 0));
		this.usuario.setLogin(preferencia.getString("login", "login falso"));
		
		
		listaDeAvaliacao = avaliacaoDAO.buscarAvaliacaoPorUsuario(usuario.setId("id"));
			if(listaDeAvaliacao != null){
				adapterAvaliacao = new ArrayAdapter<Avaliacao>(MinhasAvaliacoes.this, 
						android.R.layout.simple_list_item_1, listaDeAvaliacao);
			lista_avaliacao.setAdapter(adapterAvaliacao);
		}
		
		
	}
	
	

}
