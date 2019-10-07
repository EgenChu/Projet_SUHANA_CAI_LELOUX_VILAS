package SGBD;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

	public static void main(String[] args) {
		
//	    File fOut;
//	    RandomAccessFile f ;
//	    FileChannel fChan;
		ByteBuffer buff = ByteBuffer.allocate(Constants.PAGE_SIZE);
		
		PageId page = new PageId(5,0);
		
		try {
			DiskManager.getInstance().createFile(5);
			DiskManager.getInstance().addPage(5);
			
//			fOut = new File(getName(5));
//			f = new RandomAccessFile(fOut, "rw");
//			fChan = f.getChannel();
			
			DiskManager.getInstance().readPage(page, buff);
			
//			PageId page1 = new PageId(4,1);
//			
//			buff.put((byte)1994);
//			buff.put((byte)1994);
//			buff.rewind();
			
//			fChan.write(buff);
			
			page.setPageIdx(1);
//	
		DiskManager.getInstance().writePage(page, buff);
			
			
			
		//	DiskManager.getInstance().readPage(DiskManager.getInstance().addPage(), buff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getName(int fileIdx) {
		String sb = "../../DB/" + "Data_" + fileIdx + ".txt";
		return sb;
	}

}
