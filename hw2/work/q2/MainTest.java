public class MainTest {

	public static void main(String[] args) {
		//ArrayList<Person> line = new ArrayList<Person>();
		SyncBathroomProtocol protocol = new SyncBathroomProtocol();
		Person firstmale = new Person(false,protocol, 5000);
		Person firstfemale = new Person(true,protocol, 10000);
		Person secfemale = new Person(true,protocol, 5000);
		Person secmale = new Person(false,protocol, 2000);
		firstmale.start();
		firstfemale.start();
		secfemale.start();
		secmale.start();
		try {
			firstmale.join();
			firstfemale.join();
			secfemale.join();
			secmale.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}