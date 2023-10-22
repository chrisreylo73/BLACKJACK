import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.chrisreylo.BlackJack;
import com.chrisreylo.Card;
import com.chrisreylo.Dealer;
import com.chrisreylo.Deck;
import com.chrisreylo.Player;
import org.junit.Before;
import org.junit.Test;

public class BlackJackTests {

  private BlackJack game;

  @Before
  public void setUp() {
    game = new BlackJack();
  }

  @Test
  public void testInitialDeal() {
    // Ensure that a new game initializes the player, dealer, and deck
    Player player = game.getPlayer();
    Dealer dealer = game.getDealer();
    Deck deck = game.deck;

    assertNotNull(player);
    assertNotNull(dealer);
    assertNotNull(deck);

    // Check that both player and dealer receive two cards
    assertEquals(2, player.getHand().size());
    assertEquals(2, dealer.getHand().size());

    // Ensure that the dealer's first card is hidden
    assertTrue(
      dealer
        .getHand()
        .get(0)
        .toString()
        .equals(dealer.getHiddenCard().toString())
    );
  }

  @Test
  public void testHandleHit() {
    // Start a new game and check player's and dealer's hands
    Player player = game.getPlayer();
    Player dealer = game.getDealer();
    // Make a hit
    boolean continuePlaying = game.handleHit();
    // Check if a card is added to the player's hand
    assertEquals(3, player.getHand().size());
    // Add more cards to the player's hand until it exceeds 21
    while (player.getHandValue() <= 21) {
      player.addToHand(new Card("K", "S"));
    }
    // The player's hand now exceeds 21, should trigger dealer's turn
    continuePlaying = game.handleHit();
    assertFalse(continuePlaying);
    // Dealer should have taken cards
    assertTrue(dealer.getHand().size() > 2);
  }

  @Test
  public void testHandleStay() {
    // Start a new game
    Player player = new Player();
    Dealer dealer = new Dealer();

    dealer.addToHand(new Card("5", "C"));
    dealer.addToHand(new Card("K", "S"));
    assertTrue(dealer.getHandValue() <= 16);

    // Set up a scenario where player should win
    player.addToHand(new Card("A", "D"));
    player.addToHand(new Card("10", "H"));

    // Player stays
    assertEquals(21, player.getHandValue());

    // Set up a scenario where dealer should win
    player.addToHand(new Card("7", "C"));
    player.addToHand(new Card("K", "S"));

    // Player stays
    assertTrue(player.getHandValue() > 21);
  }

  @Test
  public void testHandlePlayAgain() {
    Player player = game.getPlayer();
    Player dealer = game.getDealer();

    // Play a game
    game.handleHit();
    game.handleHit();

    // Player stays
    game.handleStay();

    // Check that player and dealer hands are not empty
    assertFalse(player.getHand().isEmpty());
    assertFalse(dealer.getHand().isEmpty());

    // Start a new game
    game.handlePlayAgain();
  }
}
