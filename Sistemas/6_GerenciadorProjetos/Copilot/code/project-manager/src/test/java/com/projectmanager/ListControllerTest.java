import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.projectmanager.controllers.ListController;
import com.projectmanager.models.List;
import com.projectmanager.services.ListService;
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

public class ListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ListService listService;

    @InjectMocks
    private ListController listController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(listController).build();
    }

    @Test
    public void testCreateList() throws Exception {
        List newList = new List();
        newList.setTitle("New List");

        when(listService.createList(any(List.class))).thenReturn(newList);

        mockMvc.perform(post("/lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"New List\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllLists() throws Exception {
        List list1 = new List();
        list1.setTitle("List 1");
        List list2 = new List();
        list2.setTitle("List 2");

        when(listService.getAllLists()).thenReturn(Arrays.asList(list1, list2));

        mockMvc.perform(get("/lists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("List 1"))
                .andExpect(jsonPath("$[1].title").value("List 2"));
    }

    @Test
    public void testUpdateList() throws Exception {
        List updatedList = new List();
        updatedList.setTitle("Updated List");

        when(listService.updateList(anyLong(), any(List.class))).thenReturn(updatedList);

        mockMvc.perform(put("/lists/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated List\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated List"));
    }

    @Test
    public void testDeleteList() throws Exception {
        doNothing().when(listService).deleteList(anyLong());

        mockMvc.perform(delete("/lists/1"))
                .andExpect(status().isNoContent());
    }
}