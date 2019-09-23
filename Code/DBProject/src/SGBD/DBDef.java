package SGBD;
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
    
    public static void init() {
    	
    }
    
    public static void finish() {
    	
    }
    
    public  void addRelation(RelDef a) {
    	compteur++;
    	listRel.add(a);
    }
}
