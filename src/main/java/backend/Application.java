package backend;

import backend.metier.exceptions.NullObjectException;
import backend.service.hardware.arduino.ReceptionTrames;

public class Application {

	public static void main(String[] args) throws NumberFormatException, NullObjectException {
		new ReceptionTrames();

	}

}
