package SGBD;

import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.io.File;

public class DiskManager {

	private DiskManager() {
		
	}

	private static DiskManager diskManager = null;

	public static DiskManager getInstance() {
		if (diskManager == null)
			diskManager = new DiskManager();
		return diskManager;
	}

	public static void createFile(int fileIdx) throws Exception {
		File name = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/" + "Data_" + fileIdx + ".rf");
		RandomAccessFile f = new RandomAccessFile(name, "rw");
	}

	public static PageId addPage(int fileIdx) {
		File f = new File("Projet_SUHANA_CAI_LELOUX_VILAS/DB/" + "Data_" + fileIdx + ".rf");
		
		return null;
	}
}
