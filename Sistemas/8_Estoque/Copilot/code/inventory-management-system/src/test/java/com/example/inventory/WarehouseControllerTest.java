import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.inventory.controllers.WarehouseController;
import com.example.inventory.models.Warehouse;
import com.example.inventory.services.WarehouseService;
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

public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private WarehouseController warehouseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(warehouseController).build();
    }

    @Test
    public void testCreateWarehouse() throws Exception {
        Warehouse warehouse = new Warehouse(1L, "Warehouse A", "Location A");

        when(warehouseService.createWarehouse(any(Warehouse.class))).thenReturn(warehouse);

        mockMvc.perform(post("/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Warehouse A\", \"location\":\"Location A\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Warehouse A"))
                .andExpect(jsonPath("$.location").value("Location A"));
    }

    @Test
    public void testGetAllWarehouses() throws Exception {
        Warehouse warehouse1 = new Warehouse(1L, "Warehouse A", "Location A");
        Warehouse warehouse2 = new Warehouse(2L, "Warehouse B", "Location B");
        List<Warehouse> warehouses = Arrays.asList(warehouse1, warehouse2);

        when(warehouseService.getAllWarehouses()).thenReturn(warehouses);

        mockMvc.perform(get("/warehouses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testUpdateWarehouse() throws Exception {
        Warehouse warehouse = new Warehouse(1L, "Warehouse A", "Location A");

        when(warehouseService.updateWarehouse(anyLong(), any(Warehouse.class))).thenReturn(warehouse);

        mockMvc.perform(put("/warehouses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Warehouse A Updated\", \"location\":\"Location A Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Warehouse A Updated"))
                .andExpect(jsonPath("$.location").value("Location A Updated"));
    }

    @Test
    public void testDeleteWarehouse() throws Exception {
        doNothing().when(warehouseService).deleteWarehouse(anyLong());

        mockMvc.perform(delete("/warehouses/1"))
                .andExpect(status().isNoContent());
    }
}