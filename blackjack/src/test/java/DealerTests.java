import static org.junit.Assert.assertEquals;

import com.chrisreylo.Card;
import com.chrisreylo.Dealer;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class DealerTests {

  private Dealer dealer;

  @Before
  public void setUp() {
    dealer = new Dealer();
  }

  @Test
  public void testAddToHandWithNonAceCard() {
    Card card = new Card("5", "S");
    dealer.addToHand(card);

    assertEquals(5, dealer.getHandValue());
    assertEquals(0, dealer.getAceCounter());

    ArrayList<Card> hand = dealer.getHand();
    assertEquals(1, hand.size());
    assertEquals(card, hand.get(0));
  }

  @Test
  public void testAddToHandWithAceCard() {
    Card aceCard = new Card("A", "H");
    dealer.addToHand(aceCard);

    assertEquals(11, dealer.getHandValue());
    assertEquals(1, dealer.getAceCounter());

    ArrayList<Card> hand = dealer.getHand();
    assertEquals(1, hand.size());
    assertEquals(aceCard, hand.get(0));
  }

  @Test
  public void testAddToHandWithBustAndAceReduction() {
    Card aceCard = new Card("A", "C");
    Card cardQ = new Card("Q", "D");
    Card card8 = new Card("8", "S");

    dealer.addToHand(aceCard);
    dealer.addToHand(cardQ);

    // The value should be initially 21 (A=11, Q=10)
    assertEquals(21, dealer.getHandValue());

    // Add an 8, which should cause Ace to reduce its value to 1
    dealer.addToHand(card8);
    assertEquals(19, dealer.getHandValue());

    ArrayList<Card> hand = dealer.getHand();
    assertEquals(3, hand.size());
  }

  @Test
  public void testSetHiddenCard() {
    Card hiddenCard = new Card("K", "D");
    dealer.setHiddenCard(hiddenCard);

    assertEquals(hiddenCard, dealer.getHiddenCard());
  }
}
