public class Card {
    public enum Color {RED, BLUE, GREEN, YELLOW, WILD}
    public enum Value {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR}

    private Color color;
    private Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public boolean isSpecial() {
        return value != Value.ZERO && value != Value.ONE &&value != Value.TWO && value != Value.THREE && value != Value.FOUR && value != Value.FIVE && value != Value.SIX && value != Value.SEVEN && value != Value.EIGHT && value != Value.NINE;
    }

    public void performAction(Game game, Card.Color chosenColor) {
        switch (value) {
            case SKIP:
            case REVERSE:
            case DRAW_TWO:
                // do nothing, handled by player.applySpecialCard()
                break;
            case WILD:
                game.setCurrentColor(chosenColor);
                break;
            case WILD_DRAW_FOUR:
                game.setCurrentColor(chosenColor);
                break;
        }
    }
}