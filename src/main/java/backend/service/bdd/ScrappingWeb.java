package backend.service.bdd;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;

import backend.metier.bdd.RecettesCuisine;
import backend.metier.fichier.WriteBDD;
import backend.metier.formatage.HtmlToCorrectText;

public class ScrappingWeb extends Thread {
	
	private ArrayList<String> urlList;
	
	private HtmlToCorrectText htmlToCorrectText = new HtmlToCorrectText();

	private WriteBDD writeBDD;
	private int nbr=0;

	public ScrappingWeb(ArrayList<String> urlList,WriteBDD writeBDD) {
		this.urlList = urlList;
		this.writeBDD = writeBDD;
	}
	
	public ScrappingWeb( ) {
	}


	public void run() {
		
		long chrono = System.currentTimeMillis();
		

		for (String url : urlList) {
			nbr++;
			System.out.println(nbr+" : "+url);

			String codeHtml = this.getCodeHtml(url);
			

			if (codeHtml != null) {
				RecettesCuisine recette = new RecettesCuisine(codeHtml);

				recette.rechercheAll();
				this.writeBDD.add(recette);

			} else {
				System.out.println("erreur de communication avec le site : " +url);
			}
		}
		try {
			this.writeBDD.writeFile("src//main//resources//BDD750grammes//fichier");
			System.out.println("fin en "+(System.currentTimeMillis()-chrono)+" ms pour "+nbr+"  pages");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	public String getCodeHtml(String httpsUrl) {
		String codeHtml;
		String input;

		try {
			codeHtml = "";

			final URL url = new URL(httpsUrl);
			final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf8"));

			while ((input = br.readLine()) != null) {
				codeHtml = codeHtml.concat(input + " ");
			}
			
			
			codeHtml = htmlToCorrectText.changeCode(codeHtml);

			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return codeHtml;
	}
}