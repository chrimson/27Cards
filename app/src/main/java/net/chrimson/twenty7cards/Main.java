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

    // Round 1 of 3, place cards in respective columns
    System.out.print("\n");
    for (int xCols = 0; xCols < 3; xCols++) {
      System.out.printf("%2d: ", xCols);
      for (int yCols = 0; yCols < 3; yCols++) {
        for (int zCols = 0; zCols < 3; zCols++) {
          Card listCard = cardCols[xCols][yCols][zCols];
          System.out.printf("%2s %s  ", listCard.rank, listCard.suit);
        }
      }
      System.out.print("\n");
    }
    System.out.print("\n");
    myCols[0] = input.nextInt();

    // Round 2 of 3, place cards in respective columns
    System.out.print("\n");
    for (int yCols = 0; yCols < 3; yCols++) {
      System.out.printf("%2d: ", yCols);
      for (int zCols = 0; zCols < 3; zCols++) {
        for (int xCols = 0; xCols < 3; xCols++) {
          Card listCard = cardCols[xCols][yCols][zCols];
          System.out.printf("%2s %s  ", listCard.rank, listCard.suit);
        }
      }
      System.out.print("\n");
    }
    System.out.print("\n");
    myCols[1] = input.nextInt();

    // Round 3 of 3, place cards in respective columns
    System.out.print("\n");
    for (int zCols = 0; zCols < 3; zCols++) {
      System.out.printf("%2d: ", zCols);
      for (int xCols = 0; xCols < 3; xCols++) {
        for (int yCols = 0; yCols < 3; yCols++) {
          Card listCard = cardCols[xCols][yCols][zCols];
          System.out.printf("%2s %s  ", listCard.rank, listCard.suit);
        }
      }
      System.out.print("\n");
    }
    System.out.print("\n");
    myCols[2] = input.nextInt();

    // Select user's card
    Card myCard = cardCols[myCols[0]][myCols[1]][myCols[2]];
    System.out.printf("\n%2s %s !\n\n", myCard.rank, myCard.suit);
  }
}
