package SGBD;

import java.nio.ByteBuffer;
import java.sql.Timestamp;

public class Frame {
	private ByteBuffer buffer;
	private PageId pageId;
	private int pin_count;
	private boolean dirty;
	private Timestamp time;
	
	public Frame(PageId pageId) {
		ByteBuffer.allocate(Constants.PAGE_SIZE);
		this.setPageId(pageId);
		this.setPin_count(0);
		this.setDirty(false);
		setTime(null);
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

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	

}
