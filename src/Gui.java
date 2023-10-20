import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Gui {

  private int boardWidth = 800;
  private int boardHeight = 600;
  JFrame frame;
  JPanel gamePanel;
  JPanel buttonsPanel;
  JButton hitButton;
  JButton stayButton;
  JButton playAgainButton;
  JLabel win = new JLabel("PLAYER WINS!");
  JLabel loose = new JLabel("DEALER WINS!");
  int cardWidth = 105;
  int cardHeight = 145;
  BlackJack game;

  public Gui(BlackJack game) {
    this.game = game;
    this.frame = new JFrame("Black Jack");
    frame.setVisible(true);
    frame.setSize(boardWidth, boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // loose.setVisible(false);
    gamePanel =
      new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          try {
            if (!stayButton.isEnabled() && game.outcome() == true) {
              // loose.setVisible(false);
              System.out.println("TRUE");
              win.setBounds(
                (boardWidth - win.getPreferredSize().width) / 2,
                (boardHeight - win.getPreferredSize().height) / 2,
                win.getPreferredSize().width,
                win.getPreferredSize().height
              );
              win.setVisible(true);
            } else if (!stayButton.isEnabled() && game.outcome() == false) {
              // win.setVisible(false);
              System.out.println("FALSE");
              loose.setBounds(
                (boardWidth - loose.getPreferredSize().width) / 2,
                (boardHeight - loose.getPreferredSize().height) / 2,
                loose.getPreferredSize().width,
                loose.getPreferredSize().height
              );
              loose.setVisible(true);
            }

            //Dealer Hand
            ArrayList<Card> dealersHand = game.getDealer().getHand();
            for (int i = 1; i < dealersHand.size(); i++) {
              Card card = dealersHand.get(i);
              if (stayButton.isEnabled()) {
                Image hiddenCardImage = new ImageIcon(
                  getClass().getResource("./cards/BACK.png")
                )
                  .getImage();
                g.drawImage(
                  hiddenCardImage,
                  (boardWidth / 2) - (cardWidth * dealersHand.size() / 2),
                  20,
                  cardWidth,
                  cardHeight,
                  null
                );
                Image cardImg = new ImageIcon(
                  getClass().getResource(card.getImagePath())
                )
                  .getImage();

                g.drawImage(
                  cardImg,
                  (boardWidth / 2) -
                  (cardWidth * dealersHand.size() / 2) +
                  (cardWidth + 5) *
                  i,
                  20,
                  cardWidth,
                  cardHeight,
                  null
                );
              } else {
                Image hiddenCardImage = new ImageIcon(
                  getClass().getResource(dealersHand.get(0).getImagePath())
                )
                  .getImage();
                g.drawImage(
                  hiddenCardImage,
                  (boardWidth / 2) - (cardWidth * dealersHand.size() / 2),
                  20,
                  cardWidth,
                  cardHeight,
                  null
                );

                Image cardImg = new ImageIcon(
                  getClass().getResource(card.getImagePath())
                )
                  .getImage();

                g.drawImage(
                  cardImg,
                  (boardWidth / 2) -
                  (cardWidth * dealersHand.size() / 2) +
                  (cardWidth + 5) *
                  i,
                  20,
                  cardWidth,
                  cardHeight,
                  null
                );
              }
            }

            //Players Hand
            ArrayList<Card> playersHand = game.getPlayer().getHand();
            for (int i = 0; i < playersHand.size(); i++) {
              Card card = playersHand.get(i);
              Image cardImg = new ImageIcon(
                getClass().getResource(card.getImagePath())
              )
                .getImage();
              g.drawImage(
                cardImg,
                (boardWidth / 2) -
                (cardWidth * playersHand.size() / 2) +
                (cardWidth + 5) *
                i,
                boardHeight - 92 - cardHeight,
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
    gamePanel.add(win);
    gamePanel.add(loose);
    frame.add(gamePanel);

    win.setFont(new Font("Arial", Font.BOLD, 18));
    win.setForeground(Color.WHITE);
    win.setVisible(false);

    loose.setFont(new Font("Arial", Font.BOLD, 18));
    loose.setForeground(Color.WHITE);
    loose.setVisible(false);
    buttonsPanel = new JPanel();
    hitButton = new JButton("HIT");
    stayButton = new JButton("STAY");
    playAgainButton = new JButton("PLAY AGAIN?");
    playAgainButton.setVisible(false);
    hitButton.setFocusable(false);
    stayButton.setFocusable(false);
    playAgainButton.setFocusable(false);
    hitButton.setBackground(new Color(68, 70, 84));
    hitButton.setForeground(Color.WHITE);
    stayButton.setBackground(new Color(68, 70, 84));
    stayButton.setForeground(Color.WHITE);
    playAgainButton.setBackground(new Color(68, 70, 84));
    playAgainButton.setForeground(Color.WHITE);
    buttonsPanel.add(hitButton);
    buttonsPanel.add(stayButton);
    buttonsPanel.add(playAgainButton);
    buttonsPanel.setBackground(new Color(52, 53, 65));
    frame.add(buttonsPanel, BorderLayout.SOUTH);

    hitButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          hitButton.setEnabled(game.handleHit());
          gamePanel.repaint();
        }
      }
    );

    stayButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          hitButton.setEnabled(false);
          stayButton.setEnabled(false);
          game.handleStay();
          gamePanel.repaint();
          playAgainButton.setVisible(true);
        }
      }
    );

    playAgainButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          game.handlePlayAgain();
          hitButton.setEnabled(true);
          stayButton.setEnabled(true);
          playAgainButton.setVisible(false);
          win.setVisible(false);
          loose.setVisible(false);
          gamePanel.repaint();
        }
      }
    );
  }
}
