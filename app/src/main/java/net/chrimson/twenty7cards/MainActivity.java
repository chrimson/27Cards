package net.chrimson.twenty7cards;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                for (int row = 0; row < 9; row++) {
                    Card card = column.remove((int)(Math.random() * column.size()));

                    String id = "c" + String.valueOf(plan[l]) + String.valueOf(row);
                    int cardId = getResources().getIdentifier(id, "id", getPackageName());
                    TextView cardPlace = findViewById(cardId);
                    cardPlace.setText(String.format("%2s %s", card.rank, card.suit));
                }
            }

            // User input column
            myCols[round] = 0;
        }

        // Select user's card
        Card myCard = cards[myCols[0]][myCols[1]][myCols[2]];
    }
}