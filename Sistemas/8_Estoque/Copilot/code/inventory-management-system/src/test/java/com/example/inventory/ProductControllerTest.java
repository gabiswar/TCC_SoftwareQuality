import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.inventory.controllers.ProductController;
import com.example.inventory.models.Product;
import com.example.inventory.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testRegisterProduct() throws Exception {
        Product product = new Product(1L, "Product A", "Description A", 10.0, 100);
        when(productService.registerProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Product A\",\"description\":\"Description A\",\"price\":10.0,\"quantity\":100}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Product A"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product(1L, "Product A", "Description A", 10.0, 100);
        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Product A Updated\",\"description\":\"Description A Updated\",\"price\":12.0,\"quantity\":80}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product A"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(anyLong());

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product(1L, "Product A", "Description A", 10.0, 100);
        Product product2 = new Product(2L, "Product B", "Description B", 15.0, 200);
        List<Product> products = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Product A"))
                .andExpect(jsonPath("$[1].name").value("Product B"));
    }
}