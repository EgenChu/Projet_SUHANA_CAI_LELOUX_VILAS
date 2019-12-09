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

	public Noeud chercherFils(NoeudInter noeud, int cle) {
		int min = 0;
		int max = noeud.enfant.size() - 1;
		int milieu = (noeud.enfant.size() / 2);
		int indPot = -1;
		while (milieu >= min && milieu <= max) {
			if (noeud.enfant.get(milieu).getCle() == cle) {
				indPot = milieu;
				break;
			} else if (noeud.enfant.get(milieu).getCle() < cle) {
				indPot = milieu;
				min = milieu + 1;
				milieu = (min + max) / 2;
			} else if (noeud.enfant.get(milieu).getCle() > cle) {
				max = milieu - 1;
				milieu = (min + max + 1) / 2;
			}
		}
		if (indPot == -1)
			return noeud.pointeur;
		else
			return noeud.enfant.get(indPot).getFils();
	}

	public EntreeDeDonnees chercherVal(int cle) {
		Noeud noeud = chercherFils((NoeudInter) racine, cle);
		while (noeud instanceof NoeudInter) {
			noeud = chercherFils((NoeudInter) noeud, cle);
		}
		int min = 0;
		int max = ((Feuille) noeud).donnees.size() - 1;
		int milieu = ((Feuille) noeud).donnees.size() / 2;
		while (milieu >= min && milieu <= max) {
			if (((Feuille) noeud).donnees.get(milieu).getCle() == cle) {
				break;
			} else if (((Feuille) noeud).donnees.get(milieu).getCle() < cle) {
				min = milieu + 1;
				milieu = (min + max) / 2;
			} else if (((Feuille) noeud).donnees.get(milieu).getCle() > cle) {
				max = milieu - 1;
				milieu = (min + max + 1) / 2;
			}
		}
		return ((Feuille) noeud).donnees.get(milieu);
	}

	public void rajouterEntree(Feuille f, EntreeDeDonnees ed) {
		f.getDonnees().add(ed);
	}

	public void rajouterEntree(NoeudInter noeud, EntreeDIndex ei) {
		noeud.getEnfant().add(ei);
	}

	public Noeud diviser(Noeud n) {
		int iMin;
		int iMilieu;
		int iMax;

		if (racine instanceof NoeudInter) {
			if (n instanceof Feuille) {
				iMin = 0;
				iMax = ((Feuille) n).getDonnees().size() - 1;
				iMilieu = (iMin + iMax + 1) / 2;

				Feuille feuille = new Feuille(n.getParent());
				for (int i = iMilieu; i < iMax + 1; i++) {
					rajouterEntree(feuille, ((Feuille) n).getDonnees().get(i));
				}

				for (int i = iMax; i > iMilieu - 1; i--) {
					((Feuille) n).getDonnees().remove(i);
				}

				NoeudInter noeudPar = (NoeudInter) n.getParent();
				rajouterEntree(noeudPar, new EntreeDIndex(feuille.getDonnees().get(0).getCle(), feuille));

				if (noeudPar.getEnfant().size() == 2 * ordre + 1) {
					diviser(noeudPar);
				}

				return feuille;

			} else {
				iMin = 0;
				iMax = ((NoeudInter) n).getEnfant().size() - 1;
				iMilieu = ((iMin + iMax + 1) / 2);

				if (n.getParent() != null) {
					NoeudInter frere = new NoeudInter(n.getParent(),
							((NoeudInter) n).getEnfant().get(iMilieu).getFils());
					rajouterEntree((NoeudInter) n.getParent(),
							new EntreeDIndex(((NoeudInter) n).getEnfant().get(iMilieu).getCle(), frere));

					for (int i = iMilieu + 1; i < iMax + 1; i++) {
						rajouterEntree(frere, ((NoeudInter) n).getEnfant().get(i));
					}

					for (int i = iMax; i > iMilieu - 1; i--) {
						((NoeudInter) n).getEnfant().remove(i);
					}

					if (((NoeudInter) n.getParent()).getEnfant().size() == 2 * ordre + 1) {
						diviser(n.getParent());
					}

					return frere;

				} else {
					NoeudInter frere = new NoeudInter(null, ((NoeudInter) n).getEnfant().get(iMilieu).getFils());
					NoeudInter parent = new NoeudInter(null, n);
					rajouterEntree(parent, new EntreeDIndex(((NoeudInter) n).getEnfant().get(iMilieu).getCle(), frere));

					for (int i = iMilieu + 1; i < iMax + 1; i++) {
						rajouterEntree(frere, ((NoeudInter) n).getEnfant().get(i));
					}

					for (int i = iMax; i > iMilieu - 1; i--) {
						((NoeudInter) n).getEnfant().remove(i);
					}

					frere.setParent(parent);
					n.setParent(parent);

					racine = parent;

					return frere;
				}

			}

		} else {

			iMin = 0;
			iMax = ((Feuille) n).getDonnees().size() - 1;
			iMilieu = (iMin + iMax + 1) / 2;

			Feuille feuille = new Feuille(null);
			for (int i = iMilieu; i < iMax + 1; i++) {
				rajouterEntree(feuille, ((Feuille) n).getDonnees().get(i));
			}

			for (int i = iMax; i > iMilieu - 1; i--) {
				((Feuille) n).getDonnees().remove(i);
			}

			((Feuille) n).setParent(
					racine = new NoeudInter(null, n, new EntreeDIndex(feuille.getDonnees().get(0).getCle(), feuille)));
			feuille.setParent(racine);
			return feuille;
		}
	}

}
