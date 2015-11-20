import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Executes the skyline problem
 * 
 * @author Abi Sislo
 * 1444789
 * CS 2123-01
 *
 */
public class Main {
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter a file name to be read: ");
	    String inputFile= scanner.next();
	    try{
		      File tempFile = new File(inputFile);
		      Scanner tempScan=new Scanner( tempFile );
		      ArrayList<Building> buildings = new ArrayList<Building>();
		      while(tempScan.hasNext()){
		    	  int p1=tempScan.nextInt();
		    	  int height=tempScan.nextInt();
		    	  int p2=tempScan.nextInt();
		    	  Building build = new Building(p1, p2, height);
		    	  buildings.add(build);
		      }
		      int size = maxSize(buildings);
		      Skyline dandcResult = divideAndConquerSkyline(buildings,0,buildings.size()-1, size);
		      Skyline inductiveResult = inductiveSkyline(buildings, size);
		      int[] dandcFinal = convert(dandcResult);
		      int[] inductiveFinal = convert(inductiveResult);
		      System.out.println("This is the result using the divide and conquer algorithm:");
		      for(int i=0;i<dandcFinal.length;i++){
		    	  System.out.print(dandcFinal[i] + ", ");
		      }
		      System.out.println();
		      System.out.println("This is the result using the inductive algorithm:");
		      for(int j=0;j<inductiveFinal.length;j++){
		    	  System.out.print(inductiveFinal[j] + ", ");
		      }
		      tempScan.close();
		      scanner.close();
		    } catch (FileNotFoundException e) {
			  System.out.println("File not found");
		    }
	}
	/**
	 * Creates a skyline inductively.
	 * @param input array of buidling to be put into a skyline
	 * @param size width of desired skyline
	 * @return Skyline with buildings put in.
	 */
	public static Skyline inductiveSkyline(ArrayList<Building> input, int size){
		Skyline skyline = new Skyline(size);
		for(int i=0;i<input.size();i++){
			skyline = Skyline.addToSkyline(input.get(i), skyline);
		}
		return skyline;
	}
	/**
	 * Finds the largest end value to a building to use to make the skyline an appropriate size
	 * @param data a list of buildings
	 * @return the maximum end value for a building to an array
	 */
	public static int maxSize(ArrayList<Building> data){
		int maxSize = 0;
		for(int i=0; i<data.size(); i++){
			if(data.get(i).getEnd()>maxSize){
				maxSize = data.get(i).getEnd();
			}
		}
		return maxSize;
	}
	/**
	 * Creates a skyline by dividing the array of buildings and merging them back together, adding buildings in at the
	 * base case
	 * @param input list of buildings to be put into the skyline
	 * @param first starting index in the array
	 * @param last final index in the array
	 * @param size size of the skyine that the buildings will be put into
	 * @return A skyline of buildings
	 */
	public static Skyline divideAndConquerSkyline(ArrayList<Building> input, int first, int last, int size){
		if(first==last){
			Skyline skyline = new Skyline(size);
			skyline = Skyline.addToSkyline(input.get(first), skyline);
			return skyline;
		}else{
			int half = (first+last)/2;
			Skyline firstSkyline = divideAndConquerSkyline(input, first, half, size);
			Skyline secondSkyline = divideAndConquerSkyline(input, half+1, last, size);
			return Skyline.mergeSkylines(firstSkyline, secondSkyline);
			}
		}
	/**
	 * Converts a skyline from spike format to position height position format
	 * @param skyline Skyline to be converted from spike to position height position format
	 * @return a skyline in position height position form
	 */
	public static int[] convert(Skyline skyline){
		int[] heightOnly = skyline.getSkyline();
		ArrayList<Integer> php = new ArrayList<Integer>();
		php.add(0, 0);
		php.add(1, heightOnly[0]);
		int counter = 2;
		for(int i=1;i<heightOnly.length;i++){
			if(heightOnly[i-1]!=heightOnly[i]){
				php.add(counter, i);
				php.add(counter+1, heightOnly[i]);
				counter+=2;
			}
		}
		if(heightOnly[heightOnly.length-1]!=0){
			php.add(heightOnly.length);
			php.add(0);
		}
		int[] phpFinal = new int[php.size()];
		for(int i=0;i<phpFinal.length;i++){
			phpFinal[i] = php.get(i);
		}
		return phpFinal;
	}
}
