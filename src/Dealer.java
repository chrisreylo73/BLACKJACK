public class Dealer extends Player {

  private Card hiddenCard;

  @Override
  public void addToHand(Card card) {
    // Check if the card is an ACE
    if (card.getValue() == 11) {
      aceCounter++;
    }

    // Update handValue
    handValue += card.getValue();
    // Reduce Ace if needed
    while (handValue > 21 && aceCounter > 0) {
      handValue -= 10;
      aceCounter--;
    }
    //Add card to hand
    hand.add(card);
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
    hiddenCard = card;
  }

  public void revealHiddenCard() {
    addToHand(hiddenCard);
    hiddenCard = null;
  }

  public boolean shouldDrawCard() {
    if (getHandValue() < 17) {
      return true;
    }
    return false;
  }
}
