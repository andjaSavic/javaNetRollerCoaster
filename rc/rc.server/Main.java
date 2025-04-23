package rc.server;


import rc.client.Passenger;
import rc.client.RCar;
import rc.client.RollerCoasterNet;


public class Main {
	public static final int NUM_PASSENGERS = 64;
	
	public static void main(String[] args) {
		
		RollerCoasterNet rcs = new RollerCoasterNet("localhost",5555);
		Passenger[] passengers = new Passenger[NUM_PASSENGERS];
		
		RCar rc = new RCar(0, rcs);
		
		for(int i =0;i<NUM_PASSENGERS;i++) {
			passengers[i] = new Passenger(rcs);
		}
		
		rc.start();
		
		for(int i =0;i<NUM_PASSENGERS;i++) {
			passengers[i].start();
		}
		
		
	}
	

}
