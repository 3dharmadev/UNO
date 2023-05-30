import java.util.ArrayList;

public class Player {
    private final String id;
    private final String name;
    private final ArrayList<Card> hand;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void receiveInitialCards(ArrayList<Card> cards) {
        hand.addAll(cards);
    }

    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        hand.add(card);
    }

    public boolean playCard(Card card, Card.Color currentColor, Card.Value currentValue) {
        if (card.getColor() == currentColor || card.getValue() == currentValue || card.getColor() == Card.Color.WILD) {
            hand.remove(card);
            return true;
        }
        return false;
    }

    public void applySpecialCard(Card card, Game game) {
        switch (card.getValue()) {
            case SKIP:
                game.skipNextPlayer();
                break;
            case REVERSE:
                game.reverseDirection();
                break;
            case DRAW_TWO:
                game.drawCards(2);
                game.skipNextPlayer();
                break;
            case WILD:
                // do nothing, wild card can be played at any time
                break;
            case WILD_DRAW_FOUR:
                game.drawCards(4);
                game.skipNextPlayer();
                break;
        }
    }

    public boolean hasValidCards(Card.Color currentColor, Card.Value currentValue) {
        for (Card card : hand) {
            if (card.getColor() == currentColor || card.getValue() == currentValue || card.getColor() == Card.Color.WILD) {
                return true;
            }
        }
        return false;
    }
}