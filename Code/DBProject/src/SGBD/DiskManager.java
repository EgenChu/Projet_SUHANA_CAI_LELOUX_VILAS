package SGBD;

import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DiskManager {

	private DiskManager() {

	}

	private static DiskManager diskManager = null;

	public static DiskManager getInstance() {
		if (diskManager == null)
			diskManager = new DiskManager();
		return diskManager;
	}

	public boolean createFile(int fileIdx) throws IOException {
		File fichier = new File(getName(fileIdx));
			if (fichier.createNewFile()) {
//				System.out.println("Data_" + fileIdx + ".rf" + " created");
				return true;
			} else {
//				System.out.println("Data_" + fileIdx + ".rf" + " not created");
				return false;
			}
	}

	public PageId addPage(int fileIdx) throws IOException {
		File fichier = new File(getName(fileIdx));
		RandomAccessFile f = new RandomAccessFile(fichier, "rw");		
		f.setLength(f.length() + Constants.PAGE_SIZE);		
		PageId p = new PageId(fileIdx,  (int) ((f.length() / Constants.PAGE_SIZE) - 1));
		f.close();

		return p;
	}

	public void readPage(PageId p, ByteBuffer buff) throws FileNotFoundException, IOException     {
		File fichier = new File(getName(p));
		RandomAccessFile f = new RandomAccessFile(fichier, "r");
		FileChannel channel = f.getChannel();

		channel.read(buff, p.getPageIdx() * Constants.PAGE_SIZE);
		channel.close();
		f.close();
	}

	public void writePage(PageId p, ByteBuffer buff) throws IOException {
		File fichier = new File(getName(p));
		RandomAccessFile f = new RandomAccessFile(fichier, "rw");
		FileChannel channel = f.getChannel();
		
		buff.clear();
		channel.write(buff, p.getPageIdx() * Constants.PAGE_SIZE);		
		channel.close();
		f.close();
	}
	
	private String getName(PageId p) {
		String sb = Constants.PATH + "/Data_" + p.getFileIdx() + ".rf";
		return sb;
	}
	
	private String getName(int fileIdx) {
		String sb = Constants.PATH + "/Data_" + fileIdx + ".rf";
		return sb;
	}

}
