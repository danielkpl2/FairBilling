import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Users {
	private Map<String, User> users;
	
	@SuppressWarnings("unchecked")
	public Users(LogReader log){
		users = new HashMap<String, User>();
		
		for(ArrayList<String> entry: (ArrayList<ArrayList<String>>)(log).getLog()){
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalTime timestamp = LocalTime.parse(entry.get(0), formatter);
			
			addSession(entry.get(1), timestamp, entry.get(2));
		}
		
	}
	
	private void addSession(String username, LocalTime timestamp, String status){
		if(users.containsKey(username)){
			users.get(username).addSession(timestamp, status);
		}else{
			users.put(username, new User(username, timestamp, status));
		}
	}

}
