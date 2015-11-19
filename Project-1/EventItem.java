import java.util.Random;
/*
 * Abi Sislo
 * 1444789
 * CS 2123
 */
public class EventItem implements Comparable<EventItem> {
	public int tod;
	public int st;
	public int toe;
	/**
	 * Constructor for an EventItem
	 * @param tod
	 * @param st
	 * @param toe
	 */
	public EventItem(int tod, int st, int toe) {
		this.tod = tod;
		this.st = st;
		this.toe = toe;
	}
	/**
	 * Creates a new arrival EventItem
	 * @return
	 */
	public static EventItem newEvent(){
		int tMean=3;
		int tVariance=2;
		int sMean=8;
		int sVariance=3;
		int tUni=uniform(tMean, tVariance, new Random());
		int sUni=uniform(sMean, sVariance, new Random());
		return new EventItem(tUni, sUni, -1);
	}
	/**
	 * Returns a uniform integer
	 * @param mean
	 * @param variance
	 * @param rand
	 * @return
	 */
	public static int uniform(int mean, int variance, Random rand){
		return (mean-variance)+rand.nextInt(2*variance+1);
	}
	@Override
	/**
	 * Allows EventItem's to be compared
	 */
	public int compareTo(EventItem o) {
		return Integer.compare(tod, o.tod);
	}
}
