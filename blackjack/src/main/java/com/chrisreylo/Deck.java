package com.chrisreylo;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

  // Member variable to store the deck of cards
  ArrayList<Card> deck;
  // Random number generator for shuffling
  private Random random = new Random();

  public Deck() {
    // Initialize the deck with 52 cards and shuffle it
    buildDeck();
    shuffle();
  }

  public void buildDeck() {
    deck = new ArrayList<Card>();
    String[] values = {
      "A",
      "2",
      "3",
      "4",
      "5",
      "6",
      "7",
      "8",
      "9",
      "10",
      "J",
      "Q",
      "K",
    };
    String[] suits = { "C", "D", "H", "S" };

    // Create cards for each combination of value and suit
    for (int i = 0; i < suits.length; i++) {
      for (int j = 0; j < values.length; j++) {
        Card card = new Card(values[j], suits[i]);
        deck.add(card);
      }
    }

    // Display the initial deck
    System.out.println("BUILD DECK: ");
    System.out.println(deck);
  }

  public int size() {
    return deck.size();
  }

  public boolean contains(Card card) {
    for (Card crd : deck) {
      System.out.println("DECK CARD: " + crd);
      System.out.println("TARGET Card: " + card);
      System.out.println(deck.size());
      if (crd.value == card.value && crd.suit == card.suit) {
        return true;
      }
    }
    return false;
  }

  public void shuffle() {
    // Shuffle the deck by swapping cards randomly
    for (int i = 0; i < deck.size(); i++) {
      int j = random.nextInt(deck.size());
      Card currentCard = deck.get(i);
      Card randomCard = deck.get(j);
      deck.set(i, randomCard);
      deck.set(j, currentCard);
    }

    // Display the shuffled deck
    System.out.println("SHUFFLED:");
    System.out.println(deck);
  }

  public Card drawCard() {
    // Draw a card from the deck, and rebuild and shuffle the deck if only one card remains
    if (deck.size() <= 1) {
      buildDeck();
      shuffle();
    }

    return deck.remove(deck.size() - 1);
  }

  public ArrayList<Card> getDeck() {
    return deck;
  }

  public Card getCardFromDeck() {
    // Remove and return the top card from the deck
    return deck.remove(deck.size() - 1);
  }
}
