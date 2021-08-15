package backend.service.bdd;

import java.util.ArrayList;
import java.io.*;

import backend.metier.bdd.RecettesCuisine;
import backend.metier.fichier.WriteBDD;

public class ListPageWeb {

	private ArrayList<String> categiriesCuisine = lectureFichier("src//main//resources//BDD750grammes//listeLien.txt");
	private ArrayList<String> listRecette;
	private String recetteFirst = "";

	private int nbrThread = 1;
	private ArrayList<ArrayList<String>> allRecettes = new ArrayList<>();

	private int numThread;

	private WriteBDD writeBDD = new WriteBDD();

	public ListPageWeb() throws FileNotFoundException {

//		ScrappingWeb scrap = new ScrappingWeb();
//		String codeHtml = scrap.getCodeHtml("https://www.750g.com/bugnes-r7379.htm");
//		RecettesCuisine recette = new RecettesCuisine(codeHtml);
//		recette.rechercheAll();
//		WriteBDD a = new WriteBDD();
//		a.add(recette);
//		a.writeFile("src//main//resources//BDD750grammes//fichier");

//		new RecettesCuisine(scrap.getCodeHtml("https://www.750g.com/bugnes-aux-amandes-avec-leurs-creme-citron-gingembre-et-vanille-r80564.htm"));

		ScrappingWeb scrap = new ScrappingWeb();

		for (int i = 0; i <= nbrThread; i++) {
			allRecettes.add(new ArrayList<>());
		}

		for (String categorie : categiriesCuisine) {

			int nbr = 1;
			int nbrRecette = 0;

			while (true) {

				String codeHtml = scrap.getCodeHtml(categorie + "?page=" + nbr);
				RecettesCuisine recette = new RecettesCuisine(codeHtml);
				listRecette = recette.recherche("<h2 class=\"card-title\"><a href=\"", "\"", true);

				for (String myRecette : listRecette) {
					if (recetteFirst.equals(myRecette)) {
						break;
					}
					allRecettes.get(nbrRecette % nbrThread).add(myRecette);
					nbrRecette++;

				}

				if (nbr == 1) {
					recetteFirst = listRecette.get(0);
				} else if (recetteFirst.equals(listRecette.get(0))) {
					break;
				}

				System.out.println(categorie + "?page=" + nbr);
				nbr++;
			}

		}

	}

	public void start() {

		for (int i = 0; i < nbrThread; i++) {
			System.out.println(numThread);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ScrappingWeb a = new ScrappingWeb(allRecettes.get(numThread), writeBDD);
			a.start();

			numThread++;
		}

	}

	private ArrayList<String> lectureFichier(String repertoire) {

		ArrayList<String> listLien = new ArrayList<>();
		try {
			File file = new File(repertoire);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null) {
				listLien.add(line);
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listLien;
	}
}