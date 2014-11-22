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
import br.com.evaluationform.dao.TabelaAvaliativa;
import br.com.evaluationform.dao.TabelaAvaliativaDAO;
import br.com.evaluationform.dao.Usuario;

public class CriarTabela extends Activity {

	private EditText nomeTabela;
	private Button cria;
	private Button volta;
	private ListView listaTabela;
	private TabelaAvaliativaDAO tabelaDAO;
	private TabelaAvaliativa tabela;
	private Usuario usuario;
	private ArrayList<TabelaAvaliativa> listaDeTabela;
	private ArrayAdapter<TabelaAvaliativa> adapterTabela;
	
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
		this.recuperaPreferencia();


		cria.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String nomeInsere = nomeTabela.getText().toString();
				tabela = tabelaDAO.inserirTabelaAvaliativa(new TabelaAvaliativa(0, nomeInsere));
				if (tabela != null) {
					Toast.makeText(getApplicationContext(),"Proejto Criado Com sucesso. ID: "+tabela.getId_tabela_av(), Toast.LENGTH_LONG).show();
					Intent irTelaCriaCriterio = new Intent(getApplicationContext(), CriarCriterio.class);
					Bundle informa = new Bundle();
					informa.putInt("id", tabela.getId_tabela_av());
					irTelaCriaCriterio.putExtras(informa);
					startActivity(irTelaCriaCriterio);
				}
				
			}
		});
		volta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent voltaMenu = new Intent(getApplicationContext(),
						TelaPrincipal.class);
				startActivity(voltaMenu);
				finish();
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
		this.nomeTabela = (EditText) findViewById(R.id.edNomeTabela);
		this.cria = (Button) findViewById(R.id.bt_menu_tabela_criar);
		this.volta = (Button) findViewById(R.id.bt_menu_tabela_voltar);
		this.tabelaDAO = new TabelaAvaliativaDAO();
		this.listaTabela = (ListView) findViewById(R.id.lista_menu_tabela);
		
		listaDeTabela = tabelaDAO.buscarTodasTabelas();
		if(listaDeTabela != null){
			adapterTabela = new ArrayAdapter<TabelaAvaliativa>(CriarTabela.this, 
				android.R.layout.simple_list_item_1, listaDeTabela);
		listaTabela.setAdapter(adapterTabela);
		}

	}

}
