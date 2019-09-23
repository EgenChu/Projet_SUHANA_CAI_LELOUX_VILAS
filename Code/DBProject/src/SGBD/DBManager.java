package SGBD;

public class DBManager {
	
	private DBManager() {
		
	}
	
	private static DBManager dbManager = new DBManager();
	
	public static DBManager getInstance() {
		return dbManager ;
	}
	
	public void init() {
		
	}
	
	public void finish() {

	}
	
	public void processCommand(String commande) {
		
	}
	
}
