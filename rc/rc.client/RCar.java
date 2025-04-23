package rc.client;

import rc.client.RollerCoasterNet;
import rc.server.Server;

public class RCar extends Thread {
	
	public static final int NUM_PASSENGERS = 64;
	
	public static int curr_id = 0;
	public int id = 0;
	
	
	public RollerCoasterNet rc; //servisna klasa

	public RCar(int id, RollerCoasterNet service) {
		this.id = curr_id++;
		this.rc = service;
		
	}
	
	
	@Override
	public void run() {
	
		int i=0;
		while(i<8) {
			i++;
			
			System.out.println("Iteracija " + i);
			rc.startRide(curr_id);
			try {
				Thread.sleep(5000);
				//moze se smanjiti ovo vreme, ovo mi kao 						//simulira trajanje voznje
			} catch(InterruptedException e){
				throw new RuntimeException(e);
			}
			rc.endRide(curr_id);
		}
		
	}
	
}
