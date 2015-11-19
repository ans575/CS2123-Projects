import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;
/*
 * Abi Sislo
 * 1444789
 * CS 2123
 */
public class Bank {
	private PriorityQueue<EventItem> events;
	private ArrayList<Teller> tellers;

	public static void main(String[] args) {
		for (int i = 2; i < 5; i++) {
			run(i);
		}
	}
	/**
	 * Constructs a Bank object given the parameters of a PriorityQueue<EventItem> and an ArrayList<Teller>
	 * @param events
	 * @param tellers
	 */
	public Bank(PriorityQueue<EventItem> events, ArrayList<Teller> tellers) {
		this.events = events;
		this.tellers = tellers;
	}
	/**
	 * Runs the simulation
	 * @param tellerNum
	 */
	public static void run(int tellerNum) {
		int clock = 0;
		int delta = 0;
		int cProcessed=0;
		int tArrTime=0;
		int cArr=0;
		int maxLength=0;
		int waitTime=0;
		int maxWaitTime=0;
		int tServiceTime=0;
		int nextPrint = 500;
		int timeLimit = 2000;
		PriorityQueue<EventItem> events = new PriorityQueue<EventItem>();
		ArrayList<Teller> tellerList = new ArrayList<Teller>();
		Bank bank = new Bank(events, tellerList);
		for (int knt = 0; knt < tellerNum; knt++) {
			bank.tellers.add(new Teller());
		}
		EventItem nextEvent = EventItem.newEvent();
		bank.events.add(nextEvent);
		tArrTime+=nextEvent.tod;
		cArr+=1;
		while (clock <= timeLimit) {
			EventItem event = bank.events.poll();
			delta = event.tod - clock;
			clock = event.tod;
			if (clock > nextPrint) {
				snapshot(tellerNum, tellerList, clock, bank);
				nextPrint = nextPrint + 500;
			}
			if (event.toe == -1) {
				Teller minTell = minTellerLength(tellerNum, bank.tellers);
				if (minTell.teller.size() == 0) {
					minTell.teller.add(event);
					EventItem departEvent = event;
					waitTime+=clock-event.tod;
					maxWaitTime = Math.max(maxWaitTime, clock-event.tod);
					departEvent.toe = bank.tellers.indexOf(minTell);
					departEvent.tod = departEvent.tod + clock;
					bank.events.add(departEvent);
				} else {
					minTell.teller.add(event);
				}
				EventItem next = EventItem.newEvent();
				next.tod = next.tod + clock;
				tArrTime+=next.tod-clock;
				bank.events.add(next);
				cArr++;
				tServiceTime+=next.st;
			} else {
				Teller thing = bank.tellers.get(event.toe);
				EventItem trash = thing.teller.poll();
				if (thing.teller.size() > 0) {
					EventItem nextDepart = thing.teller.peek();
					waitTime+=clock-nextDepart.tod;
					maxWaitTime = Math.max(maxWaitTime, clock-nextDepart.tod);
					nextDepart.toe = bank.tellers.indexOf(thing);
					nextDepart.tod = clock
							+ EventItem.uniform(8, 3, new Random());
					bank.events.add(nextDepart);
				}
				cProcessed++;
			}
			for (int m = 0; m < tellerNum; m++) {
				if (bank.tellers.get(m).teller.size() == 0) {
					bank.tellers.get(m).idleTime = bank.tellers.get(m).idleTime + delta;
				}if(bank.tellers.get(m).teller.size()>=maxLength){
					maxLength = bank.tellers.get(m).teller.size();
				}
			}
		}
		runStats(cProcessed, tArrTime, cArr, tServiceTime, tellerNum, bank.tellers, maxLength, waitTime, maxWaitTime);
	}
	/**
	 * Finds and returns the teller with the minimum queue length in the bank.
	 * @param tellerNum
	 * @param tellerList
	 * @return
	 */
	public static Teller minTellerLength(int tellerNum,
			ArrayList<Teller> tellerList) {
		ArrayList<Integer> tellerSizes = new ArrayList<Integer>();
		for (int i = 0; i < tellerNum; i++) {
			int temp = tellerList.get(i).teller.size();
			tellerSizes.add(temp);
		}
		int mindex = tellerSizes.indexOf(Collections.min(tellerSizes));
		return tellerList.get(mindex);
	}
	/**
	 * Prints out a snapshot of the simulation at the appropriate time
	 * @param tellerNum
	 * @param tellerList
	 * @param clock
	 * @param bank
	 */
	public static void snapshot(int tellerNum, ArrayList<Teller> tellerList,
			int clock, Bank bank) {
		for (int i = 0; i < tellerNum; i++) {
			System.out.println("Number of people in line for teller " + (i + 1)
					+ ": " + tellerList.get(i).teller.size());
			System.out.println("Idle time for teller " + (i + 1) + ": "
					+ tellerList.get(i).idleTime);
		}
		System.out.println("Number of events: " + bank.events.size());
		System.out.println("Clock time: " + clock);
		System.out.println();
	}

	public static void runStats(int cProcessed, double tArrTime, int cArr, double tServiceTime, 
			int tellerNum, ArrayList<Teller> tellerList, int maxLength, double waitTime, int maxWaitTime) {
		System.out.println("The total number of customers processed: " + cProcessed);
		System.out.println("The average inter-arrival time: " + (tArrTime/cArr));
		System.out.println("The avaerage service time: " + (tServiceTime/cArr));
		System.out.println("The average wait time per customer: " + (waitTime/cArr));
		for(int i=1;i<=tellerNum;i++){
			System.out.println("Percent of idle time for teller " + i + ": " + ((tellerList.get(i-1).idleTime/2000)*100)+"%");
		}
		System.out.println("The maximum customer wait time: " + maxWaitTime);
		System.out.println("The maximum queue length of any customer queue: " + maxLength);
		int total=0;
		for(int j=1; j<=tellerNum;j++){
			System.out.println("The total number of people left in teller " + j + "'s line: "+ (tellerList.get(j-1).teller.size()));
			total+=tellerList.get(j-1).teller.size();
		}
		System.out.println("The total number of people left in all of the teller lines: " + total);
		System.out.println();
	}
}
