package com.siva.memorygame;

import java.util.*;

/*
 * As per requirement, board is intialized based on the hardcoded board size. currently configured as 6. All card in the deck is intialized to face down.
 * System follows rule like at most only 2 cards are open.
 * If cards matched instead showing red color a message is displayed stating card matched similarly for the case of card not matched too.
  * Deck is currently printed for reference, but unfortunately coderpad is not displaying unicode characters in its console.
   * you can copy the code to the dev machine and while excuting you can find the deck unicode tokens are printed.
 *Algorithm
 * 1. Game is initialized
 * 2. Board is printed for reference
 * 3. User selects the first card to open by giving row and column
 * 4. User selects the second card to open by giving row and column
 * 5. if card matches, it prints the message stating the same. If it did not match it resets the open cards.
 * 6. The above steps continue until all pairs are identified.
 *
 *Design Idea
 * Card - holds the card details and its related operation
 * Deck - holds the board and its related operation
 * Game - Holds an instance of the game and executes the game operation
 * RandomNumberUtil - Utility class for generating randomNumber
 * FaceValueGenerator - Utility class holding and randomly generating one of the 768 MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS unicode characters(based on the requirement)
 * Solution - trigger of the game and to interact via the console
 */

public class MemoryGame {
    Game game = new Game();
    public static void main(String[] args) {
        MemoryGame memoryGame = new MemoryGame();
        memoryGame.game.intializeGame();
        System.out.println("    Game Initialized");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Nter the row : ");
            int row = scanner.nextInt();
            System.out.print("Nter the Column : ");
            int column = scanner.nextInt();
            String result = memoryGame.game.playGame(row, column);
            System.out.println(result);
            if("    Game Over".equals(result))
                break;
        }
    }

    class Card {

        boolean isFaceUp;

        String cardFaceValue;

        boolean matched;

        Card(String cardFaceValue) {
            this.cardFaceValue = cardFaceValue;
        }

        @Override
        public boolean equals(Object obj) {
            return this.cardFaceValue.equals(((Card)obj).cardFaceValue);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    class Deck {

        Card [][] deck;

        RandomNumberUtil randomNumberUtil = new RandomNumberUtil();
        FaceValueGenerator faceValueGenerator = new FaceValueGenerator();

        Deck(int gridSize) {
            deck = new Card[gridSize][gridSize];
            populateDeck(gridSize);
            printDeck();
        }

        private void printDeck() {
            for(int row =0; row < deck.length; row++) {
                for(int column =0; column < deck.length; column++) {
                    System.out.print(deck[row][column].cardFaceValue + "  ");
                }
                System.out.println();
            }
            //Arrays.stream(deck).forEach(x -> Arrays.stream(x).forEach(y -> System.out.println(y.cardFaceValue)));
        }

        private void populateDeck(int gridSize) {
            int totalNoOfCards = gridSize*gridSize;
            int counter = 0;
            int pairCounter = 1;
            String faceValue="";
            while (counter < totalNoOfCards) {
                faceValue = 0 == pairCounter%2 ? faceValue : faceValueGenerator.generateRandomFaceValue();
                int randomRow =randomNumberUtil.getRandomNumber(gridSize);
                int randomColumn =randomNumberUtil.getRandomNumber(gridSize);
                if(null == deck[randomRow][randomColumn]) {
                    deck[randomRow][randomColumn] = new Card(faceValue);
                    counter++;
                    pairCounter++;
                }
            }
        }


    }

    class RandomNumberUtil {

        private Random random = new Random();

        private int getRandomNumber(int bound) {
            return random.nextInt(bound);
        }
    }

    class FaceValueGenerator {

        final List<String> chars = new ArrayList<>();

        RandomNumberUtil randomNumberUtil = new RandomNumberUtil();

        {
            Character.UnicodeBlock block = Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS;


            for (int codePoint = Character.MIN_CODE_POINT; codePoint <= Character.MAX_CODE_POINT; codePoint++) {
                if (block == Character.UnicodeBlock.of(codePoint)) {
                    chars.add(String.valueOf((char) codePoint));
                }
            }
        }

        public String generateRandomFaceValue() {
            return chars.get(randomNumberUtil.getRandomNumber(chars.size()-1));
        }
    }

    class Game {

        final static int MAX_NO_OF_OPEN_CARDS = 2;

        final static int BOARD_SIZE = 6;

        int noOfCardsToMatch;

        int noOfCardsOpen;

        Deck board;

        Card openCard1, openCard2;

        public void intializeGame() {
            board = new Deck(BOARD_SIZE);
            noOfCardsToMatch = BOARD_SIZE*BOARD_SIZE;
            noOfCardsOpen = 0;
        }

        private boolean isGameOver() {
            return 0 == noOfCardsToMatch;
        }

        private boolean doesTheOpenCardsMatch() {
            return openCard1.equals(openCard2);
        }

        public String playGame(int rowIndex, int columnIndex) {

            if(noOfCardsOpen >= MAX_NO_OF_OPEN_CARDS)
                return "    cannot open any more new cards as of now";

            switch (noOfCardsOpen) {
                case 0 :
                    if(board.deck[rowIndex][columnIndex].matched || board.deck[rowIndex][columnIndex].isFaceUp)
                        return "    cannot open a matched or already open card";
                    openCard1 = board.deck[rowIndex][columnIndex];
                    openCard1.isFaceUp = true;
                    noOfCardsOpen++;
                    break;
                case 1 :
                    if(board.deck[rowIndex][columnIndex].matched || board.deck[rowIndex][columnIndex].isFaceUp)
                        return "    cannot open a matched or already open  card";
                    openCard2 = board.deck[rowIndex][columnIndex];
                    openCard1.isFaceUp = true;
                    noOfCardsOpen++;
                    break;
            }

            if(noOfCardsOpen == MAX_NO_OF_OPEN_CARDS) {
                if(doesTheOpenCardsMatch()) {
                    noOfCardsToMatch-=2;
                    openCard1.matched = true;
                    openCard2.matched = true;
                    System.out.println("    card matched");
                }else
                    System.out.println("    card not matched");
                openCard1.isFaceUp = false;
                openCard2.isFaceUp = false;
                noOfCardsOpen = 0;
            }

            if(isGameOver())
                return "    Game Over";

            return "    continue playing";

        }

    }
}
