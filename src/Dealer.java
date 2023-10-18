public class Dealer extends Player {

  public boolean shouldDrawCard() {
    if (getHandValue() < 17) {
      return true;
    }
    return false;
  }
}
