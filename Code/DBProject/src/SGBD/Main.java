package SGBD;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		PageId page = new PageId(1, 0);
		PageId page2 = new PageId(1, 1);
		PageId page3 = new PageId(1, 2);


		try {
			DiskManager.getInstance().createFile(1);
			DiskManager.getInstance().addPage(1);
			DiskManager.getInstance().addPage(1);
			DiskManager.getInstance().addPage(1);
			DiskManager.getInstance().addPage(1);
			DiskManager.getInstance().addPage(1);
			
			BufferManager.getInstance().getPage(page);
			BufferManager.getInstance().getPage(page2);
			ByteBuffer b = ByteBuffer.allocate(4);
			b.put((byte)4);
			b.rewind();
			BufferManager.getInstance().frames.get(0).setBuffer(b);
			BufferManager.getInstance().freePage(page, true);
			BufferManager.getInstance().getPage(page3);


			/*DiskManager.getInstance().readPage(page, buff);
			page.setPageIdx(1);
			DiskManager.getInstance().writePage(page, buff);*/

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ByteBuffer bf =ByteBuffer.allocate(9);
		String s = "Hello";
		
		bf.putInt(3);
		System.out.println(bf.position());
		bf.put(s.getBytes());
		System.out.println(bf.position());
		
	}

	public static String getName(int fileIdx) {
		String sb = "../../DB/" + "Data_" + fileIdx + ".rf";
		return sb;
	}

}
