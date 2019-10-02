package SGBD;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Main {

	public static void main(String[] args) {
		ByteBuffer buff = ByteBuffer.allocate(Constants.PAGE_SIZE);
		
		try {
			DiskManager.getInstance().createFile(3);
			DiskManager.getInstance().readPage(DiskManager.getInstance().addPage(3), buff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
