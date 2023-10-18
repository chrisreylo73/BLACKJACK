import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class BlackJack {

  Player player;
  Dealer dealer;
  Deck deck;

  public BlackJack() {
    // Initialize the game components
    deck = new Deck();
    dealer = new Dealer();
    player = new Player();
    start();
  }

  public void initDeal() {
    player.addToHand(deck.getCardFromDeck());
    player.addToHand(deck.getCardFromDeck());
    System.out.println(
      "PLAYER HAND: " + player.getHand() + "   VALUE: " + player.getHandValue()
    );
    Card hiddenCard = deck.getCardFromDeck();
    dealer.setHiddenCard(hiddenCard);
    dealer.addToHand(hiddenCard);
    dealer.addToHand(deck.getCardFromDeck());
    System.out.println(
      "DEALER HAND: " + dealer.getHand() + "   VALUE: " + dealer.getHandValue()
    );
  }

  public void start() {
    initDeal();
  }
}
