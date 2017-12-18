package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
  private ArrayList<Card> cards;

  public Player() {
    this.cards = new ArrayList<Card>();
  }

  public void getCard(Card card) {
    cards.add(card);
  }

  public LargestCard getLargestCardType() {
    HashMap<Suit, Integer> cardBucket = new HashMap<>();
    cardBucket.put(Suit.ClUB, 0);
    cardBucket.put(Suit.DIAMOND, 0);
    cardBucket.put(Suit.HEART, 0);
    cardBucket.put(Suit.SPADE, 0);
    cardBucket.put(Suit.SMALL_JOKER, 0);
    cardBucket.put(Suit.LARGE_JOKER, 0);
    for(Card card : cards) {
      Suit suit = card.getSuit();
      if (card.getNumber() == 2 || suit == Suit.SMALL_JOKER || suit == Suit.LARGE_JOKER) {
        cardBucket.put(suit, cardBucket.get(suit) + 1);
        continue;
      }
    }

    LargestCard currentLargest = LargestCard.NOTHING;
    for (HashMap.Entry<Suit, Integer> entry : cardBucket.entrySet()) {
      Suit suit = entry.getKey();
      Integer num = entry.getValue();
      LargestCard newLargest = LargestCard.NOTHING;
      if (num >= 2) {
        if (suit == Suit.SMALL_JOKER || suit == Suit.LARGE_JOKER) {
          newLargest = num ==2 ? LargestCard.DOUBLE_JOKER : LargestCard.TRIPLE_JOKER;
        } else {
          newLargest = num == 2 ? LargestCard.DOUBLE_REGULAR : LargestCard.TRIPLE_REGULAR;
        }
      }
      if (newLargest.ordinal() > currentLargest.ordinal()) {
        currentLargest = newLargest;
      }
    }

    return currentLargest;
  }


}
