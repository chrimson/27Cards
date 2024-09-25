package net.chrimson.twenty7cards;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Card> deck   = null;
    int[]           myCols = new int[3];
    String[]        suits  = { "♣", "♦", "♥", "♠" };
    String[]        ranks  = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    Card[][][]      cards  = new Card[3][3][3];
    int[]           plan   = new int[3];
    ArrayList<Card> column = new ArrayList<Card>();
    int             round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.b0).setOnClickListener(this);
        findViewById(R.id.b1).setOnClickListener(this);
        findViewById(R.id.b2).setOnClickListener(this);

        findViewById(R.id.rstBtn).setOnClickListener(this);

        start();
    }

    protected void start() {
        // Construct a deck
        deck = new ArrayList<Card>();
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

        // Enable only column buttons
        findViewById(R.id.rst).setVisibility(View.GONE);
        findViewById(R.id.colBtns).setVisibility(View.VISIBLE);

        // For round 0, list cards in their columns, then select which column
        round = 0;
        displayCards();
    }

    protected void displayCards() {
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
                cardPlace.setTypeface(null, Typeface.BOLD);
                cardPlace.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                cardPlace.setTextSize(32);
                cardPlace.setTextColor(card.color);
                cardPlace.setText(String.format("%2s %s", card.rank, card.suit));
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.b0) {
            // User input column
            myCols[round] = 0;
        }
        if (view.getId() == R.id.b1) {
            // User input column
            myCols[round] = 1;
        }
        if (view.getId() == R.id.b2) {
            // User input column
            myCols[round] = 2;
        }
        if (view.getId() == R.id.rstBtn) {
            start();
            return;
        }

        round ++;
        if (round < 3) {
            // For round 1 and 2, list cards in their columns, then select which columns
            displayCards();
        } else {
            // Select user's card
            Card myCard = cards[myCols[0]][myCols[1]][myCols[2]];

            // Clear all cards
            for (int col = 0; col < 3; col++) {
                for (int row = 0; row < 9; row++) {
                    String id = "c" + String.valueOf(col) + String.valueOf(row);
                    int cardId = getResources().getIdentifier(id, "id", getPackageName());
                    TextView cardPlace = findViewById(cardId);
                    cardPlace.setText("");
                }
            }

            // Only show my card
            TextView cardPlace = findViewById(R.id.c14);
            cardPlace.setTextSize(96);
            cardPlace.setTextColor(myCard.color);
            cardPlace.setText(String.format("%2s %s", myCard.rank, myCard.suit));

            // Switch to reset button
            findViewById(R.id.colBtns).setVisibility(View.GONE);
            findViewById(R.id.rst).setVisibility(View.VISIBLE);
        }
    }
}