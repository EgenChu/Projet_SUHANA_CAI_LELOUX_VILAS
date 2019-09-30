package SGBD;

import java.nio.ByteBuffer;

public class Frame {
	private byte[] buffer;
	private PageId pageId;
	private int pin_count;
	private boolean dirty;
	
	public Frame(byte[] buffer,PageId pageId,int pin_count,boolean dirty) {
		this.buffer=buffer;
		this.pageId=pageId;
		this.pin_count=pin_count;
		this.dirty=dirty;
	}
	

}
