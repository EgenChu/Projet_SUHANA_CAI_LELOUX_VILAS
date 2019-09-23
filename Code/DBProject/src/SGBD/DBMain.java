package SGBD;

public class DBMain {

	public static void main(String[] args) {
		
		DBManager dbManager = DBManager.getInstance();
		dbManager.init();
		
		while(true){
			String chaine = Saisie.lireChaine(null);
			if(chaine.equalsIgnoreCase("exit")) {
				dbManager.finish();
				break;
			}
			else
				dbManager.processCommand(chaine);
		}
	}

}
