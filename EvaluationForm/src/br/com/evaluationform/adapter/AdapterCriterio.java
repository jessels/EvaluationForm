package br.com.evaluationform.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View rootView = LayoutInflater.from(context).inflate(R.layout.adapter_nota, parent, false);
		
		TextView tvDesc = (TextView) rootView.findViewById(R.id.tx_adap_desc);
		TextView tvPeso = (TextView) rootView.findViewById(R.id.tx_adap_peso);
		EditText edNota1 = (EditText) rootView.findViewById(R.id.ed_adap_nota1);
		EditText edNota2 = (EditText) rootView.findViewById(R.id.ed_adap_nota2);
		
		Criterio crite = criterio.get(position);
		
		tvDesc.setText(crite.getDescricao());
		tvPeso.setText(String.valueOf(crite.getPeso()));
		edNota1.setText("");
		edNota2.setText("");
		return rootView;
	}

}
