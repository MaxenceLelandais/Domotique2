package backend.metier.hardware.arduino;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.processing.Generated;

import org.junit.jupiter.api.Test;

import backend.metier.exceptions.NullObjectException;

public class TestTrame {
	


	private Trame createTestSubject() throws NullObjectException {
		return new Trame("");
	}
	

	@Test
	public void testConstructor() throws NullObjectException {
		
		
		new Trame(null);
	}

	@Test
	public void testGetName() throws Exception {
		Trame testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getName();
	}

	@Test
	public void testSetName() throws Exception {
		Trame testSubject;
		String name = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setName(name);
	}

	@Test
	public void testGetType() throws Exception {
		Trame testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getType();
	}

	@Test
	public void testSetType() throws Exception {
		Trame testSubject;
		String type = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setType(type);
	}

	@Test
	public void testGetPin() throws Exception {
		Trame testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getPin();
	}

	@Test
	public void testSetPin() throws Exception {
		Trame testSubject;
		String pin = "";

		// default test
		testSubject = createTestSubject();
		testSubject.setPin(pin);
	}

	@Test
	public void testGetValue() throws Exception {
		Trame testSubject;
		float result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getValue();
	}

	@Test
	public void testSetValue() throws Exception {
		Trame testSubject;
		float value = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setValue(value);
	}

	@Test
	public void testGetListData() throws Exception {
		Trame testSubject;
		String[] result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getListData();
	}

	@Test
	public void testSetListData() throws Exception {
		Trame testSubject;
		String[] listData = new String[] { "" };

		// default test
		testSubject = createTestSubject();
		testSubject.setListData(listData);
	}
}