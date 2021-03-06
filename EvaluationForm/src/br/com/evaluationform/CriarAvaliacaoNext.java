package br.com.evaluationform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import br.com.evaluationform.dao.Avaliacao;
import br.com.evaluationform.dao.AvaliacaoDAO;
import br.com.evaluationform.dao.Usuario;
import br.com.evaluationform.dao.UsuarioDAO;

public class CriarAvaliacaoNext extends Activity{
	
	private Spinner spAvaliador;
	private ListView listAvaliador;
	private Usuario usuario;
	private Button btSalva;
	private Button btMais;
	private AvaliacaoDAO avaliacaoDAO;
	private UsuarioDAO avaliadorDAO;
	private int id_click;
	private ArrayList<Usuario> listaAvaliador;
	private ArrayAdapter<Usuario> adapterAvaliador;
	public EditText editData;
	private int ano, mes, dia;
	private int i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avaliacao_next);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		this.inicializaComponentes();
		this.recuperaPreferencia();
		
		editData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Calendar c = Calendar.getInstance();
				ano = c.get(Calendar.YEAR);
				mes = c.get(Calendar.MONTH);
				dia = c.get(Calendar.DAY_OF_MONTH);
				
				DatePickerDialog dataPicker = new DatePickerDialog(CriarAvaliacaoNext.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int ano, int mes,
							int dia) {
						editData.setText(dia + "/" + (mes+1) + "/" + ano);
						
					}
				}, ano, mes, dia);
				dataPicker.show();
			}
		});
		final ArrayList<Usuario> listaAvaliador2 = new ArrayList<Usuario>();
			
		final ArrayAdapter<Usuario> adapterAvaliador2 = new ArrayAdapter<Usuario>(CriarAvaliacaoNext.this, 
				android.R.layout.simple_list_item_1, listaAvaliador2);
		
		spAvaliador.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				id_click = position;
//				avaliador = listaAvaliador.get(position).getNome().toString();
//				avaliadorId = listaAvaliador.get(position).getId();
				listaAvaliador2.add(listaAvaliador.get(position));
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		Intent it = getIntent();
		final Integer id_tabela = getIntent().getIntExtra("id_tabela", 0);
		final Integer id_projeto = getIntent().getIntExtra("id_projeto", 0);
		final String nome_av = getIntent().getStringExtra("nome_av");
		Log.i("DEBUG", ""+id_projeto);
		Log.i("NOME", "" + nome_av);
		btMais.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//listAvaliador.setAdapter(adapterAvaliador2);
				listaAvaliador.get(id_click);
				listaAvaliador.remove(listaAvaliador.get(id_click));
				listAvaliador.setAdapter(adapterAvaliador2);
			}
		});
		btSalva.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String dataDigitada = editData.getText().toString();
				Date dateFormatada=null;
				
//				Calendar calendario = Calendar.getInstance();
//				calendario.setLenient(false);
//				calendario.setTimeZone(TimeZone.getTimeZone("GMT"));
//				dateFormatada = calendario.getTime();
				
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					dateFormatada = sdf.parse(dataDigitada);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.i("data", "ID USER "+dateFormatada);
			
				for(i=0; i<listaAvaliador2.size(); i++){
					Log.i("DEBUG", "ID USER "+listaAvaliador2.get(i).getId());
					Avaliacao a = new Avaliacao(0, nome_av, listaAvaliador2.get(i).getId(), id_projeto, id_tabela,dateFormatada);
					avaliacaoDAO.inserirAvaliacao(a);
				}
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
		this.spAvaliador = (Spinner) findViewById(R.id.spinner_de_avaliadores);
		this.listAvaliador = (ListView) findViewById(R.id.lista_avaliadores);
		this.btSalva = (Button) findViewById(R.id.bt_avaliacao_next_salva);
		this.avaliacaoDAO = new AvaliacaoDAO();
		this.avaliadorDAO = new UsuarioDAO();
		this.btMais = (Button) findViewById(R.id.bt_avaliacao_next_mais);
		this.editData = (EditText) findViewById(R.id.editData);
		
		
		listaAvaliador = avaliadorDAO.buscarTodosUsuarios();
		if(listaAvaliador != null){
			adapterAvaliador = new ArrayAdapter<Usuario>
			(CriarAvaliacaoNext.this, 
			android.R.layout.simple_list_item_1, 
			listaAvaliador);
			
			spAvaliador.setAdapter(adapterAvaliador);
		}
	}
}
