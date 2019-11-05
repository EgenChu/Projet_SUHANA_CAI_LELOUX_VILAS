package SGBD;

import java.io.IOException;

public class DBMain {

	public static void main(String[] args) {
		Constants.PATH = args[0];
		try {
			DBManager.getInstance().init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			String chaine = Saisie.lireChaine("Veuillez saisir une commande : ");
			if (chaine.equalsIgnoreCase("exit")) {
				try {
					DBManager.getInstance().finish();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			} else
				try {
					DBManager.getInstance().processCommand(chaine);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
