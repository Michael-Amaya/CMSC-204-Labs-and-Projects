import java.util.Arrays;
import java.util.LinkedList;

public class Main {
	
	static boolean isPrime(int n) { 
        // Corner case 
        if (n <= 1) 
            return false; 
  
        // Check from 2 to n-1 
        for (int i = 2; i < n; i++) 
            if (n % i == 0) 
                return false; 
  
        return true; 
    }
	
	public static void main(String[] args) throws CollisionException {
		lqHash();
		bucketHash();
	}
	
	public static void bucketHash() {
		System.out.println("============== Buckets ================ ");
		
		Integer[] arrayToPutInHash = {27,53,13,10,138,109,49,174,26,24};
		int tableSize = 10;
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] bucket = new LinkedList[tableSize];
		
		for (int i = 0; i < arrayToPutInHash.length; i++) {
			Integer current = arrayToPutInHash[i];
			int initialPlacement = current % tableSize;
			
			if (bucket[initialPlacement] == null)
				bucket[initialPlacement] = new LinkedList<Integer>();
			
			bucket[initialPlacement].add(current);
		}
		
		for(LinkedList<Integer> current : bucket) {
			if (current == null) {
				System.out.print("null");
				System.out.println();
				continue;
			}
				
			for (Integer item : current) {
				System.out.print(item + ", ");
			}
			
			System.out.println();
		}
		
		System.out.println("====================================");
	}
	
	public static void lqHash() throws CollisionException {
		System.out.println("============== LQ ================ ");
		Integer[] arrayToPutInHash = {27,53,13,10,138,109,49,174,26,24};
		
		int numOfCollisions = 0;
		int tableSize = 13;
		int k = 19;
		
		HashTable hc = new HashTable(tableSize);
		
		for(int i = 0; i < arrayToPutInHash.length; i++) {
			Integer current = arrayToPutInHash[i];
			int initialPlacement = current % tableSize;
			int q = (int) Math.ceil(current / tableSize);
			int offset;
			
			if (q % tableSize != 0) {
				offset = q;
			} else {
				offset = k;
			}
			
			int currentCollisions = 0;
			while (hc.get(initialPlacement) != null) {
				numOfCollisions++;
				currentCollisions++;
				System.out.println("Collisions: " + currentCollisions + " for " + current);
				initialPlacement = (initialPlacement + offset) % tableSize;
			}
			
			System.out.println("Putting " + current + " in " + initialPlacement);
			hc.add(initialPlacement, current);
		}
		
		System.out.println(numOfCollisions);
		System.out.println(hc);
		
		System.out.println("====================================");
	}
}
