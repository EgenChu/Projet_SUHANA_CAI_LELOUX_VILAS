package SGBD;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class HeapFile {

	private RelDef reldef;

	public HeapFile(RelDef reldef) {
		this.reldef = reldef;
	}

	public RelDef getReldef() {
		return reldef;
	}

	public void createNewOndisk() throws IOException {
		PageId temp;
		if (DiskManager.getInstance().createFile(reldef.getFileIdx())) {

			temp = DiskManager.getInstance().addPage(reldef.getFileIdx());

			ByteBuffer buff = BufferManager.getInstance().getPage(temp);
			for (int i = 0; i < Constants.PAGE_SIZE; i += Integer.BYTES) {
				buff.putInt(i, 0);
			}
			BufferManager.getInstance().freePage(temp, true);
			System.out.println(temp);
		} else
			System.out.println("file already exist");
	}

	public PageId addDataPage() throws IOException {
		ByteBuffer bf;
		int totalpage;

		PageId headerPage = new PageId(reldef.getFileIdx(), 0);
		bf = BufferManager.getInstance().getPage(headerPage);

		totalpage = bf.getInt(0) +1;
		bf.putInt(0, totalpage);
		bf.putInt(totalpage * Integer.BYTES, reldef.getSlotCount());

		BufferManager.getInstance().freePage(headerPage, true);

		return DiskManager.getInstance().addPage(reldef.getFileIdx());
	}

	public PageId getFreeDataPageId() {
		ByteBuffer bf;
		int i = 4;
		int pagelibre = 1, totalpage;

		PageId headerPage = new PageId(reldef.getFileIdx(), 0);

		bf = BufferManager.getInstance().getPage(headerPage);

		totalpage = bf.getInt(0);
		while (bf.getInt(i) == 0 && pagelibre <= totalpage) {
			i += Integer.BYTES;
			pagelibre++;
		}
//		System.out.println(totalpage + "------------" + pagelibre);

		BufferManager.getInstance().freePage(headerPage, false);

		if (pagelibre > totalpage)
			return null;
		else
			return new PageId(reldef.getFileIdx(), pagelibre);
	}

	public Rid writeRecordToDataPage(Record record, PageId pageId) {
		ByteBuffer bf;
		int indiceCaseLibre = 0;
		bf = BufferManager.getInstance().getPage(pageId);

		while (bf.get(indiceCaseLibre) != (byte) 0) {
			indiceCaseLibre++;
		}

		record.writeToBuffer(bf, indiceCaseLibre * reldef.getRecordSize() + reldef.getSlotCount());
		bf.put(indiceCaseLibre, (byte) 1);

		BufferManager.getInstance().freePage(pageId, true);

		PageId headerPage = new PageId(reldef.getFileIdx(), 0);
		bf = BufferManager.getInstance().getPage(headerPage);

		int valeur_pagemodif = bf.getInt(pageId.getPageIdx() * Integer.BYTES) - 1;
		bf.putInt(pageId.getPageIdx() * Integer.BYTES, valeur_pagemodif);

		BufferManager.getInstance().freePage(headerPage, true);

		return new Rid(pageId, indiceCaseLibre);
	}

	public List<Record> getRecordsInDataPage(PageId p) throws IOException {
		List<Record> listrec = new ArrayList<>();
		ByteBuffer buff = BufferManager.getInstance().getPage(p);

		for (int i = 0; i < reldef.getSlotCount(); i++) {
			// System.out.println(buff.get(i) == 1 ? buff.get(i) : "");
			if (buff.get(i) == (byte) 1) {
				Record temp = new Record(this.reldef);
				temp.readFromBuffer(buff, i * reldef.getRecordSize() + reldef.getSlotCount());
				listrec.add(temp);
			}
		}
		BufferManager.getInstance().freePage(p, false);
		return listrec;
	}

	public Rid insertRecord(Record record) throws IOException {
		PageId libre = getFreeDataPageId();

		if (libre != null) {
			return writeRecordToDataPage(record, libre);
		} else {
			libre = addDataPage();
			return writeRecordToDataPage(record, libre);
		}
	}

	public List<Record> getAllRecords() throws IOException {
		List<Record> listrec = new ArrayList<>();

		PageId header = new PageId(reldef.getFileIdx(), 0);
		ByteBuffer buff = BufferManager.getInstance().getPage(header);

		int nbDataPage = buff.getInt(0);

		BufferManager.getInstance().freePage(header, false);

		for (int i = 1; i < nbDataPage + 1; i++) {
			PageId pageCourant = new PageId(reldef.getFileIdx(), i);
			listrec.addAll(getRecordsInDataPage(pageCourant));
		}
		return listrec;
	}

	/*
	 * public void deleteRecordInDataPage(List<Record> listRecords) { int indicePage
	 * = 1; boolean trouve = false;
	 * 
	 * PageId header = new PageId(reldef.getFileIdx(), 0); ByteBuffer bb =
	 * BufferManager.getInstance().getPage(header); int totalePage = bb.getInt(0);
	 * BufferManager.getInstance().freePage(header, false);
	 * 
	 * do { PageId page = new PageId(reldef.getFileIdx(), indicePage);
	 * 
	 * ByteBuffer buff = BufferManager.getInstance().getPage(page);
	 * 
	 * for (int i = 0; i < reldef.getSlotCount(); i++) {
	 * 
	 * if (buff.get(i) == (byte) 1) { Record temp = new Record(this.reldef);
	 * temp.readFromBuffer(buff, i * reldef.getRecordSize() +
	 * reldef.getSlotCount()); if (listRecords.contains(temp)) { buff.put(i, (byte)
	 * 0); trouve = true; }
	 * 
	 * } } BufferManager.getInstance().freePage(page, trouve); indicePage++; trouve
	 * = false; } while (indicePage < totalePage + 1);
	 * 
	 * }
	 */

	public int deleteInHeapFile(int colonne, String valeur) {
		int totalDeletedRecord = 0;

		PageId header = new PageId(reldef.getFileIdx(), 0);
		ByteBuffer bb = BufferManager.getInstance().getPage(header);
		int totalePage = bb.getInt(0);
		BufferManager.getInstance().freePage(header, false);

		for (int i = 1; i < totalePage + 1; i++) {
			PageId page = new PageId(reldef.getFileIdx(), i);
			totalDeletedRecord += deleteInDataPage(page, colonne - 1, valeur);
		}

		return totalDeletedRecord;
	}

	private int deleteInDataPage(PageId page, int colonne, String valeur) {
//		System.out.println(colonne);
		int offset = 0;
		int deletedRecord = 0;
		boolean trouve = false;

		ByteBuffer buff = BufferManager.getInstance().getPage(page);

		for (int i = 0; i < reldef.getSlotCount(); i++) {
			offset = calculPositionColonne(colonne, i);
			if (buff.get(i) == (byte) 1) {
				if (reldef.getList().get(colonne).startsWith("int")) {
					if (buff.getInt(offset) == Integer.parseInt(valeur)) {
						buff.put(i, (byte) 0);
						trouve = true;
						deletedRecord++;
					}
				} else if (reldef.getList().get(colonne).startsWith("float")) {
					if (buff.getFloat(offset) == Float.parseFloat(valeur)) {
						buff.put(i, (byte) 0);
						trouve = true;
						deletedRecord++;
					}
				} else if (reldef.getList().get(colonne).startsWith("string")) {
					String string = reldef.getList().get(colonne);

					StringTokenizer st = new StringTokenizer(string, "string");
					int sizeString = Integer.parseInt(st.nextToken().toString());
					StringBuffer sb = new StringBuffer();

					for (int j = 0; j < sizeString; j++) {
						sb.append(buff.getChar(offset + j * Character.BYTES));
					}

					if (sb.toString().equals(valeur)) {
						buff.put(i, (byte) 0);
						trouve = true;
						deletedRecord++;
					}

				}

			}
		}

		BufferManager.getInstance().freePage(page, trouve);
		return deletedRecord;
	}

	private int calculPositionColonne(int colonne, int indiceRecord) {
		int offset = 0;

		int indice = 0;

		while (indice != colonne) {
			String string = reldef.getList().get(indice);
			if (string.startsWith("int")) {
				offset += Integer.BYTES;

			} else if (string.startsWith("float")) {
				offset += Float.BYTES;

			} else if (string.startsWith("string")) {

				StringTokenizer st = new StringTokenizer(string, "string");
				int sizeString = Integer.parseInt(st.nextToken().toString());

				for (int j = 0; j < sizeString; j++) {
					offset += Character.BYTES;
				}
			}
			indice++;
		}

		return reldef.getSlotCount() + reldef.getRecordSize() * indiceRecord + offset;
	}

	private List<Rid> exportRidInDataPage(PageId page, int colonne, int valeur) {
		int offset = 0;
		List<Rid> rids = new ArrayList<>();

		ByteBuffer buff = BufferManager.getInstance().getPage(page);

		for (int i = 0; i < reldef.getSlotCount(); i++) {
			offset = calculPositionColonne(colonne, i);
			if (buff.get(i) == (byte) 1) {
				if (reldef.getList().get(colonne).startsWith("int")) {
					if (buff.getInt(offset) == valeur) {
						rids.add(new Rid(page, i));
					}

				}

			}
		}

		BufferManager.getInstance().freePage(page, false);

		return rids;
	}

	private List<Rid> exportsRidInHeapFile(int colonne, int valeur) {
		List<Rid> allRids = new ArrayList<>();

		PageId header = new PageId(reldef.getFileIdx(), 0);
		ByteBuffer bb = BufferManager.getInstance().getPage(header);
		int totalePage = bb.getInt(0);
		BufferManager.getInstance().freePage(header, false);

		for (int i = 1; i < totalePage + 1; i++) {
			PageId page = new PageId(reldef.getFileIdx(), i);
			allRids.addAll(exportRidInDataPage(page, colonne, valeur));
		}

		return allRids;
	}

	public Map<Integer,List<Rid>> exportRid(String relname, int colx, String valeur) throws IOException{
		Map<Integer,List<Rid>> values = new HashMap<>();
		List<Record> allrecord = getAllRecords();
		List<Integer> valuesInt = new ArrayList<>();
		
		for(Record record : allrecord) {
			if(!valuesInt.contains(Integer.parseInt(record.getValues().get(colx))))
				valuesInt.add(Integer.parseInt(record.getValues().get(colx)));
		}
		
		for(Integer integer : valuesInt) {
			values.put(integer, exportsRidInHeapFile(colx,Integer.parseInt(valeur)));
		}
		
		return values;
	}
}
