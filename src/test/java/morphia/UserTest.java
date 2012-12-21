package morphia;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.mongodb.MongoClient;

public class UserTest {

	private Datastore ds;
	
	@Before
	public void setUp() throws UnknownHostException {
		ds = new Morphia().createDatastore( new MongoClient(), "mtest");

	}
	
	@Test
	public void should_save_user() {
		User u = new User();
		ds.save(u);
	}
	
}
