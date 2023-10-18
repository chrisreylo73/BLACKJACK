import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class BlackJack {

  private Player player;
  Dealer dealer;
  Deck deck;

  public BlackJack() {
    // Initialize the game components
    deck = new Deck();
    dealer = new Dealer();
    start();
  }

  public void initDeal() {
    player.addToHand(deck.drawCard());
    player.addToHand(deck.drawCard());
  }

  public void start() {
    // Implement the main game loop

  }
}
