package lab1;

import java.util.ArrayList;
import java.util.Collections;

public class Lab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	System.out.println("Hello World");

	}
	
    public boolean palindrome(String word) {
        ArrayList<Character> toLabTest = new ArrayList<Character>();
        ArrayList<Character> reverse = new ArrayList<Character>();
        for (char c : word.toCharArray()) {
            toLabTest.add(c);
            reverse.add(c);
        }
        Collections.reverse(reverse);
        return toLabTest.equals(reverse) ? true : false;
    }
}
