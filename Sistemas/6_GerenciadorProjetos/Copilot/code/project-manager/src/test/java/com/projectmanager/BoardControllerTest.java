import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.projectmanager.controllers.BoardController;
import com.projectmanager.models.Board;
import com.projectmanager.services.BoardService;
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

public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private BoardController boardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

    @Test
    public void testCreateBoard() throws Exception {
        Board board = new Board("Test Board", "Description");
        when(boardService.createBoard(any(Board.class))).thenReturn(board);

        mockMvc.perform(post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Board\", \"description\":\"Description\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Board"))
                .andExpect(jsonPath("$.description").value("Description"));
    }

    @Test
    public void testGetAllBoards() throws Exception {
        Board board1 = new Board("Board 1", "Description 1");
        Board board2 = new Board("Board 2", "Description 2");
        List<Board> boards = Arrays.asList(board1, board2);
        when(boardService.getAllBoards()).thenReturn(boards);

        mockMvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Board 1"))
                .andExpect(jsonPath("$[1].name").value("Board 2"));
    }

    @Test
    public void testUpdateBoard() throws Exception {
        Board updatedBoard = new Board("Updated Board", "Updated Description");
        when(boardService.updateBoard(anyLong(), any(Board.class))).thenReturn(updatedBoard);

        mockMvc.perform(put("/boards/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Board\", \"description\":\"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Board"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    public void testDeleteBoard() throws Exception {
        doNothing().when(boardService).deleteBoard(anyLong());

        mockMvc.perform(delete("/boards/1"))
                .andExpect(status().isNoContent());
    }
}