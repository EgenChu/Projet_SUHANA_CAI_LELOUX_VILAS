package SGBD;

import java.util.List;

public class B_Tree {
	private Noeud racine;
	private int ordre;

	public B_Tree(int ordre) {
		this.ordre = ordre;
		racine = new Feuille();
	}

	public void bulkLoad() {

	}

	
	public Noeud chercherFils(NoeudInter noeud , int cle) {
		int min =0;
		int max = noeud.enfant.size()-1;
		int milieu = (noeud.enfant.size()/2);
		int indPot = -1;
		while(milieu>=min && milieu <=max) {
			if(noeud.enfant.get(milieu).getCle()==cle) {
				indPot = milieu;
				break;
			}
			else if(noeud.enfant.get(milieu).getCle() < cle) {
				indPot = milieu;
				min = milieu+1;
				milieu = (min+max)/2;	
			}
			else if(noeud.enfant.get(milieu).getCle() > cle) {
				max= milieu-1;
				milieu = (min+max+1)/2;
			}	
		}
		if (indPot == -1)
			return noeud.pointeur;
		else
			return noeud.enfant.get(indPot).getFils();
	}
	
	
	
	public EntreeDeDonnees chercherVal(int cle) {
		Noeud noeud = chercherFils((NoeudInter)racine , cle);
		while (noeud instanceof NoeudInter) {
			noeud = chercherFils((NoeudInter)noeud , cle);
		}
		int min =0;
		int max = ((Feuille)noeud).donnees.size()-1;
		int milieu = ((Feuille)noeud).donnees.size()/2;
		while(milieu>=min && milieu <=max) {
			if(((Feuille)noeud).donnees.get(milieu).getCle()==cle) {
				break;
			}
			else if(((Feuille)noeud).donnees.get(milieu).getCle() < cle) {
				min = milieu+1;
				milieu = (min+max)/2;	
			}
			else if(((Feuille)noeud).donnees.get(milieu).getCle() > cle) {
				max= milieu-1;
				milieu = (min+max+1)/2;
			}	
		}
			return ((Feuille)noeud).donnees.get(milieu);
	}
		
	

	public void rajouterEntree(Feuille f, EntreeDeDonnees ed) {

	}

	public void rajouterEntree(NoeudInter noeud, EntreeDIndex ei) {

	}

	public void diviser(Noeud n) {

	}


}
