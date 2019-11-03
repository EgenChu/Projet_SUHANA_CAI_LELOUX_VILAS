package SGBD;

import java.io.IOException;
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

	public void init() throws IOException {
		DBDef.getInstance().init();
	}

	public void finish() throws IOException {
		BufferManager.getInstance().flushAll();
		DBDef.getInstance().finish();
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
				recordSize+= Character.BYTES*Integer.parseInt(string.split("string")[1]);
			}

		}
		
		int slotCount = Constants.PAGE_SIZE/(recordSize +1);
		RelDef reldef = new RelDef(relname, numcol, list, DBDef.getInstance().compteur, recordSize, slotCount);
		
		DBDef.getInstance().addRelation(reldef);
	}


}
