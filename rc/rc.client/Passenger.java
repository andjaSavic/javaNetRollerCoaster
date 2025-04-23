package rc.client;

import rc.client.RollerCoasterNet;


public class Passenger extends Thread {
	
	public static final int NUM_PASSENGERS = 64;
	
	public static int curr_id = 0;
	public int id = 0;
	
	
	private RollerCoasterNet rc; //servisna klasa

	public Passenger(RollerCoasterNet service) {
		this.id = curr_id++;
		this.rc = service;

	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep((long)(Math.random()*1000));
			} catch (InterruptedException e) {
				
				throw new RuntimeException();
			}
			this.rc.ride(this.id);
		}
	}
	
}
