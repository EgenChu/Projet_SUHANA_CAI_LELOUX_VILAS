package SGBD;

import java.util.ArrayList;
import java.util.List;

public class Noeud {
	protected List<Integer> valeurs;
	private Noeud racine;
	private int ordre;

	public List<Integer> getValeurs() {
		return valeurs;
	}

	public Noeud() {
		valeurs = new ArrayList<>();
	}

	public int findIndice(int valeur) {
		int res=-1;
		int min =0;
		int max = valeurs.size()-1;
		int milieu = (valeurs.size()/2);
		while(milieu>=min && milieu <=max) {
			int indPot = milieu;
			if (valeur<valeurs.get(milieu)) {
				max= milieu-1;
				milieu = (min+max)/2;
			}
			else if(valeur==valeurs.get(milieu)) {
				res = milieu;
				break;
			} else {
				min = milieu + 1;
				milieu = (min + max) / 2;
			}
			else {
				min = indPot;
				milieu = (min+max)/2;
			}
		}
		return res;
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
