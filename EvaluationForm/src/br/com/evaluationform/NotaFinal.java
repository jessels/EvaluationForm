package br.com.evaluationform;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import br.com.evaluationform.dao.NotaDAO;

public class NotaFinal extends Activity{
	
	private TextView txExibe, txAluno1, txAluno2, nota1, nota2;
	private NotaDAO notaDAO;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		notaDAO.notaFinal1(nome_avaliacao);

	}
	private void inicializaComponentes(){
		this.txExibe = (TextView) findViewById(R.id.nota_final_exibe);
		this.txAluno1 = (TextView) findViewById(R.id.nota_final_aluno1);
		this.txAluno2 = (TextView) findViewById(R.id.nota_final_aluno2);
		this.nota1 = (TextView) findViewById(R.id.nota1);
		this.nota2 = (TextView) findViewById(R.id.nota2);
	}

}