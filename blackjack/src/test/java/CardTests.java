import static org.junit.Assert.*;

import com.chrisreylo.Card;
import org.junit.Test;

public class CardTests {

  // CARD CLASS TESTS
  @Test
  public void testGetValue() {
    Card cardA = new Card("A", "H");
    assertEquals(11, cardA.getValue());

    Card cardK = new Card("K", "C");
    assertEquals(10, cardK.getValue());

    Card card2 = new Card("2", "D");
    assertEquals(2, card2.getValue());
  }

  @Test
  public void testGetImagePath() {
    Card card = new Card("Q", "S");
    assertEquals("/cards/Q-S.png", card.getImagePath());
  }

  @Test
  public void testToString() {
    Card card = new Card("10", "D");
    assertEquals("10-D", card.toString());
  }

  @Test
  public void testCardInitialization() {
    Card card = new Card("9", "H");
    assertNotNull(card);
    assertEquals("9", card.value);
    assertEquals("H", card.suit);
  }
}
