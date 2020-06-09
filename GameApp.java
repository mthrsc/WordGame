
/**
* GameApp.java
* 25 4 2020
* @author Matthieu Roscio
**/

import javax.swing.JOptionPane;
import java.util.Random;

public class GameApp {

  public static void main(String[] args) {

    // Variables
    int player1Lives = 0; // Minimun 3
    int player2Lives = 0;
    int lives = 0;
    String player1Input = new String();
    String player2Input = new String();
    char randomLetter = new Character('a');
    String lastTwoLetters = new String();
    int gamesLeft = 0;
    double player1Points = 0.00;
    double player2Points = 0.00;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String currentPlayer = "Player 1";
    boolean userPass = false;
    boolean wordValid = false;
    boolean exitInputLoop = false;
    String latestInput = new String();
    boolean firstRound = true;
    String wordInvalidReason;

    // object
    Game g = new Game();
    Random rand = new Random();

    // Welcome and Input
    JOptionPane.showMessageDialog(null,
        "Welcome to Java Word Game! Java Words  Game  is  a  turn-based  game, where  the  players  take  turns  to  provide one word at a time \n"
            + "Points are counted as follow: If your word is shorter than 6 letters, each letter is score 0.5 point. \n"
            + "If your word is 6 letter or longer, each letter score 1 point.\n"
            + "In case you are stuck and need to pass your turn enter \"-\" and loose a life.");

    gamesLeft = Integer.parseInt(JOptionPane.showInputDialog(null, "How many games do you want to play ?"));

    randomLetter = alphabet.charAt(rand.nextInt(alphabet.length()));

    player1Lives = player2Lives = lives = rand.nextInt(3) + 3;
    System.out.println("player1Lives: " + player1Lives + " - random letter: " + randomLetter);

    JOptionPane.showMessageDialog(null, "Each player start with " + player1Lives + " lives \n"
        + "A game end when one of the player is out of lives \n" + "You chose to play " + gamesLeft + " games");

    g.setPlayer1Lives(player1Lives);
    g.setPlayer2Lives(player2Lives);
    g.setRandomLetter(randomLetter);

    do {
      // Compute
      do {
        firstRound = g.getFirstRound();
        System.out.println("Is it a new round ? " + firstRound);
        if (firstRound == true) {
          if (currentPlayer.equals("Player 1")) {
            player1Input = JOptionPane.showInputDialog(null,
                "Player 1 : Points: " + player1Points + " Lives: " + player1Lives + "\n" + "Player 2 : Points: "
                    + player2Points + " Lives: " + player2Lives + "\n\n" + "It is " + currentPlayer + "'s turn \n"
                    + "Please enter a word starting with the letter \n" + "\"" + randomLetter + "\"");
            player1Input = player1Input.toLowerCase();
            latestInput = player1Input;
            g.setPlayer1Input(player1Input);
            g.setCurrentPlayer(currentPlayer);
            g.compute();
          } else if (currentPlayer.equals("Player 2")) {
            player2Input = JOptionPane.showInputDialog(null,
                "Player 1 : Points: " + player1Points + " Lives: " + player1Lives + "\n" + "Player 2 : Points: "
                    + player2Points + " Lives: " + player2Lives + "\n\n" + "It is " + currentPlayer + "'s turn \n"
                    + "Please enter a word starting with the letter \n" + "\"" + randomLetter + "\"");
            player2Input = player2Input.toLowerCase();
            latestInput = player2Input;
            g.setPlayer2Input(player2Input);
            g.setCurrentPlayer(currentPlayer);
            g.compute();
          }
        } else {
          lastTwoLetters = g.getLastTwoLetters();
          if (currentPlayer.equals("Player 1")) {
            player1Input = JOptionPane.showInputDialog(null,
                "Player 1 : Points: " + player1Points + " Lives: " + player1Lives + "\n" + "Player 2 : Points: "
                    + player2Points + " Lives: " + player2Lives + "\n\n" + "It is " + currentPlayer + "'s turn \n"
                    + "Please enter a word starting with the letters \n" + "\"" + lastTwoLetters + "\"");
            player1Input = player1Input.toLowerCase();
            latestInput = player1Input;
            g.setPlayer1Input(player1Input);
            g.setCurrentPlayer(currentPlayer);
            g.compute();
          } else if (currentPlayer.equals("Player 2")) {
            player2Input = JOptionPane.showInputDialog(null,
                "Player 1 : Points: " + player1Points + " Lives: " + player1Lives + "\n" + "Player 2 : Points: "
                    + player2Points + " Lives: " + player2Lives + "\n\n" + "It is " + currentPlayer + "'s turn \n"
                    + "Please enter a word starting with the letters \n" + "\"" + lastTwoLetters + "\"");
            player2Input = player2Input.toLowerCase();
            latestInput = player2Input;
            g.setPlayer2Input(player2Input);
            g.setCurrentPlayer(currentPlayer);
            g.compute();
          }
        }
        userPass = g.getUserPass();
        wordValid = g.getWordValid();

        if (userPass == true || wordValid == true) {
          exitInputLoop = true;
        } else {
          exitInputLoop = false;
          wordInvalidReason = g.getWordInvalidReason();
          JOptionPane.showMessageDialog(null, wordInvalidReason);
        }
      } while (exitInputLoop == false);

      // Output
      currentPlayer = g.getCurrentPlayer();
      player1Lives = g.getPlayer1Lives();
      player2Lives = g.getPlayer2Lives();
      player1Points = g.getPlayer1Points();
      player2Points = g.getPlayer2Points();
      wordInvalidReason = g.getWordInvalidReason();

      if (userPass == true && wordValid == false) {
        JOptionPane.showMessageDialog(null, wordInvalidReason);
        randomLetter = alphabet.charAt(rand.nextInt(alphabet.length()));
        g.setRandomLetter(randomLetter);
      } else {
        JOptionPane.showMessageDialog(null,
            "Well done " + currentPlayer + ", \"" + latestInput + "\" is a valid word. \n" + "Player 1 points: "
                + player1Points + "\n" + "Player 2 points: " + player2Points);
        if (firstRound == true) {
          firstRound = false;
          g.setFirstRound(firstRound);
        }
      }

      userPass = false;
      wordValid = false;
      g.setUserPass(userPass);
      g.setWordValid(wordValid);

      if (currentPlayer.equals("Player 1")) {
        currentPlayer = "Player 2";
      } else if (currentPlayer.equals("Player 2")) {
        currentPlayer = "Player 1";
      }

      if (player1Lives == 0) {
        gamesLeft--;
        if (gamesLeft > 0) {
          JOptionPane.showMessageDialog(null,
              "Player 1 is out of lives ! Player 2 wins !" + "\n" + "Player 1 points: " + player1Points + "\n"
                  + "Player 2 points: " + player2Points + "\n" + "There is " + gamesLeft
                  + " games left. Starting new game now.");
        } else {
          JOptionPane.showMessageDialog(null, "Player 1 is out of lives ! Player 2 wins !" + "\n" + "Player 1 points: "
              + player1Points + "\n" + "Player 2 points: " + player2Points + "\n" + "That was the last game");
        }
        currentPlayer = "Player 1";
        player1Lives = player2Lives = lives;
        g.setPlayer1Lives(player1Lives);
        g.setPlayer2Lives(player2Lives);
        player1Points = player2Points = 0.0;
        g.setPlayer1Point(player1Points);
        g.setPlayer2Points(player2Points);
        System.out.println("Resting all");
      } else if (player2Lives == 0) {
        gamesLeft--;
        if (gamesLeft > 0) {
          JOptionPane.showMessageDialog(null,
              "Player 2 is out of lives ! Player 1 wins !" + "\n" + "Player 1 points: " + player1Points + "\n"
                  + "Player 2 points: " + player2Points + "\n" + "There is " + gamesLeft
                  + " games left. Starting new game now.");
        } else {
          JOptionPane.showMessageDialog(null, "Player 2 is out of lives ! Player 1 wins !" + "\n" + "Player 1 points: "
              + player1Points + "\n" + "Player 2 points: " + player2Points + "\n" + "That was the last game, thanks for playing.");
        }
        currentPlayer = "Player 1";
        player1Lives = player2Lives = lives;
        g.setPlayer1Lives(player1Lives);
        g.setPlayer2Lives(player2Lives);
        player1Points = player2Points = 0.0;
        g.setPlayer1Point(player1Points);
        g.setPlayer2Points(player2Points);
        System.out.println("Resting all");
      }
    } while (gamesLeft > 0);
  }
}