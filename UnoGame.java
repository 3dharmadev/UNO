import java.util.ArrayList;
import java.util.Scanner;

public class UnoGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // initialize players
        ArrayList<Player> players = new ArrayList<>();
        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // consume newline
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            players.add(new Player(Integer.toString(i + 1), name));
        }

        // initialize game
        Game game = new Game(players);
        game.start();
        game.playGame();
    }
}
