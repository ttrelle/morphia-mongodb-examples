package morphia;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jmkgreen.morphia.Morphia;
import com.mongodb.MongoClient;

public class OrderDaoTest {

	private OrderDao dao;
	
	@Before
	public void setUp() throws UnknownHostException {
		dao = new OrderDao(new Morphia().createDatastore( new MongoClient(), "odm_morphia"));
		dao.deleteAll();
	}
	
	@Test
	public void should_find_by_items_quantity() {
		// given
		Order order = new Order("Tobias Trelle, gold customer");
		List<Item> items = new ArrayList<Item>();
		items.add( new Item(1, 47.11, "Item #1") );
		items.add( new Item(2, 42.0, "Item #2") );
		order.setItems(items);
		dao.save(order);
		
		// when
		List<Order> orders = dao.findByItemsQuantity(2);
		
		// then
		assertNotNull(orders);
		assertEquals(1, orders.size());
	}
	
	@Test
	public void should_find_by_items_price_greater_than() {
		// given
		Order order = new Order("Tobias Trelle, gold customer");
		List<Item> items = new ArrayList<Item>();
		items.add( new Item(1, 47.11, "Item #1") );
		items.add( new Item(2, 42.0, "Item #2") );
		order.setItems(items);
		dao.save(order);
		
		// when
		List<Order> orders = dao.findByItemsPriceGreaterThan(100.0);
		
		// then
		assertNotNull(orders);
		assertEquals(0, orders.size());
	}	

	@Test
	public void should_project_items() {
		// given
		Order order = new Order("Tobias Trelle, gold customer");
		List<Item> items = new ArrayList<Item>();
		items.add( new Item(1, 47.11, "Item #1") );
		items.add( new Item(2, 42.0, "Item #2") );
		order.setItems(items);
		dao.save(order);
		
		// when
		items = dao.findItems("Tobias Trelle, gold customer");
		
		// then
		assertNotNull(items);
		assertEquals(2, items.size());
		
		Item item = items.get(0);
		assertNotNull(item);
		assertNotNull(item.getDescription());
		assertNotNull(item.getPrice());
		assertNotNull(item.getQuantity());
	}	
	
	
}
