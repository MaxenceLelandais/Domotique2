package backend.service.bdd;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;

import backend.metier.bdd.RecettesCuisine;
import backend.metier.formatage.HtmlToCorrectText;

public class ScrappingWeb extends Thread {
	
	private ArrayList<String> urlList;
	
	private HtmlToCorrectText htmlToCorrectText = new HtmlToCorrectText();

	public ScrappingWeb(ArrayList<String> urlList) {
		this.urlList = urlList;
	}
	
	public ScrappingWeb() {
	}


	public void run() {
		

		for (String url : urlList) {
			System.out.println(url);

			String codeHtml = this.getCodeHtml(url);
			

			if (codeHtml != null) {
				RecettesCuisine recette = new RecettesCuisine(codeHtml);

				recette.rechercheAll();
				System.out.println(url);

			} else {
				System.out.println("erreur de communication avec le site : " +url);
			}
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