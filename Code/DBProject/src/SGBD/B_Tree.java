package SGBD;

import java.util.List;

public class B_Tree {
	private Noeud racine;
	private int ordre;

	public B_Tree(int ordre) {
		this.ordre = ordre;
		racine = new Feuille();
	}

	public void addNoeud(int valeur, List<Rid> rids) {
		//s'il y a de la place dans la feuille
		if (racine.getValeurs().size() < 2 * ordre) {
			racine.getValeurs().add(valeur);
			if (racine instanceof Feuille) {
				((Feuille) racine).getRids().add(rids);
			} 
		//On ajout et on refait remonter le noeud médian
		}else {
			racine.getValeurs().add(valeur);
			int min = 0, max = racine.getValeurs().size() - 1;
			int mediane = racine.getValeurs().get(((min + max)/2));
			
			//this.racine = new NoeudInter()
			
			
			if (racine instanceof Feuille) {
				((Feuille) racine).getRids().add(rids);
			} 
			NoeudInter pere = new NoeudInter();
			pere.getValeurs().add(racine.getValeurs().get(racine.getValeurs().size()/2));
			int i =0;
			while(racine.getValeurs().get(i)!=pere.getValeurs().get(pere.getValeurs().size()-1));
				pere.getEnfant().add(racine.getValeurs().get(i));

		}

	}

}
