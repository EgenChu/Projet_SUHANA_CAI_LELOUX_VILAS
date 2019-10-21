package SGBD;

public class Rid {
	private PageId pageId;
	private int slotIdx;
	
	public Rid(PageId pageId, int slotIdx) {
		this.pageId = pageId;
		this.slotIdx = slotIdx;
	}
	
	public PageId getPageId() {
		return pageId;
	}
	
	public int getSlotIdx() {
		return slotIdx;
	}
}
