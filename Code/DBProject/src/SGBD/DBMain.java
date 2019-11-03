package SGBD;

import java.io.IOException;

public class DBMain {

	public static void main(String[] args) throws IOException {
		Constants.PATH = args[0];
		DBManager.getInstance().init();

		while (true) {
			String chaine = Saisie.lireChaine(null);
			if (chaine.equalsIgnoreCase("exit")) {
				DBManager.getInstance().finish();
				break;
			} else
				DBManager.getInstance().processCommand(chaine);
		}
	}

}
