package SGBD;

import java.util.ArrayList;
import java.util.List;

public class Feuille extends Noeud{
	protected List<EntreeDeDonnees> donnees;

	public Feuille(Noeud parent){
		super(parent);
		donnees = new ArrayList<>();
	}
	
	/*
	 * public EntreeDeDonnees trouver(int val){ return donnees.get(findIndice(val));
	 * }
	 */

	public List<EntreeDeDonnees> getDonnees() {
		return donnees;
	}
	
}