public class BlackJack {

  // Declare instance variables for game components
  private Player player;
  private Dealer dealer;
  private Deck deck;
  private Gui gui;

  public BlackJack() {
    // Initialize the game components and start the game
    gui = new Gui(this);
    start();
  }

  public void start() {
    // Start a new game by performing the initial deal
    initialDeal();
  }

  public void initialDeal() {
    // Create a new deck, player, and dealer
    deck = new Deck();
    dealer = new Dealer();
    player = new Player();

    // Deal two cards to the player
    player.addToHand(deck.getCardFromDeck());
    player.addToHand(deck.getCardFromDeck());

    // Deal one hidden card to the dealer and one visible card
    Card hiddenCard = deck.getCardFromDeck();
    dealer.setHiddenCard(hiddenCard);
    dealer.addToHand(hiddenCard);
    dealer.addToHand(deck.getCardFromDeck());

    // Check if the player has a blackjack (21)
    if (player.getHandValue() == 21) {
      gui.handleStay();
    }
  }

  public boolean handleHit() {
    // Handle a player's "hit" action by dealing a card to the player
    Card card = deck.getCardFromDeck();
    player.addToHand(card);

    if (player.getHandValue() == 21) {
      // Player wins with a blackjack (21)
      return true;
    } else if (player.getHandValue() > 21) {
      // Dealer wins if player's hand value exceeds 21
      return false;
    }
    // Player continues to play
    return true;
  }

  public boolean handleStay() {
    // Handle a player's "stay" action, allowing the dealer to play
    while (dealer.getHandValue() <= 16) {
      Card card = deck.getCardFromDeck();
      dealer.addToHand(card);
    }

    if (player.getHandValue() == 21) {
      // Player wins with a blackjack (21)
      return true;
    } else if (
      dealer.getHandValue() <= 21 &&
      dealer.getHandValue() > player.getHandValue()
    ) {
      // Dealer wins if dealer's hand value is higher than player's and doesn't exceed 21
      return false;
    } else {
      // Player wins if dealer's hand value is lower than player's or exceeds 21
      return true;
    }
  }

  public void handlePlayAgain() {
    // Start a new game by resetting game components
    start();
  }

  public Player getDealer() {
    return dealer;
  }

  public Player getPlayer() {
    return player;
  }
}
