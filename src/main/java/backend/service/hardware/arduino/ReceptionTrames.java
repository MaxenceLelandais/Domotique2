package backend.service.hardware.arduino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import backend.metier.exceptions.NullObjectException;
import backend.metier.hardware.arduino.Trame;

public class ReceptionTrames {

	public static void main(String[] args) throws IOException, NumberFormatException, NullObjectException {
		// Server Host

		String serverHost = "169.254.177.13";
		Logger LOG = Logger.getLogger("ReceptionTrames");

		Socket socketOfClient = null;
		BufferedReader in = null;

		BufferedWriter os = null;

		try {

			// Send a request to connect to the server is listening
			socketOfClient = new Socket(serverHost, 23);

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

			// Read data sent from the server.
			// By reading the input stream of the Client Socket.

			//os.write("HELO");

			// End of line
			os.newLine();

			// Flush data.
			os.flush();
			String trame;
			String[] data;
			Trame newTrame;
			while ((trame = in.readLine()) != null) {
				LOG.info(trame);

				data = trame.split(";");

				newTrame = new Trame(data[0], data[1], data[2], Float.parseFloat(data[3]));
				LOG.info(newTrame.getName());

			}

			in.close();
			socketOfClient.close();
		} catch (UnknownHostException e) {
			LOG.severe("Trying to connect to unknown host: " + e);
		} catch (IOException e) {
			LOG.severe("IOException:  " + e);
		}

	}

}
