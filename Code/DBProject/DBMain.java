package SGBD;

public class DBMain {

	public static void main(String[] args) {
		DBManager.getInstance();
		getInstance().init();
		while(true){
			String chaine = Saisie.lireChaine(null);
			if(chaine.equalsIgnoreCase("exit")) {
				getInstance().Finish;
				break;
			}
			else
				getInstance().ProcessCommand(chaine);
		}
	}

}
