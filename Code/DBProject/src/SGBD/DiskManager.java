package SGBD;

import java.io.RandomAccessFile;
import java.io.File;
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

	public void createFile(int fileIdx) throws IOException {
		File fichier = new File(getName(fileIdx));
			if (fichier.createNewFile()) {
				System.out.println("Data_" + fileIdx + ".rf" + " created");
			} else {
				System.out.println("Data_" + fileIdx + ".rf " + " not created");
			}
	}

	public PageId addPage(int fileIdx) throws IOException {
		File fichier = new File(getName(fileIdx));
		RandomAccessFile f = new RandomAccessFile(fichier, "rw");
		
		
//		System.out.println(f.length() + ": size before effect");
		f.setLength(f.length() + Constants.PAGE_SIZE);
//		System.out.println(f.length() + ": size after effect");
		
		PageId p = new PageId(fileIdx, (int) ((f.length() / Constants.PAGE_SIZE) - 1));
		f.close();

		return p;
	}

	public void readPage(PageId p, ByteBuffer buff) throws IOException {
		File fichier = new File(getName(p));
		RandomAccessFile f = new RandomAccessFile(fichier, "r");
		FileChannel channel = f.getChannel();

//		FileChannel.open(Path.of(, more), StandardOpenOption.READ)();
		int nmbbyte = channel.read(buff, p.getPageIdx() * Constants.PAGE_SIZE);
		System.out.println(nmbbyte);
//		f.readFully(buff, p.getPageIdx()*Constants.pageSize, Constants.pageSize);
		
		channel.close();
		f.close();
	}

	public void writePage(PageId p, ByteBuffer buff) throws IOException {
		File fichier = new File(getName(p));
		RandomAccessFile f = new RandomAccessFile(fichier, "rw");
		FileChannel channel = f.getChannel();
		
		buff.rewind();
		int nmbbyte = channel.write(buff, p.getPageIdx() * Constants.PAGE_SIZE);
//		f.write(buff, p.getPageIdx() * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
		System.out.println(nmbbyte );
		
		channel.close();
		f.close();
	}
	
	private String getName(PageId p) {
		String sb = "../../DB/" + "Data_" + p.getFileIdx() + ".txt";
		return sb;
	}
	
	private String getName(int fileIdx) {
		String sb = "../../DB/" + "Data_" + fileIdx + ".txt";
		return sb;
	}

}
