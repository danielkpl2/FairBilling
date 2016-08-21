import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;


public class LogReaderTest {
	String newLine = System.getProperty("line.separator");
	
	@Test
	public void testCorrectInput() throws IOException {
		
		
		String testString = "14:02:03 ALICE99 Start" + newLine +
				"14:02:05 CHARLIE End" + newLine +
				"14:02:34 ALICE99 End";
		
		
		
		BufferedReader bf = new BufferedReader(new StringReader(testString));
		
		LogReader logReader = new LogReader(bf);
				
		assertEquals(3, logReader.getLogSize());
		
	}

}
