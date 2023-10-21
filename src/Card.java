public class Card {

  // Member variables to store the card's value and suit
  String value;
  String suit;

  // Constructor to initialize the card with a value and a suit
  Card(String value, String suit) {
    this.value = value;
    this.suit = suit;
  }

  // Function to get the numerical value of the card
  public int getValue() {
    switch (value) {
      case "A":
        return 11;
      case "K":
      case "Q":
      case "J":
        return 10;
      default:
        return Integer.parseInt(value);
    }
  }

  // Function to get the file path for the card's image
  public String getImagePath() {
    return "./cards/" + toString() + ".png";
  }

  // Function to get a string representation of the card
  public String toString() {
    return value + "-" + suit;
  }
}
