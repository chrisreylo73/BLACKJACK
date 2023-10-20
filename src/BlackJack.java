import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class BlackJack {

  Player player;
  Dealer dealer;
  Deck deck;
  Gui gui;

  public BlackJack() {
    // Initialize the game components
    deck = new Deck();
    dealer = new Dealer();
    player = new Player();
    gui = new Gui(this);
    start();
  }

  public void initDeal() {
    player.addToHand(deck.getCardFromDeck());
    player.addToHand(deck.getCardFromDeck());
    System.out.println(
      "PLAYER HAND: " + player.getHand() + "   VALUE: " + player.getHandValue()
    );
    System.out.println("   ACE COUNTER: " + dealer.getAceCounter());

    Card hiddenCard = deck.getCardFromDeck();
    dealer.setHiddenCard(hiddenCard);
    dealer.addToHand(hiddenCard);
    dealer.addToHand(deck.getCardFromDeck());
    System.out.println(
      "DEALER HAND: " + dealer.getHand() + "   VALUE: " + dealer.getHandValue()
    );
    System.out.println(
      "   ACE COUNTER: " +
      dealer.getAceCounter() +
      "    HIDDEN CARD: " +
      dealer.getHiddenCard()
    );
  }

  public void handleHit() {}

  public void handleStay() {}

  public void start() {
    initDeal();
  }

  // public Card getCardFromDeck() {
  //   return deck.getCardFromDeck();
  // }

  // public ArrayList<Card> getPlayersHand() {
  //   return player.getHand();
  // }

  // public void addCardToPlayerHand(Card card) {
  //   player.addToHand(card);
  // }

  // public ArrayList<Card> getDealerHand() {
  //   return dealer.getHand();
  // }

  public Deck getDeck() {
    return deck;
  }

  public Player getDealer() {
    return dealer;
  }

  public Player getPlayer() {
    return player;
  }
}
