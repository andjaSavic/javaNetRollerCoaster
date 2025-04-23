package rc.common;

import rc.server.RollercoasterMonitor;
import rc.server.Visitor;

public class Test {
	public static final int KAP = 10;
	public static final int  NumV = 80;
	public static void main(String[] args) {
		
		RollercoasterMonitor rm = new RollercoasterMonitor(KAP);
		Visitor[] posetioci = new Visitor[NumV];
		for(int i=0;i<NumV;i++) {
			posetioci[i] = new Visitor(rm);
			
		}
		rm.start();
		for(int i=0;i<NumV;i++) {
			posetioci[i].start();
			
		}
		
	}

}
