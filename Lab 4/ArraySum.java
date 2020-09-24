/** ArraySum serves the purpose of containing one recursive
 *  method to find the sum of the integers in an array
 * 
 *  @author Michael Amaya
 *
 */
public class ArraySum {
	
	/** This method is recursive. The way it works is it will keep
	 *  calling itself until the index is 0. It calls itself by sending
	 *  the array needed to be worked on and an index minus one. This causes
	 *  the program to run again and returns the sum of the items in the
	 *  array, finally adding the remaining item in the array.
	 *  
	 *  @param a		The array to be summed up
	 *  @param index	The current index in the array being added
	 *  @return			The sum of the numbers in the array from the top of the array, going backwards
	 */
	public int sumOfArray (Integer[] a,int index) {
		if (index == 0) return 0;
		return sumOfArray(a, index - 1) + a[index - 1];
	}
}
