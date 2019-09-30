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
	
	
	
}
