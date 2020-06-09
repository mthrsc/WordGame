
/**
 * Game.java 
 * 25 4 2020
 * @author Matthieu Roscio
 **/

import java.util.Arrays;

public class Game {

  // Variables
  private String player1Input;
  private String player2Input;
  private String lastTwoLetters;
  private int player1Lives; // Minimun 3
  private int player2Lives;
  private double player1Points;
  private double player2Points;
  private int gamesLeft;
  private String currentPlayer;
  private boolean firstRound;
  private char randomLetter;
  private String tempS;
  private char tempC;
  private boolean wordValid;
  private int wordLength;
  private boolean userPass;
  private StringBuffer sb;
  private String wordInvalidReason;

  private final String[] WORDS;

  // Constructor
  public Game() {
    WORDS = new String[] { "able", "about", "above", "act", "add", "afraid", "after", "again", "against", "age", "ago",
        "agree", "air", "all", "allow", "also", "always", "among", "and", "anger", "animal", "answer", "any", "appear",
        "apple", "are", "area", "aria", "arm", "arrange", "arrive", "art", "ask", "atom", "baby", "back", "bad", "ball",
        "balloon", "band", "bank", "bar", "base", "basic", "bat", "bear", "beat", "beauty", "bed", "been", "before",
        "began", "begin", "behind", "believe", "bell", "best", "better", "between", "big", "bird", "bit", "black",
        "bliss", "block", "blood", "blow", "blue", "board", "boat", "body", "bone", "book", "bookkeeper", "bookkeeping",
        "born", "both", "bottom", "bought", "box", "boy", "branch", "bread", "break", "bright", "bring", "broad",
        "broke", "brother", "brought", "brown", "build", "burn", "busy", "but", "buy", "cabin", "call", "came", "camp",
        "can", "capital", "captain", "car", "card", "care", "carry", "case", "cat", "catch", "caught", "cause", "cell",
        "cent", "center", "century", "certain", "chair", "chance", "change", "chaos", "character", "charge", "chart",
        "check", "cheerleader", "cheese", "chick", "chief", "child", "children", "choose", "chord", "circle", "city",
        "claim", "class", "classroom", "clean", "clear", "climb", "climbing", "clock", "close", "clothe", "cloud",
        "coast", "coat", "coffee", "cold", "collect", "colony", "color", "column", "come", "committee", "common",
        "company", "compare", "complete", "condition", "connect", "consider", "consonant", "contain", "continent",
        "continue", "control", "cook", "cool", "copy", "corn", "corner", "correct", "cost", "cotton", "could", "count",
        "country", "course", "cover", "cow", "crease", "create", "crop", "cross", "crowd", "cry", "current", "cut",
        "dad", "dance", "danger", "dark", "day", "dead", "deal", "dear", "death", "decide", "decimal", "deep", "deer",
        "degree", "depend", "describe", "desert", "design", "determine", "develop", "dictionary", "did", "die",
        "differ", "difficult", "direct", "discuss", "distant", "divide", "division", "doctor", "does", "dog", "dollar",
        "done", "door", "double", "down", "draw", "dream", "dress", "drink", "drive", "drop", "dry", "duck", "during",
        "each", "ear", "early", "earth", "ease", "east", "eat", "edge", "effect", "egg", "eight", "either", "electric",
        "element", "else", "end", "enemy", "energy", "engine", "enough", "enter", "equal", "equate", "especially",
        "even", "evening", "event", "ever", "every", "exact", "example", "except", "excite", "exercise", "expect",
        "experience", "experiment", "eye", "face", "fact", "fair", "fall", "family", "famous", "far", "farm", "fast",
        "fat", "father", "favor", "fear", "feed", "feel", "feeling", "feet", "fell", "felt", "few", "field", "fig",
        "fight", "figure", "fill", "final", "find", "fine", "finger", "finish", "fire", "first", "fish", "fit", "five",
        "flat", "floor", "flow", "flower", "fly", "follow", "food", "foot", "for", "force", "forest", "form", "forward",
        "found", "four", "fraction", "free", "freedom", "fresh", "friend", "from", "front", "fruit", "fry", "full",
        "fun", "game", "garden", "gas", "gather", "gave", "general", "gentle", "get", "girl", "give", "glad", "glass",
        "gold", "gone", "good", "got", "govern", "grand", "grass", "gray", "great", "green", "grew", "ground", "group",
        "grow", "guess", "guide", "gun", "gym", "had", "hair", "half", "hand", "happen", "happy", "hard", "has", "hat",
        "have", "head", "hear", "heard", "heart", "heat", "heavy", "held", "help", "her", "here", "high", "hill", "him",
        "his", "history", "hit", "hold", "hole", "home", "hope", "horse", "hot", "hour", "house", "how", "huge",
        "human", "hundred", "hunt", "hurry", "hymn", "ice", "idea", "imagine", "inch", "include", "indicate",
        "industry", "insect", "instant", "instrument", "interest", "invent", "iron", "island", "job", "join", "joy",
        "jump", "just", "keep", "kept", "key", "kill", "kind", "king", "knew", "know", "knowledge", "lady", "lake",
        "land", "language", "large", "last", "late", "laugh", "law", "lay", "lead", "learn", "least", "leave", "led",
        "left", "leg", "length", "less", "let", "letter", "level", "lie", "life", "lift", "light", "like", "line",
        "liquid", "list", "listen", "little", "live", "locate", "log", "lone", "long", "look", "loop", "lost", "lot",
        "loud", "love", "low", "lynx", "lyrics", "machine", "made", "magnet", "main", "major", "make", "man", "many",
        "map", "mark", "market", "mass", "master", "match", "material", "matter", "may", "mean", "meant", "measure",
        "meat", "meet", "melody", "men", "metal", "method", "middle", "might", "mile", "milk", "million", "mind",
        "mine", "minute", "miracle", "miss", "mix", "modern", "molecule", "moment", "money", "month", "moon", "more",
        "morning", "most", "mother", "motion", "mount", "mountain", "mountaineering", "mouth", "move", "much",
        "multiply", "music", "must", "myth", "name", "nation", "natural", "nature", "near", "necessary", "neck", "need",
        "neighbor", "never", "new", "next", "night", "nine", "noise", "noon", "nor", "north", "nose", "not", "note",
        "nothing", "notice", "noun", "now", "number", "numeral", "nymph", "object", "observe", "occur", "ocean", "off",
        "offer", "office", "often", "oil", "old", "once", "one", "only", "open", "opera", "operate", "opposite",
        "order", "organ", "original", "other", "our", "out", "over", "own", "oxygen", "page", "paint", "painter",
        "painting", "pair", "paper", "paragraph", "parent", "part", "particular", "party", "pass", "past", "path",
        "pattern", "pay", "people", "performance", "perhaps", "period", "person", "phrase", "pick", "picture", "piece",
        "pitch", "place", "plain", "plan", "plane", "planet", "plant", "play", "please", "plural", "poem", "point",
        "poor", "populate", "port", "pose", "position", "possible", "post", "pound", "power", "practice", "prepare",
        "present", "press", "pretty", "print", "probable", "problem", "process", "produce", "product", "program",
        "proof", "proper", "property", "protect", "prove", "provide", "pull", "push", "put", "quart", "queen",
        "question", "quick", "quiet", "quite", "quotient", "race", "radio", "rail", "rain", "raise", "ran", "range",
        "rather", "reach", "read", "ready", "real", "reason", "receive", "record", "red", "region", "remember",
        "repeat", "reply", "represent", "require", "rest", "result", "rhythm", "rich", "ride", "right", "ring", "rise",
        "river", "road", "rock", "roll", "room", "root", "rope", "rose", "round", "row", "rub", "rule", "run", "safe",
        "said", "sail", "salt", "same", "sample", "sand", "sat", "save", "saw", "say", "scale", "school", "science",
        "score", "sea", "search", "season", "seat", "second", "section", "see", "seed", "seem", "segment", "select",
        "self", "sell", "send", "sense", "sent", "sentence", "separate", "serve", "set", "settle", "seven", "several",
        "shall", "shape", "share", "sharp", "she", "sheet", "shell", "shine", "ship", "shoe", "shop", "shore", "short",
        "should", "shoulder", "shout", "show", "shy", "side", "sight", "sign", "silent", "silver", "similar", "simple",
        "since", "sing", "single", "sister", "sit", "six", "size", "skill", "skin", "sky", "slave", "sleep", "slip",
        "slow", "sly", "small", "smell", "smile", "snow", "soft", "soil", "soldier", "solution", "solve", "some", "son",
        "song", "soon", "soul", "sound", "south", "space", "speak", "special", "speech", "speed", "spell", "spend",
        "spoke", "spot", "spread", "spring", "spy", "square", "stand", "star", "start", "state", "station", "stay",
        "stead", "steam", "steel", "step", "stick", "still", "stone", "stood", "stop", "store", "story", "straight",
        "strange", "stream", "street", "stretch", "string", "strong", "student", "study", "subject", "substance",
        "subtract", "success", "such", "sudden", "suffix", "sugar", "suggest", "suit", "summer", "sun", "supply",
        "support", "sure", "surface", "surprise", "swim", "syllable", "symbol", "system", "table", "tail", "take",
        "talk", "tall", "teach", "team", "teeth", "tell", "temperature", "ten", "term", "test", "than", "thank", "that",
        "the", "their", "them", "then", "there", "these", "they", "thick", "thin", "thing", "think", "third", "this",
        "those", "though", "thought", "thousand", "three", "through", "throw", "thus", "tie", "time", "tiny", "tire",
        "together", "told", "tone", "too", "took", "tool", "top", "total", "touch", "toward", "town", "track", "trade",
        "train", "travel", "tree", "triangle", "trip", "trouble", "truck", "try", "tube", "turn", "twenty", "two",
        "type", "under", "unit", "until", "use", "usual", "valley", "value", "vary", "verb", "very", "view", "village",
        "visit", "voice", "vowel", "wait", "walk", "wall", "want", "war", "warm", "was", "wash", "watch", "water",
        "wave", "way", "wear", "weather", "week", "weekend", "weight", "well", "went", "were", "west", "what", "wheel",
        "when", "where", "whether", "which", "while", "white", "who", "whole", "whose", "why", "wide", "wife", "wild",
        "will", "win", "wind", "window", "wing", "winter", "wire", "wish", "with", "woman", "women", "wonder", "wood",
        "word", "work", "world", "would", "write", "written", "wrong", "wrote", "wry", "xerox", "xylophone", "yard",
        "year", "yellow", "yes", "yet", "you", "young", "your", "zebra", "zen", "zero", "zillion", "zinc", "zip",
        "zone", "zoo", "zookeeper", "zoologist" };
    player1Input = new String();
    player2Input = new String();
    player1Lives = 0;
    player2Lives = 0;
    player1Points = 0.00;
    player2Points = 0.00;
    gamesLeft = 0;
    currentPlayer = new String();
    firstRound = true;
    randomLetter = new Character('a');
    tempS = new String();
    tempC = new Character('a');
    wordValid = false;
    wordLength = 0;
    userPass = false;
    sb = new StringBuffer();
  }

  // Setters
  public void setPlayer1Input(String p1Input) {
    this.player1Input = p1Input;
  }

  public void setPlayer2Input(String p2Input) {
    this.player2Input = p2Input;
  }

  public void setPlayer1Lives(int p1Lives) {
    this.player1Lives = p1Lives;
  }

  public void setPlayer2Lives(int p2Lives) {
    this.player2Lives = p2Lives;
  }

  public void setCurrentPlayer(String currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public void setRandomLetter(char randomLetter) {
    this.randomLetter = randomLetter;
  }

  public void setPlayer1Point(double p1p) {
    this.player1Points = p1p;
  }

  public void setPlayer2Points(double p2p) {
    this.player2Points = p2p;
  }

  public void setUserPass(boolean userPass) {
    this.userPass = userPass;
  }

  public void setWordValid(boolean wordValid) {
    this.wordValid = wordValid;
  }

  public void setFirstRound(boolean firstRound) {
    this.firstRound = firstRound;
  }

  // Compute
  public void compute() {

    if (firstRound == true) {
      if (currentPlayer.equals("Player 1")) {

        tempC = player1Input.charAt(0);

        // Is the word at shorter than 3 letters ?
        if (player1Input.length() < 3) {
          // Is the Player trying to pass
          if (player1Input.charAt(0) == '-') {
            wordValid = false;
            userPass = true;
            player1Lives--;
            firstRound = true;
            wordInvalidReason = "Player 1 passes. -1 life";
          } else { // Word is invalid because it is too short
            wordValid = false;
            userPass = false;
            wordInvalidReason = "Word is invalid: Word too short";
          }
        } else {
          // Is the word beginning with the random letter ?
          if (tempC == randomLetter) {
            // Is the word valid ? Is it in the provided dictionnary ?
            if (Arrays.asList(WORDS).contains(player1Input)) {
              // Word is valid
              wordValid = true;
              userPass = false;
              wordLength = player1Input.length();
              player1Points += pointCalculus(wordLength);
              sb.append(player1Input.charAt(player1Input.length() - 2));
              sb.append(player1Input.charAt(player1Input.length() - 1));
              lastTwoLetters = sb.toString();
              sb.setLength(0);
            } else {
              // Word is invalid
              wordValid = false;
              userPass = false;
              wordInvalidReason = "Word is invalid: Not in dictionnary";
            }
          } else {
            // Word is invalid
            wordValid = false;
            userPass = false;
            wordInvalidReason = "Word is invalid: First two letters do not match requirement";
          }

        }
      } else if (currentPlayer.equals("Player 2")) {

        tempC = player2Input.charAt(0);

        // Is the word at shorter than 3 letters ?
        if (player2Input.length() < 3) {
          // Is the Player trying to pass
          if (player2Input.charAt(0) == '-') {
            wordValid = false;
            userPass = true;
            player2Lives--;
            firstRound = true;
            wordInvalidReason = "Player 2 passes. -1 life";
          } else { // Word is invalid because it is too short
            wordValid = false;
            userPass = false;
            wordInvalidReason = "Word is invalid: Word too short";
          }
        } else {
          // Is the word beginning with the random letter ?
          if (tempC == randomLetter) {
            // Is the word valid ? Is it in the provided dictionnary ?
            if (Arrays.asList(WORDS).contains(player2Input)) {
              // Word is valid
              wordValid = true;
              userPass = false;
              wordLength = player2Input.length();
              player2Points += pointCalculus(wordLength);
              sb.append(player2Input.charAt(player2Input.length() - 2));
              sb.append(player2Input.charAt(player2Input.length() - 1));
              lastTwoLetters = sb.toString();
              sb.setLength(0);
            } else {
              // Word is invalid
              wordValid = false;
              userPass = false;
              wordInvalidReason = "Word is invalid: Not in dictionnary";
            }
          } else {
            // Word is invalid
            wordValid = false;
            userPass = false;
            wordInvalidReason = "Word is invalid: First two letters do not match requirement";
          }
        }
      }
    } else {
      if (currentPlayer.equals("Player 1")) {
        // PLAYER 1 FLOW

        // Is the word 3 letters long ?
        if (player1Input.length() < 3) {
          // Is the Player trying to pass
          if (player1Input.charAt(0) == '-') {
            wordValid = false;
            userPass = true;
            player1Lives--;
            firstRound = true;
            wordInvalidReason = "Player 1 passes. -1 life";
          } else { // Word is invalid because it is too short
            wordValid = false;
            userPass = false;
            wordInvalidReason = "Word is invalid: Word too short";
          }
        } else {
          // Is the word beginning with the last two letters ?
          sb.append(player1Input.charAt(0));
          sb.append(player1Input.charAt(1));
          tempS = sb.toString();
          sb.setLength(0);

          if (tempS.equals(lastTwoLetters)) {
            // Is the word valid ? Is it in the provided dictionnary ?
            if (Arrays.asList(WORDS).contains(player1Input)) {
              // Word is valid
              wordValid = true;
              userPass = false;
              wordLength = player1Input.length();
              player1Points += pointCalculus(wordLength);
              sb.append(player1Input.charAt(player1Input.length() - 2));
              sb.append(player1Input.charAt(player1Input.length() - 1));
              lastTwoLetters = sb.toString();
              sb.setLength(0);
            } else {
              // Word is invalid
              wordValid = false;
              userPass = false;
              wordInvalidReason = "Word is invalid: Not in dictionnary";
            }
          } else {
            // Word is invalid
            wordValid = false;
            userPass = false;
            wordInvalidReason = "Word is invalid: First two letters do not match requirement";
          }
        }
      } else if (currentPlayer.equals("Player 2")) {
        // PLAYER 2 FLOW
        // Is the word 3 letters long ?
        if (player2Input.length() < 3) {
          // Is the Player trying to pass
          if (player2Input.charAt(0) == '-') {
            wordValid = false;
            userPass = true;
            player2Lives--;
            firstRound = true;
            wordInvalidReason = "Player 2 passes. -1 life";
          } else { // Word is invalid because it is too short
            wordValid = false;
            userPass = false;
            wordInvalidReason = "Word is invalid: Word too short";
          }
        } else {
          // Is the word beginning with the last two letters ?
          sb.append(player2Input.charAt(0));
          sb.append(player2Input.charAt(1));
          tempS = sb.toString();
          sb.setLength(0);

          if (tempS.equals(lastTwoLetters)) {
            // Is the word valid ? Is it in the provided dictionnary ?
            if (Arrays.asList(WORDS).contains(player2Input)) {
              // Word is valid
              wordValid = true;
              userPass = false;
              wordLength = player2Input.length();
              player2Points += pointCalculus(wordLength);
              sb.append(player2Input.charAt(player2Input.length() - 2));
              sb.append(player2Input.charAt(player2Input.length() - 1));
              lastTwoLetters = sb.toString();
              sb.setLength(0);
            } else {
              // Word is invalid
              wordValid = false;
              userPass = false;
              wordInvalidReason = "Word is invalid: Not in dictionnary";
            }
          } else {
            // Word is invalid
            wordValid = false;
            userPass = false;
            wordInvalidReason = "Word is invalid: First two letters do not match requirement";
          }
        }
      }
    }
  }

  private double pointCalculus(int wordL) {
    System.out.println("wordL: " + wordL);
    double score = 0.00;
    if (wordL >= 6) {
      score = wordL * 1;
    } else if (wordL < 6) {
      score = wordL * 0.5;
    }
    return score;
  }

  // Getters
  public String getLastTwoLetters() {
    return lastTwoLetters;
  }

  public int getGamesLeft() {
    return gamesLeft;
  }

  public String getCurrentPlayer() {
    return currentPlayer;
  }

  public int getPlayer1Lives() {
    return player1Lives;
  }

  public int getPlayer2Lives() {
    return player2Lives;
  }

  public boolean getUserPass() {
    return userPass;
  }

  public boolean getWordValid() {
    return wordValid;
  }

  public double getPlayer1Points() {
    return player1Points;
  }

  public double getPlayer2Points() {
    return player2Points;
  }

  public boolean getFirstRound() {
    return firstRound;
  }

  public String getWordInvalidReason(){
    return wordInvalidReason;
  }
}
