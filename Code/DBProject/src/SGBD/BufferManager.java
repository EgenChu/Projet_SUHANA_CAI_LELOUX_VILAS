package SGBD;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BufferManager {

	Frame[] frames = new Frame[Constants.FRAME_COUNT];

	private BufferManager() {

	}

	private static BufferManager bufferManager = null;

	public static BufferManager getInstance() {
		if (bufferManager == null)
			bufferManager = new BufferManager();
		return bufferManager;
	}

	public ByteBuffer GetPage(PageId pageId) {
		int espace = Constants.FRAME_COUNT;
		for (int i = 0; i < frames.length; i++) {
			if (frames[i].getPin_count() == 0) {
				if (!frames[i].isRefbit()) {
					espace = i;
					break;
				}
			}
		}
		if (espace == Constants.FRAME_COUNT) {
			System.out.println("Erreur dans le code");
			return null;
		} else {
			try {
				DiskManager.getInstance().readPage(pageId, frames[espace].getBuffer());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return frames[espace].getBuffer();

		}
	}

	public void FreePage(PageId pageId, boolean valdirty) {
		for (int i = 0; i < frames.length; i++) {
			if(frames[i].getPageId().equals(pageId)) {
				frames[i].setPin_count(frames[i].getPin_count()-1);
				frames[i].setDirty(valdirty);
			}
		}
	}
	
	
	public void FlushAll() {
		
	}
}
