package SGBD;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TestMain {

	public static void main(String[] args) {
		/*
		 * ByteBuffer bb = ByteBuffer.allocate(8);
		 * 
		 * bb.putInt(0, 12); bb.putInt(4, 123);
		 * 
		 * System.out.println(bb.getInt(0)); System.out.println(bb.getInt(4));
		 * 
		 * bb.putInt(0, 12312); System.out.println("------\n" + bb.getInt(0));
		 * System.out.println(bb.getInt(4));
		 */
		try {
			
			/*
			  PageId page0 = DiskManager.getInstance().addPage(1); PageId page1 =
			  DiskManager.getInstance().addPage(2); PageId page2 =
			  DiskManager.getInstance().addPage(3);
			 */
			Constants.PATH = args[0];
			createNewOndisk(1);
			createNewOndisk(2);
			createNewOndisk(3);
			
			PageId header1 = new PageId(1,0);
			PageId header2 = new PageId(2,0);
			PageId header3 = new PageId(3,0);
			
			System.out.println("*-----------*");
			System.out.println(BufferManager.getInstance().getPage(header1).getInt(0) + " " + BufferManager.getInstance().getPage(header1).getInt(4));
			BufferManager.getInstance().freePage(header1, false);
			System.out.println(BufferManager.getInstance().getPage(header2).getInt(0)+ " " + BufferManager.getInstance().getPage(header2).getInt(4));
			BufferManager.getInstance().freePage(header2, false);
			System.out.println(BufferManager.getInstance().getPage(header3).getInt(0)+ " " + BufferManager.getInstance().getPage(header3).getInt(4));
			BufferManager.getInstance().freePage(header3, false);
			System.out.println("*-----------*");
			
			
			
			PageId p1 = addDataPage(1,819);
			System.out.println("*-----------*");
			System.out.println(BufferManager.getInstance().getPage(header1).getInt(0) + " " + BufferManager.getInstance().getPage(header1).getInt(4));
			BufferManager.getInstance().freePage(header1, false);
			System.out.println(BufferManager.getInstance().getPage(header2).getInt(0)+ " " + BufferManager.getInstance().getPage(header2).getInt(4));
			BufferManager.getInstance().freePage(header2, false);
			System.out.println(BufferManager.getInstance().getPage(header3).getInt(0)+ " " + BufferManager.getInstance().getPage(header3).getInt(4));
			BufferManager.getInstance().freePage(header3, false);
			System.out.println("*-----------*");
			
			
			
			PageId p2 = addDataPage(2,828);
			System.out.println("*-----------*");
			System.out.println(BufferManager.getInstance().getPage(header1).getInt(0) + " " + BufferManager.getInstance().getPage(header1).getInt(4));
			BufferManager.getInstance().freePage(header1, false);
			System.out.println(BufferManager.getInstance().getPage(header2).getInt(0)+ " " + BufferManager.getInstance().getPage(header2).getInt(4));
			BufferManager.getInstance().freePage(header2, false);
			System.out.println(BufferManager.getInstance().getPage(header3).getInt(0)+ " " + BufferManager.getInstance().getPage(header3).getInt(4));
			BufferManager.getInstance().freePage(header3, false);
			System.out.println("*-----------*");
			
			
			
			/*
			 * PageId p3 = addDataPage(3,87);
			 * 
			 * System.out.println(BufferManager.getInstance().getPage(header1).getInt(0) +
			 * " " + BufferManager.getInstance().getPage(header1).getInt(4));
			 * BufferManager.getInstance().freePage(header1, false);
			 * System.out.println(BufferManager.getInstance().getPage(header2).getInt(0)+
			 * " " + BufferManager.getInstance().getPage(header2).getInt(4));
			 * BufferManager.getInstance().freePage(header2, false);
			 * System.out.println(BufferManager.getInstance().getPage(header3).getInt(0)+
			 * " " + BufferManager.getInstance().getPage(header3).getInt(4));
			 * BufferManager.getInstance().freePage(header3, false);
			 */
			
/*			BufferManager.getInstance().getPage(page0);
			BufferManager.getInstance().getPage(page2);


			BufferManager.getInstance().freePage(page0, true);
			BufferManager.getInstance().freePage(page2, true);*/

			BufferManager.getInstance().flushAll();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createNewOndisk(int file) throws IOException {
		PageId temp;
		if (DiskManager.getInstance().createFile(file)) {

			temp = DiskManager.getInstance().addPage(file);

			ByteBuffer buff = BufferManager.getInstance().getPage(temp);
			for (int i = 0; i < Constants.PAGE_SIZE; i += Integer.BYTES) {
				buff.putInt(i, 0);
			}
			BufferManager.getInstance().freePage(temp, true);
			System.out.println(temp);
		} else
			System.out.println("file already exist");
	}

	public static PageId addDataPage(int file,int nbslotcount) throws IOException {
		ByteBuffer bf;
		int totalpage;

		PageId headerPage = new PageId(file, 0);
		bf = BufferManager.getInstance().getPage(headerPage);

		totalpage = bf.getInt(0) + 1;
		bf.putInt(0, totalpage);
		bf.putInt(totalpage * Integer.BYTES, nbslotcount);

		BufferManager.getInstance().freePage(headerPage, true);

		return DiskManager.getInstance().addPage(file);
	}

	public PageId getFreeDataPageId(int file) {
		ByteBuffer bf;
		int i = 4;
		int pagelibre = 1, totalpage;


		PageId headerPage = new PageId(file, 0);

		bf = BufferManager.getInstance().getPage(headerPage);

		totalpage = bf.getInt(0);
		while (bf.getInt(i) == 0 && pagelibre <= totalpage) {
			i += Integer.BYTES;
			pagelibre++;
		}

		BufferManager.getInstance().freePage(headerPage, false);


		if (pagelibre > totalpage)
			return null;
		else
			return new PageId(file, pagelibre);
	}

}
