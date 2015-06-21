public class MainTest {

	public static void main(String[] args) {
		//ArrayList<Person> line = new ArrayList<Person>();
		SyncBathroomProtocol protocol = new SyncBathroomProtocol();
		Person firstmale = new Person(false,protocol, 5000);
		Person thmale = new Person(false,protocol, 7000);
		Person firstfemale = new Person(true,protocol, 10000);
		Person secfemale = new Person(true,protocol, 5000);
		Person thfemale = new Person(true, protocol,10000);
		Person secmale = new Person(false,protocol, 2000);
		Person fofemale = new Person(true, protocol,5000);
		Person fifemale = new Person(true, protocol,6000);
		Person sifemale = new Person(true, protocol,6000);
		Person sefemale = new Person(true, protocol,6000);
		Person fomale = new Person(false,protocol, 2000);
		Person fimale = new Person(false,protocol, 2000);

		firstmale.start();
		thmale.start();
		firstfemale.start();
		secfemale.start();
		thfemale.start();
		secmale.start();
		fofemale.start();
		fifemale.start();
		sifemale.start();
		sefemale.start();
		fomale.start();
		fimale.start();
		try {
			firstmale.join();
			thmale.join();
			firstfemale.join();
			secfemale.join();
			thfemale.join();
			secmale.join();
			fofemale.join();
			fifemale.join();
			sifemale.join();
			sefemale.join();
			fomale.join();
			fimale.join();			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}