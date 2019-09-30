package SGBD;

import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.io.File;

public class DiskManager {
	public static void createFile(int fileIdx) throws Exception{
		File name = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/"+"Data_"+ fileIdx+".rf");
		RandomAccessFile f = new RandomAccessFile(name,"rw");
	}
	
	public static PageId addPage(int fileIdx) {
//		Path path = ;
		File f =  File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/"+"Data_"+ fileIdx+".rf");
		
	}
}
