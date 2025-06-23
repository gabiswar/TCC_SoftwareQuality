import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.restaurant.models.Order;
import com.restaurant.models.MenuItem;
import com.restaurant.services.PaymentService;
import com.restaurant.utils.ReceiptGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PaymentServiceTest {

    private PaymentService paymentService;
    private ReceiptGenerator receiptGenerator;

    @BeforeEach
    public void setUp() {
        receiptGenerator = mock(ReceiptGenerator.class);
        paymentService = new PaymentService(receiptGenerator);
    }

    @Test
    public void testCalculateTotalPrice() {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Pizza", 10.0, "Delicious cheese pizza"));
        items.add(new MenuItem("Pasta", 8.0, "Creamy Alfredo pasta"));

        Order order = new Order();
        order.setMenuItems(items);

        double totalPrice = paymentService.calculateTotalPrice(order);
        assertEquals(18.0, totalPrice);
    }

    @Test
    public void testGenerateReceipt() {
        Order order = new Order();
        order.setId(1);
        order.setTotalPrice(20.0);

        when(receiptGenerator.generateReceipt(order)).thenReturn("Receipt for Order ID: 1\nTotal: $20.0");

        String receipt = paymentService.generateReceipt(order);
        assertEquals("Receipt for Order ID: 1\nTotal: $20.0", receipt);
        verify(receiptGenerator).generateReceipt(order);
    }
}