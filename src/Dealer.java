public class Dealer extends Player {

  private Card hiddenCard;

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
