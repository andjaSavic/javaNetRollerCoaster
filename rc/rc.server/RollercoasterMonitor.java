package rc.server;

import rc.common.RCInterface;

public class RollercoasterMonitor implements RCInterface{
	private int k;
	private int cnt = 0;
	private boolean exit = false;
	private boolean enter = false;
	public static int ID = 0;
	private int id = 0;
	private int visitorTurn=0;
	private int next =0;
	
	@Override
	public synchronized void startRide(int k) {
		this.k = k;
		enter = true;
		System.out.println("Vozilo " + id + " pusta putnike da udju...");
		notifyAll();

		while (cnt < k) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Svi putnici usli u vozilo " + id + " !");
	}
	
	@Override
	public synchronized void endRide(int id) {
		System.out.println("Vozilo " + id + " zavrsilo voznju...");

		exit = true;

		notifyAll();

		while (cnt > 0) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Svi putnici izasli iz vozila " + id + " !");

	}

	@Override
	public synchronized void ride(int id) {
		// cekam dok vozilo ne pozove start ride
		//int myTurn = visitorTurn.getAndIncrement();
		int myTurn = visitorTurn++;
		while (enter == false && myTurn!=next) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Putnik " + id + " usao u vozilo...");
		//next.incrementAndGet();
		cnt++;
		
		if (cnt == k) {
			enter = false;
			
			notifyAll();
		}

		// vozim se....

		// cekam dok vozilo ne pozove end ride
		while (exit == false) {
			try {
				wait();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

		cnt--;
		System.out.println("Putnik " + id + " izasao iz vozila...");

		if (cnt == 0) {
			exit = false;
			next++;
			System.out.println("Poslednji putnik " + id + " izasao iz vozila...");
			notifyAll();
		}

	}

	public RollercoasterMonitor(int k) {
		super();
		this.k = k;
		this.cnt = 0;
		this.exit = false;
		this.enter = false;
		this.id = ID++;
	}

}
