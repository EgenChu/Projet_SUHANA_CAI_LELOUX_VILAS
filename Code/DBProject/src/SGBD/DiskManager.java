package SGBD;

import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class DiskManager {

	private DiskManager() {
		
	}

	private static DiskManager diskManager = null;

	public static DiskManager getInstance() {
		if (diskManager == null)
			diskManager = new DiskManager();
		return diskManager;
	}

	public static void createFile(int fileIdx) {
		File name = new File("../../DB/" + "Data_" + fileIdx + ".rf");
		try {
			if(name.createNewFile()) {
				System.out.println("Data_" + fileIdx + ".rf" + " created");
			}else {
				System.out.println("Data_" + fileIdx + ".rf " + " not created");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static PageId addPage(int fileIdx) {		
		File fichier = new File("../../DB/"+"Data_"+ fileIdx+".rf");
		RandomAccessFile f = null;
		PageId p = null;
		
		try {
			f = new RandomAccessFile(fichier,"rw");
			System.out.println(f.length() + ": size");
			f.setLength(f.length()+ Constants.pageSize);
			System.out.println(f.length() + ": size after effect");
			p = new PageId(fileIdx,(int)((f.length()/Constants.pageSize)-1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	
	public static void readPage(PageId p,byte[] buff) throws IOException {
		File fichier = new File("../../DB/"+"Data_"+ p.getFileIdx()+".rf");
		RandomAccessFile f = new RandomAccessFile(fichier,"rw");	
//		f.seek(p.getPageIdx()*Constants.pageSize);
		f.readFully(buff, p.getPageIdx()*Constants.pageSize, Constants.pageSize);
		f.close();
	}
	
	public static void writePage(PageId p,byte[] buff) throws IOException {
		File fichier = new File("../../DB/"+"Data_"+ p.getFileIdx()+".rf");
		RandomAccessFile f = new RandomAccessFile(fichier,"rw");
//		f.seek(p.getPageIdx()*Constants.pageSize);
		f.write(buff,p.getPageIdx()*Constants.pageSize,Constants.pageSize);
	}
}

