package br.com.evaluationform;

import br.com.evaluationform.dao.UsuarioDAO;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.ListView;

public class SelecionarAvaliador extends Activity{
	
	private ListView listSelectAv;
	private Button btFinaliza;
	private UsuarioDAO usuarioDAO;
	//private ArrayList
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecionar_avaliador);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		
		
	
		
	}
	private void inicializaComponentes(){
		this.listSelectAv = (ListView) findViewById(R.id.lv_avaliadores);
		this.btFinaliza = (Button) findViewById(R.id.bt_finaliza);
		
		
	}

}
