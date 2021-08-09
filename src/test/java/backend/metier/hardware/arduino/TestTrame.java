package backend.metier.hardware.arduino;

import javax.annotation.processing.Generated;

import org.junit.jupiter.api.Test;

public class TestTrame {

	private Trame createTestSubject() {
		return new Trame("", "", "", 0);
	}
	

	@Test
	public void testConstructor() throws Exception {
		
		new Trame(null, "", "", 0);
		new Trame("", null, "", 0);
		new Trame("", "", null, 0);
		new Trame("", "", "", null);

		// default test
		testSubject = createTestSubject();
		result = testSubject.getName();
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