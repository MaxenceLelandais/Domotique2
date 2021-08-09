package backend.service.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Logger;

public class Ecriture {
	public static void main(String[] args) {
		Logger LOG = Logger.getLogger("ReceptionTrames");

		try {

			// 1 charger driver MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// 2 créer la connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");

			// 3 créer un état de connection
			Statement st = con.createStatement();

			// 4 Insertion des données
			String query = "INSERT INTO `acquisition` (`IDAcquisition`, `nomAcquisition`, `typeAcquisition`, `pinAcquisition`, `valeurAcquisition`) VALUES (NULL, 'capteur', 'in', 'A2', '125.0')";
			st.executeUpdate(query);

			// 5 fermer connection
			con.close();
			
			// 6 traitement des exceptions

		} catch (Exception e) {
			LOG.severe(e.getMessage());
		}
	}
}
