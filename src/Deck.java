import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

  ArrayList<Card> deck;
  private Random random = new Random();

  public Deck() {
    // Initialize the deck with 52 deck
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
    for (int i = 0; i < suits.length; i++) {
      for (int j = 0; j < values.length; j++) {
        Card card = new Card(values[j], suits[i]);
        deck.add(card);
      }
    }
    System.out.println("BUILD DECK: ");
    System.out.println(deck);
  }

  public void shuffle() {
    // Shuffle the deck
    for (int i = 0; i < deck.size(); i++) {
      int j = random.nextInt(deck.size());
      Card currentCard = deck.get(i);
      Card randomCard = deck.get(j);
      deck.set(i, randomCard);
      deck.set(j, currentCard);
    }

    System.out.println("SHUFFLED:");
    System.out.println(deck);
  }

  public ArrayList<Card> getDeck() {
    return deck;
  }

  public Card drawCard() {
    // Draw a card from the deck
    if (deck.size() > 1) {
      buildDeck();
      shuffle();
    }

    return deck.remove(deck.size() - 1);
  }
}
