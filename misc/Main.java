import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner         input  = new Scanner(System.in);
    int[]           myCols = new int[3];
    String[]        suits  = { "C", "D", "H", "S" };
    String[]        ranks  = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    ArrayList<Card> deck   = new ArrayList<Card>();
    Card[][][]      cards  = new Card[3][3][3];
    int[]           plan   = new int[3];
    ArrayList<Card> column = new ArrayList<Card>();

    // Construct a deck
    for (int suit = 0; suit < 4; suit++) {
      for (int rank = 0; rank < 13; rank++) {
        deck.add(new Card(suits[suit], ranks[rank]));
      }
    }

    // Choose 27 for the game, assigning columns for each round
    for (plan[0] = 0; plan[0] < 3; plan[0]++) {
      for (plan[1] = 0; plan[1] < 3; plan[1]++) {
        for (plan[2] = 0; plan[2] < 3; plan[2]++) {
          cards[plan[0]][plan[1]][plan[2]] = deck.remove((int)(Math.random() * deck.size()));
        }
      }
    }

    // For 3 rounds, list cards in their columns, each time selecting which columns
    for (int round = 0; round < 3; round ++) {
      System.out.print("\n");

      // Cycle order of rounds
      int l = round % 3;
      int m = (round + 1) % 3;
      int n = (round + 2) % 3;

      // Construct each column
      for (plan[l] = 0; plan[l] < 3; plan[l]++) {
        for (plan[m] = 0; plan[m] < 3; plan[m]++) {
          for (plan[n] = 0; plan[n] < 3; plan[n]++) {
            column.add(cards[plan[0]][plan[1]][plan[2]]);
          }
        }

        // Randomize column
        System.out.printf("%2d:  ", plan[l]);
        while (column.size() > 0) {
          Card card = column.remove((int)(Math.random() * column.size()));
          System.out.printf("%2s %s  ", card.rank, card.suit);
        }
        System.out.print("\n");
      }
      System.out.print("\n");

      // User input column
      myCols[round] = input.nextInt();
    }

    // Select user's card
    Card myCard = cards[myCols[0]][myCols[1]][myCols[2]];
    System.out.printf("\n%2s %s !\n\n", myCard.rank, myCard.suit);
  }
}
