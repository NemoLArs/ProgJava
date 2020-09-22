package upg1;

import java.util.Scanner;

public class Password {
	
	
	private static String password = "java";
	public static boolean checkPassword = true;
	private static int count;

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while (checkPassword && count < 3) {
		System.out.println("Please enter your password");
		System.out.print("You have guessed "+count);
		System.out.println(" times out of 3");
		
	
			String userInput = scanner.nextLine();
			handleInput(userInput);
			}
		scanner.close();
	}

	private static void handleInput(String userInput) {
		
		
		if(password.equals(userInput.toLowerCase())){
			checkPassword = false;
			System.out.println("Welcome my master!");	
			
		} 
		
		else if (count+(1)==(3)){
			
			checkPassword = false;
			System.out.println("To many wrong guesses! You are out!");
			
		}
		
		else {
			count ++;
			System.out.println("Wrong password");	
		}
		
		return;

	}
}
