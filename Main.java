package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
      Instant startTime = Instant.now();
      System.out.println("This is Poker game");
      //Deck oneDeck = new Deck();
      //oneDeck.printDeck();

      final Integer NUM_OF_TRY = 1000000;
      Integer success = 0;
      for (int i = 0; i < NUM_OF_TRY; i++) {
        success = success + playGame();
      }

      System.out.println("Success rate: " + ((double)success/NUM_OF_TRY));
      Instant endTime = Instant.now();
      System.out.println("Time Duration: " + Duration.between(startTime, endTime));
    }

    public static Integer playGame() {

      Integer num_of_cards_per_player = 25;
      Deck oneDeck = new Deck();
      ArrayList<Player> playerList = new ArrayList<>();
      for (int i = 0; i < 6; i++) {
        playerList.add(new Player());
      }

      for (int i = 0; i < num_of_cards_per_player; i++) {
        for (int j = 0; j < 6; j++) {
          playerList.get(j).getCard(oneDeck.getCard());
        }
      }

      LargestCard currentLargest = LargestCard.NOTHING;
      for (int j = 1; j < 6; j++) {
        LargestCard newLargest = playerList.get(j).getLargestCardType();
        if (newLargest.ordinal() > currentLargest.ordinal()) {
          currentLargest = newLargest;
        }
      }

      // Check if the host can get two or three joker before he gets extra 12 cards.
      LargestCard hostLargestCard = playerList.get(0).getLargestCardType();
      if (currentLargest == LargestCard.NOTHING) {
        if (hostLargestCard == LargestCard.DOUBLE_JOKER || hostLargestCard == LargestCard.TRIPLE_JOKER) {
          return 1;
        } else {
          return 0;
        }
      }

      for (int i = 0; i < 12; i++) {
        playerList.get(0).getCard(oneDeck.getCard());
      }
      hostLargestCard = playerList.get(0).getLargestCardType();
      if (hostLargestCard.ordinal() > currentLargest.ordinal()) {
        currentLargest = hostLargestCard;
      }

      if (currentLargest == LargestCard.DOUBLE_JOKER || currentLargest == LargestCard.TRIPLE_JOKER) {
        return 1;
      }
      return 0;
    }

}
