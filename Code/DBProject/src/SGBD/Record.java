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

	public void writeToBuffer(ByteBuffer buffer, int position) {
		String string;
		List<String> list = reldef.getList();
		
		for (int i = 0; i < reldef.getNumcol(); i++) {
			string = list.get(i);
			
			if(string.startsWith("int")) {
				buffer.putInt(position,Integer.parseInt(values.get(i)));
				position += Integer.BYTES;
			}
			else if(string.startsWith("float")) {
				buffer.putFloat(position,Float.parseFloat(values.get(i)));
				position += Float.BYTES;
			}
			else if(string.startsWith("string")) {
				buffer.put(values.get(i).getBytes());
				position += values.get(i).length();
			}
		}

	}
	
	public void readFromBuffer(ByteBuffer buffer, int position) {
		String string;
		List<String> list = reldef.getList();
		
		for (int i = 0; i < reldef.getNumcol(); i++) {
			string = list.get(i);
			
			if(string.startsWith("int")) {
				buffer.getInt(position);
				position += Integer.BYTES;
			}
			else if(string.startsWith("float")) {
				buffer.getFloat(position);
				position += Float.BYTES;
			}
			else if(string.startsWith("string")) {
				StringTokenizer st = new StringTokenizer(string,"string");
				int sizeString = Integer.parseInt(st.toString());
				
				CharBuffer cb = buffer.asCharBuffer();
				
				for(int j = position; i < sizeString;j++) {
					cb.get(j);
				}
				
				position += sizeString;
			}
		}
		
	}

}
