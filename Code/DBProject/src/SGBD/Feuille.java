package SGBD;

import java.util.ArrayList;
import java.util.List;

public class Feuille extends Noeud{
	List<EntreeDeDonnees> donnees;

	public Feuille(){
		super();
		donnees = new ArrayList<>();
	}
	
}