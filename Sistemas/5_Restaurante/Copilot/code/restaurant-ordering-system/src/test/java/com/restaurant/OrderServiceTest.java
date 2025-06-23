import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.restaurant.models.Order;
import com.restaurant.models.MenuItem;
import com.restaurant.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void testCreateOrder() {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Pizza", 10.0, "Delicious cheese pizza"));
        items.add(new MenuItem("Pasta", 8.0, "Creamy Alfredo pasta"));

        Order order = orderService.createOrder(items);
        assertNotNull(order);
        assertEquals(2, order.getMenuItems().size());
        assertEquals(18.0, order.getTotalPrice());
        assertEquals("Pending", order.getStatus());
    }

    @Test
    public void testViewOrder() {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Burger", 5.0, "Juicy beef burger"));
        Order order = orderService.createOrder(items);

        Order fetchedOrder = orderService.viewOrder(order.getId());
        assertNotNull(fetchedOrder);
        assertEquals(order.getId(), fetchedOrder.getId());
    }

    @Test
    public void testUpdateOrderStatus() {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Salad", 4.0, "Fresh garden salad"));
        Order order = orderService.createOrder(items);

        orderService.updateOrderStatus(order.getId(), "In Progress");
        Order updatedOrder = orderService.viewOrder(order.getId());
        assertEquals("In Progress", updatedOrder.getStatus());
    }
}