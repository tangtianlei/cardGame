package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

  ArrayList<Card> deckOfCards;
  private static final int NUM_OF_DECKS = 3;

  public Deck() {
    this.deckOfCards = new ArrayList<Card>();
    createDeck();
    shuffleDeck();
  }

  public void createDeck() {
    for(Suit suit : Suit.values()) {
      if (suit == Suit.SMALL_JOKER || suit == Suit.LARGE_JOKER) {
        continue;
      }
      for (int i = 1; i <= 13; ++i) {
        for (int deckNum = 0; deckNum < NUM_OF_DECKS; deckNum++) {
          Card oneCard = new Card(suit, i);
          deckOfCards.add(oneCard);
        }
      }
    }

    for (int deckNum = 0; deckNum < NUM_OF_DECKS; deckNum++) {
      Card smallJoker = new Card(Suit.SMALL_JOKER, 1);
      Card largeJoker = new Card(Suit.LARGE_JOKER, 1);
      deckOfCards.add(smallJoker);
      deckOfCards.add(largeJoker);
    }
  }

  public void shuffleDeck() {
    Collections.shuffle(deckOfCards);
  }

  public Card getCard() {
    Card giveCard = deckOfCards.get(0);
    deckOfCards.remove(0);
    return giveCard;
  }

  public void printDeck() {
    for (int i = 0; i < deckOfCards.size(); i++) {
      Card card = deckOfCards.get(i);
      System.out.println("Index: " + (i+1) + "\t\tSuit: " + card.getSuit() + "\t\tnumber: " + card.getNumber());
    }
  }
}
