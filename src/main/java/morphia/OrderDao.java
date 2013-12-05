package morphia;

import java.util.List;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.dao.BasicDAO;

public class OrderDao extends BasicDAO<Order, ObjectId> {

	protected OrderDao(Datastore ds) {
		super(ds);
	}

	List<Order> findByItemsQuantity(int quantity) {
		return 
			find( createQuery().filter("items.quantity", quantity) )
			.asList();
	}

	List<Order> findByItemsPriceGreaterThan(double price) {
		return
			find( createQuery().field("items.price").greaterThan(price) )
			.asList();
	}
	
	// Projection
	List<Item> findItems(String customerName) {
		List<Order> orders = find(createQuery().
				field("custInfo").equal(customerName).
				retrievedFields(true, "items")
				).asList();
		
		// check for null in production code!
		return orders.get(0).getItems();
	}
	
	public void deleteAll() {
		deleteByQuery(createQuery());
	}
	
}
