import java.util.LinkedList;
import java.util.Queue;
/*
 * Abi Sislo
 * 1444789
 * CS 2123
 */
public class Teller {
	/**
	 * Initiating teller fields for the teller object
	 */
	public Queue<EventItem> teller = new LinkedList<EventItem>();
	public double idleTime=0.0;
}
