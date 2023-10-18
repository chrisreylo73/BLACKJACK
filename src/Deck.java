import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

  ArrayList<Card> deck;

  public Deck() {
    // Initialize the deck with 52 cards
    buildDeck();
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
    System.out.println(deck.size());
  }

  public void shuffle() {
    // Shuffle the deck
  }

  public Card drawCard() {
    // Draw a card from the deck
    return null;
  }
}
