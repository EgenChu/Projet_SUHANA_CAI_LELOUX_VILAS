package SGBD;

import java.util.List;

public class EntreeDeDonnees {
	private int cle;
	private List<Rid> rid;
	
	public EntreeDeDonnees(int cle ,List<Rid> rid) {
		this.cle = cle;
		this.rid =rid;	
	}
	
	public int getCle() {
		return cle;
	}
}
