public class Card {

  String value;
  String type;

  Card(String value, String type) {
    this.value = value;
    this.type = type;
  }

  public String toString() {
    return value + "-" + type;
  }
}
