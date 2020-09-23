package lab1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestForTest {

	
	@Test
	public void addition() {
		
		System.out.println("Adding test");
		Calculator calculator = new Calculator();
		assertEquals(2, calculator.add(1,  1));
		
	}
	
	
	@Test
	public void additionButFails() {
		
		System.out.println("Adding failing test");
		Calculator calculator = new Calculator();
		assertEquals(2, calculator.add(2,  1));
		
		
	}

	
}
