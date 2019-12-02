package SGBD;

import java.io.IOException;

public class DBMain {

	public static void main(String[] args) {
		boolean reponse = true;

		Constants.PATH = args[0];
		try {
			DBManager.getInstance().init();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (reponse) {
			String chaine = Saisie.lireChaine("Veuillez saisir une commande : ");
			if (chaine.equalsIgnoreCase("exit")) {
				reponse = false;
				try {
					DBManager.getInstance().finish();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			} else if (valideCommande(chaine)) {
				try {
					DBManager.getInstance().processCommand(chaine);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.err.println("VOTRE COMMANDE N'EST PAS VALIDE, VEUILLEZ RÉESSAYER");
			}
		}
	}

	public static boolean valideCommande(String chaine) {
		return (chaine.startsWith("create") || chaine.startsWith("insert") || chaine.startsWith("select")
				|| chaine.startsWith("selectall") || chaine.startsWith("clean") || chaine.startsWith("insertall")
				|| chaine.startsWith("delete")) || chaine.startsWith("join");
	}

}
