import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final ArrayList<Player> players;
    private final Deck deck;
    private Card currentCard;
    private Card.Color currentColor;
    private int currentPlayerIndex;
    private int direction;
    private boolean gameOver;

    public Game(ArrayList<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.currentCard = deck.drawCard();
        this.currentColor = currentCard.getColor();
        this.currentPlayerIndex = 0;
        this.direction = 1;
        this.gameOver = false;
    }

    public void start() {
        for (Player player : players) {
            ArrayList<Card> initialCards = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                initialCards.add(deck.drawCard());
            }
            player.receiveInitialCards(initialCards);
        }
    }

    public void playGame() {
        while (!gameOver) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("Current player: " + currentPlayer.getName());
            if (!currentPlayer.hasValidCards(currentColor, currentCard.getValue())) {
                System.out.println("Drawing card...");
                currentPlayer.drawCard(deck);
            }
            System.out.println("Current card: " + currentCard.getColor() + " " + currentCard.getValue());
            System.out.println("Current color: " + currentColor);
            System.out.println("Your hand: " + currentPlayer.getHand());
            Card selectedCard = currentPlayer.getHand().get(0); // default to first card
            boolean validPlay = false;
            while (!validPlay) {
                selectedCard = currentPlayer.getHand().get(0); // default to first card
                if (selectedCard.isSpecial()) {
                    System.out.println("Special card played: " + selectedCard.getValue());
                }
                validPlay = currentPlayer.playCard(selectedCard, currentColor, currentCard.getValue());
                if (!validPlay) {
                    System.out.println("Invalid play, please choose a different card.");
                    break;
                }
            }
            if (validPlay) {
                if (selectedCard.isSpecial()) {
                    ((WildCard) selectedCard).performAction(this, currentColor);
                }
                if (selectedCard.getColor() == Card.Color.WILD) {
                    currentColor = ((WildCard) selectedCard).chooseColor();
                } else {
                    currentColor = selectedCard.getColor();
                }
                currentCard = selectedCard;
                System.out.println("Played card: " + currentCard.getColor() + " " + currentCard.getValue());
                if (currentPlayer.getHand().isEmpty()) {
                    gameOver = true;
                    System.out.println(currentPlayer.getName() + " wins!");
                } else {
                    currentPlayerIndex = getNextPlayerIndex();
                }
            }
        }
    }



    public void skipNextPlayer() {
        currentPlayerIndex = getNextPlayerIndex();
    }

    public void reverseDirection() {
        direction *= -1;
        currentPlayerIndex = getNextPlayerIndex();
    }

    public void drawCards(int numCards) {
        Player nextPlayer = getNextPlayer();
        for (int i = 0; i < numCards; i++) {
            nextPlayer.drawCard(deck);
        }
    }

    private int getNextPlayerIndex() {
        int nextPlayerIndex = currentPlayerIndex + direction;
        if (nextPlayerIndex < 0) {
            nextPlayerIndex = players.size() - 1;
        } else if (nextPlayerIndex >= players.size()) {
            nextPlayerIndex = 0;
        }
        return nextPlayerIndex;
    }

    private Player getNextPlayer() {
        return players.get(getNextPlayerIndex());
    }

    public void setCurrentColor(Card.Color color) {
        currentColor = color;
    }
}