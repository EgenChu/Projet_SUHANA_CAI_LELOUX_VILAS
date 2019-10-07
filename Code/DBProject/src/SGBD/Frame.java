package SGBD;

import java.nio.ByteBuffer;
import java.sql.Timestamp;

public class Frame {
	private ByteBuffer buffer;
	private PageId pageId;
	private int pin_count;
	private boolean dirty;
	private Timestamp time;
	
	public Frame() {
		buffer=ByteBuffer.allocate(Constants.PAGE_SIZE);
		this.setPageId(null);
		this.setPin_count(0);
		this.setDirty(false);
		 this.setTime(null);
	}
	
	public void setFrame(PageId pageId ,int pin_count ,boolean dirty ) {
		this.pageId=pageId;
		this.pin_count=pin_count;
		this.dirty=dirty;
		if(pin_count==0)
			setTime(new Timestamp(System.currentTimeMillis()));	
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
