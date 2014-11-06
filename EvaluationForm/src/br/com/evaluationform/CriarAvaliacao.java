package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.evaluationform.dao.Projeto;
import br.com.evaluationform.dao.ProjetoDAO;
import br.com.evaluationform.dao.TabelaAvaliativa;
import br.com.evaluationform.dao.TabelaAvaliativaDAO;
import br.com.evaluationform.dao.Usuario;
import br.com.evaluationform.dao.UsuarioDAO;

public class CriarAvaliacao extends Activity {

	private ListView listProjeto;
	private ListView listAvaliadores;
	private ListView listTabela;
	private ProjetoDAO projetoDAO;
	private UsuarioDAO avaliadorDAO;
	private TabelaAvaliativaDAO tabelaDAO;
	private ArrayList<Projeto> listaProjeto;
	private ArrayAdapter<Projeto> adapterProjeto;
	private ArrayList<Usuario> listaAvaliador;
	private ArrayAdapter<Usuario> adapterAvaliador;
	private ArrayList<TabelaAvaliativa> listaTabela;
	private ArrayAdapter<TabelaAvaliativa> adapterTabela;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.criar_avaliacao);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		listaProjeto = projetoDAO.buscarTodosProjetos();
		if (listaProjeto != null) {
			adapterProjeto = new ArrayAdapter<Projeto>(
					CriarAvaliacao.this,
					android.R.layout.simple_selectable_list_item,
					listaProjeto);

			listProjeto.setAdapter(adapterProjeto);

		}
		listaAvaliador = avaliadorDAO.buscarTodosUsuarios();
		if (listaAvaliador != null) {
			adapterAvaliador = new ArrayAdapter<Usuario>(
					CriarAvaliacao.this,
					android.R.layout.simple_selectable_list_item, 
					listaAvaliador);
			
			listAvaliadores.setAdapter(adapterAvaliador);
		}
		
			listaTabela = tabelaDAO.buscarTodasTabelas();
				if(listaTabela != null){
					adapterTabela = new ArrayAdapter<TabelaAvaliativa>
					(CriarAvaliacao.this, 
					android.R.layout.simple_list_item_1, 
					listaTabela);
					
				listTabela.setAdapter(adapterTabela);
		}

	}
	private void inicializaComponentes() {
		this.listProjeto = (ListView) findViewById(R.id.lista_avaliacao_projeto);
		this.listAvaliadores = (ListView) findViewById(R.id.lista_avaliacao_avaliadores);
		this.listTabela = (ListView) findViewById(R.id.lista_avaliacao_tabela);
		this.projetoDAO = new ProjetoDAO();
		this.avaliadorDAO = new UsuarioDAO();
		this.tabelaDAO = new TabelaAvaliativaDAO();

	}	

}
