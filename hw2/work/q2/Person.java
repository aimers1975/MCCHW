public class Person extends Thread {

	boolean female;
	SyncBathroomProtocol prot;
	int timeInBath;

	public Person(boolean fem, SyncBathroomProtocol bathProt, int myTimeInBath) {
		this.female = fem;
		this.prot = bathProt;
		this.timeInBath = myTimeInBath; 

	}

	public void run() {
		if(female) {
			prot.enterFemale();
			try {
				Thread.sleep(timeInBath);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			prot.leaveFemale();
		} else {
			prot.enterMale();
			try {
				Thread.sleep(timeInBath);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			prot.leaveMale();			
		}


	}
	
}