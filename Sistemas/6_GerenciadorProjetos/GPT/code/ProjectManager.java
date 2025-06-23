import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    private List<Board> boards;

    public ProjectManager() {
        boards = new ArrayList<>();
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void addBoard(Board board) {
        boards.add(board);
    }

    public void removeBoard(Board board) {
        boards.remove(board);
    }

    public Board findBoard(String name) {
        for (Board b : boards) {
            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }

    public ProjectList findList(Board board, String listName) {
        for (ProjectList l : board.getLists()) {
            if (l.getName().equalsIgnoreCase(listName)) {
                return l;
            }
        }
        return null;
    }

    public Card findCard(ProjectList list, String cardTitle) {
        for (Card c : list.getCards()) {
            if (c.getTitle().equalsIgnoreCase(cardTitle)) {
                return c;
            }
        }
        return null;
    }
}
