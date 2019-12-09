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
	
}