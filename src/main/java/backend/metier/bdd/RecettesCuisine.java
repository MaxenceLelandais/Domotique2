package backend.metier.bdd;

import java.util.ArrayList;
import java.util.List;

import backend.metier.formatage.HtmlToCorrectText;

public class RecettesCuisine {

	private String codeHtml;
	private int post = 0;

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
	private String tempsAttente;

	private ArrayList<String> etapes = new ArrayList<>();
	
	private HtmlToCorrectText htmlToCorrectText = new HtmlToCorrectText();

	public String getNom() {
		return nom;
	}

	public String getTempsTotal() {
		return tempsTotal;
	}

	public String getDifficulte() {
		return difficulte;
	}

	public String getCout() {
		return cout;
	}

	public String getNote() {
		return note;
	}

	public String getAvis() {
		return avis;
	}

	public String getNombreDePersonnes() {
		return nombreDePersonnes;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public List<String> getMateriels() {
		return materiels;
	}

	public List<String[]> getImagesIngredients() {
		return imagesIngredients;
	}

	public List<String[]> getImagesMateriels() {
		return imagesMateriels;
	}

	public String getTempsPreparation() {
		return tempsPreparation;
	}

	public String getTempsCuisson() {
		return tempsCuisson;
	}

	public String getTempsAttente() {
		return tempsAttente;
	}

	public List<String> getEtapes() {
		return etapes;
	}

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
		this.findTempsPreparationCuissonAttente();
		this.findEtapes();

		this.afficheResultat();
	}

	public String recherche(String rechercheStart, String rechercheEnd) {

		int longueurStart;
		int debut;
		int fin;

		longueurStart = rechercheStart.length();
		debut = codeHtml.indexOf(rechercheStart, post) + longueurStart;

		if (debut == -1 || debut < post) {
			return "undefined";
		}

		fin = codeHtml.indexOf(rechercheEnd, debut);
		if (fin == -1) {
			return "undefined";
		}
		post = fin + rechercheEnd.length();

		return codeHtml.substring(debut, fin).replaceFirst("\\s++$", "");
	}

	public ArrayList<String> recherche(String rechercheStart, String rechercheEnd, boolean all) {

		ArrayList<String> pages = new ArrayList<>();

		if (all) {
			while (true) {
				String resultat = recherche(rechercheStart, rechercheEnd);

				if (resultat == "undefined") {
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
		if(nombreDePersonnes=="0" ) {
			nombreDePersonnes = "undefined";
		}
	}

	private void findIngredients() {

		String resultat;
		String urlImg;
		String nomImg;
		int savePost;

		while (true) {

			savePost = post;

			urlImg = recherche("<img data-src=\"", "\" class=\"to-lazy");

			nomImg = recherche("alt=\"", "\"");

			resultat = recherche("<span class=\"recipe-ingredients-item-label\">", "</span>");

			if (resultat == "undefined") {
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
		int savePost;
		int positionMateriel = codeHtml.indexOf("<header><h2>Matériel</h2></header>", post);

		if (positionMateriel != -1) {

			while (true) {

				savePost = post;

				urlImg = recherche("<img data-src=\"", "\" class=\"to-lazy");

				nomImg = recherche("alt=\"", "\"");

				resultat = recherche("<span class=\"recipe-equipments-item-label\">", "</span>");

				if (resultat == "undefined") {
					post = savePost;
					break;
				}

				materiels.add(resultat);

				String[] img = { urlImg, nomImg };

				imagesMateriels.add(img);
			}
		}
	}


	private void findTempsPreparationCuissonAttente() {
		recherche("<header><h2>", "Préparation");

		tempsPreparation = recherche("datetime=\"", "</time></strong>");
		if("undefined"!=tempsPreparation) {
			tempsPreparation = tempsPreparation.split(">")[1];
		}
		tempsCuisson = recherche("datetime=\"", "</time></strong>");
		if("undefined"!=tempsCuisson) {
			tempsCuisson = tempsCuisson.split(">")[1];
		}
		tempsAttente = recherche("datetime=\"", "</time></strong>");
		if("undefined"!=tempsAttente) {
			tempsAttente = tempsAttente.split(">")[1];
		}
	}

	private void findEtapes() {

		String resultat;


		while (true) {

			resultat = recherche("<div class=\"recipe-steps-text\"><p>", "</p></div>");
			if (resultat == "undefined") {
				break;
			}

			etapes.add(htmlToCorrectText.suppBalise(resultat));
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

		System.out.print("temps de préparation : " + tempsPreparation + " ,  ");
		System.out.print("temps de cuisson : " + tempsCuisson + " ,  ");
		System.out.println("temps de attente : " + tempsAttente + " ");

		System.out.println("\nétapes : ");
		for (String etape : etapes) {
			System.out.println("- " + etape);
		}
	}

}
