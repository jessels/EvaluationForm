package br.com.evaluationform.abas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.evaluationform.CriarAvaliacao;
import br.com.evaluationform.R;
import br.com.evaluationform.TelaPrincipal;

public class MenuAvaliacao extends Activity{
	
	private Button btCriar;
	private Button btMinhas;
	private Button btVolta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_avaliacao);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		btCriar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaCriarAvaliacao = new Intent(getApplicationContext(), CriarAvaliacao.class);
				startActivity(irTelaCriarAvaliacao);
			}
		});
//		btMinhas.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent irTelaMinhasAvaliacoes = new Intent(getApplicationContext(), MinhasAvaliacoes.class);
//				startActivity(irTelaMinhasAvaliacoes);
//			}
//		});
		btVolta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent voltarTelaInicial = new Intent(getApplicationContext(), TelaPrincipal.class);
				startActivity(voltarTelaInicial);
				finish();
			}
		});
		
	}
	
	private void inicializaComponentes(){
		this.btCriar = (Button) findViewById(R.id.bt_menu_avaliacao_criar);
		this.btMinhas = (Button) findViewById(R.id.bt_menu_avaliacao_minhas);
		this.btVolta = (Button) findViewById(R.id.bt_menu_avaliacao_volta);
	}

}
