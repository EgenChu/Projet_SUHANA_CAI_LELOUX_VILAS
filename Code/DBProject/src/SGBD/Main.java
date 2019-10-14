package SGBD;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		/*
		 * ByteBuffer buff = ByteBuffer.allocate(Constants.PAGE_SIZE);
		 * 
		 * PageId page = new PageId(1, 0); PageId page2 = new PageId(1, 1); PageId page3
		 * = new PageId(1, 2);
		 * 
		 * 
		 * try { DiskManager.getInstance().createFile(1);
		 * DiskManager.getInstance().addPage(1); DiskManager.getInstance().addPage(1);
		 * DiskManager.getInstance().addPage(1); DiskManager.getInstance().addPage(1);
		 * DiskManager.getInstance().addPage(1);
		 * 
		 * BufferManager.getInstance().getPage(page);
		 * BufferManager.getInstance().getPage(page2);
		 * BufferManager.getInstance().freePage(page, false);
		 * BufferManager.getInstance().getPage(page3);
		 * 
		 * 
		 * DiskManager.getInstance().readPage(page, buff); page.setPageIdx(1);
		 * DiskManager.getInstance().writePage(page, buff);
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		
		/*
		 * String s = "string"; String s1 = "string10"; String s3 = "string5";
		 * 
		 * StringTokenizer st = new StringTokenizer(s1,"string");
		 * System.out.println(st.nextToken()); st = new StringTokenizer(s3,"string");
		 * 
		 * 
		 * System.out.println(st.nextToken());
		 */

		ByteBuffer bf =ByteBuffer.allocate(9);
		String s = "Hello";
		
		System.out.println(bf.position());
		bf.putInt(3);
		bf.put(s.getBytes());
		
		System.out.println(bf.position());
		
	}

	public static String getName(int fileIdx) {
		String sb = "../../DB/" + "Data_" + fileIdx + ".rf";
		return sb;
	}

}
