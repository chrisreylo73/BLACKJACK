public class BlackJack {

  Player player;
  Dealer dealer;
  Deck deck;
  Gui gui;

  public BlackJack() {
    // Initialize the game components
    gui = new Gui(this);
    start();
  }

  public void initialDeal() {
    deck = new Deck();
    dealer = new Dealer();
    player = new Player();
    player.addToHand(deck.getCardFromDeck());
    player.addToHand(deck.getCardFromDeck());

    Card hiddenCard = deck.getCardFromDeck();
    dealer.setHiddenCard(hiddenCard);
    dealer.addToHand(hiddenCard);
    dealer.addToHand(deck.getCardFromDeck());
    if (player.getHandValue() == 21) {
      gui.handleStay();
    }
  }

  public boolean handleHit() {
    Card card = deck.getCardFromDeck();
    player.addToHand(card);
    if (player.getHandValue() == 21) {
      //PLAYER WINS!
      return true;
    } else if (player.getHandValue() > 21) {
      //DEALER WINS
      return false;
    }
    return true;
  }

  public boolean handleStay() {
    while (dealer.getHandValue() <= 16) {
      Card card = deck.getCardFromDeck();
      dealer.addToHand(card);
    }
    if (player.getHandValue() == 21) {
      return true;
    } else if (
      dealer.getHandValue() <= 21 &&
      dealer.getHandValue() > player.getHandValue()
    ) {
      // DEALER WINS
      return false;
    } else {
      // PLAYER WINS
      return true;
    }
  }

  public void handlePlayAgain() {
    start();
  }

  public void start() {
    initialDeal();
  }

  public boolean outcome() {
    if (
      dealer.getHandValue() <= 21 &&
      dealer.getHandValue() > player.getHandValue()
    ) {
      return false;
    }
    return true;
  }

  public Player getDealer() {
    return dealer;
  }

  public Player getPlayer() {
    return player;
  }
}
