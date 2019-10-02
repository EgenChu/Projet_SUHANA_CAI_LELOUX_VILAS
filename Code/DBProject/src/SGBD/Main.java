package SGBD;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		DiskManager.createFile(3);
		System.out.println(DiskManager.addPage(3));
	}

}
