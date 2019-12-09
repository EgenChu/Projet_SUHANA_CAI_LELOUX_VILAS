package SGBD;

import java.util.ArrayList;
import java.util.List;

public class NoeudInter extends Noeud{
	List<EntreeDIndex> enfant;
	Noeud pointeur;

	public NoeudInter(Noeud parent, Noeud noeudAgauche,EntreeDIndex edi) {
		super(parent);
		enfant = new ArrayList<>();
		this.pointeur = noeudAgauche;
		enfant.add(edi);
	}
	
	public NoeudInter(Noeud parent, Noeud noeudAgauche) {
		super(parent);
		this.pointeur = noeudAgauche;
		enfant = new ArrayList<>();
	}

	public List<EntreeDIndex> getEnfant() {
		return enfant;
	}
	
	public Noeud getPointeur() {
		return pointeur;
	}

}

