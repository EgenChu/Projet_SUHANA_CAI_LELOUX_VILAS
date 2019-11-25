package SGBD;

import java.util.ArrayList;
import java.util.List;

public abstract class Noeud {
	protected List<Integer> valeurs;


	public Noeud() {
		valeurs = new ArrayList<>();
	}

	public int findIndice(int valeur) {
		int res=-1;
		int min =0;
		int max = valeurs.size()-1;
		int milieu = (valeurs.size()/2);
		while(milieu>=min && milieu <=max) {
			if (valeur<valeurs.get(milieu)) {
				max= milieu-1;
				milieu = (min+max)/2;
			}
			else if(valeur==valeurs.get(milieu)) {
				res = milieu;
				break;
			}
			else {
				min = milieu+1;
				milieu = (min+max)/2;
			}
		}
		return res;
	}

}
