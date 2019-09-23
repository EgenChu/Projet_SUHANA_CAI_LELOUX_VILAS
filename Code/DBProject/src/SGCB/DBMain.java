package SGBD;

public class DBMain {

	public static void main(String[] args) {
		dbManager=DBManager.getInstance();
		dbManager.init();
		while(true){
			String chaine = Saisie.lireChaine(null);
			if(chaine.equalsIgnoreCase("exit")) {
				dbManager.Finish;
				break;
			}
			else
				dbManager.ProcessCommand(chaine);
		}
	}

}
