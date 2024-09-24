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
    int[]           colPlan  = new int[3];

    // Construct a deck
    for (int suit = 0; suit < 4; suit++) {
      for (int rank = 0; rank < 13; rank++) {
        deck.add(new Card(suits[suit], ranks[rank]));
      }
    }

    // Choose 27 for the game, assigning columns for each round
    for (colPlan[0] = 0; colPlan[0] < 3; colPlan[0]++) {
      for (colPlan[1] = 0; colPlan[1] < 3; colPlan[1]++) {
        for (colPlan[2] = 0; colPlan[2] < 3; colPlan[2]++) {
          cardCols[colPlan[0]][colPlan[1]][colPlan[2]] = deck.remove((int)(Math.random() * deck.size()));
        }
      }
    }

    // Round 1 of 3, place cards in respective columns
    System.out.print("\n");
    for (colPlan[0] = 0; colPlan[0] < 3; colPlan[0]++) {
      System.out.printf("%2d: ", colPlan[0]);
      for (colPlan[1] = 0; colPlan[1] < 3; colPlan[1]++) {
        for (colPlan[2] = 0; colPlan[2] < 3; colPlan[2]++) {
          Card listCard = cardCols[colPlan[0]][colPlan[1]][colPlan[2]];
          System.out.printf("%2s %s  ", listCard.rank, listCard.suit);
        }
      }
      System.out.print("\n");
    }
    System.out.print("\n");
    myCols[0] = input.nextInt();

    // Round 2 of 3, place cards in respective columns
    System.out.print("\n");
    for (colPlan[1] = 0; colPlan[1] < 3; colPlan[1]++) {
      System.out.printf("%2d: ", colPlan[1]);
      for (colPlan[2] = 0; colPlan[2] < 3; colPlan[2]++) {
        for (colPlan[0] = 0; colPlan[0] < 3; colPlan[0]++) {
          Card listCard = cardCols[colPlan[0]][colPlan[1]][colPlan[2]];
          System.out.printf("%2s %s  ", listCard.rank, listCard.suit);
        }
      }
      System.out.print("\n");
    }
    System.out.print("\n");
    myCols[1] = input.nextInt();

    // Round 3 of 3, place cards in respective columns
    System.out.print("\n");
    for (colPlan[2] = 0; colPlan[2] < 3; colPlan[2]++) {
      System.out.printf("%2d: ", colPlan[2]);
      for (colPlan[0] = 0; colPlan[0] < 3; colPlan[0]++) {
        for (colPlan[1] = 0; colPlan[1] < 3; colPlan[1]++) {
          Card listCard = cardCols[colPlan[0]][colPlan[1]][colPlan[2]];
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
