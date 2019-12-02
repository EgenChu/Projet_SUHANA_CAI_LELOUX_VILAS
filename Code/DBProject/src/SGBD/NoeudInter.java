package SGBD;

import java.util.ArrayList;
import java.util.List;

public class NoeudInter extends Noeud{
	List<EntreeDIndex> enfant;
	Noeud pointeur;

	public NoeudInter() {
		super();
		enfant = new ArrayList<>();
	}

	public List<EntreeDIndex> getEnfant() {
		return enfant;
	}

}

