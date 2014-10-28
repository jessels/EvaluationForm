package br.com.evaluationform;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import br.com.evaluationform.abas.AbaEventos;
import br.com.evaluationform.abas.AbaMinhasAvaliacoes;
import br.com.evaluationform.abas.AbaProjetos;
import br.com.evaluationform.abas.AbaTabelaAvaliativa;

public class TelaPrincipal extends FragmentActivity  {
	
	
	
	private ActionBar actionBar;

	
	
	//private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_principal);
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		
		Tab tab1 = actionBar.newTab();
		tab1.setText("Eventos");
		tab1.setTabListener(new Navegar(new AbaEventos()));
		actionBar.addTab(tab1, false);
		
		Tab tab2 = actionBar.newTab();
		tab2.setText("Projetos");
		tab2.setTabListener(new Navegar(new AbaProjetos()));
		actionBar.addTab(tab2, false);
		
		Tab tab3 = actionBar.newTab();
		tab3.setText("Tabelas");
		tab3.setTabListener(new Navegar(new AbaTabelaAvaliativa()));
		actionBar.addTab(tab3, false);
		
		Tab tab4 = actionBar.newTab();
		tab4.setText("Minhas Avaliações");
		tab4.setTabListener(new Navegar(new AbaMinhasAvaliacoes()));
		actionBar.addTab(tab4, false);
		
		if(savedInstanceState != null){
			int indiceTab = savedInstanceState.getInt("indiceTab");
			getActionBar().setSelectedNavigationItem(indiceTab);
		}
		else{
			getActionBar().setSelectedNavigationItem(0);
		}
        
	}
	private class Navegar implements ActionBar.TabListener{
		private Fragment frag;
		
		public Navegar(Fragment frag){
			this.frag = frag;
			
		}
		

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
		fts.replace(R.id.abasSelec, frag);
		fts.commit();

	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
		fts.commit();

	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
		fts.replace(R.id.abasSelec, frag);
		fts.commit();

	}
	
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("indiceTab", getActionBar().getSelectedNavigationIndex());
	}

}