/**
 * BT Fair Billing Test
 * Author: Daniel Kasprowicz
 * Date: 20 August 2016
 * 
 */

import java.io.FileNotFoundException;

public class FairBilling {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		LogReader log = new LogReader(args[0]);
		//System.out.print(log);
		
		//Map<User, List<Session>> users = new HashMap<User, List<Session>>();
		Users users = new Users(log);
		
		
		
		
		
	}

}
