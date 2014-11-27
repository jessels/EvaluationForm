package br.com.evaluationform.adapter;

import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import br.com.evaluationform.R;
import br.com.evaluationform.dao.Criterio;

public class AdapterCriterio extends BaseAdapter{
	
	private List<Criterio> criterio;
	private Context context;
	
	int position;

	public AdapterCriterio (Context context, List<Criterio> crit){
		this.criterio = crit;
		this.context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return criterio.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return criterio.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return criterio.get(position).getId_criterio();
	}
	
	public List<Criterio> getList(){
		return criterio;
	}
	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View rootView = LayoutInflater.from(context).inflate(R.layout.adapter_nota, parent, false);
		
		TextView tvDesc = (TextView) rootView.findViewById(R.id.tx_adap_desc);
		TextView tvPeso = (TextView) rootView.findViewById(R.id.tx_adap_peso);
		final EditText edNota1 = (EditText) rootView.findViewById(R.id.ed_adap_nota1);
		final EditText edNota2 = (EditText) rootView.findViewById(R.id.ed_adap_nota2);
		
		
		edNota1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				
				double nota1  = 0;
				try{
					nota1 = Double.parseDouble(edNota1.getText().toString());
					criterio.get(position).getNota().setNota_aluno1(nota1);
					
				}catch(NumberFormatException e){
					//Toast.makeText(context, "Erro, digite apenas números", Toast.LENGTH_LONG).show();
					//edNota1.setText("");
				}
			}
		});
		
		edNota2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				
				double nota2  = 0;
				try{
					nota2 = Double.parseDouble(edNota2.getText().toString());
					criterio.get(position).getNota().setNota_aluno2(nota2);
					
				}catch(NumberFormatException e){
					//Toast.makeText(context, "Erro, digite apenas números", Toast.LENGTH_LONG).show();
					//edNota2.setText("");
				}
			}
		});
		
		Criterio crite = criterio.get(position);
		
		tvDesc.setText(crite.getDescricao());
		tvPeso.setText(String.valueOf(crite.getPeso()));
		edNota1.setText("");
		edNota2.setText("");
		return rootView;
	}

}
