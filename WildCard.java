import java.util.Scanner;

import java.util.Scanner;

public class WildCard extends Card {
    public WildCard() {
        super(Card.Color.WILD, Card.Value.WILD);
    }

    public Card.Color chooseColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a color (R)ed, (B)lue, (G)reen, or (Y)ellow: ");
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "R":
                return Card.Color.RED;
            case "B":
                return Card.Color.BLUE;
            case "G":
                return Card.Color.GREEN;
            case "Y":
                return Card.Color.YELLOW;
            default:
                System.out.println("Invalid color, please choose again.");
                return chooseColor();
        }
    }

    public void performAction(Game game, Card.Color chosenColor) {
        super.performAction(game, chosenColor);
    }
}

