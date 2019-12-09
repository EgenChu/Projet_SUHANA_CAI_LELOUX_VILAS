package SGBD;

public class EntreeDIndex {
	private int cle;
	private Noeud fils;
	
	public EntreeDIndex(int cle, Noeud fils) {
		this.cle = cle;
		this.fils = fils;
	}

	public Noeud getFils() {
		return fils;
	}

	public int getCle() {
		return cle;
	}
	
}
