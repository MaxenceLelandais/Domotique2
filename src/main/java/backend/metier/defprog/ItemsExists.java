package backend.metier.defprog;



import backend.metier.exceptions.NullObjectException;

public class ItemsExists {

	public static void CheckItemsExists(Object ... objects) throws NullObjectException {
		for (Object object : objects) {
			if (object == null) {
				throw new NullObjectException("null pointer exception");
			}
		}
	}



}
