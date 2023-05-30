import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        for (Card.Color color : Card.Color.values()) {
            if (color == Card.Color.WILD) {
                for (int i = 0; i < 4; i++) {
                    cards.add(new WildCard());
                    cards.add(new WildDrawFourCard());
                }
            }
            else {
                for (Card.Value value : Card.Value.values()) {
                    if (value == Card.Value.ZERO) {
                        cards.add(new Card(color, value));
                    } else if (value != Card.Value.WILD && value != Card.Value.WILD_DRAW_FOUR) {
                        for (int i = 0; i < 2; i++) {
                            cards.add(new Card(color, value));
                        }
                    }
                }
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            replenish();
        }
        return cards.remove(cards.size() - 1);
    }

    private void replenish() {
        cards.clear();
        initialize();
        shuffle();
    }
}