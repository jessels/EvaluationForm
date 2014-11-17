package br.com.evaluationform;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.com.evaluationform.abas.MenuAvaliacao;
import br.com.evaluationform.abas.MenuEvento;
import br.com.evaluationform.abas.MenuProjeto;
import br.com.evaluationform.dao.Usuario;

public class TelaPrincipal extends Activity {
	
	private TextView tvUsuarioLogado, tvSenhaLogado;
	private Usuario usuario;
	private Button btDeslogar, menuEvento, menuTabela, menuProjeto, menuAvaliacao, config;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_principal);
		this.inicializaComponentes();
		
		menuEvento.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaEvento = new Intent(getApplicationContext(), MenuEvento.class);
				startActivity(irTelaEvento);
			}
		});
		menuProjeto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaProjeto = new Intent(getApplicationContext(), MenuProjeto.class);
				startActivity(irTelaProjeto);
			}
		});
		menuTabela.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaTabela = new Intent(getApplicationContext(), CriarTabela.class);
				startActivity(irTelaTabela);
			}
		});
		menuAvaliacao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irTelaAvaliacao = new Intent(getApplicationContext(), MenuAvaliacao.class);
				startActivity(irTelaAvaliacao);
			}
		});
		config.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent irTelaConfig = new Intent(getApplicationContext(),);
			}
		});
		
//		this.tvUsuarioLogado.setText(this.tvUsuarioLogado.getText()+this.usuario.getLogin());
//		this.tvSenhaLogado.setText(this.tvSenhaLogado.getText()+this.usuario.getSenha());
//		this.btDeslogar.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				limparSharedPreferences();
//				Intent intentAutenticacao = new Intent(getApplicationContext(),TelaLogin.class);
//				startActivity(intentAutenticacao);
//				finish();
//			}
//		});
		
	}
	
	private void limparSharedPreferences() {
		SharedPreferences spPreferencias = getApplicationContext().getSharedPreferences(TelaLogin.NOME_PREFERENCIA, MODE_APPEND);
		Editor editarPreferencias = spPreferencias.edit();
		editarPreferencias.clear();	
		editarPreferencias.commit();
	}
	
	private void inicializaComponentes() {
//		this.tvUsuarioLogado = (TextView)findViewById(R.id.tv_usuario_logado);
//		this.tvSenhaLogado = (TextView)findViewById(R.id.tv_senha_logado);
//		this.btDeslogar = (Button)findViewById(R.id.bt_deslogar);
		this.menuEvento = (Button) findViewById(R.id.menu_evento);
		this.menuProjeto = (Button) findViewById(R.id.menu_projeto);
		this.menuTabela = (Button) findViewById(R.id.menu_tabela);
		this.menuAvaliacao = (Button) findViewById(R.id.menu_avaliacao);
		this.config = (Button) findViewById(R.id.menu_config);
		
		Bundle bundle = getIntent().getExtras();
		this.usuario =  (Usuario)bundle.getSerializable("usuario");
	}
	
}
