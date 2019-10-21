package SGBD;

public class RId {
	private PageId pageId;
	private int slotIdx;
	
	public RId(PageId pageId, int slotIdx) {
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
