package SGBD;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class FileManager {

	private List<HeapFile> heapFiles;

	private FileManager() {
		heapFiles = new ArrayList<>();
	}

	private static FileManager fileManager = null;

	public static FileManager getInstance() {
		if (fileManager == null)
			fileManager = new FileManager();
		return fileManager;
	}

	public void init() {
		for (int i = 0; i < DBDef.getInstance().getListRel().size(); i++) {
			heapFiles.add(new HeapFile(DBDef.getInstance().getListRel().get(i)));
		}
	}

	public void CreateRelationFile(RelDef relDef) throws IOException {
		HeapFile hF = new HeapFile(relDef);
		heapFiles.add(hF);
		hF.createNewOndisk();
	}

	public Rid insertRecordInRelation(Record record, String relName) throws IOException {
		Rid rid = null;
		for (int i = 0; i < heapFiles.size(); i++) {
			if (heapFiles.get(i).getReldef().getRelname().equals(relName)) {
				rid = heapFiles.get(i).insertRecord(record);
				break;
			}
		}
		return rid;
	}

	public List<Record> selectAllFromRelation(String relName) throws IOException {
		List<Record> list = null;
		for (int i = 0; i < heapFiles.size(); i++) {
			if (heapFiles.get(i).getReldef().getRelname().equals(relName))
				list = heapFiles.get(i).getAllRecords();
		}
		return list;
	}

	public List<Record> selectFromRelation(String relName, int idxCol, String valeur) throws IOException {
		List<Record> recordsOnHeapFile = null;
		List<Record> list2 = new ArrayList<Record>();

		for (int i = 0; i < heapFiles.size(); i++) {
			if (heapFiles.get(i).getReldef().getRelname().equals(relName))
				recordsOnHeapFile = heapFiles.get(i).getAllRecords();
		}

		for (int i = 0; i < recordsOnHeapFile.size(); i++) {
			if (recordsOnHeapFile.get(i).getValues().get(idxCol - 1).equals(valeur))
				list2.add(recordsOnHeapFile.get(i));
		}
		return list2;
	}

	public void reset() {
		heapFiles.clear();
	}

	public List<HeapFile> getHeapFiles() {
		return heapFiles;
	}

	/*
	 * public int deleteFromRelation(String relName, int idxCol, String valeur)
	 * throws IOException { List<Record> recordsOnHeapFile = null; List<Record>
	 * list2 = new ArrayList<Record>(); HeapFile hp = null;
	 * 
	 * for (int i = 0; i < heapFiles.size(); i++) { if
	 * (heapFiles.get(i).getReldef().getRelname().equals(relName)) { hp =
	 * heapFiles.get(i); recordsOnHeapFile = heapFiles.get(i).getAllRecords(); } }
	 * 
	 * for (int i = 0; i < recordsOnHeapFile.size(); i++) { if
	 * (recordsOnHeapFile.get(i).getValues().get(idxCol - 1).equals(valeur))
	 * list2.add(recordsOnHeapFile.get(i)); }
	 * 
	 * if (hp != null) { hp.deleteRecordInDataPage(list2); }
	 * 
	 * return list2.size(); }
	 */

	public int deletedFromRelation(String relname, int idxCol, String valeur) {
		HeapFile current = null;
		int i = 0;
		int deletedRecord = 0;

		while (current == null && i < heapFiles.size()) {
			if (heapFiles.get(i).getReldef().getRelname().equals(relname)) {
				current = heapFiles.get(i);
				deletedRecord += current.deleteInHeapFile(idxCol, valeur);
			}
			i++;
		}
		return deletedRecord;
	}

	public void createTree(String relname, int colx, int ordre) {
		TreeMap<Integer, List<Rid>> table;

		for (int i = 0; i < heapFiles.size(); i++) {
			if (heapFiles.get(i).getReldef().getRelname().equals(relname)) {

				if (heapFiles.get(i).getReldef().getList().get(colx - 1).equals("int")) {

					try {
						table = new TreeMap<>(heapFiles.get(i).exportRid(relname, colx));
						B_Tree tree = new B_Tree(ordre);
						int indiceOrdre = 0;
						Feuille feuille =  new Feuille(null);

						/*
						 * for (Integer integer : table.keySet()) { tree.insertionEdd(new
						 * EntreeDeDonnees(integer, table.get(integer))); }
						 */
						
						for (Integer integer : table.keySet()) {
							if(indiceOrdre == 2 * tree.getOrdre()) {
								tree.bulkloading(feuille);
								indiceOrdre = 0;
								feuille =  new Feuille(null);
								
							}
							tree.rajouterEntree(feuille, new EntreeDeDonnees(integer, table.get(integer)));
							indiceOrdre++;
							//tree.insertionEdd(new EntreeDeDonnees(integer, table.get(integer)));
						}
						tree.bulkloading(feuille);

						heapFiles.get(i).getIndex().put(colx, tree);

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}

	}

	public int selectindex(String relname, int colx, int valeur) {
		EntreeDeDonnees edd = null;
		HeapFile hp = null;
		int total = 0;
		
		try {
			int i = 0;
			
			do {
				if (heapFiles.get(i).getReldef().getRelname().equals(relname)) {
					hp = heapFiles.get(i);
				}
				i++;
			} while (i < heapFiles.size());
			
			if(hp != null) {
				edd = hp.getIndex().get(colx).chercherVal(valeur);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("L'entrée d'index recherché n'existe pas dans l'abre donnée");
		}catch(NullPointerException e){
			System.out.println("Il n'existe pas d'arbre référencé pour cette colonne");
		}

		finally {
			if (edd != null) {
				for (Rid rid : edd.getRid()) {
					total++;
					System.out.println(hp.ridToRecord(rid));
				}
			}
		}
		return total;
	}

	public int join(String relname1, String relname2, int numCol1, int numCol2) throws IOException {
		int nbreTupleJoin = 0;
		HeapFile current1 = null;
		HeapFile current2 = null;
		int i = 0;

		while (i < heapFiles.size()) {
			if (heapFiles.get(i).getReldef().getRelname().equals(relname1)) {
				current1 = heapFiles.get(i);
			}
			if (heapFiles.get(i).getReldef().getRelname().equals(relname2)) {
				current2 = heapFiles.get(i);
			}
			i++;
		}

		PageId headerCurrent1 = new PageId(current1.getReldef().getFileIdx(), 0);
		PageId headerCurrent2 = new PageId(current2.getReldef().getFileIdx(), 0);

		ByteBuffer buff = BufferManager.getInstance().getPage(headerCurrent1);
		int nbPagecurrent1 = buff.getInt(0);
		BufferManager.getInstance().freePage(headerCurrent1, false);

		buff = BufferManager.getInstance().getPage(headerCurrent2);
		int nbPagecurrent2 = buff.getInt(0);
		BufferManager.getInstance().freePage(headerCurrent2, false);

		for (int j = 1; j < nbPagecurrent1 + 1; j++) {
			List<Record> records1 = current1.getRecordsInDataPage(new PageId(current1.getReldef().getFileIdx(), j));
			for (int k = 1; k < nbPagecurrent2 + 1; k++) {
				List<Record> records2 = current2.getRecordsInDataPage(new PageId(current2.getReldef().getFileIdx(), k));

				for (int l = 0; l < records1.size(); l++) {
					for (int m = 0; m < records2.size(); m++) {
						if (records1.get(l).getValues().get(numCol1 - 1)
								.equals(records2.get(m).getValues().get(numCol2 - 1))) {
							System.out.print(records1.get(l));
							System.out.print(" ; " + records2.get(m) + "\n");
							nbreTupleJoin++;
						}
					}
				}

			}
		}

		return nbreTupleJoin;
	}

}
