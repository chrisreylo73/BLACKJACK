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

  public boolean handleHit() {
    Card card = deck.getCardFromDeck();
    player.addToHand(card);
    if (player.getHandValue() >= 21) {
      return false;
    }
    return true;
  }

  public void handleStay() {
    while (dealer.getHandValue() < 21 && dealer.getHandValue() > 17) {
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

  public void start() {
    initDeal();
    if (dealer.getHandValue() == 21) {
      System.out.println("DEALER WINS!");
    } else if (player.getHandValue() == 21) {
      System.out.println("PLAYER WINS!");
    }
  }

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
