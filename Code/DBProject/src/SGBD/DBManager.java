package SGBD;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
		FileManager.getInstance().init();
	}

	public void finish() throws IOException {
		BufferManager.getInstance().flushAll();
		DBDef.getInstance().finish();
	}

	public void processCommand(String commande) throws IOException {
		StringTokenizer st = new StringTokenizer(commande);
		String nomCommand = st.nextToken();
		String relname;
		
		switch(nomCommand) {
			case "create":
				relname = st.nextToken();
				int numcol = Integer.parseInt(st.nextToken());
				List<String> list = new ArrayList<String>();

				while(st.hasMoreTokens()) {
					list.add(st.nextToken());
				}

				creatRelation(relname,numcol,list);
				break;
			
			case "insert": 
				relname = st.nextToken();
				List<String> listValues = new ArrayList<String>();
				while(st.hasMoreTokens()) {
					listValues.add(st.nextToken());
				}
				FileManager.getInstance().insert(relname, listValues);
				break;
			case "clean":
				clean();
				break;
			case "selectall":
				relname = st.nextToken();
				if (st.hasMoreTokens()) {
					select(relname,Integer.parseInt(st.nextToken()),st.nextToken());
				}
				else {
					selectAll(relname);
				}
				break;
					
			
		}

		


	}

	public void creatRelation(String relname, int numcol, List<String> list) throws IOException {
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
		RelDef reldef = new RelDef(relname, numcol, list, DBDef.getInstance().getCompteur(), recordSize, slotCount);
		
		DBDef.getInstance().addRelation(reldef);
		FileManager.getInstance().CreateRelationFile(reldef);
	}
	
	private void clean() {
		File fichier = new File(Constants.PATH+"/Catalogue.def");
		fichier.delete();
		boolean test= true;
		int i = 0;
		while(test){
		File fichier2 = new File(Constants.PATH+"/Data_"+i+".rf");
		test = fichier2.delete();
		i++;
		}
		BufferManager.getInstance().reset();;
		DBDef.getInstance().reset();
		FileManager.getInstance().reset();
	}
	
	private void selectAll(String relName) throws IOException {
		List<Record> list = FileManager.getInstance().selectAllFromRelation(relName);
		for(int i = 0 ; i < list.size();i++) {
		System.out.println(list.get(i));
		}
		System.out.println("Total records = "+list.size());
		
	}
	
	public void select(String relName, int idxCol, String valeur) throws IOException {
		List<Record> list = FileManager.getInstance().selectFromRelation(relName, idxCol, valeur);
		for(int i = 0 ; i < list.size();i++) {
		System.out.println(list.get(i));
		}
		System.out.println("Total records = "+list.size());
	}
	
	public void delete(String relName, int idxCol, String valeur) throws IOException {
		List<Record> list = FileManager.getInstance().selectFromRelation(relName, idxCol, valeur);
		int totalDel = list.size();
		for(int i = 0 ; i < list.size();i++) {
		System.out.println(list.get(i));
		}
		System.out.println("Total records = "+ totalDel);
	}

}
