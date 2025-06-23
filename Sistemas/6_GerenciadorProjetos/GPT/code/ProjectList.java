import java.util.ArrayList;
import java.util.List;

public class ProjectList {
    private String name;
    private List<Card> cards;

    public ProjectList(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    @Override
    public String toString() {
        return "List{" +
                "name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }
}
