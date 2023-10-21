import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Gui {

  private final int boardWidth = 800;
  private final int boardHeight = 600;
  private final int cardWidth = 94;
  private final int cardHeight = 130;

  private final JFrame frame;
  private final JPanel gamePanel;
  private final JPanel buttonsPanel;
  private final JButton hitButton;
  private final JButton stayButton;
  private final JButton playAgainButton;
  private final JLabel win = new JLabel("PLAYER WINS!");
  private final JLabel lose = new JLabel("DEALER WINS!");

  private final BlackJack game;

  public Gui(BlackJack game) {
    this.game = game;

    frame = new JFrame("Black Jack");
    frame.setSize(boardWidth, boardHeight);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    gamePanel =
      new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          try {
            if (!stayButton.isEnabled()) {
              updateResultLabel(win, game.handleStay());
              updateResultLabel(lose, !game.handleStay());
            }
            drawDealerHand(g);
            drawPlayerHand(g);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
    gamePanel.setLayout(new BorderLayout());
    gamePanel.setBackground(new Color(52, 101, 77));
    frame.add(gamePanel);

    buttonsPanel = new JPanel();
    hitButton = createStyledButton("HIT");
    stayButton = createStyledButton("STAY");
    playAgainButton = createStyledButton("PLAY AGAIN?");
    playAgainButton.setVisible(false);
    hitButton.addActionListener(e -> handleHit());
    stayButton.addActionListener(e -> handleStay());
    playAgainButton.addActionListener(e -> handlePlayAgain());
    buttonsPanel.add(hitButton);
    buttonsPanel.add(stayButton);
    buttonsPanel.add(playAgainButton);
    buttonsPanel.setBackground(new Color(52, 53, 65));
    frame.add(buttonsPanel, BorderLayout.SOUTH);

    setupResultLabels();
    frame.setVisible(true);
  }

  private void updateResultLabel(JLabel label, boolean condition) {
    label.setVisible(condition);
    if (condition) {
      label.setBounds(
        (boardWidth - label.getPreferredSize().width) / 2,
        (boardHeight - label.getPreferredSize().height) / 2,
        label.getPreferredSize().width,
        label.getPreferredSize().height
      );
    }
  }

  private void drawDealerHand(Graphics g) {
    ArrayList<Card> dealersHand = game.getDealer().getHand();
    int numCards = dealersHand.size();
    int centerX = boardWidth / 2;

    if (stayButton.isEnabled()) {
      Image hiddenCardImage = new ImageIcon(
        getClass().getResource("./cards/BACK.png")
      )
        .getImage();
      drawImage(
        g,
        hiddenCardImage,
        centerX - (cardWidth * numCards / 2),
        20,
        cardWidth,
        cardHeight
      );
    } else {
      Image hiddenCardImage = new ImageIcon(
        getClass().getResource(dealersHand.get(0).getImagePath())
      )
        .getImage();
      drawImage(
        g,
        hiddenCardImage,
        centerX - (cardWidth * numCards / 2),
        20,
        cardWidth,
        cardHeight
      );
    }

    for (int i = 1; i < numCards; i++) {
      Card card = dealersHand.get(i);
      Image cardImage = new ImageIcon(
        getClass().getResource(card.getImagePath())
      )
        .getImage();
      drawImage(
        g,
        cardImage,
        centerX - (cardWidth * numCards / 2) + (cardWidth + 5) * i,
        20,
        cardWidth,
        cardHeight
      );
    }
  }

  private void drawPlayerHand(Graphics g) {
    ArrayList<Card> playersHand = game.getPlayer().getHand();
    int numCards = playersHand.size();
    int centerX = boardWidth / 2;
    int yCoordinate = boardHeight - 92 - cardHeight;

    for (int i = 0; i < numCards; i++) {
      Card card = playersHand.get(i);
      Image cardImage = new ImageIcon(
        getClass().getResource(card.getImagePath())
      )
        .getImage();
      drawImage(
        g,
        cardImage,
        centerX - (cardWidth * numCards / 2) + (cardWidth + 5) * i,
        yCoordinate,
        cardWidth,
        cardHeight
      );
    }
  }

  private JButton createStyledButton(String text) {
    JButton button = new JButton(text);
    button.setFocusable(false);
    button.setBackground(new Color(68, 70, 84));
    button.setForeground(Color.WHITE);
    return button;
  }

  private void setupResultLabels() {
    setupResultLabel(win);
    setupResultLabel(lose);
  }

  private void setupResultLabel(JLabel label) {
    label.setFont(new Font("Arial", Font.BOLD, 18));
    label.setForeground(Color.WHITE);
    label.setVisible(false);
    gamePanel.add(label);
  }

  private void handleHit() {
    hitButton.setEnabled(game.handleHit());
    gamePanel.repaint();
  }

  public void handleStay() {
    hitButton.setEnabled(false);
    stayButton.setEnabled(false);
    playAgainButton.setVisible(true);
    gamePanel.repaint();
  }

  private void handlePlayAgain() {
    game.handlePlayAgain();
    hitButton.setEnabled(true);
    stayButton.setEnabled(true);
    playAgainButton.setVisible(false);
    win.setVisible(false);
    lose.setVisible(false);
    gamePanel.repaint();
  }

  private void drawImage(
    Graphics g,
    Image image,
    int x,
    int y,
    int width,
    int height
  ) {
    g.drawImage(image, x, y, width, height, null);
  }
}
