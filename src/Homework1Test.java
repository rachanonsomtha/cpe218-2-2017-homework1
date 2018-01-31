import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

public class Homework1Test {
	
	ArrayList<CalculatorTestCase> theTestCaseList = new ArrayList<CalculatorTestCase>();

	@Test
	public void testMain() {
		theTestCaseList.add(new CalculatorTestCase("251-*32*+", "(2*(5-1))+(3*2)=14"));
		theTestCaseList.add(new CalculatorTestCase("51+", "5+1=6"));
		theTestCaseList.add(new CalculatorTestCase("51-", "5-1=4"));
		theTestCaseList.add(new CalculatorTestCase("51*", "5*1=5"));
		theTestCaseList.add(new CalculatorTestCase("51/", "5/1=5"));
		theTestCaseList.add(new CalculatorTestCase("1251-*32*+*", "1*((2*(5-1))+(3*2))=14"));
		theTestCaseList.add(new CalculatorTestCase("13-57*/9+", "((1-3)/(5*7))+9=9"));
		
		
		for (CalculatorTestCase testCase: theTestCaseList) {
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    PrintStream ps = new PrintStream(baos);
		    PrintStream old = System.out;
		    System.setOut(ps);
		    Homework1.main(new String[] {testCase.getQuestion()});
		    System.out.flush();
		    System.setOut(old);
		    assertEquals(baos.toString().replaceAll("\n", ""), testCase.getAnswer());
		}
	}

}
