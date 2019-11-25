package SGBD;

import java.util.ArrayList;
import java.util.List;

public class NoeudInter extends Noeud{
	List<Noeud> enfant;

	public NoeudInter() {
		super();
		enfant = new ArrayList<>();
	}

}

