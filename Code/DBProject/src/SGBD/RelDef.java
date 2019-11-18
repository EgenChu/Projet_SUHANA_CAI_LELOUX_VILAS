package SGBD;

import java.util.List;


public class RelDef {

	private String relname;
	private int numcol;
	private List <String> list;
	private int fileIdx;
	private int recordSize;
	private int slotCount;


	public RelDef(String relname, int numcol, List<String> list,int fileIdx, int recordSize, int slotCount) {
		this.relname = relname;
		this.numcol = numcol;
		this.list = list ;
		this.fileIdx = fileIdx;
		this.recordSize = recordSize;
		this.slotCount = slotCount;
	}

	public List<String> getList() {
		return this.list;
	}

	public int getNumcol() {
		return this.numcol;
	}

	public int getSlotCount() {
		return slotCount;
	}	

	@Override
	public String toString() {
		return relname+";"+numcol+";"+list+";"+fileIdx+";"+recordSize+";"+slotCount;
	}

	public int getFileIdx() {
		return fileIdx;
	}
	
	public int getRecordSize() {
		return recordSize;
	}
	
	public String getRelname() {
		return relname;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelDef other = (RelDef) obj;
		if (fileIdx != other.fileIdx)
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (numcol != other.numcol)
			return false;
		if (recordSize != other.recordSize)
			return false;
		if (relname == null) {
			if (other.relname != null)
				return false;
		} else if (!relname.equals(other.relname))
			return false;
		if (slotCount != other.slotCount)
			return false;
		return true;
	}
	
	
	
}
