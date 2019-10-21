package SGBD;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DBManager {

	private DBManager() {

	}

	private static DBManager dbManager = null;

	public static DBManager getInstance() {
		if (dbManager == null) dbManager = new DBManager();
		return dbManager ;
	}

	public void init() {
		DBDef.init();
	}

	public void finish() {
		BufferManager.getInstance().flushAll();
		DBDef.finish();
	}

	public void processCommand(String commande) {
		StringTokenizer st = new StringTokenizer(commande);

		@SuppressWarnings("unused")
		String command = st.nextToken();

		String relname = st.nextToken();
		int numcol = Integer.parseInt(st.nextToken());
		ArrayList<String> list = new ArrayList<String>();

		while(st.hasMoreTokens()) {
			list.add(st.nextToken());
		}

		creatRelation(relname,numcol,list);


	}

	public void creatRelation(String relname, int numcol, ArrayList<String> list) {
		int recordSize = 0;
		for (int i = 0; i < numcol; i++) {
			String string = list.get(i);
			if (string.startsWith("int")) {
				recordSize += Integer.BYTES;
			} else if (string.startsWith("float")) {
				recordSize += Float.BYTES;
			} else if (string.startsWith("string")) {
				StringTokenizer st = new StringTokenizer(string, "string");
				recordSize+= 2*Integer.parseInt(st.nextToken());
			}

		}
		int slotCount = Constants.PAGE_SIZE/(recordSize +1);
		RelDef reldef = new RelDef(relname, numcol, list, DBDef.getInstance().compteur, recordSize, slotCount);
		DBDef.getInstance().addRelation(reldef);
	}


}
