package SGBD;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
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
    public static DBDef getInstance()
    {   return DBDef;
    }
    
    public static void init() throws IOException {
    	BufferManager.getInstance().flushAll();
    	File fichier = new File(Constants.PATH+"/Catalogue.def");
		RandomAccessFile f = new RandomAccessFile(fichier, "r");
		f.re
    	
    }
    
    public static void finish() throws IOException {
    	BufferManager.getInstance().flushAll();
    	File fichier = new File(Constants.PATH+"/Catalogue.def");
		RandomAccessFile f = new RandomAccessFile(fichier, "w");
		f.writeChars(Integer.toString(DBDef.compteur)+DBDef.listRel.toString());
		f.close();
    }
    
    public  void addRelation(RelDef a) {
    	compteur++;
    	listRel.add(a);
    }
}
