import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Gui {

  private int boardWidth;
  private int boardHeight;
  JFrame frame;
  JPanel gamePanel;
  JPanel buttonsPanel;
  JButton hitButton;
  JButton stayButton;
  int cardWidth = 110;
  int cardHeight = 154;

  public Gui(BlackJack game) {
    this.boardWidth = 600;
    this.boardHeight = 600;
    this.frame = new JFrame("Black Jack");
    frame.setVisible(true);
    frame.setSize(boardWidth, boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gamePanel =
      new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
          super.paintComponent(g);

          try {
            //Draw Hidden Card
            Image hiddenCardImage = new ImageIcon(
              getClass().getResource("./cards/BACK.png")
            )
              .getImage();

            g.drawImage(hiddenCardImage, 20, 20, cardWidth, cardHeight, null);

            //Dealer Hand
            ArrayList<Card> dealersHand = game.getDealerHand();
            for (int i = 0; i < dealersHand.size(); i++) {
              Card card = dealersHand.get(i);
              Image cardImg = new ImageIcon(
                getClass().getResource(card.getImagePath())
              )
                .getImage();

              g.drawImage(
                cardImg,
                cardWidth + 25 + (cardWidth + 5) * i,
                20,
                cardWidth,
                cardHeight,
                null
              );
            }

            //PlayersHand
            ArrayList<Card> playersHand = game.getPlayersHand();
            for (int i = 0; i < playersHand.size(); i++) {
              Card card = playersHand.get(i);
              Image cardImg = new ImageIcon(
                getClass().getResource(card.getImagePath())
              )
                .getImage();
              g.drawImage(
                cardImg,
                20 + (cardWidth + 5) * i,
                320,
                cardWidth,
                cardHeight,
                null
              );
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
    gamePanel.setLayout(new BorderLayout());
    gamePanel.setBackground(new Color(52, 101, 77));
    frame.add(gamePanel);
    buttonsPanel = new JPanel();
    hitButton = new JButton("HIT");
    stayButton = new JButton("STAY");
    hitButton.setFocusable(false);
    stayButton.setFocusable(false);
    buttonsPanel.add(hitButton);
    buttonsPanel.add(stayButton);
    frame.add(buttonsPanel, BorderLayout.SOUTH);
  }
}