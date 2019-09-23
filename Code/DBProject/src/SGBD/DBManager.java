package SGBD;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DBManager {
	
	private DBManager() {
		
	}
	
	private static DBManager dbManager = new DBManager();
	
	public static DBManager getInstance() {
		return dbManager ;
	}
	
	public void init() {
		DBDef.init();
	}
	
	public void finish() {
		DBDef.finish();
	}
	
	public void processCommand(String commande) {
		StringTokenizer st = new StringTokenizer(commande);
		
		String command = st.nextToken();
		String relname = st.nextToken();
		int numcol = Integer.parseInt(st.nextToken());
		ArrayList list = new ArrayList<String>();
		
		while(st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		
		creatRelation(relname,numcol,list);
		
		
	}
	
	public void creatRelation(String relname, int numcol, ArrayList<String> list) {
		RelDef reldef = new RelDef(relname, numcol, list);
		DBDef.getInstance().addRelation(reldef);
	}
	
	
}
