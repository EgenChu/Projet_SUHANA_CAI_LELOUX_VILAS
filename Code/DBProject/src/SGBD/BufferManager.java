package SGBD;

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
	
	public void FreePage (PageId pageId, boolean valdirty) {
		
	}
}
