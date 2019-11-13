package SGBD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		/*
		 * String s = "2;[int,int]"; String[] st = s.split(";");
		 * 
		 * System.out.println(st[1]);
		 * 
		 * ByteBuffer bf = ByteBuffer.allocate(9);
		 * 
		 * 
		 * for (int i = 0; i < 8; i += Integer.BYTES) { bf.putInt(i, 0); }
		 * 
		 * 
		 * File fichier = new File(
		 * 
		 * ); try { RandomAccessFile file = new RandomAccessFile(fichier, "rw");
		 * if(fichier.createNewFile()) System.out.println("créé");; FileChannel channel
		 * = file.getChannel();
		 * 
		 * channel.read(bf);
		 * 
		 * for (int i = 0; i < 8; i += Integer.BYTES) {
		 * System.out.println(bf.getInt(i)); }
		 * 
		 * ByteBuffer bfr = ByteBuffer.allocate(9);
		 * 
		 * bfr.putInt(0, 1);
		 * 
		 * channel.write(bf);
		 * 
		 * bf.putInt(0, 2);
		 * 
		 * 
		 * channel.read(bfr);
		 * 
		 * for (int i = 0; i < 8; i += Integer.BYTES) {
		 * System.out.println(bfr.getInt(i)); }
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

//			public static void main(String[] args) { Constants.PATH = args[0];
		/*
		 * try { DiskManager.getInstance().createFile(1); } catch (IOException e) {
		 * e.printStackTrace(); }
		 * 
		 * 
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
		 * 
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

		Constants.PATH = args[0];
		List<String> typecol = new ArrayList<>();
		typecol.add("int");
		RelDef reldef = new RelDef("première", 1, typecol, 100, 4, 819);

		HeapFile hf = new HeapFile(reldef);

		FileManager.getInstance().getHeapFiles().add(hf);

		// File fichier = new File(Constants.PATH + "/Data_100.rf");

		try {

			/*
			 * RandomAccessFile file = new RandomAccessFile(fichier, "rw"); if
			 * (fichier.createNewFile()) System.out.println("créé"); else {
			 * System.out.println("non créé"); }
			 * 
			 * FileChannel channel = file.getChannel();
			 */

			hf.createNewOndisk();

			PageId p = new PageId(reldef.getFileIdx(), 0);

			// Chargement de la headerPage 0
			ByteBuffer bf = BufferManager.getInstance().getPage(p);

			System.out.println("header page : " + p.getPageIdx() + bf.getInt(0) + bf.getInt(4));
			System.out.println("---------");
			BufferManager.getInstance().freePage(p, true); // 0,0,0

			List<String> values = new ArrayList<>();
			values.add("127");
			DBManager.getInstance().insert("première", values);
			
			values.remove("127");
			values.add("234");
			
			DBManager.getInstance().insert("première", values);
			
			bf = BufferManager.getInstance().getPage(p);

			System.out.println("header page : " + p.getPageIdx() + bf.getInt(0) + bf.getInt(4));
			System.out.println("---------");
			BufferManager.getInstance().freePage(p, true); // 0,0,0

			BufferManager.getInstance().flushAll();
			// Page 1 ajouté

			PageId page = new PageId(100,1);

			bf = BufferManager.getInstance().getPage(p);
			ByteBuffer bb = BufferManager.getInstance().getPage(page);
			System.out.println(page.getPageIdx() + " : " + bb.get(0) + " : " + bb.get(1) + " : " + bb.get(2));
			System.out.println(
					"header page : " + p.getPageIdx() + bf.getInt(0) + "+++++"+ bf.getInt(4) +"+++++++"+ bf.getInt(8) + bf.getInt(12));
			System.out.println("---------");
			BufferManager.getInstance().freePage(p, true); // 1,819,0,0
			BufferManager.getInstance().freePage(page, true);

			// Page 2 ajouté
			/*
			 * page = new PageId(100,1);
			 * 
			 * bf = BufferManager.getInstance().getPage(p);
			 * System.out.println(page.getPageIdx() + " : "); System.out.println(
			 * "header page : " + p.getPageIdx() + bf.getInt(0) + bf.getInt(4) +
			 * bf.getInt(8) + bf.getInt(12)); System.out.println("---------");
			 * BufferManager.getInstance().freePage(p, true); // 2,819,819,0
			 */
			DiskManager.getInstance().writePage(p, bf);

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Constants.PATH = args[0]; File fichier = new File(Constants.PATH +
		 * "/Data_0.rf"); try { RandomAccessFile file = new RandomAccessFile(fichier,
		 * "rw"); FileChannel channel = file.getChannel();
		 * 
		 * ByteBuffer buff = ByteBuffer.allocate(Constants.PAGE_SIZE);
		 * 
		 * channel.read(buff); System.out.println(buff.getInt(0));
		 * 
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}
}
