package SGBD;

public class BufferManager {
	private BufferManager() {
		
	}

	private static BufferManager bufferManager = null;

	public static BufferManager getInstance() {
		if (bufferManager == null)
			bufferManager = new BufferManager ();
		return bufferManager ;
	}
	
	
}
