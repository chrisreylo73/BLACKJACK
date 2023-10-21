public class Dealer extends Player {

  // Declare a private instance variable for the hidden card
  private Card hiddenCard;

  @Override
  public void addToHand(Card card) {
    // Check if the card is an ACE
    if (card.getValue() == 11) {
      aceCounter++;
    }

    // Update handValue
    handValue += card.getValue();

    // Reduce Ace if needed to avoid busting
    while (handValue > 21 && aceCounter > 0) {
      handValue -= 10;
      aceCounter--;
    }

    // Add the received card to the dealer's hand
    hand.add(card);

    // Display the dealer's hand value and additional information
    System.out.println("\n____________DEALER____________");
    System.out.println("HAND: " + getHand() + "   VALUE: " + getHandValue());
    System.out.println(
      "   ACE COUNTER: " +
      getAceCounter() +
      "    HIDDEN CARD: " +
      getHiddenCard()
    );
  }

  public Card getHiddenCard() {
    return hiddenCard;
  }

  public void setHiddenCard(Card card) {
    // Set the hidden card for the dealer
    hiddenCard = card;
  }
}
