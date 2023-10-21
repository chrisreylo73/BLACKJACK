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
  private final JPanel scorePanel = new JPanel();

  private final JButton hitButton;
  private final JButton stayButton;
  private final JButton playAgainButton;
  private final JLabel win = new JLabel("PLAYER WINS!");
  private final JLabel lose = new JLabel("DEALER WINS!");

  private final BlackJack game;
  private int winCounter = 0;
  private int loseCounter = 0;

  private final JLabel wScore = new JLabel("Wins: " + winCounter);
  private final JLabel lScore = new JLabel("Loses: " + loseCounter);

  public Gui(BlackJack game) {
    this.game = game;

    frame = new JFrame("Black Jack");
    frame.setSize(boardWidth, boardHeight);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    gamePanel =
      new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          try {
            if (!stayButton.isEnabled()) {
              updateResultLabel(win, game.handleStay());
              updateResultLabel(lose, !game.handleStay());
              updateWinLoseCounters();
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
    buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
    buttonsPanel.setBackground(new Color(52, 53, 65));
    scorePanel.setBackground(new Color(52, 53, 65));
    hitButton = createStyledButton("HIT");
    stayButton = createStyledButton("STAY");
    playAgainButton = createStyledButton("PLAY AGAIN?");
    playAgainButton.setVisible(false);
    hitButton.addActionListener(e -> handleHit());
    stayButton.addActionListener(e -> handleStay());
    playAgainButton.addActionListener(e -> handlePlayAgain());
    wScore.setVisible(true);
    lScore.setVisible(true);
    buttonsPanel.add(hitButton);
    buttonsPanel.add(stayButton);
    buttonsPanel.add(playAgainButton);

    frame.add(buttonsPanel, BorderLayout.SOUTH);

    setupLabels();
    buttonsPanel.add(scorePanel);
    frame.setVisible(true);
  }

  // Function to update the visibility of result labels
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

  // Function to draw the dealer's hand
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

  // Function to draw the player's hand
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

  // Function to create styled buttons
  private JButton createStyledButton(String text) {
    JButton button = new JButton(text);
    button.setFocusable(false);
    button.setBackground(new Color(68, 70, 84));
    button.setForeground(Color.WHITE);
    return button;
  }

  // Function to set up the result labels
  private void setupLabels() {
    setupResultLabel(win);
    setupResultLabel(lose);
    setupScoreLabel(wScore);
    setupScoreLabel(lScore);
  }

  // Function to set up a result label
  private void setupResultLabel(JLabel label) {
    label.setFont(new Font("Arial", Font.BOLD, 18));
    label.setForeground(Color.WHITE);
    label.setVisible(false);
    gamePanel.add(label);
  }

  // Function to set up a score label
  private void setupScoreLabel(JLabel label) {
    label.setForeground(Color.WHITE);
    label.setVisible(true);
    scorePanel.add(wScore);
    scorePanel.add(lScore);
  }

  // Function to update win and lose counters
  private void updateWinLoseCounters() {
    wScore.setText("Wins: " + winCounter);
    lScore.setText("Loses: " + loseCounter);
  }

  // Function to handle the "Hit" button click
  private void handleHit() {
    hitButton.setEnabled(game.handleHit());
    gamePanel.repaint();
  }

  // Function to handle the "Stay" button click
  public void handleStay() {
    hitButton.setEnabled(false);
    stayButton.setEnabled(false);
    if (game.handleStay() == false) {
      loseCounter++;
    } else {
      winCounter++;
    }
    playAgainButton.setVisible(true);
    gamePanel.repaint();
  }

  // Function to handle the "Play Again?" button click
  private void handlePlayAgain() {
    game.handlePlayAgain();
    hitButton.setEnabled(true);
    stayButton.setEnabled(true);
    playAgainButton.setVisible(false);
    win.setVisible(false);
    lose.setVisible(false);
    gamePanel.repaint();
  }

  // Function to draw an image on the panel
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
