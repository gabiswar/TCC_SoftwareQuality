import java.util.ArrayList;
import java.util.List;

public class Board {
    private String name;
    private String description;
    private List<ProjectList> lists;

    public Board(String name, String description) {
        this.name = name;
        this.description = description;
        this.lists = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProjectList> getLists() {
        return lists;
    }

    public void addList(ProjectList list) {
        lists.add(list);
    }

    public void removeList(ProjectList list) {
        lists.remove(list);
    }

    @Override
    public String toString() {
        return "Board{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lists=" + lists +
                '}';
    }
}
