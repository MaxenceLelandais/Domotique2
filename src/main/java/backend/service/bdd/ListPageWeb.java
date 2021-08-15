package backend.service.bdd;

import java.util.ArrayList;
import java.io.*;

import backend.metier.bdd.RecettesCuisine;

public class ListPageWeb {

	private ArrayList<String> categiriesCuisine = lectureFichier("src//main//resources//BDD750grammes//listeLien.txt");
	private ArrayList<String> listRecette;
	private ArrayList<String> listRecetteFirst = new ArrayList<>();

	private int nbrThread = 10;
	private ArrayList<ArrayList<String>> allRecettes = new ArrayList<>();

	private int numThread;

	public ListPageWeb() {

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
					allRecettes.get(nbrRecette % nbrThread).add(myRecette);
					nbrRecette++;
				}

				if (nbr == 1) {
					listRecetteFirst = listRecette;
				} else if (listRecetteFirst.get(0).equals(listRecette.get(0))) {
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
			ScrappingWeb a = new ScrappingWeb(allRecettes.get(numThread));
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