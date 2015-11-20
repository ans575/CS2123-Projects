/**
 * 
 * @author Abi Sislo
 * 1444789
 * CS 2013-01
 *
 */
public class HashTable {
	private int[] hash;
	/**
	 * Constructs a hash table of a given table size
	 * @param tableSize size of hash table
	 */
	public HashTable(int tableSize){
		this.setHash(new int[tableSize]);
	}
	/**
	 * Sets a the hash table to the new hash table.
	 * @param hash
	 */
	public void setHash(int[] hash) {
		this.hash = hash;
	}
	/**
	 * Resturns the hash table
	 * @return hash table
	 */
	public int[] getHash() {
		return hash;
	}
	/**
	 * Searches for all key values in the range of 1 to 10,000 in the given hash table using linear probing
	 * @param table hash table that will be searched
	 * @param loadFactor load factor of the hash table, used for print statement output
	 */
	public static void linearProbingSearch(HashTable table, int loadFactor){
		int size = table.getHash().length;
		int keyMaxVal=10000;
		int numSuccessful=0;
		int numUnsuccessful=0;
		int sum=0;
		int sum2=0;
		for(int i=1; i<=keyMaxVal; i++){
			boolean success = false;
			int counter=0;
			int hKey=i%size;
			int probes=1;
			while(counter<=size){
				if(table.getHash()[hKey]==i){
					success=true;
					break;
				}else if(table.getHash()[hKey]==0){
					break;
				}else{
					probes++;
					hKey++;
					counter++;
					if(hKey>=size){
						hKey = hKey-size;
					} 
				}
			}
			if(success){
				numSuccessful++;
//				System.out.println(i + " was found in the Hash table after " + probes + " probes");
				sum+=probes;
			}else{
				numUnsuccessful++;
//				System.out.println(i + " was not found in the Hash table after " + probes + " probes");
				sum2+=probes;
			}
		}
		double avgProbesUS = sum2/(double) numUnsuccessful;
		double avgProbesS = sum/(double )numSuccessful;
		System.out.println("For a hash table with a " + loadFactor + "% load factor using linear probing:");
		System.out.println("The average number of probes for an unsuccessful search: " + avgProbesUS);
		System.out.println("The average number of probes for a successful search: " + avgProbesS);
		System.out.println();
	}
	/**
	 * Searches for all key values in the range of 1 to 10,000 in the given hash table using quadratic probing
	 * @param table hash table to be searched 
	 * @param loadFactor load factor of the hash table, used for print statement output
	 */
	public static void quadraticProbingSearch(HashTable table, int loadFactor){
		int size = table.getHash().length;
		int keyMaxVal=10000;
		int numSuccessful=0;
		int numUnsuccessful=0;
		int sum=0;
		int sum2=0;
		int maxProbe = (size-1)/2;
		for(int i=1;i<=keyMaxVal;i++){
			boolean success=false;
			int probes=1;
			int key = i%table.getHash().length;
			if(table.getHash()[key]==0){
			
			}else if(table.getHash()[key]==i){
				sum+=probes;
				numSuccessful++;
			}else{
				probes++;
				for(int j=1; j<maxProbe; j++){
					int key2 = (i+j*j)%size;
					int key3 = (i-j*j)%size;
					while(key2<0){
						key2+=table.getHash().length;
					}
					while(key3<0){
						key3+=table.getHash().length;
					}
					if(table.getHash()[key2]==i){
						success=true;
						sum += probes;
						break;
					}else if(table.getHash()[key3]==i){
						probes++;
						sum += probes;
						success=true;
						break;
					}else if(table.getHash()[key2]==0){
						break;
					} else if(table.getHash()[key3]==0){
						probes++;
						break;
					} else{
						probes+=2;
					}
				}
			}
			if(!success){
				sum2+=probes;
				numUnsuccessful++;
			}
		}
		double avgProbesUS = sum2 /(double)numUnsuccessful;
		double avgProbesS = sum/(double)numSuccessful;
		System.out.println("For a hash table with a " + loadFactor + "% load factor using quadratic probing:");
		System.out.println("The average number of probes for an unsuccessful search: " + avgProbesUS);
		System.out.println("The average number of probes for a successful search: " + avgProbesS);
		System.out.println();
	}
}
