package br.com.evaluationform;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.evaluationform.dao.Avaliacao;
import br.com.evaluationform.dao.AvaliacaoDAO;
import br.com.evaluationform.dao.Usuario;

public class MinhasAvaliacoes extends Activity{
	
	private ListView lista_avaliacao;
	private ArrayList<Avaliacao> listaDeAvaliacao;
	private ArrayAdapter<Avaliacao> adapterAvaliacao;
	private AvaliacaoDAO avaliacaoDAO;
	private Usuario usuario;
	private Avaliacao avaliacaoSelecionada;
	private Avaliacao avaliacao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.minhas_avaliacoes);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		recuperaPreferencia();
		this.inicializaComponentes();
		
		
		lista_avaliacao.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int itemPosition = position;
				if(lista_avaliacao.getSelectedItem() != null){
					AlertDialog.Builder dialogo = new AlertDialog.Builder(MinhasAvaliacoes.this);
					dialogo.setTitle("Avaliação");
					dialogo.setMessage("Deseja avaliar "+ lista_avaliacao.getSelectedItem().toString());
					dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
//							Intent irTelaAvaliacao = new Intent(getApplicationContext(), TelaNota.class);
//							startActivity(irTelaAvaliacao);
//							dialog.cancel();
						}
					});
					dialogo.setNeutralButton("Não", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent cancelaDialogo = new Intent(getApplicationContext(), MinhasAvaliacoes.class);
							startActivity(cancelaDialogo);
						}
					});
					dialogo.show();
				}
//				avaliacaoSelecionada = (Avaliacao) lista_avaliacao.getItemAtPosition(position);
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
	
	private void inicializaComponentes(){
		this.lista_avaliacao = (ListView) findViewById(R.id.lista_minhas_avaliacoes);
		this.avaliacaoDAO = new AvaliacaoDAO();
		Log.d("lista", this.usuario.getId() + "");
		this.listaDeAvaliacao = avaliacaoDAO.buscarAvaliacaoPorUsuario(this.usuario.getId());
		
			if(this.listaDeAvaliacao != null){
				this.adapterAvaliacao = new ArrayAdapter<Avaliacao>(this,android.R.layout.simple_list_item_1, listaDeAvaliacao);
				this.lista_avaliacao.setAdapter(adapterAvaliacao);
		}
		
		
	}
	
	

}
