package lab1;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class LabTest {
	Lab lab = new Lab();
	
    @Test
    public void checkPalindrome() {
        System.out.println(" test palindrom");
        assertTrue(lab.palindrome("mom"));
        assertTrue(lab.palindrome("123321"));
    }

    @Test
    public void checkPalindromeFail() {
        System.out.println(" testing palindrome");
        
        assertTrue(lab.palindrome("racecars"));
        assertTrue(lab.palindrome("456"));
    }

}
