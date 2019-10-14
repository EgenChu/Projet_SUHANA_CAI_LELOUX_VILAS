package SGBD;

import java.nio.ByteBuffer;

public class Frame {
	private ByteBuffer buffer;
	private PageId pageId;
	private int pin_count;
	private boolean dirty;
	private boolean refbit;
	
	public Frame() {
		buffer=ByteBuffer.allocate(Constants.PAGE_SIZE);
		this.setPageId(null);
		this.setPin_count(0);
		this.setDirty(false);
		 this.setRefbit(false);
	}
	
	public void setFrame(PageId pageId ,int pin_count ,boolean dirty ) {
		this.pageId=pageId;
		this.pin_count=pin_count;
		this.dirty=dirty;
		if(pin_count==0)
		setRefbit(true);	
	}
	
	public void reset() {
		buffer.clear();
		pageId = null;
		pin_count = 0;
		dirty = false;
		refbit = false;
	}
	

	public PageId getPageId() {
		return pageId;
	}

	public void setPageId(PageId pageId) {
		this.pageId = pageId;
	}

	public int getPin_count() {
		return pin_count;
	}

	public void setPin_count(int pin_count) {
		this.pin_count = pin_count;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public ByteBuffer getBuffer() {
		return buffer;
	}

	public boolean isRefbit() {
		return refbit;
	}

	public void setRefbit(boolean refbit) {
		this.refbit = refbit;
	}

}
