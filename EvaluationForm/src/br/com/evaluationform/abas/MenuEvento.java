package br.com.evaluationform.abas;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import br.com.evaluationform.CriarEvento;
import br.com.evaluationform.R;
import br.com.evaluationform.dao.Evento;
import br.com.evaluationform.dao.EventoDAO;
import br.com.evaluationform.dao.Usuario;

public class MenuEvento extends Activity {

	private Button criaEvento;
	private ListView listaMostraEvento;
	private EventoDAO eventoDAO;
	private Usuario usuario;
	private boolean excluiEvento;
	private ArrayList<Evento> listaEventos;
	private ArrayList<Evento> listaEventosExcluir;
	private ArrayAdapter<Evento> adapterEventos;
	private ArrayAdapter<Evento> adapterExcluir;
	private Evento eventoSelecionado;
	private Evento evento;

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

		listaMostraEvento.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				eventoSelecionado = (Evento)listaMostraEvento.getAdapter().getItem(position);
				adapterEventos.remove(eventoSelecionado);
				return false;
			}
		});
	
		criaEvento.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent irTelaCriarEvento = new Intent(getApplicationContext(),
						CriarEvento.class);
				startActivity(irTelaCriarEvento);
			}
		});

	}

	private void inicializaComponentes() {
		this.criaEvento = (Button) findViewById(R.id.bt_evento_criar);
		this.listaMostraEvento = (ListView) findViewById(R.id.lista_menu_evento);
		this.eventoDAO = new EventoDAO();
//		SharedPreferences preferencia = getSharedPreferences(TelaLogin.NOME_PREFERENCIA, MODE_APPEND);
//		this.usuario.setId(preferencia.getInt("id", 0));
//		this.usuario.setLogin(preferencia.getString("login", "login falso"));
		
		listaEventos = eventoDAO.buscarTodosEventos();
		if (listaEventos != null) {
			adapterEventos = new ArrayAdapter<Evento>(
					MenuEvento.this, android.R.layout.simple_list_item_1,
					listaEventos);

			listaMostraEvento.setAdapter(adapterEventos);

		}
		
			


	}
	private void excluirDaLista(){
		excluiEvento = eventoDAO.excluirEvento(evento.getId_evento());
		if(excluiEvento){
			
		}
	
	}

}
