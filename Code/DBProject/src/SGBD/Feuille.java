package SGBD;

import java.util.ArrayList;
import java.util.List;

public class Feuille extends Noeud{
	List<EntreeDeDonnees> donnees;

	public Feuille(Noeud parent){
		super(parent);
		donnees = new ArrayList<>();
	}

	public List<EntreeDeDonnees> getDonnees() {
		return donnees;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < donnees.size(); i++) {
			sb.append(donnees.get(i).getCle()).append(" : ");
		}
		
		return sb.toString();
	}
	
}