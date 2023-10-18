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

  public void initDeal() {}

  public void start() {}
}
