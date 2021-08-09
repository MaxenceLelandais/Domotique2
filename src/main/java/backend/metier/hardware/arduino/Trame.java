package backend.metier.hardware.arduino;
import backend.metier.defprog.ItemsExists;
import backend.metier.exceptions.NullObjectException;

public class Trame {

	private String name;
	private String type;
	private String pin;
	private float value;

	private String[] listData;

	public Trame(String name, String type, String pin, float value) throws NullObjectException  {

		ItemsExists.CheckItemsExists(name,type,pin,value);
		
		this.name = name;
		this.type = type;
		this.pin = pin;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String[] getListData() {
		return listData;
	}

	public void setListData(String[] listData) {
		this.listData = listData;
	}

}
