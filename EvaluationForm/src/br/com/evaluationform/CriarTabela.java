package br.com.evaluationform;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.evaluationform.dao.CriterioDAO;
import br.com.evaluationform.dao.TabelaAvaliativaDAO;

public class CriarTabela extends Activity {
	
	private EditText nomeTabela;
	private Button salvar;
	private TabelaAvaliativaDAO tabelaDAO;
	private CriterioDAO criterioDAO;
	
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
		
		salvar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nomeInsere = nomeTabela.getText().toString();
			}
		});
		
	}
	
	private void inicializaComponentes(){
		this.nomeTabela = (EditText) findViewById(R.id.edNomeTabela);
		this.salvar = (Button) findViewById(R.id.btSalvar);
		this.tabelaDAO = new TabelaAvaliativaDAO();
		
	}

}
