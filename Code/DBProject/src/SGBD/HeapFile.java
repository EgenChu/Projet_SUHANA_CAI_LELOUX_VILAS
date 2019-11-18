package SGBD;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

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

		totalpage = bf.getInt(0) + 1;
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
			//System.out.println(buff.get(i) == 1 ? buff.get(i) : "");
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
		List<Record> listPerPage = null;

		PageId header = new PageId(reldef.getFileIdx(), 0);
		ByteBuffer buff = BufferManager.getInstance().getPage(header);

		int nbDataPage = buff.getInt(0);

		BufferManager.getInstance().freePage(header, false);

		for (int i = 1; i < nbDataPage + 1; i++) {
			PageId pageCourant = new PageId(reldef.getFileIdx(), i);
			if(listPerPage != null) listPerPage.clear();
			listPerPage = getRecordsInDataPage(pageCourant);

			for (int j = 0; j < listPerPage.size(); j++) {
				listrec.add(listPerPage.get(j));
			}
		}
		return listrec;
	}

}
