import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner         input    = new Scanner(System.in);
    int[]           myCols   = new int[3];
    String[]        suits    = { "C", "D", "H", "S" };
    String[]        ranks    = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    ArrayList<Card> deck     = new ArrayList<Card>();
    Card[][][]      cardCols = new Card[3][3][3];

    // Construct a deck
    for (int suit = 0; suit < 4; suit++) {
      for (int rank = 0; rank < 13; rank++) {
        deck.add(new Card(suits[suit], ranks[rank]));
      }
    }

    // Choose 27 for the game, assigning columns for each round
    for (int xCols = 0; xCols < 3; xCols++) {
      for (int yCols = 0; yCols < 3; yCols++) {
        for (int zCols = 0; zCols < 3; zCols++) {
          cardCols[xCols][yCols][zCols] = deck.remove((int)(Math.random() * deck.size()));
        }
      }
    }

    // For 3 rounds, place cards in respective columns
    for (int round = 0; round < 3; round++) {
      System.out.print("\n");
      for (int xCols = 0; xCols < 3; xCols++) {
        System.out.printf("%2d: ", xCols);
        for (int yCols = 0; yCols < 3; yCols++) {
          for (int zCols = 0; zCols < 3; zCols++) {
            if (round == 0) System.out.printf("%2s %s  ", cardCols[xCols][yCols][zCols].rank, cardCols[xCols][yCols][zCols].suit);
            if (round == 1) System.out.printf("%2s %s  ", cardCols[yCols][zCols][xCols].rank, cardCols[yCols][zCols][xCols].suit);
            if (round == 2) System.out.printf("%2s %s  ", cardCols[zCols][xCols][yCols].rank, cardCols[zCols][xCols][yCols].suit);
          }
        }
        System.out.print("\n");
      }
      System.out.print("\n");
      myCols[round] = input.nextInt();
    }

    // Select user's card
    Card myCard = cardCols[myCols[0]][myCols[1]][myCols[2]];
    System.out.printf("\n%2s %s !\n\n", myCard.rank, myCard.suit);
  }
}
