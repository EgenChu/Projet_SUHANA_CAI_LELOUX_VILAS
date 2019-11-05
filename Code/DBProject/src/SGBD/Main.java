package SGBD;

import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		
		String s = "2;[int,int]";
		String[] st = s.split(";");
		
		System.out.println(st[1]);

		/*
		 * public static void main(String[] args) { Constants.PATH = args[0];
		 * 
		 * try { DiskManager.getInstance().createFile(1); } catch (IOException e) {
		 * e.printStackTrace(); }
		 * 
		 * PageId page = new PageId(1, 0); PageId page2 = new PageId(1, 1); PageId page3
		 * = new PageId(1, 2);
		 * 
		 * 
		 * try {
		 * 
		 * DiskManager.getInstance().createFile(1); PageId page0 =
		 * DiskManager.getInstance().addPage(1); PageId page1 =
		 * DiskManager.getInstance().addPage(1); PageId page2 =
		 * DiskManager.getInstance().addPage(1); PageId page3 =
		 * DiskManager.getInstance().addPage(1); PageId page4 =
		 * DiskManager.getInstance().addPage(1);
		 * 
		 * System.out.println(page0); System.out.println(page1);
		 * System.out.println(page2); System.out.println(page3);
		 * System.out.println(page4);
		 * 
		 * BufferManager.getInstance().getPage(page0);
		 * BufferManager.getInstance().getPage(page2);
		 * 
		 * 
		 * BufferManager.getInstance().getFrames().get(0).getBuffer().putChar(0,'c');
		 * BufferManager.getInstance().getFrames().get(1).getBuffer().putChar(0,'C');
		 * 
		 * BufferManager.getInstance().freePage(page0, true);
		 * BufferManager.getInstance().freePage(page2, true);
		 * 
		 * BufferManager.getInstance().flushAll();
		 * 
		 * 
		 * 
		 * 
		 * BufferManager.getInstance().getPage(page3);
		 * BufferManager.getInstance().getPage(page4);
		 * BufferManager.getInstance().freePage(page3, false);
		 * BufferManager.getInstance().getPage(page0);
		 * 
		 * 
		 * 
		 * 
		 * //System.out.println(BufferManager.getInstance().getFrames().get(0).getBuffer
		 * ().getChar(0));
		 * 
		 * 
		 * 
		 * DiskManager.getInstance().readPage(page0, b); page0.setPageIdx(1);
		 * DiskManager.getInstance().writePage(page0, b);
		 * 
		 * 
		 * 
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * 
		 * 
		 * ByteBuffer bf = ByteBuffer.allocate(9); String s = "Hello";
		 * 
		 * bf.putInt(3); System.out.println(bf.position()); bf.put(s.getBytes());
		 * System.out.println(bf.position());
		 * 
		 * 
		 * 
		 * ByteBuffer bf = ByteBuffer.allocate(50);
		 * 
		 * String aze = "azerty"; ArrayList<String> list = new ArrayList<>();
		 * list.add("int"); list.add("float"); list.add("string3"); list.add("string6");
		 * 
		 * RelDef reldef = new RelDef("nom",4,list);
		 * 
		 * Record record = new Record(reldef);
		 * 
		 * record.getValues().add("3"); record.getValues().add("5.f");
		 * record.getValues().add("Hey"); record.getValues().add("Hello!");
		 * 
		 * 
		 * record.writeToBuffer(bf, 4);
		 * 
		 * record.readFromBuffer(bf, 4);
		 * 
		 * 
		 * 
		 * ByteBuffer bf = ByteBuffer.allocate(12); StringBuffer sb = new
		 * StringBuffer();
		 * 
		 * for (int i = 0; i < 12; i += Integer.BYTES) { bf.putInt(i, 0); }
		 * 
		 * for (int i = 0; i < 12; i += Integer.BYTES) { sb.append(bf.getInt(i)); }
		 * 
		 * System.out.println(sb.toString());
		 * 
		 * 
		 * }
		 */
	}
}
