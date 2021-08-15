package backend.metier.fichier;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import backend.metier.bdd.RecettesCuisine;

public class WriteBDD {

	private ArrayList<ArrayList<String>> recetteAll = new ArrayList<>();
	private ArrayList<String> difficulteAll = new ArrayList<>();
	private ArrayList<String> coutAll = new ArrayList<>();

	private ArrayList<ArrayList<String>> ingredientAll = new ArrayList<>();
	private ArrayList<ArrayList<String>> materielAll = new ArrayList<>();
	private ArrayList<String> etapeAll = new ArrayList<>();

	private ArrayList<ArrayList<String>> recetteToIngredientAll = new ArrayList<>();
	private ArrayList<ArrayList<String>> recetteToMaterielAll = new ArrayList<>();
	private ArrayList<ArrayList<String>> recetteToEtapeAll = new ArrayList<>();

	public void add(RecettesCuisine recette) {

		String difficulte = recette.getDifficulte();
		ArrayList<String> oneRecette = new ArrayList<>();

		if (!difficulteAll.contains(difficulte)) {
			difficulteAll.add(difficulte);
		}

		String cout = recette.getCout();
		if (!coutAll.contains(cout)) {
			coutAll.add(cout);
		}

		oneRecette.add(recette.getNom());
		oneRecette.add(recette.getTempsTotal());
		oneRecette.add(String.valueOf(difficulteAll.indexOf(difficulte)));
		oneRecette.add(String.valueOf(coutAll.indexOf(cout)));
		oneRecette.add(recette.getNote());
		oneRecette.add(recette.getAvis());
		oneRecette.add(recette.getTempsPreparation());
		oneRecette.add(recette.getTempsCuisson());
		oneRecette.add(recette.getTempsAttente());
		oneRecette.add(recette.getNombreDePersonnes());

		if (!recetteAll.contains(oneRecette)) {
			recetteAll.add(oneRecette);
		}

		String numRecette = String.valueOf(recetteAll.indexOf(oneRecette));

		ArrayList<String> ingredientsNom = recette.getIngredients();

		int nbr = 0;
		for (ArrayList<String> ingredient : recette.getImagesIngredients()) {
			
			if (!ingredientAll.contains(ingredient)) {
				ingredientAll.add(ingredient);
			}
			ArrayList<String> recetteToIngredient = new ArrayList<>();

			recetteToIngredient.add(numRecette);
			recetteToIngredient.add(String.valueOf(ingredientAll.indexOf(ingredient)));
			recetteToIngredient.add(ingredientsNom.get(nbr));

			recetteToIngredientAll.add(recetteToIngredient);

			nbr++;
		}

		for (ArrayList<String> materiel : recette.getImagesMateriels()) {

			if (!materielAll.contains(materiel)) {
				materielAll.add(materiel);
			}
			ArrayList<String> recetteToMateriel = new ArrayList<>();

			recetteToMateriel.add(numRecette);
			recetteToMateriel.add(String.valueOf(materielAll.indexOf(materiel)));
			
			

			recetteToMaterielAll.add(recetteToMateriel);

		}

		for (String etape : recette.getEtapes()) {

			if (!etapeAll.contains(etape)) {
				etapeAll.add(etape);
			}
			ArrayList<String> recetteToEtape = new ArrayList<>();

			recetteToEtape.add(numRecette);
			recetteToEtape.add(String.valueOf(etapeAll.indexOf(etape)));

			recetteToEtapeAll.add(recetteToEtape);

		}

	}

	public void writeFile(String raccourci) throws FileNotFoundException {

		PrintWriter writerCout = new PrintWriter(raccourci + "//cout.txt");
		for (String cout : coutAll) {
			writerCout.println(cout);
		}
		writerCout.close();

		PrintWriter writerDifficulte = new PrintWriter(raccourci + "//difficulte.txt");
		for (String difficulte : difficulteAll) {
			writerDifficulte.println(difficulte);
		}
		writerDifficulte.close();

		PrintWriter writerEtape = new PrintWriter(raccourci + "//etape.txt");
		for (String etape : etapeAll) {
			writerEtape.println(etape);
		}
		writerEtape.close();

		PrintWriter writerIngredient = new PrintWriter(raccourci + "//ingredient.txt");
		for (ArrayList<String> ingredient : ingredientAll) {
			writerIngredient.println(ingredient.get(0) + "," + ingredient.get(1));
		}
		writerIngredient.close();

		PrintWriter writerMateriel = new PrintWriter(raccourci + "//materiel.txt");
		for (ArrayList<String> materiel : materielAll) {
			writerMateriel.println(materiel.get(0) + "," + materiel.get(1));
		}
		writerMateriel.close();

		PrintWriter writerRecette = new PrintWriter(raccourci + "//recette.txt");
		for (ArrayList<String> recette : recetteAll) {
			for (int i = 0; i < 9; i++) {
				writerRecette.print(recette.get(i) + ",");
			}
			writerRecette.println(recette.get(9));
		}
		writerRecette.close();

		PrintWriter writerRecetteToEtape = new PrintWriter(raccourci + "//recetteToEtape.txt");
		for (ArrayList<String> recetteEtape : recetteToEtapeAll) {
			writerRecetteToEtape.println(recetteEtape.get(0) + "," + recetteEtape.get(1));
		}
		writerRecetteToEtape.close();

		PrintWriter writerRecetteToIngredient = new PrintWriter(raccourci + "//recetteToIngredient.txt");
		for (ArrayList<String> recetteIngredient : recetteToIngredientAll) {
			writerRecetteToIngredient.println(
					recetteIngredient.get(0) + "," + recetteIngredient.get(1) + "," + recetteIngredient.get(2));
		}
		writerRecetteToIngredient.close();

		PrintWriter writerRecetteToMateriel = new PrintWriter(raccourci + "//recetteToMateriel.txt");
		for (ArrayList<String> recetteMateriel : recetteToMaterielAll) {
			writerRecetteToMateriel.println(recetteMateriel.get(0) + "," + recetteMateriel.get(1));
		}
		writerRecetteToMateriel.close();

	}

}
