package backend.service.bdd;

import java.sql.*;
import java.util.logging.Logger;

public class Lecture {

	public static void main(String[] args) {
		Logger LOG = Logger.getLogger("ReceptionTrames");

		try {

			// 1 charger driver MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// 2 créer la connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");

			// 3 créer un état de connection
			Statement st = con.createStatement();

			// 4 créer une requête de sélection
			ResultSet res = st.executeQuery("select * from acquisition");

			// 5 parcourt des données
			while (res.next()) {
				System.out.print("Nom : " + res.getString(2));
				System.out.print(",  type : " + res.getString(3));
				System.out.print(",  pin : " + res.getString(4));
				System.out.println(",  value : " + res.getString(5));
			}

			// 6 fermer connection
			con.close();
			
			// 7 traitement des exceptions

		} catch (Exception e) {
			LOG.severe(e.getMessage());
		}
	}

}
