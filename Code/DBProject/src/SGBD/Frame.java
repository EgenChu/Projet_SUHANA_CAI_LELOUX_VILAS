package SGBD;

import java.nio.ByteBuffer;

public class Frame {
	private ByteBuffer buffer;
	private PageId pageId;
	private int pin_count;
	private boolean dirty;
	
	public Frame(PageId pageId,int pin_count,boolean dirty) {
		ByteBuffer.allocate(Constants.PAGE_SIZE);
		this.pageId=pageId;
		this.pin_count=pin_count;
		this.dirty=dirty;
	}
	

}
