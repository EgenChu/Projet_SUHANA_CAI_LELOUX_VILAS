package SGBD;

import java.util.ArrayList;
import java.util.List;

public class Noeud {
	protected List<Integer> valeurs;
	private Noeud parent;


	public Noeud(Noeud parent) {
		valeurs = new ArrayList<>();
		this.parent = parent;
	}

	public Noeud getParent() {
		return parent;
	}
	
	public void setParent(Noeud parent) {
		this.parent = parent;
	}

//	
//	public void addNoeud(int valeur, List<Rid> rids) {
//		//s'il y a de la place dans la feuille
//		if (racine.getValeurs().size() < 2 * ordre) {
//			racine.getValeurs().add(valeur);
//			if (racine instanceof Feuille) {
//				((Feuille) racine).getRids().add(rids);
//			} 
//		//On ajout et on refait remonter le noeud médian
//		}else {
//			racine.getValeurs().add(valeur);
//			int min = 0, max = racine.getValeurs().size() - 1;
//			int mediane = racine.getValeurs().get(((min + max)/2));
//			
//			//this.racine = new NoeudInter()
//			
//			
//			if (racine instanceof Feuille) {
//				((Feuille) racine).getRids().add(rids);
//			} 
//			NoeudInter pere = new NoeudInter();
//			pere.getValeurs().add(racine.getValeurs().get(racine.getValeurs().size()/2));
//			int i =0;
//			while(racine.getValeurs().get(i)!=pere.getValeurs().get(pere.getValeurs().size()-1));
//				pere.getEnfant().add(racine.getValeurs().get(i));
//		}
//		}

	
}
