import static org.junit.Assert.assertEquals;

import com.chrisreylo.Card;
import com.chrisreylo.Player;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {

  private Player player;

  @Before
  public void setUp() {
    player = new Player();
  }

  @Test
  public void testAddToHandWithNonAceCard() {
    Card card = new Card("7", "H");
    player.addToHand(card);

    assertEquals(7, player.getHandValue());
    assertEquals(0, player.getAceCounter());

    ArrayList<Card> hand = player.getHand();
    assertEquals(1, hand.size());
    assertEquals(card, hand.get(0));
  }

  @Test
  public void testAddToHandWithAceCard() {
    Card aceCard = new Card("A", "D");
    player.addToHand(aceCard);

    assertEquals(11, player.getHandValue());
    assertEquals(1, player.getAceCounter());

    ArrayList<Card> hand = player.getHand();
    assertEquals(1, hand.size());
    assertEquals(aceCard, hand.get(0));
  }

  @Test
  public void testAddToHandWithAceAndOtherCards() {
    Card aceCard = new Card("A", "C");
    Card card2 = new Card("2", "S");

    player.addToHand(aceCard);
    player.addToHand(card2);

    assertEquals(13, player.getHandValue());
    assertEquals(1, player.getAceCounter());

    ArrayList<Card> hand = player.getHand();
    assertEquals(2, hand.size());
    assertEquals(aceCard, hand.get(0));
    assertEquals(card2, hand.get(1));
  }

  @Test
  public void testAddToHandWithBustAndAceReduction() {
    Card aceCard = new Card("A", "H");
    Card cardK = new Card("K", "D");
    Card card8 = new Card("8", "C");

    player.addToHand(aceCard);
    player.addToHand(cardK);

    // The value should be initially 19 (A=11, K=10)
    assertEquals(21, player.getHandValue());

    // Add an 8, which should cause Ace to reduce its value to 1
    player.addToHand(card8);
    assertEquals(19, player.getHandValue());

    ArrayList<Card> hand = player.getHand();
    assertEquals(3, hand.size());
  }
}
