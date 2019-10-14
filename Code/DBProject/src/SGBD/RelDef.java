package SGBD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
