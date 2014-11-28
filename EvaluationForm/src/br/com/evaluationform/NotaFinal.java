package br.com.evaluationform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import br.com.evaluationform.dao.Nota;
import br.com.evaluationform.dao.NotaDAO;

public class NotaFinal extends Activity{
	
	private TextView txExibe, txAluno1, txAluno2, nota1, nota2;
	private NotaDAO notaDAO;
	private Nota nota;
	private double valor1 = 0, valor2 = 0;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_nota_final);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		Intent it = getIntent();
		final String nome_avaliacao = getIntent().getStringExtra("nome_av");
		Log.i("TESTE FINAL", "nome " + nome_avaliacao);
		txExibe.setText(nome_avaliacao);
		Log.d("Valor nota1", notaDAO.notaFinal1(nome_avaliacao) + "");
		valor1 = notaDAO.notaFinal1(nome_avaliacao);
		Log.i("TESTE", "nota1" + (nome_avaliacao));
		valor2 = notaDAO.notaFinal2(nome_avaliacao);
		this.nota1.setText("Nota 1: " + valor1);
		if(valor2 != 0){
			this.nota2.setText("Nota 2: " + String.valueOf(valor2));
		} else {
			this.nota2.setText("Não há valores para a nota do segundo aluno");
		}
		

	}
	private void inicializaComponentes(){
		this.txExibe = (TextView) findViewById(R.id.nota_final_exibe);
		this.txAluno1 = (TextView) findViewById(R.id.nota_final_aluno1);
		this.txAluno2 = (TextView) findViewById(R.id.nota_final_aluno2);
		this.nota1 = (TextView) findViewById(R.id.nota1);
		this.nota2 = (TextView) findViewById(R.id.nota2);
		this.notaDAO = new NotaDAO();
	}

}