package backend;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import backend.metier.exceptions.NullObjectException;
import backend.service.bdd.ListPageWeb;

public class Application {

	public static void main(String[] args) throws NumberFormatException, NullObjectException, FileNotFoundException {
//		new ReceptionTrames();
		ListPageWeb a = new ListPageWeb();
		a.start();
		
	

	}

}
