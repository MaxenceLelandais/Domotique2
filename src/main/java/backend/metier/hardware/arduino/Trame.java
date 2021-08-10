package backend.metier.hardware.arduino;

import backend.metier.defprog.ItemsExists;
import backend.metier.exceptions.NullObjectException;

public class Trame {

	private String name;
	private String pin;
	private String value;
	private String[] listData;

	public Trame(String trame) throws NullObjectException {

		ItemsExists.CheckItemsExists(trame);
		
		this.listData = trame.split(";");
		this.name = listData[0];
		this.pin = listData[1];
		this.value = listData[2];
	}

	public String getName() {
		return name;
	}

	public String getPin() {
		return pin;
	}

	public String getValue() {
		return value;
	}

	public String[] getListData() {
		return listData;
	}
	
	public String getAllData() {		
		return name+"','"+pin+"','"+value;
	}
}
