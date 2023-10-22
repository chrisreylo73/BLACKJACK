import static org.junit.Assert.*;

import com.chrisreylo.Card;
import com.chrisreylo.Deck;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class DeckTests {

  private Deck deck;

  @Before
  public void setUp() {
    deck = new Deck();
  }

  @Test
  public void testBuildDeck() {
    // Ensure that the deck is built with 52 cards
    Deck deck = new Deck();
    // ArrayList<Card> cards = deck;
    assertEquals(52, deck.size());
    // Check the presence of some specific cards
    Card aceOfHearts = new Card("A", "H");
    Card tenOfClubs = new Card("10", "C");
    assertEquals(true, deck.contains(tenOfClubs));
    assertEquals(true, deck.contains(aceOfHearts));
  }

  @Test
  public void testShuffle() {
    Deck deck1 = new Deck();
    Deck deck2 = new Deck();

    // Shuffle 1 deck
    deck2.shuffle();

    // Ensure the two decks have the same number of cards
    assertEquals(deck1.getDeck().size(), deck2.getDeck().size());
    // Compare the shuffled deck with the un-shuffled deck
    assertFalse(deck1.getDeck() == deck2.getDeck());
  }

  @Test
  public void testDrawCard() {
    // Draw a card from the deck and ensure it's not null
    Card drawnCard = deck.drawCard();
    assertNotNull(drawnCard);

    // Ensure that the card is removed from the deck
    ArrayList<Card> cards = deck.getDeck();
    assertFalse(cards.contains(drawnCard));
  }

  @Test
  public void testGetCardFromDeck() {
    // Get the top card from the deck without drawing it
    Card topCard = deck.getCardFromDeck();
    assertNotNull(topCard);

    // Ensure that the card is still in the deck
    ArrayList<Card> cards = deck.getDeck();
    assertFalse(cards.contains(topCard));
  }
}
