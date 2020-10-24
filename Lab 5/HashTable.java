
public class HashTable {
	Integer[] elements;
	
	public HashTable(int size) {
		elements = new Integer[size];
	}
	
	public void add(int index, Integer element) throws CollisionException {
		if (elements[index] != null)
			throw new CollisionException();
		
		elements[index] = element;
	}
	
	public Integer get(int index) {
		return elements[index];
	}
	
	public String toString() {
		String toReturn = "";
		for (Integer element: elements) 
			toReturn += element + ", ";
		return toReturn;
	}
}
