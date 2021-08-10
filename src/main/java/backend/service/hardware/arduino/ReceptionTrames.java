package backend.service.hardware.arduino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import backend.metier.bdd.GestionBDD;
import backend.metier.exceptions.NullObjectException;
import backend.metier.hardware.arduino.Trame;

public class ReceptionTrames {

	private Logger LOG = Logger.getLogger("ReceptionTrames");

	private Socket socketOfClient = null;
	private BufferedReader in = null;
	private BufferedWriter os = null;

	private String trame;
	private Trame newTrame;

	private GestionBDD BDD;
	private String localisation;
	private String user;
	private String password;
	private String query;

	public ReceptionTrames() throws NumberFormatException, NullObjectException {

		this.localisation = "localhost/domotique";
		this.user = "root";
		this.password = "";
		connectionServeur("169.254.177.13", 23);
	}

	private void connectionServeur(String serverHost, int serverPort) throws NullObjectException {

		try {
			// Send a request to connect to the server is listening
			socketOfClient = new Socket(serverHost, serverPort);

			// Input stream at Client (Receive data from the server).
			in = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

		} catch (UnknownHostException e) {
			LOG.severe("Don't know about host " + serverHost);
			return;
		} catch (IOException e) {
			LOG.severe("Couldn't get I/O for the connection to " + serverHost);
			return;
		}

		try {
			// envois demande connection à l'arduino
			os.newLine();
			os.flush();

			this.receptionStockageTrame();

			in.close();
			os.close();
			socketOfClient.close();
		} catch (UnknownHostException e) {
			LOG.severe("Trying to connect to unknown host: " + e);
		} catch (IOException e) {
			LOG.severe("IOException:  " + e);
		}
	}

	private void receptionStockageTrame() throws IOException, NullObjectException {

		BDD = new GestionBDD(localisation, user, password);

		while ((trame = in.readLine()) != null) {

			newTrame = new Trame(trame);
			LOG.info(trame);

			query = "INSERT INTO `acquisition`";
			query += "(`IDAcquisition`, `nameAcquisition`, `pinAcquisition`, `valeurAcquisition`, `dateAcquisition`)";
			query += "VALUES (NULL, '" + newTrame.getAllData() + "', current_timestamp())";

			BDD.writeBDD(query);
		}
		
		BDD.closeConnection();
		
	}

}
