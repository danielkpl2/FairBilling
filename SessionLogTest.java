import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;


public class SessionLogTest {
	private String newLine = System.getProperty("line.separator");
	
	@Test
	public void testCorrectInput() throws IOException {
		String testString = "14:02:03 ALICE99 Start" + newLine +
				"14:02:05 CHARLIE End" + newLine +
				"14:02:34 ALICE99 End";
		BufferedReader bf = new BufferedReader(new StringReader(testString));
		SessionLog logReader = new SessionLog(bf);
		assertEquals(3, logReader.getLogSize());
	}
	
	@Test
	public void testIncorrectInput(){
		String testString = "14:02:03 ALICE99 Start" + newLine +
				"14:02:05 CHARLIE End" + newLine +
				"abcd" + newLine +
				"one two three" + newLine +
				"14:02:34 ALICE99 End";
		BufferedReader bf = new BufferedReader(new StringReader(testString));
		SessionLog logReader = new SessionLog(bf);
		assertEquals(3, logReader.getLogSize());
	}
	
	@Test
	public void testIncorrectTimeStamp(){
		String testString = "24:02:11 ALICE99 Start" + newLine +
				"14:02:05 CHARLIE End" + newLine + //correct
				"14:02:34 ALICE99 End" + newLine + //correct
				"14:60:00 ALICE99 Start" + newLine +
				"14:59:60 CHARLIE Start" + newLine +
				"0:01:30 CHARLIE End" + newLine +
				"00:0:30 CHARLIE End" + newLine +
				"00:01:3 CHARLIE End" + newLine +
				"-1:12:15 CHARLIE Start" + newLine +
				"ab:cd:ef CHARLIE Start";

		BufferedReader bf = new BufferedReader(new StringReader(testString));
		SessionLog logReader = new SessionLog(bf);
		assertEquals(2, logReader.getLogSize());
		
	}
	
	@Test
	public void testStartEnd(){
		String testString = "23:02:11 ALICE99 somestring" + newLine +
				"14:02:05 CHARLIE End" + newLine +
				"14:02:34 ALICE99 Start";
		
		BufferedReader bf = new BufferedReader(new StringReader(testString));
		SessionLog logReader = new SessionLog(bf);
		assertEquals(2, logReader.getLogSize());
	}
	

}
