import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.restaurant.models.Order;
import com.restaurant.models.KitchenStatus;
import com.restaurant.services.KitchenService;

class KitchenServiceTest {

    private KitchenService kitchenService;
    private Order order;

    @BeforeEach
    void setUp() {
        kitchenService = new KitchenService();
        order = new Order();
        order.setId(1);
        order.addMenuItem("Pizza", 10.0);
        order.addMenuItem("Pasta", 8.0);
    }

    @Test
    void testSendOrderToKitchen() {
        kitchenService.sendOrderToKitchen(order);
        KitchenStatus status = kitchenService.getOrderStatus(order.getId());
        assertEquals("preparing", status.getStatus());
    }

    @Test
    void testUpdateOrderStatus() {
        kitchenService.sendOrderToKitchen(order);
        kitchenService.updateOrderStatus(order.getId(), "ready");
        KitchenStatus status = kitchenService.getOrderStatus(order.getId());
        assertEquals("ready", status.getStatus());
    }

    @Test
    void testGetOrderStatus() {
        kitchenService.sendOrderToKitchen(order);
        KitchenStatus status = kitchenService.getOrderStatus(order.getId());
        assertNotNull(status);
        assertEquals(order.getId(), status.getOrderId());
    }
}