import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(new File("C:\\Users\\user\\eclipse-workspace\\Hangman txt file\\words.txt"));
		Scanner keyboard = new Scanner(System.in);    //keyboard input

		ArrayList<String> words = new ArrayList<>();
		
		while (scanner.hasNext()) {
			words.add(scanner.nextLine());    //add words to arraylist
		}
		
		Random rand = new Random();
		String word = words.get(rand.nextInt(words.size()));  //random class
		
		System.out.println(word);       //output random word
		
		ArrayList<Character> playerGuesses = new ArrayList<>();
		
		int wrongCount = 0;       
		
	
		//drawing man
		while (true) {
		printHangedMan(wrongCount);
		
		if (wrongCount >= 6) {
			System.out.println("You lose!");
			break;
		}
		
			
			
			
		printWordState(word, playerGuesses);
		if (!getPlayerGuess(keyboard, word, playerGuesses)) {
			wrongCount++;
		}
		
		if(printWordState(word, playerGuesses)) {  //if everything is ok game finished
			break;
		}
		
		System.out.println("Please enter your guess for the word: ");   //guessing whole word
		if (keyboard.nextLine().equals(word)) {
			System.out.println("You win!");
			break;
		}
		else {
			System.out.println("Nope! Try again...");
		}
		
	}
		
	}

	
	//drawig man method
	private static void printHangedMan(int wrongCount) {
		//increasing lines
		System.out.println(" -------");
		System.out.println(" |     |");
		if (wrongCount >= 1) {
			System.out.println(" O");
		}
		if (wrongCount >= 2) {
			System.out.print("\\ ");
			if (wrongCount >= 3) {
				System.out.println("/");
			}
			else {
				System.out.println();
			}
		}
		if (wrongCount >= 4) {
			System.out.println(" |");
		}
		
		
		if (wrongCount >= 5) {
			System.out.print("/ ");
			if (wrongCount >= 6) {
				System.out.println("\\");
			}
			else {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println();
	}

	//guessing method
	private static boolean getPlayerGuess(Scanner keyboard, String word, ArrayList<Character> playerGuesses) {
		System.out.println("Please enter a letter: ");
		String letterGuess = keyboard.nextLine();
		playerGuesses.add(letterGuess.charAt(0));
		
		return word.contains(letterGuess);
	}

	//finding letters method
	private static boolean printWordState(String word, ArrayList<Character> playerGuesses) {
		int correctCount = 0;
		
		for (int i = 0; i < word.length(); i++) {
			if (playerGuesses.contains(word.charAt(i))) {  //checking letters
				System.out.print(word.charAt(i));    //if correct
				correctCount++;
			}
			else {                                  //if false
				System.out.print("-");
			}
		}
		System.out.println();
		
		return (word.length() == correctCount);
	}

}
