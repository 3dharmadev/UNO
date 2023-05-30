import java.util.Scanner;

public class WildDrawFourCard extends Card {
    public WildDrawFourCard() {
        super(Card.Color.WILD, Card.Value.WILD_DRAW_FOUR);
    }

    public Card.Color chooseColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a color (R)ed, (B)lue, (G)reen, or (Y)ellow: ");
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "R" -> {
                return Color.RED;
            }
            case "B" -> {
                return Color.BLUE;
            }
            case "G" -> {
                return Color.GREEN;
            }
            case "Y" -> {
                return Color.YELLOW;
            }
            default -> {
                System.out.println("Invalid color, please choose again.");
                return chooseColor(); // recursively call chooseColor() until a valid color is chosen
            }
        }
    }

    public void performAction(Game game, Card.Color chosenColor) {
        super.performAction(game, chosenColor);
        game.drawCards(4);
        game.skipNextPlayer();
    }
}







