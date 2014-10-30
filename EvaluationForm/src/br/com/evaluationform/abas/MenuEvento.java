package br.com.evaluationform.abas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.com.evaluationform.CriarEvento;
import br.com.evaluationform.R;

public class MenuEvento extends Activity{
	
	private Button criaEvento;
	private ListView listaMostraEvento;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_evento);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		criaEvento.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaCriarEvento = new Intent(getApplicationContext(), CriarEvento.class);
				startActivity(irTelaCriarEvento);
			}
		});
		
	}
	
	private void inicializaComponentes(){
		this.criaEvento = (Button) findViewById(R.id.bt_evento_criar);
		this.listaMostraEvento = (ListView) findViewById(R.id.lista_menu_evento);
		
	}

}
