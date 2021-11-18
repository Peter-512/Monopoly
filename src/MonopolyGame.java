import java.util.Scanner;

public class MonopolyGame {

    public static void main(String[] args) {
        final int MIN_PLAYERS = 2;
        final int MAX_PLAYERS = 8;
        int numberOfPlayers;
        final int MAX_TURNS = 20;
        boolean gameOver = false;


        // Read in amount of players
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter the amount of players (2-8): ");
        numberOfPlayers = keyboard.nextInt();
        while (numberOfPlayers > MAX_PLAYERS || numberOfPlayers < MIN_PLAYERS) {
            System.out.print("Invalid amount. Try again: ");
            numberOfPlayers = keyboard.nextInt();
        }

        // Initialize instances
        Board board = new Board();
        Die dieOne = new Die();
        Die dieTwo = new Die();
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(String.format("Player #%d", i + 1), board, dieOne, dieTwo);
        }

        // Game loop
        for (int currentTurn = 1; currentTurn <= MAX_TURNS; currentTurn++) {    // Loop through turns
            System.out.printf("Turn #%d%n", currentTurn);
            for (Player player : players) {                                     // Loop through players

                // check if more than one player is left
                int countStillPlaying = 0;
                for (Player p : players) {
                    if (!p.isBankrupt()) {
                        countStillPlaying++;
                    }
                }
                if (countStillPlaying == 1) {
                    gameOver = true;
                    break;
                }

                // Skip player if bankrupt
                if (player.isBankrupt()) continue;

                // Player turn
                System.out.println(player.getName());
                player.move();
                player.tryToBuy();
                System.out.println(dieOne);
                System.out.println(dieTwo);
                System.out.printf("Position: %s %n", player.piece.getPosition().getName());
                System.out.printf("Money: %s$ %n", player.getMoney());
                System.out.println(player.piece.getPosition());
                System.out.println();

                // Set player bankrupt if he has negative money
                if (player.getMoney() < 0) {
                    player.setBankrupt();
                }
            }
            if (gameOver) break;
        }
        
        // Find winner
        // TODO: possibility of a tie
        Player winner = null;
        for (Player player : players) {
            if (winner == null) {
                winner = player;
            } else
                if (winner.getMoney() < player.getMoney()) {
                winner = player;
            }
        }
        System.out.printf("%s is the winner! %n", winner.getName());

        keyboard.close();
    }
}
