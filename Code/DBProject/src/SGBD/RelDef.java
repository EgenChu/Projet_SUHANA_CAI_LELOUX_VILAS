package SGBD;

import java.util.ArrayList;
import java.util.List;

public class RelDef {
	
	String relname;
	int numcol;
	List <String> list;


	public RelDef(String relname, int numcol, ArrayList<String> list) {
		this.relname = relname;
		this.numcol = numcol;
		this.list = list ;
	}
	
	public int getNumcol() {
		return numcol;
	}
	
	public List<String> getList() {
		return list;
	}
	
}
