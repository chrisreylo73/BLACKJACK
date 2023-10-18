import java.util.ArrayList;

public class Player {

  private ArrayList<Card> hand;
  private int handValue;
  private int aceCounter;

  public Player() {
    // Initialize an empty hand and score
    hand = new ArrayList<>();
    handValue = 0;
    aceCounter = 0;
  }

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
  }

  public int getAceCounter() {
    return aceCounter;
  }

  public int getHandValue() {
    return handValue;
  }

  public ArrayList<Card> getHand() {
    return hand;
  }
}
