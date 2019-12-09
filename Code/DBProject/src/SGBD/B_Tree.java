package SGBD;

public class B_Tree {
	private Noeud racine;
	private int ordre;

	public B_Tree(int ordre) {
		this.ordre = ordre;
		racine = new Feuille(null);
	}

	public void bulkLoad(EntreeDeDonnees edd) {
		if (racine instanceof NoeudInter) {
			Noeud noeud = (NoeudInter) racine;

			do {
				noeud = chercherFils((NoeudInter) noeud, edd.getCle());
			} while (noeud instanceof NoeudInter);


			if (!(noeud instanceof NoeudInter)) {
				rajouterEntree((Feuille) noeud, edd);
				if (((Feuille) noeud).donnees.size() == 2 * ordre + 1) {
					diviser(noeud);
				}
			}

		} else {
			if (((Feuille) racine).donnees.size() < 2 * ordre + 1) {
				rajouterEntree((Feuille) racine, edd);
				if (((Feuille) racine).donnees.size() == 2 * ordre + 1) {
					diviser(racine);
				}
			}
		}
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
		f.getDonnees().add(ed);
	}

	public void rajouterEntree(NoeudInter noeud, EntreeDIndex ei) {
		noeud.getEnfant().add(ei);
	}

	public void diviser(Noeud n) {

	}
}
