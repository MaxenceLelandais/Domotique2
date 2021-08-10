package backend.metier.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class GestionBDD {
	
	private Logger LOG = Logger.getLogger("ReceptionTrames");
	private Connection con;
	private Statement st;
	
	public GestionBDD(String localisation, String user, String password) {
		try {

			// 1 charger driver MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// 2 créer la connection
			con = DriverManager.getConnection("jdbc:mysql://"+localisation, user, password);

			// 3 créer un état de connection
			st = con.createStatement();

		} catch (Exception e) {
			LOG.severe(e.getMessage());
			closeConnection();
		}
	}
	
	public void writeBDD(String query) {
		
		try {
			st.executeUpdate(query);
			
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			closeConnection();
		}
	}
	
	public ResultSet readBDD(String query) {
		try {

			// 4 créer une requête de sélection
			return st.executeQuery(query);
			
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			closeConnection();
		}
		return null;
	}
	
	public void closeConnection() {
		try {

			// 5 fermer connection
			con.close();
			
		} catch (Exception e) {
			LOG.severe(e.getMessage());
		}
	}

}
