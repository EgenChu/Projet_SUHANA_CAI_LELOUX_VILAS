package SGBD;

import java.util.ArrayList;
import java.util.List;

public class Feuille extends Noeud{
	protected List<List<Rid>> rids;

	public Feuille(){
		super();
		rids = new ArrayList<>();
	}
	
	public List<Rid> trouver(int val){
		return rids.get(findIndice(val));
	}

	public List<List<Rid>> getRids() {
		return rids;
	}
	
}