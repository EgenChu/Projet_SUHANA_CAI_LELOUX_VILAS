package SGBD;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class BufferManager {

	private ArrayList<Frame> frames;

	private BufferManager() {
		frames = new ArrayList<Frame>(Constants.FRAME_COUNT);
		for (int i = 0; i < Constants.FRAME_COUNT; i++) {
			frames.add(new Frame());
		}
	}

	private static BufferManager bufferManager = null;

	public static BufferManager getInstance() {
		if (bufferManager == null)
			bufferManager = new BufferManager();
		return bufferManager;
	}

	public ByteBuffer getPage(PageId pageId) {
		int espace = Constants.FRAME_COUNT;
		
		for (int i = 0; i < frames.size(); i++) {
			if (frames.get(i).getPageId() != null) {
				if (frames.get(i).getPageId().equals(pageId)) {
					return(frames.get(i).getBuffer());
				}
			}
		}
		
		for (int i = 0; i < frames.size(); i++) {
			if (frames.get(i).getPin_count() == 0) {
				if (!frames.get(i).isRefbit()) {
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
				if (frames.get(espace).isDirty()) {
					try {
						DiskManager.getInstance().writePage(frames.get(espace).getPageId(),frames.get(espace).getBuffer());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				frames.get(espace).setFrame(pageId, frames.get(espace).getPin_count() + 1, false);
				DiskManager.getInstance().readPage(pageId, frames.get(espace).getBuffer());
				//System.out.println(pageId.toString() + " remplie dans le buffer " + espace);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return frames.get(espace).getBuffer();
		}
	}

	public void freePage(PageId pageId, boolean valdirty) {
		for (int i = 0; i < frames.size(); i++) {
			if (frames.get(i).getPageId() != null) {
				if (frames.get(i).getPageId().equals(pageId)) {
					if(frames.get(i).getPin_count()>0)
						frames.get(i).setPin_count(frames.get(i).getPin_count() - 1);
					if(!frames.get(i).isDirty())
						frames.get(i).setDirty(valdirty);
				}
			}
		}
	}

	public void flushAll() {

		for (int i = 0; i < frames.size(); i++) {
			if (frames.get(i).isDirty()) {
				try {
					DiskManager.getInstance().writePage(frames.get(i).getPageId(), frames.get(i).getBuffer());
				} catch (IOException e) {
					e.printStackTrace();
				}
				frames.get(i).setDirty(false);
			}
		}

		for (int i = 0; i < frames.size(); i++) {
			frames.get(i).reset();
		}

	}

	public ArrayList<Frame> getFrames() {
		return frames;
	}

	public void reset() {
		for (int i = 0; i < frames.size(); i++) {
			frames.get(i).reset();
		}
	}

}
