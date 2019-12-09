package SGBD;

public class Noeud {
	private Noeud parent;


	public Noeud(Noeud parent) {
		this.parent = parent;
	}

	public Noeud getParent() {
		return parent;
	}
	
	public void setParent(Noeud parent) {
		this.parent = parent;
	}

	
}