/**
 * BT Fair Billing Test
 * Author: Daniel Kasprowicz
 * Date: 20 August 2016
 * 
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FairBilling {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		SessionLog log = new SessionLog(args[0]);
		//System.out.print(log);
		
		//Map<User, List<Session>> users = new HashMap<User, List<Session>>();
		Users users = new Users(log);
		System.out.print(users);
		
		
		
	}

}
