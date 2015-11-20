import java.util.ArrayList;
import java.util.Random;
/**
 * @author Abi Sislo
 * 1444789
 * CS 2123-01
 *
 */
public class Main {
	public static void main(String args[]){
		int tableSize=1019;
		ArrayList<Integer> keys = randomKeys(.6, tableSize);
		ArrayList<Integer> keys2 = randomKeys(.8, tableSize);
		HashTable ht = new HashTable(tableSize);
		HashTable ht2 = new HashTable(tableSize);
		HashTable ht3 = new HashTable(tableSize);
		HashTable ht4 = new HashTable(tableSize);
		linearProbing(ht, keys);
		linearProbing(ht2, keys2);
		quadraticProbing(ht3, keys);
		quadraticProbing(ht4, keys2);
		HashTable.linearProbingSearch(ht, 60);
		HashTable.linearProbingSearch(ht2, 80);
		HashTable.quadraticProbingSearch(ht3, 60);
		HashTable.quadraticProbingSearch(ht4, 80);
	}
	/**
	 * Generates a list  of unique random keys in the range 1 to 10,000 that will fill a hash table of given size
	 * for a given load factor
	 * @param loadFactor load factor of hash table that the keys will be used to fill
	 * @param tableSize table size of that hash table that the keys will be inserted into
	 * @return list of unique random keys
	 */
	public static ArrayList<Integer> randomKeys(double loadFactor, int tableSize){
		int size = (int) (tableSize * loadFactor);
		ArrayList<Integer> randKeys = new ArrayList<Integer>(size);
		for(int i=0; i<size; i++){
			Random rn = new Random();
			int rand = rn.nextInt(10000)+1;
			while(randKeys.contains(rand)){
				rand = rn.nextInt(10000)+1;
			}
			randKeys.add(rand);
		}
		return randKeys;
	}
	/**
	 * Inserts all of the keys into the hash table using the linear probing method
	 * @param table Hash table that keys will be inserted into
	 * @param rKeys list of keys that will be put into the hash table
	 */
	public static void linearProbing(HashTable table, ArrayList<Integer> rKeys){
		int size = table.getHash().length;
		for(int i=0; i<rKeys.size(); i++){
			int hKey=rKeys.get(i)%size;
			if(table.getHash()[hKey]==0){
				table.getHash()[hKey]=rKeys.get(i);
			}else{
				int counter=0;
				while(table.getHash()[hKey]!=0 && counter<=size){
					hKey+=1;
					counter+=1;
					if(hKey>=size){
						hKey = hKey-size;
					} 
				}
				table.getHash()[hKey]=rKeys.get(i);
			}
		}
	}
	/**
	 * Inserts the keys into the hash table using the quadratic probing method
	 * @param table Hash table that keys will be inserted into
	 * @param rKeys list of keys that will be put into the hash table
	 */
	public static void quadraticProbing(HashTable table, ArrayList<Integer> rKeys){
		int maxProbe = (table.getHash().length-1)/2;
		int key = rKeys.get(0)%table.getHash().length;
		table.getHash()[key]=rKeys.get(0);
		for(int idx=0;idx<rKeys.size();idx++){
			int nKey = rKeys.get(idx)%table.getHash().length;
			if(table.getHash()[nKey]==0){
				table.getHash()[nKey]=rKeys.get(idx);
			} else{
				for(int j=1; j<maxProbe; j++){
					int key2 = (rKeys.get(idx)+j*j)%table.getHash().length;
					while(key2<0){
						key2+=table.getHash().length;
					}
					if(table.getHash()[key2]==0){
						table.getHash()[key2]= rKeys.get(idx);
						break;
					}
					int key3 = (rKeys.get(idx)-j*j)%table.getHash().length;
					while(key3<0){
						key3+=table.getHash().length;
					}
					if(table.getHash()[key3]==0){
						table.getHash()[key3]= rKeys.get(idx);
						break;
					}
				}
			}
		}
	}
}
