package SGBD;

public class PageId {
	private int fileIdx;
	private int pageIdx;
	
	public PageId(int fileIdx,int pageIdx) {
		this.setFileIdx(fileIdx) ;
		this.setPageIdx(pageIdx);
	}

	public int getFileIdx() {
		return fileIdx;
	}

	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}

	public int getPageIdx() {
		return pageIdx;
	}

	public void setPageIdx(int pageIdx) {
		this.pageIdx = pageIdx;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("pageId : ");
		sb.append(getPageIdx());
		sb.append(" fileId: ");
		sb.append(getFileIdx()).append(" ");
		
		return sb.toString();
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageId other = (PageId) obj;
		if (fileIdx != other.fileIdx)
			return false;
		if (pageIdx != other.pageIdx)
			return false;
		return true;
	}
}
