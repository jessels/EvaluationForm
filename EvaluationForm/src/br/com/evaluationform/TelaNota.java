package br.com.evaluationform;

import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.com.evaluationform.adapter.AdapterCriterio;
import br.com.evaluationform.dao.Avaliacao;
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
	private Avaliacao avaliacao;
	private Criterio criterio;
	private AdapterCriterio adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nota_avaliacao);
	
		recuperaPreferencia();
		this.inicializaComponentes();
		
		
		btAvalia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Nota nota = new Nota(0, nota_aluno1, nota_aluno2, criterio.getId_criterio(), usuario.getId(), avaliacao.getId_avaliacao());
				notaDAO.inserirNota(nota);
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
		this.criterioDAO = new CriterioDAO();
		this.notaDAO = new NotaDAO();
		this.criterio = new Criterio();
		
		List<Criterio> crit = criterioDAO.buscarCriterioPorTab(19);
		AdapterCriterio adapCriterio = new AdapterCriterio(this, crit);
		
		listaCriterio.setAdapter(adapCriterio);
	}
	

}
