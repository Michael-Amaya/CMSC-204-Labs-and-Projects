public class ArraySumDriver {
	private final static int ARRAY_SIZE = 6;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int index = 0;

		Integer[] myArray = new Integer[ARRAY_SIZE];
		ArraySum arraySum = new ArraySum();
		
		myArray[index++] = 3;
		myArray[index++] = 5;
		myArray[index++] = 2;
		myArray[index++] = 6;
		
		int sum = arraySum.sumOfArray(myArray, 3);
		System.out.println(sum);
		
		myArray[index++] = 7;
		myArray[index++] = 1;
		
		sum = arraySum.sumOfArray(myArray, 5);
		System.out.println(sum);
		
		/* ------------------------------------------- */
		Integer[] myTestArray = {37,46,36,32,54,77};
		
		sum = arraySum.sumOfArray(myTestArray, 3);
		System.out.println(sum); // <-- Should be 119
		
		sum = arraySum.sumOfArray(myTestArray, 6);
		System.out.println(sum); // <-- Should be 282
	}

}