package br.com.evaluationform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TelaInicial extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_inicial);
		
		
		final int TEMPO_ENTRADA = 5000;
		final Sessao sessao = new Sessao(getApplicationContext());
		
		new Handler().postDelayed(new Runnable() {
			 
            @Override
            public void run() {

            	if (sessao.LOGADO()){
        			Intent irTelaPrincipal = new Intent(getApplicationContext(), TelaPrincipal.class);
                    startActivity(irTelaPrincipal);
                    finish();
        		}
        		else {
        			Intent irTelaLogin = new Intent(getApplicationContext(), TelaLogin.class);
                    startActivity(irTelaLogin);
                    finish();
        		}
            }
		
	}, TEMPO_ENTRADA);

}
}