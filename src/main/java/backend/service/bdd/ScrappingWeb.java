package backend.service.bdd;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;

import backend.metier.bdd.RecettesCuisine;

public class ScrappingWeb {

	public static void main(String[] args) throws InterruptedException {
		new ScrappingWeb().scrape();
	}

	private void scrape() throws InterruptedException {

		long chrono = System.currentTimeMillis(); // Create a date object
		System.out.println("start : "); // Display the current date
		final String httpsUrl = "https://www.750g.com/beignets-aux-pommes-r81596.htm";

		try {
			System.out.println("connect: " + (System.currentTimeMillis() - chrono));
			final URL url = new URL(httpsUrl);
			final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			System.out.println("get page: " + (System.currentTimeMillis() - chrono));
			System.out.println("****** Content of the URL ********");
			final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf8"));

			String input;
			String codeHtml = "";
			System.out.println("write " + (System.currentTimeMillis() - chrono));
			

			while ((input = br.readLine()) != null) {

				codeHtml = codeHtml.concat(input + " ");

			}
//			System.out.println(codeHtml);

			System.out.println("start getInfo: " + (System.currentTimeMillis() - chrono));

			RecettesCuisine recette = new RecettesCuisine(codeHtml);
			System.out.println("end getInfo: " + (System.currentTimeMillis() - chrono));

			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}