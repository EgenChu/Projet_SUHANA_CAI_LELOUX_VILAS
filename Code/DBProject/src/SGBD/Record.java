package SGBD;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class Record {

	private RelDef reldef;
	private List<String> values;

	public Record(RelDef reldef) {
		this.reldef = reldef;
		values = new ArrayList<>();
	}

	public void writeToBuffer(ByteBuffer buffer, int position) {
		int nbvalues = reldef.getNumcol();

		for (int i = 0; i < nbvalues; i++) {

			switch (reldef.getList().get(i)) {

			case "int":
				Integer intvalues = Integer.parseInt(values.get(i));
				buffer.putInt(position, intvalues);
				position += Integer.BYTES;
				break;

			case "float":
				Float floatvalues = Float.parseFloat(values.get(i));
				buffer.putFloat(position, floatvalues);
				position += Float.BYTES;
				break;

			case "String":
				CharBuffer cb = buffer.asCharBuffer();
				cb.put(values.get(i));
				break;

			}

		}

	}
	
	public void readFromBuffer(ByteBuffer buffer, int position) {
		while(buffer.hasRemaining()) {
		buffer.get(position);
		position++;
		}
	}

	

}
