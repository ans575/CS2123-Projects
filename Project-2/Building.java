/**
 * This class represents a building before being added into a skyline
 * 
 * @author Abi Sislo
 *1444789
 *CS 2123-01
 */
public class Building {
	private int start;
	private int end;
	private int height;
	/**
	 * 
	 * @param start starting x position of the building
	 * @param end ending x position of the building
	 * @param height height of the building
	 */
	public Building(int start, int end, int height){
		this.start = start;
		this.end = end-1;
		this.height = height;
	}
	/**
	 * 
	 * @return the starting x position of the building
	 */
	public int getStart() {
		return start;
	}
	/**
	 * 
	 * @return the ending x position of the building
	 */
	public int getEnd() {
		return end;
	}
	/**
	 * 
	 * @return the height of the building
	 */
	public int getHeight() {
		return height;
	}
}
