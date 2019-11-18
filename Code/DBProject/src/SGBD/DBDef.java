package SGBD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class DBDef {
	private List<RelDef> listRel;
	private int compteur = 0;

	/** Constructeur privé */
	private DBDef() {
		listRel = new ArrayList<RelDef>();
	}

	/** Instance unique pré-initialisée */
	private static DBDef DBDef = new DBDef();

	/** Point d'accès pour l'instance unique du singleton */
	public static DBDef getInstance() {
		
		return DBDef;
	}

	public void init() throws IOException {
		BufferManager.getInstance().flushAll();

		File fichier = new File(Constants.PATH + "/Catalogue.def");

		if (fichier.exists()) {
			if (fichier.length() == 0)
				System.out.println("Le fichier est vide");
			else
				readRelDefFromFile(fichier);
		} else {
			fichier.createNewFile();
			System.out.println("le fichier n'existe pas, il vient d'être est créé");
		}
	}

	public void finish() throws IOException {
		BufferManager.getInstance().flushAll();
		File fichier = new File(Constants.PATH + "/Catalogue.def");
		fichier.delete();

		fichier.createNewFile();
		try (BufferedWriter br = new BufferedWriter(new FileWriter(fichier))) {
			br.write(this.toString());
		}
	}

	public void addRelation(RelDef a) {
		compteur++;
		listRel.add(a);
	}

	public List<RelDef> getListRel() {
		return listRel;
	}

	public int getCompteur() {
		return compteur;
	}

	public void reset() {
		this.compteur = 0;
		listRel.clear();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.compteur);

		for (int i = 0; i < this.listRel.size(); i++) {
			sb.append("\n");
			sb.append(this.listRel.get(i).toString());
		}
		return sb.toString();
	}

	private void readRelDefFromFile(File fichier) throws IOException{
		FileReader file = new FileReader(fichier);
		BufferedReader br = new BufferedReader(file);
		this.compteur = Integer.parseInt(br.readLine().trim());

		if (this.compteur != 0) {
			String row;
			while ((row = br.readLine()) != null) {
				String[] data = row.split("\\s*;\\s*");

				String relname = data[0];
				int numcol = Integer.parseInt(data[1].trim());

				ArrayList<String> listString = new ArrayList<String>();
				StringTokenizer st2 = new StringTokenizer(data[2].trim(), "[, ]");
				while (st2.hasMoreTokens()) {
					listString.add(st2.nextToken());
				}
				
				int fileIdx = Integer.parseInt(data[3].trim());
				int recordSize = Integer.parseInt(data[4].trim());
				int slotCount = Integer.parseInt(data[5].trim());
				listRel.add(new RelDef(relname, numcol, listString, fileIdx, recordSize, slotCount));
			}
		}

	}

}
