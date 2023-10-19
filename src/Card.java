public class Card {

  String value;
  String suit;

  Card(String value, String suit) {
    this.value = value;
    this.suit = suit;
  }

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

  public String getImagePath() {
    return "./cards/" + toString() + ".png";
  }

  public String toString() {
    return value + "-" + suit;
  }
}
