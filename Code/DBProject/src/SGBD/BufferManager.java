package SGBD;

import java.nio.ByteBuffer;

public class BufferManager {
	
	Frame[] frames = new Frame[Constants.FRAME_COUNT] ;
	
	private BufferManager() {
		
	}

	private static BufferManager bufferManager = null;

	public static BufferManager getInstance() {
		if (bufferManager == null)
			bufferManager = new BufferManager ();
		return bufferManager ;
	}
	
	public ByteBuffer GetPage(PageId pageId) {
		int espace;
		for(int i = 0; i < frames.length; i++) {
			if(frames[i].getPin_count()== 0) {
				espace = i;
				break;
			}
		}
		for(int i = 0; i < frames.length; i++) {
			if(!frames[i].isRefbit()) {
				espace = i;
				break;
			}
		}
		
		
	}
	
	public void FreePage (PageId pageId, boolean valdirty) {
		
	}
}
