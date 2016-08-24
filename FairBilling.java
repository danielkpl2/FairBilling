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
		
		SessionLog log = new SessionLog(args[0]);

		Users users = new Users(log);
		
		users.printReport();
		
		
	}

}
