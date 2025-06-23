import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.projectmanager.controllers.CardController;
import com.projectmanager.models.Card;
import com.projectmanager.services.CardService;
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

public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cardController).build();
    }

    @Test
    public void testCreateCard() throws Exception {
        Card card = new Card("Test Card", "Description", "2023-12-31");
        when(cardService.createCard(any(Card.class))).thenReturn(card);

        mockMvc.perform(post("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Card\",\"description\":\"Description\",\"dueDate\":\"2023-12-31\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Card"));
    }

    @Test
    public void testGetAllCards() throws Exception {
        Card card1 = new Card("Card 1", "Description 1", "2023-12-31");
        Card card2 = new Card("Card 2", "Description 2", "2023-12-31");
        List<Card> cards = Arrays.asList(card1, card2);
        when(cardService.getAllCards()).thenReturn(cards);

        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Card 1"))
                .andExpect(jsonPath("$[1].title").value("Card 2"));
    }

    @Test
    public void testUpdateCard() throws Exception {
        Card card = new Card("Updated Card", "Updated Description", "2023-12-31");
        when(cardService.updateCard(anyLong(), any(Card.class))).thenReturn(card);

        mockMvc.perform(put("/cards/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Card\",\"description\":\"Updated Description\",\"dueDate\":\"2023-12-31\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Card"));
    }

    @Test
    public void testDeleteCard() throws Exception {
        doNothing().when(cardService).deleteCard(anyLong());

        mockMvc.perform(delete("/cards/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testMoveCard() throws Exception {
        doNothing().when(cardService).moveCard(anyLong(), anyLong());

        mockMvc.perform(post("/cards/move/1/to/2"))
                .andExpect(status().isOk());
    }
}