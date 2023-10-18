import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

  private ArrayList<Card> hand;
  private int score;

  public Player() {
    // Initialize an empty hand and score
    hand = new ArrayList<>();
    score = 0;
  }

  public void addToHand(Card card) {
    // Add a card to the player's hand
    hand.add(card);
  }

  public int getHandValue() {
    int handValue = 0;
    int numAces = 0;
    for (Card card : hand) {
      handValue += card.getValue();
      if (card.getValue() == 11) {
        numAces++;
      }
    }

    while (handValue > 21 && numAces > 0) {
      handValue -= 10;
      numAces--;
    }
    return handValue;
  }
}
