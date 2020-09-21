package hangman;

import java.util.Map;
import java.util.Scanner;


public class Hangman {

	private static String SECRET_WORD = WordSecret.CHOOSEN_WORD;
	private static String hiddenWord = new String(new char[SECRET_WORD.length()]).replace("\0", "?");
	private static final Map<Integer, String> hangmanMap = buildHangmanMap();
	private static int count;
	private static int guesses = 6;
	
	
	WordSecret wordSecret = new WordSecret();
	
	
	private class WordSecret {
		
		 private static final String CHOOSEN_WORD = "santa";
		
	}
	

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		while (count < 6 && hiddenWord.contains("?")) {
			System.out.println("MrMagic's Hangman game! \n \nYou can either guess one letter or the whole word \n ");
			System.out.println("Wrong guesses left = " + guesses);
			System.out.println(hiddenWord);
			String userInput = scanner.nextLine();
			System.out.println(handleInput(userInput));
		}

		scanner.close();
	}

	private static String handleInput(String userInput) {
		if (userInput.isEmpty()) {
			return "Not a valid input, can't be empty";

		} else if (userInput.length() == 1) {
			return letterGuess(userInput.toLowerCase());
		}

		return wordGuess(userInput.toLowerCase());
	}

	public static String letterGuess(String userInput) {
		StringBuilder newHiddenWord = getNewHiddenWord(userInput);

		if (hiddenWord.equals(newHiddenWord.toString())) {
			return playerFailed();
		} else {
			hiddenWord = newHiddenWord.toString();
		}
		if (SECRET_WORD.equals(hiddenWord)) {
			return playerWon();
		}
		return "";
	}

	private static StringBuilder getNewHiddenWord(String userInput) {

		StringBuilder newHiddenWord = new StringBuilder();
		for (int i = 0; i < SECRET_WORD.length(); i++) {
			if (SECRET_WORD.charAt(i) == userInput.charAt(0)) {
				newHiddenWord.append(userInput.charAt(0));
			} else if (hiddenWord.charAt(i) != '?') {
				newHiddenWord.append(SECRET_WORD.charAt(i));
			} else {
				newHiddenWord.append("?");
			}
		}
		return newHiddenWord;
	}

	public static String wordGuess(String userInput) {
		return SECRET_WORD.equalsIgnoreCase(userInput) ? playerWon() : playerFailed();
	}

	private static String playerFailed() {
		count++;
		guesses--;
		return hangmanMap.get(count);
	}

	public static String playerWon() {
		count = 7;
		return "Congratz, you won! The correct word was " + SECRET_WORD;
	}

	public static Map<Integer, String> buildHangmanMap() {
		final String wrong = "\nWrong guess, try again";
		final String vertical = "\n   |\n   |\n   |\n   |\n   |\n   |\n   |\n";
		final String verticalWithNoose = "         |\n   |\n   |\n   |\n   |\n   |\n   |\n   |\n";
		final String verticalWithHead = "         |\n   |          O\n   |\n   |\n   |\n   |\n   |\n   |\n";
		final String verticalWithShoulders = "         |\n   |          O\n   |         /|\\\n   |\n   |\n   |\n   |\n   |\n";
		final String verticalWithFullBody = "         |\n   |          O\n   |         /|\\\n   |         / \\\n   |\n   |\n   |\n   |\n";
		final String corner = "   ____________\n   |/";
		final String legs = "__/|\\__";
		final String firstGuess = legs + wrong;
		final String secondGuess = vertical + legs + wrong;
		final String thirdGuess = corner + verticalWithNoose + wrong;
		final String fourthGuess = corner + verticalWithHead + legs + wrong;
		final String fifthGuess = corner + verticalWithShoulders + legs + wrong;
		final String sixthGuess = corner + verticalWithFullBody + legs + "\nGAME OVER, the word was: " + SECRET_WORD;
		return Map.of(1, firstGuess, 2, secondGuess, 3, thirdGuess, 4, fourthGuess, 5, fifthGuess, 6, sixthGuess);
	}
}
