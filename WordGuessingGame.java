package package_name;

import java.util.Scanner;

public class WordGuessingGame {

	public static void main(String[] args) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String mysteryWord = "";
		boolean isMysteryWordSolved = false;
		boolean isMysteryWordValid = false;
		int guessesLeft = 10;
		int guessAttempts = 0;
		char characterGuess = 0;
		
		System.out.println(alphabet);
		
		Scanner scanner = new Scanner(System.in);  // Create a Scanner object
	    
	    System.out.println("-----------------------------------------\n");
	    System.out.println("Word Guessing Game \n");
	    System.out.println("-----------------------------------------\n");
	    
	    while(isMysteryWordValid == false) {
		    System.out.println("Other Player - Enter your word (up to 10 letters only, not case sensitive):");
	    	mysteryWord = scanner.nextLine();
	    	
	    	if(mysteryWord.matches("[a-zA-Z]+") && mysteryWord.length() <= 10) {
	    		isMysteryWordValid = true;
	    	}else {
	    		System.out.println("The mystery word you enter must only contain letters and be less than 10 characters\n");
	    	}
	    }
    	
    	for(int i = 0; i < 20; i++) { // print 20 blank lines
    		System.out.println();
    	}
    	
    	String mysteryWordHidden = mysteryWord.replaceAll(".", "*");
    	StringBuilder wordToDate = new StringBuilder(mysteryWordHidden);
    	
	    while(!isMysteryWordSolved) {
	    	System.out.println("Word to date: " + wordToDate + " (" + guessesLeft + " guess(es) left)\n");
	    	System.out.println("Want to solve the puzzle? Enter \"Y\" to solve the puzzle, or \"N\" to guess a character:");
	    	char guessType = scanner.next().charAt(0);
	    	scanner.nextLine();
	    	
	    	if(guessType == 'Y' || guessType == 'y') {
	    		boolean isWordGuessValid = false;
	    		String wordGuess = "";
	    		
	    		while(isWordGuessValid == false) {
	    			System.out.println("Enter the mystery word:");
	    			wordGuess = scanner.nextLine();
	    		
	    			if(wordGuess.matches("[a-zA-Z]+")) {
	    				isWordGuessValid = true;
	    			}else {
	    				System.out.println("The word you enter must only contain letters");
	    			}
	    		}
	    		
	    		guessesLeft--;
	    		guessAttempts++;
	    			    		
	    		if(wordGuess.equals(mysteryWord)) {
	    			System.out.println("Congratulations you guessed the mystery word \"" + mysteryWord + "\" in " + guessAttempts + " guess(es)");
	    			isMysteryWordSolved = true;
	    		}else {
	    			if(wordGuess.matches("[a-zA-Z]+")) {
	    				System.out.println(wordGuess + " is not the mystery word\n");
	    			}else {
	    				System.out.println(wordGuess + " is not the mystery word and your guess should only contain letters\n");
	    			}
	    		}
	    	}else if(guessType == 'N' || guessType == 'n'){
	    		boolean isCharacterValid = false;
	    		
	    		while(isCharacterValid == false) {
		    		System.out.println("Letters to try: " + alphabet);
		    		System.out.println("Which letter should I check for");
		    		characterGuess = scanner.next().charAt(0);
		    		scanner.nextLine();
		    		
		    		if(Character.toString(characterGuess).matches("[a-zA-Z]+")) {
		    			isCharacterValid = true;
		    		}else {
		    			System.out.println("The character you enter must be a letter");
		    		}
	    		}
	    		
	    		guessesLeft--;
	    		guessAttempts++;
	    		
	    		// update mysteryWordHidden if it contains characterGuess
	    		if(mysteryWord.indexOf(characterGuess) != -1) {
	    			if(wordToDate.indexOf(Character.toString(characterGuess)) != -1) {	    				
	    				int lastOccurence = wordToDate.lastIndexOf(Character.toString(characterGuess));

	    				if(mysteryWord.indexOf(characterGuess, lastOccurence + 1) != -1) {
	    					wordToDate.setCharAt(mysteryWord.indexOf(characterGuess, 
		    						lastOccurence + 1), characterGuess);
	    				}else {
	    					System.out.println("There are no more \"" + Character.toUpperCase(characterGuess) + "\'s\" in the mystery word\n");
	    	    			alphabet = alphabet.replace(Character.toUpperCase(characterGuess), '*');
	    				}
	    			}else {
	    				wordToDate.setCharAt(mysteryWord.indexOf(characterGuess), characterGuess);
	    			}
	    		}else {
	    			System.out.println("The letter " + characterGuess + " is not in the mystery word\n");
	    			alphabet = alphabet.replace(Character.toUpperCase(characterGuess), '*');
	    		}
	    		
	    		if(wordToDate.toString().equals(mysteryWord)) {
	    			System.out.println("Congratulations you guessed the mystery word \"" + mysteryWord + "\" in " + guessAttempts + " guess(es)");
	    			isMysteryWordSolved = true;
	    		}
	    	}else {
	    		System.out.println("You must choose 'Y' or 'N'");
	    	}
	    }
	}
}
