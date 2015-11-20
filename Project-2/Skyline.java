/**
 * This class generates the maximum height at a given position of a skyline
 * 
 * @author Abi Sislo
 * 1444789
 * CS 2123-01
 *
 */
public class Skyline {
	private int[] skyline;
	/**
	 * Creates a new Skyline with a specified size
	 * @param size width of the skyline
	 */
	public Skyline(int size){
		this.skyline = new int[size];
	}
	/**
	 * Inserts a building into the skyline by comparing the height at the existing skyline at that position to
	 * the buildings height. If the new building is taller than the existing skyline at that position then the 
	 * new skyline height will be the height of the building being added, if not the skyline will remain the same 
	 * height at that position
	 * @param nBuild building being added to the skyline
	 */
	public static Skyline addToSkyline(Building nBuild, Skyline skyline){
		if(nBuild.getStart()>=skyline.getSkyline().length || nBuild.getEnd()>=skyline.getSkyline().length){
			Skyline newSkyline = new Skyline(nBuild.getEnd()+1);
			for(int i= nBuild.getStart();i<=nBuild.getEnd();i++){
				newSkyline.getSkyline()[i] = nBuild.getHeight();
			}
			return mergeSkylines(skyline, newSkyline);
		}else{
			for(int i=nBuild.getStart(); i<=nBuild.getEnd(); i++){
				if(skyline.getSkyline()[i]<nBuild.getHeight()){
					skyline.getSkyline()[i]= nBuild.getHeight();
				}
			}
			return skyline;
		}
	}
	/**
	 * This will merge two skylines together by updating the height at each position in the skyline.
	 * If the new skyline is taller than the old skyline at a specific position then the new height
	 * of the merged skyline will be the height of the new skyline, otherwise it will remain the same.
	 * @param sl2 the skyline being merged with the original skyline
	 * @return the merged skyline
	 */
	public static Skyline mergeSkylines(Skyline originalSkyline, Skyline newSkyline){
		int length =0;
		if(newSkyline.skyline.length>originalSkyline.skyline.length){
			length = newSkyline.skyline.length;
			Skyline tempSkyline = new Skyline(length);
			for(int i=0;i<originalSkyline.skyline.length;i++){
				tempSkyline.skyline[i]= originalSkyline.skyline[i];
			}
			for(int i=0; i<length ;i++){
				if(newSkyline.skyline[i]>=tempSkyline.skyline[i]){
					tempSkyline.skyline[i]= newSkyline.skyline[i];
				}
			}
			return tempSkyline;
		}else{
			length = originalSkyline.skyline.length;
			for(int i=0; i<newSkyline.skyline.length ;i++){
				if(newSkyline.skyline[i]>=originalSkyline.skyline[i]){
					originalSkyline.skyline[i]= newSkyline.skyline[i];
				}
			}
			return originalSkyline;
		}
	}
	/**
	 * 
	 * @return array of heights
	 */
	public int[] getSkyline(){
		return skyline;
	}
}
