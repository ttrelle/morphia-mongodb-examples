package morphia;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;

@Entity("users")
public class User1 {

	@Id ObjectId id;
	
}
