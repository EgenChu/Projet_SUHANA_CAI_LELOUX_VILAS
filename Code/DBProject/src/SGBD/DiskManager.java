package SGBD;

import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.io.File;
import java.nio.ByteBuffer;

public class DiskManager {
	public static void createFile(int fileIdx) throws Exception{
		File name = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/"+"Data_"+ fileIdx+".rf");
		RandomAccessFile f = new RandomAccessFile(name,"rw");
	}
	
	public static PageId addPage(int fileIdx) {
		
		File fichier = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/"+"Data_"+ fileIdx+".rf");
		RandomAccessFile f = new RandomAccessFile(fichier,"rw");
		f.setLength(f.length()+ Constants.pageSize);
		PageId p = new PageId(fileIdx,(f.length()/Constants.pageSize)-1);
		return p;
	}
	
	public static void readPage(PageId p,ByteBuffer buff) {
		File fichier = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/"+"Data_"+ p.fileIdx+".rf");
		RandomAccessFile f = new RandomAccessFile(fichier,"rw");	
		f.readFully(buff,f.seek(p.pageIdx*Constants.pageSize),Constants.pageSize);
	}
	
	public static void writePage(PageId p,ByteBuffer buff) {
		File fichier = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/"+"Data_"+ p.fileIdx+".rf");
		RandomAccessFile f = new RandomAccessFile(fichier,"rw");
		f.write(buff,f.seek(p.pageIdx*Constants.pageSize),Constants.pageSize);
	}
}

