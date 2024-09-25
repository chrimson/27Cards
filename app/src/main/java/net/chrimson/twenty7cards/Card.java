package net.chrimson.twenty7cards;

import android.graphics.Color;

public class Card {
  String suit;
  String rank;
  int color;

  public Card(String s, String r) {
    suit = s;
    rank = r;

    if (suit == "♣" || suit == "♠") {
      color = Color.parseColor("#7f7f7f");
    }

    if (suit == "♦" || suit == "♥") {
      color = Color.RED;
    }
  }
}
