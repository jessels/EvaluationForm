package br.com.evaluationform;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.evaluationform.adapter.AdapterCriterio;
import br.com.evaluationform.dao.Criterio;
import br.com.evaluationform.dao.CriterioDAO;
import br.com.evaluationform.dao.Nota;
import br.com.evaluationform.dao.NotaDAO;
import br.com.evaluationform.dao.Usuario;

public class TelaNota extends Activity{
	
	private ListView listaCriterio;
	private Button btAvalia;
	private CriterioDAO criterioDAO;
	private NotaDAO notaDAO;
	private Usuario usuario;
	private AdapterCriterio adapCriterio;
	private TextView tx_nota;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nota_avaliacao);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		recuperaPreferencia();
		this.inicializaComponentes();
	
		Intent it = getIntent();
		final Integer id_avaliacao = getIntent().getIntExtra("id_avaliacao", 0);
		final Integer id_tabela_av = getIntent().getIntExtra("id_tabela_av", 0);
		final String nome_av = getIntent().getStringExtra("nome_av");
		Log.i("TESTE", "id da tabela " + id_tabela_av);
		Log.i("TESTE", "id da avaliacao " + id_avaliacao);
		
		tx_nota.setText(nome_av);
	
		List<Criterio> crit = criterioDAO.buscarCriterioPorTab(id_tabela_av);
		adapCriterio = new AdapterCriterio(this, crit);
		
		listaCriterio.setAdapter(adapCriterio);
		
		btAvalia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				List<Criterio> criterios = adapCriterio.getList();
				
				for (Criterio criterio : criterios) {
					Nota nota = criterio.getNota();
					nota.setId_avaliacao(id_avaliacao);
					nota.setId_user(usuario.getId());
					if(notaDAO.inserirNota(nota)){
						Toast.makeText(getApplicationContext(), "Avaliação feita com sucesso!", Toast.LENGTH_LONG).show();
					}
				}
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
		this.listaCriterio = (ListView) findViewById(R.id.list_nota_Criterio);
		this.btAvalia = (Button) findViewById(R.id.bt_nota_avaliar);
		this.tx_nota = (TextView) findViewById(R.id.nota_tela_exibixcao);
		this.criterioDAO = new CriterioDAO();
		this.notaDAO = new NotaDAO();
	}
}
