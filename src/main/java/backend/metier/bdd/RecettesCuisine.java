package backend.metier.bdd;

import java.util.ArrayList;

public class RecettesCuisine {

	private String codeHtml;
	private int post = 0;
	private int savePost = 0;

	private String nom;

	private String tempsTotal;
	private String difficulte;
	private String cout;

	private String note;
	private String avis;

	private String nombreDePersonnes;

	private ArrayList<String> ingredients = new ArrayList<>();
	private ArrayList<String> materiels = new ArrayList<>();
	private ArrayList<String[]> imagesIngredients = new ArrayList<>();
	private ArrayList<String[]> imagesMateriels = new ArrayList<>();

	private String tempsPreparation;
	private String tempsCuisson;

	private ArrayList<String> etapes = new ArrayList<>();

	public RecettesCuisine(String codeHtml) {

		this.codeHtml = codeHtml;
	}

	public void rechercheAll() {

		this.findNom();
		this.findNoteAvie();
		this.findInfo();
		this.findNbrPersonne();
		this.findIngredients();
		this.findMateriels();
		this.findTempsPreparationCuisson();
		this.findEtapes();

//		this.afficheResultat();
	}

	public String recherche(String rechercheStart, String rechercheEnd) {

		int longueurStart;
		int debut;
		int fin;

		longueurStart = rechercheStart.length();
		debut = codeHtml.indexOf(rechercheStart, post) + longueurStart;

		if (debut == -1 || debut < post) {
			return null;
		}
		
		fin = codeHtml.indexOf(rechercheEnd, debut);
		if (fin == -1) {
			return null;
		}
		post = fin + rechercheEnd.length();

		return codeHtml.substring(debut, fin).replaceFirst("\\s++$", "");
	}
	
	public ArrayList<String> recherche(String rechercheStart, String rechercheEnd, boolean all) {
		
		ArrayList<String> pages = new ArrayList<>();
		
		if(all) {
			while (true) {
				String resultat = recherche(rechercheStart, rechercheEnd);
				
				if (resultat == null) {
					break;
				}
				pages.add(resultat);
			}
		}
		
		return pages;
		
	}

	private void findNom() {

		nom = recherche("<span class=\"u-title-page u-align-center\" role=\"heading\" aria-level=\"1\">", "</span>");
	}

	private void findNoteAvie() {

		note = recherche("<span class=\"u-bold\">", "</span>");
		avis = recherche("</span><span class=\"rating-votes\">", "avis         </span>");
	}

	private void findInfo() {

		String skip = "<div class=\"recipe-info-item\">";

		recherche(skip, "</svg");
		tempsTotal = recherche(">", "</div>");
		recherche(skip, "</svg");
		difficulte = recherche(">", "</div>");
		recherche(skip, "</svg");
		cout = recherche(">", "</div>");

	}

	private void findNbrPersonne() {

		nombreDePersonnes = recherche("<span class=\"ingredient-variator-label\">", "personnes</span>");
	}

	private void findIngredients() {

		String resultat;
		String urlImg;
		String nomImg;

		while (true) {

			savePost = post;

			urlImg = recherche("<img data-src=\"", "\" class=\"to-lazy");

			nomImg = recherche("alt=\"", "\"");

			resultat = recherche("<span class=\"recipe-ingredients-item-label\">", "</span>");

			if (resultat == null) {
				post = savePost;
				break;
			}

			ingredients.add(resultat);

			String[] img = { urlImg, nomImg };

			imagesIngredients.add(img);
		}
	}

	private void findMateriels() {

		String resultat;
		String urlImg;
		String nomImg;

		int positionMateriel = codeHtml.indexOf("<header><h2>Matériel</h2></header>", post);

		if (positionMateriel != -1) {

			while (true) {

				savePost = post;

				urlImg = recherche("<img data-src=\"", "\" class=\"to-lazy");

				nomImg = recherche("alt=\"", "\"");

				resultat = recherche("<span class=\"recipe-equipments-item-label\">", "</span>");

				if (resultat == null) {
					post = savePost;
					break;
				}

				materiels.add(resultat);

				String[] img = { urlImg, nomImg };

				imagesMateriels.add(img);
			}
		}
	}

	private void findTempsPreparationCuisson() {
		recherche("<header><h2>", "Préparation");

		tempsPreparation = recherche("M\">", "min</time></strong>");
		tempsCuisson = recherche("M\">", "min</time></strong>");
	}

	private void findEtapes() {

		String resultat;

		while (true) {

			resultat = recherche("<div class=\"recipe-steps-text\"><p>", "</p></div>");
			if (resultat == null) {
				break;
			}

			etapes.add(resultat.replaceAll(" <br>", ""));
		}

	}

	private void afficheResultat() {
		System.out.println("nom : " + nom);

		System.out.print("\ntemps total : " + tempsTotal + " ,  ");
		System.out.print("difficulté : " + difficulte + " ,  ");
		System.out.println("coût : " + cout);

		System.out.print("\nnote : " + note + " / 5 ,  ");
		System.out.println("avis : " + avis + " avis");

		System.out.println("\npour : " + nombreDePersonnes + " personne(s)\n");

		for (int n = 0; n < ingredients.size(); n++) {

			System.out.print("ingredient : " + ingredients.get(n) + " ,  ");
			System.out.print("image : " + imagesIngredients.get(n)[0] + " ,  ");
			System.out.println("nom de l'image : " + imagesIngredients.get(n)[1]);
		}

		for (int k = 0; k < materiels.size(); k++) {

			System.out.print("materiel : " + materiels.get(k) + " ,  ");
			System.out.print("image : " + imagesMateriels.get(k)[0] + " ,  ");
			System.out.println("nom de l'image : " + imagesMateriels.get(k)[1]);
		}

		System.out.print("temps de préparation : " + tempsPreparation + " mins,  ");
		System.out.println("temps de cuisson : " + tempsCuisson + " mins");

		System.out.println("\nétapes : ");
		for (String etape : etapes) {
			System.out.println("- " + etape);
		}
	}

}
