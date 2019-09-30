package SGBD;

import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.io.File;
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

	public static void createFile(int fileIdx) throws IOException {
		File name = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/" + "Data_" + fileIdx + ".rf");
		RandomAccessFile f = new RandomAccessFile(name, "rw");
	}

	public static PageId addPage(int fileIdx) throws IOException {		
		File fichier = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/"+"Data_"+ fileIdx+".rf");
		RandomAccessFile f = new RandomAccessFile(fichier,"rw");
		f.setLength(f.length()+ Constants.pageSize);
		PageId p = new PageId(fileIdx,(int)((f.length()/Constants.pageSize)-1));
		return p;
	}
	
	public static void readPage(PageId p,byte[] buff) throws IOException {
		File fichier = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/"+"Data_"+ p.getFileIdx()+".rf");
		RandomAccessFile f = new RandomAccessFile(fichier,"rw");	
//		f.seek(p.getPageIdx()*Constants.pageSize);
		f.readFully(buff, p.getPageIdx()*Constants.pageSize, Constants.pageSize);
		f.close();
	}
	
	public static void writePage(PageId p,byte[] buff) throws IOException {
		File fichier = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/"+"Data_"+ p.getFileIdx()+".rf");
		RandomAccessFile f = new RandomAccessFile(fichier,"rw");
//		f.seek(p.getPageIdx()*Constants.pageSize);
		f.write(buff,p.getPageIdx()*Constants.pageSize,Constants.pageSize);
	}
}

