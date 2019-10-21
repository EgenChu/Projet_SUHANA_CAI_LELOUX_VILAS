package SGBD;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Record {

	private RelDef reldef;
	private List<String> values;

	public Record(RelDef reldef) {
		this.reldef = reldef;
		values = new ArrayList<>();
	}

	public List<String> getValues() {
		return values;
	}

	public void writeToBuffer(ByteBuffer buffer, int position) {
		String string;
		List<String> list = reldef.getList();

		for (int i = 0; i < reldef.getNumcol(); i++) {
			string = list.get(i);

			if (string.startsWith("int")) {
				buffer.putInt(position, Integer.parseInt(values.get(i)));
				position += Integer.BYTES;
			} else if (string.startsWith("float")) {
				buffer.putFloat(position, Float.parseFloat(values.get(i)));
				position += Float.BYTES;
			} else if (string.startsWith("string")) {
				StringTokenizer st = new StringTokenizer(string, "string");
				for (int j = 0; j < values.get(i).length(); j++) {
					buffer.putChar(position, values.get(i).charAt(j));
					position += Character.BYTES;
				}

			}
		}

	}

	public void readFromBuffer(ByteBuffer buffer, int position) {
		String string;
		List<String> list = reldef.getList();

		for (int i = 0; i < reldef.getNumcol(); i++) {
			string = list.get(i);

			if (string.startsWith("int")) {
//				System.out.println(buffer.getInt(position));
				buffer.getInt(position);
				position += Integer.BYTES;

			} else if (string.startsWith("float")) {
				buffer.getFloat(position);
//				System.out.println(buffer.getFloat(position));
				position += Float.BYTES;
			} else if (string.startsWith("string")) {

				StringTokenizer st = new StringTokenizer(string, "string");
				int sizeString = Integer.parseInt(st.nextToken().toString());

				for (int j = 0; j < sizeString; j++) {
//					System.out.println(buffer.getChar(position)); 
					buffer.getChar(position);
					position += Character.BYTES;
				}

			}
		}

	}

}