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
    if (player.getHandValue() > 21) {
      System.out.println("DEALER WINS!");
    } else if (player.getHandValue() == 21) {
      System.out.println("PLAYER WINS!");
    }
  }

  public boolean handleHit() {
    Card card = deck.getCardFromDeck();
    player.addToHand(card);
    if (player.getHandValue() > 21) {
      System.out.println("DEALER WINS!");
      return false;
    } else if (player.getHandValue() == 21) {
      System.out.println("PLAYER WINS!");
    }
    return true;
  }

  public void handleStay() {
    while (dealer.getHandValue() <= 17) {
      Card card = deck.getCardFromDeck();
      dealer.addToHand(card);
    }
    if (
      dealer.getHandValue() <= 21 &&
      dealer.getHandValue() > player.getHandValue()
    ) {
      System.out.println("DEALER WINS!");
    } else {
      System.out.println("PLAYER WINS!");
    }
  }

  public void handlePlayAgain() {
    start();
  }

  public void start() {
    initialDeal();
    if (dealer.getHandValue() == 21) {
      System.out.println("DEALER WINS!");
    } else if (player.getHandValue() == 21) {
      System.out.println("PLAYER WINS!");
    }
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
