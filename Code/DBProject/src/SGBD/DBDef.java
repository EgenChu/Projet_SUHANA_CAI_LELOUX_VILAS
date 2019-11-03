package SGBD;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class DBDef
{   
	List<RelDef> listRel;
	int compteur = 0;
    
	/** Constructeur privé */
    private DBDef(){
    	listRel = new ArrayList <RelDef>();
    }
 
    /** Instance unique pré-initialisée */
    private static DBDef DBDef  = new DBDef();
    
     
    /** Point d'accès pour l'instance unique du singleton */
    public static DBDef getInstance() {   
    	return DBDef;
    }
    
    public void init() throws IOException {
    	BufferManager.getInstance().flushAll();
    	File fichier = new File(Constants.PATH+"/Catalogue.def");
		RandomAccessFile f = new RandomAccessFile(fichier, "r");
		StringTokenizer st = new StringTokenizer(f.readUTF(),"*");
		DBDef.compteur = Integer.parseInt(st.nextToken());
		while(st.hasMoreTokens()) {
			String relname = st.nextToken();
			int numcol =Integer.parseInt(st.nextToken());
			ArrayList<String> listString = new ArrayList<String>();
			StringTokenizer st2 = new StringTokenizer(st.nextToken(),"[, ]");
			while(st2.hasMoreTokens()) {
				listString.add(st2.nextToken());
			}
			int fileIdx = Integer.parseInt(st.nextToken());
			int recordSize = Integer.parseInt(st.nextToken());
			int slotCount = Integer.parseInt(st.nextToken());
			listRel.add(new RelDef(relname, numcol, listString,fileIdx, recordSize, slotCount));
		}
    	f.close();
    }
    
    public void finish() throws IOException {
    	BufferManager.getInstance().flushAll();
    	File fichier = new File(Constants.PATH+"/Catalogue.def");
		RandomAccessFile f = new RandomAccessFile(fichier, "w");
		f.writeChars(Integer.toString(DBDef.compteur)+DBDef.listRel.toString());
		f.close();
    }
    
    public void addRelation(RelDef a) {
    	compteur++;
    	listRel.add(a);
    }
}
